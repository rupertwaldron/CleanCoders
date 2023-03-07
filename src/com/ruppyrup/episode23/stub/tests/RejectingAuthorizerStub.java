package com.ruppyrup.episode23.stub.tests;

import com.ruppyrup.episode23.stub.authorizer.Authorizer;
import com.ruppyrup.episode23.stub.authorizer.InvalidUserID;
import com.ruppyrup.episode23.stub.authorizer.UserID;

public class RejectingAuthorizerStub implements Authorizer {
  public UserID authorize(String username, String password) {
    return new InvalidUserID();
  }

  public void hold(String username) {
  }
}
