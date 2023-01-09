package com.ruppyrup.episode25.command.temporal;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
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

class TestCommands {

    private final RestRepository repo = new RestRepository();
    private final CommandRepository commandRepo = new CommandRepository();
    private final ExecutorService fixedThreadPool = Executors.newCachedThreadPool(); // fixed pool of threads

    private final Map<String, Boolean> data = Map.of(
            "Visa", false,
            "Discover", true,
            "Mastercard", true
    );

    @Test
    void canExecuteCommandsReceivedFromMultipleThreads() throws InterruptedException {
        for (var val : data.entrySet()) {
            Arrays.stream(Shopper.values())
                    .forEach(shopper -> fixedThreadPool.execute(() -> {
                        try {
                            processRequest(shopper, val.getKey(), val.getValue());
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }));
        }


        while (commandRepo.noOfUnexecutedCommands() < 13) {
            Thread.sleep(100);
        }

        commandRepo.executeCommands();

        Thread.sleep(1000);

        commandRepo.executeCommands();

        assertThat(commandRepo.noOfUnexecutedCommands()).isEqualTo(0);
        assertThat(repo.noOfRequestsSaved()).isEqualTo(6);
        assertThat(commandRepo.noOfUndoCommands()).isEqualTo(27);


        fixedThreadPool.shutdown();
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
    private Deque<ReqCommand> commands = new LinkedList<>();

    private Deque<ReqCommand> undos = new LinkedList<>();


    private final AtomicBoolean savePermitted = new AtomicBoolean(true);

    public synchronized void saveCommmand(ReqCommand command) {
        if (savePermitted.get())
            commands.addLast(command);
    }

    public synchronized void executeCommands() throws InterruptedException {
        savePermitted.set(false);
        while (!commands.isEmpty()) {
            ReqCommand toUndo = commands.pop();
            toUndo.execute();
            undos.addFirst(toUndo);
        }

        savePermitted.set(true);
    }

    public int noOfUnexecutedCommands() {
        return commands.size();
    }

    public int noOfUndoCommands() {
        return undos.size();
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
