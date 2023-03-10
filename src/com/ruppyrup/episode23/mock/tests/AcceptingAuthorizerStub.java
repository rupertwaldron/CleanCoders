package com.ruppyrup.episode23.mock.tests;

import com.ruppyrup.episode23.mock.authorizer.Authorizer;
import com.ruppyrup.episode23.mock.authorizer.UserID;

public class AcceptingAuthorizerStub implements Authorizer {
  public UserID authorize(String username, String password) {
    return new UserID(1);
  }

  public void hold(String username) {
  }
}
