package com.ruppyrup.episode23.stub.login;

import com.ruppyrup.episode23.stub.authorizer.Authorizer;
import com.ruppyrup.episode23.stub.authorizer.UserID;

import java.util.HashMap;

public class LoginInteractor {

    public static final String LOGIN_FAILURE_MESSAGE = "Login Failed";
    public static final String LOGIN_SUCESS_MESSAGE = "Login Succeeded";
    private Authorizer authorizer;
    private HashMap<String, Integer> loginAttemptCounter = new HashMap<String, Integer>();

    public void setAuthorizer(Authorizer authorizer) {
        this.authorizer = authorizer;
    }

    public String login(String username, String password) {

        UserID userID = authorizer.authorize(username, password);
        if (!userID.isValid()) {
            return LOGIN_FAILURE_MESSAGE;

        } else {
            return LOGIN_SUCESS_MESSAGE;
        }
    }
}

