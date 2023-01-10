package com.ruppyrup.episode25.command.temporal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.assertj.core.api.Assertions.*;

class TestReqCommands {
    public static void main(String[] args) throws InterruptedException {
        RestRepository repo = new RestRepository();
        CommandRepository commandRepo = new CommandRepository();
        RestRequest request = new RestRequest(Shopper.CHALLENGE, "Visa", true);
        RestResponse response = new RestResponse(request);
        ReqCommand save = new SavePayment(repo, request);
        commandRepo.saveCommmand(save);
//        save.execute();

        ReqCommand process = new ProcessRequest(request, response);
        commandRepo.saveCommmand(process);
//        process.execute();

        commandRepo.executeCommands();

        System.out.println(response.getDependentResponse());
    }
}

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestCommands {

    private final RestRepository repo = new RestRepository();
    private final CommandRepository commandRepo = new CommandRepository();
    private final ExecutorService fixedThreadPool = Executors.newCachedThreadPool(); // fixed pool of threads
    private final ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);

    private final Map<String, Boolean> data = Map.of(
            "Visa", false,
            "Discover", true,
            "Mastercard", true
    );

    private final List<String> dataKeys = new ArrayList<>(data.keySet());
    private final Random random = new Random();
    int noOfCommmands = 10;

    @AfterAll
    void shutDown() {
        fixedThreadPool.shutdown();
        ses.shutdown();
    }

    @Test
    void canExecuteCommandsReceivedFromMultipleThreads() throws InterruptedException {
        final AtomicInteger counter = new AtomicInteger(0);

        ses.scheduleAtFixedRate(() -> {
            try {
                commandRepo.executeCommands();
                counter.incrementAndGet();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, 10, 100, TimeUnit.MILLISECONDS);


        for (int i = 0; i < noOfCommmands; i++) {
            Arrays.stream(Shopper.values())
                    .forEach(shopper -> fixedThreadPool.execute(() -> {
                        try {
                            int index =  random.nextInt(dataKeys.size());
                            String card = dataKeys.get(index);
                            processRequest(shopper, card, data.get(card));
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }));
        }

        int expectedCommands = noOfCommmands * 9;

        while (counter.get() < 10) {
            Thread.sleep(100);
        }

        assertThat(commandRepo.noOfUnexecutedCommands()).isEqualTo(0);
        assertThat(commandRepo.noOfUndoCommands()).isEqualTo(expectedCommands);
        assertThat(commandRepo.numberOfCommandsProcessed()).isEqualTo(expectedCommands);
        assertThat(commandRepo.numberOfCommandsSaved()).isEqualTo(expectedCommands);
    }

    private void processRequest(Shopper shopper, String card, boolean saveCard) throws InterruptedException {
        RestRequest request = new RestRequest(shopper, card, saveCard);
        RestResponse response = new RestResponse(request);
        ReqCommand save = new SavePayment(repo, request);
        commandRepo.saveCommmand(save);
        ReqCommand processReq = new ProcessRequest(request, response);
        commandRepo.saveCommmand(processReq);
        ReqCommand processResp = new ProcessResponse(response);
        commandRepo.saveCommmand(processResp);
    }
}


class RestResponse {
    private Shopper shopper;
    private String card;
    private boolean saveCard;
    private String dependentResponse;

    public RestResponse(RestRequest request) {
        shopper = request.getShopper();
        card = request.getCard();
        saveCard = request.isSaveCard();
    }

    public void setDependentResponse(String dependentResponse) {
        this.dependentResponse = dependentResponse;
    }

    public String getDependentResponse() {
        return dependentResponse;
    }
}


public class RestRequest {
    private Shopper shopper;
    private String card;
    private boolean saveCard;

    public RestRequest(Shopper shopper, String card, boolean saveCard) {
        this.shopper = shopper;
        this.card = card;
        this.saveCard = saveCard;
    }

    public Shopper getShopper() {
        return shopper;
    }

    public String getCard() {
        return card;
    }

    public boolean isSaveCard() {
        return saveCard;
    }

    @Override
    public String toString() {
        return "RestRequest{" +
                "shopper=" + shopper +
                ", card='" + card + '\'' +
                ", saveCard=" + saveCard +
                '}';
    }
}

interface ReqCommand {
    void execute();
}


class SavePayment implements ReqCommand {
    private RestRepository repo;
    private RestRequest request;

    public SavePayment(RestRepository repo, RestRequest request) {
        this.repo = repo;
        this.request = request;
    }

    @Override
    public void execute() {
        if (request.isSaveCard()) {
            repo.save(request);
            System.out.println("Card is saved");
        }

    }
}


class ProcessRequest implements ReqCommand {
    private RestRequest request;
    private RestResponse response;

    public ProcessRequest(RestRequest request, RestResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() {
        switch (request.getShopper()) {
            case CHALLENGE:
                response.setDependentResponse(Shopper.CHALLENGE.getMessage());
                break;
            case INDENTIFY:
                response.setDependentResponse(Shopper.INDENTIFY.getMessage());
                break;
            case REDIRECT:
                response.setDependentResponse(Shopper.REDIRECT.getMessage());
                break;
        }

    }
}

class ProcessResponse implements ReqCommand {
    private RestResponse response;

    public ProcessResponse(RestResponse response) {
        this.response = response;
    }

    @Override
    public void execute() {
        System.out.println("Process response :: " + response.getDependentResponse());
    }
}


class CommandRepository {
    private final Lock lock = new ReentrantLock();
    final Condition executionInProgress = lock.newCondition();
    private Deque<ReqCommand> commands = new ConcurrentLinkedDeque<>();

    private Deque<ReqCommand> undos = new ConcurrentLinkedDeque<>();
    private final AtomicInteger executeCounter = new AtomicInteger(0);
    private final AtomicInteger saveCounter = new AtomicInteger(0);

    public void saveCommmand(ReqCommand command) {
            commands.addLast(command);
            saveCounter.incrementAndGet();
    }

    public void executeCommands() throws InterruptedException {
        System.out.println("Starting command execution");
        while (!commands.isEmpty()) {
            ReqCommand toUndo = commands.pop();
//            Thread.sleep(100);
            toUndo.execute();
            undos.addFirst(toUndo);
            executeCounter.incrementAndGet();
        }
    }

    public int noOfUnexecutedCommands() {
        return commands.size();
    }

    public int noOfUndoCommands() {
        return undos.size();
    }

    public int numberOfCommandsProcessed() {
        return executeCounter.get();
    }

    public int numberOfCommandsSaved() {
        return saveCounter.get();
    }
}

class RestRepository {
    List<RestRequest> requests = new ArrayList<>();

    public void save(RestRequest request) {
        requests.add(request);
        System.out.println("Saved :: " + request);
    }

    public int noOfRequestsSaved() {
        return requests.size();
    }
}

enum Shopper {
    CHALLENGE("Challenge"),
    INDENTIFY("Identify"),
    REDIRECT("Redirect");

    private String message;

    Shopper(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    //hello world
}
