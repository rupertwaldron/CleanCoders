package com.ruppyrup.episode4;

import java.util.List;

public class Authorise {

    private List<String> authorisedUsers = List.of("Rupert");
    private String authoriseUser;


    public void authoriseUserGood(String user) throws IllegalAccessException {
        if (authoriseUser.contains(user))
            authoriseUser = user;
        else
            throw new IllegalAccessException("User isn't authorised");
    }

    public boolean authoriseUserBad(String user) {
        if (authoriseUser.contains(user)) {
            authoriseUser = user;
            return true;
        }
        return false;
    }
}
