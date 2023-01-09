package com.ruppyrup.episode25.command.temporal;

class TestReqCommands {
    public static void main(String[] args) {
        RestRepository repo = new RestRepository();
        RestRequest request = new RestRequest(Shopper.CHALLENGE, "Visa", true);
        ReqCommand save = new SavePayment(repo, request);
        save.execute();
        RestResponse response = new RestResponse(request);

        ReqCommand process = new ProcessRequest(request, response);
        process.execute();

        System.out.println(response.getDependentResponse());
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

    public ProcessRequest (RestRequest request, RestResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() {
       switch(request.getShopper()) {
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


class RestRepository {
    public void save(RestRequest response) {
        System.out.println("Saved :: " + response);
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
