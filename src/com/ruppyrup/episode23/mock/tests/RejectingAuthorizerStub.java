package com.ruppyrup.episode23.mock.tests;

import com.ruppyrup.episode23.mock.authorizer.Authorizer;
import com.ruppyrup.episode23.mock.authorizer.InvalidUserID;
import com.ruppyrup.episode23.mock.authorizer.UserID;

public class RejectingAuthorizerStub implements Authorizer {
  public UserID authorize(String username, String password) {
    return new InvalidUserID();
  }

  public void hold(String username) {
  }
}
