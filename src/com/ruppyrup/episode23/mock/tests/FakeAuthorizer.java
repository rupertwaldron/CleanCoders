package com.ruppyrup.episode23.mock.tests;


import com.ruppyrup.episode23.mock.authorizer.Authorizer;
import com.ruppyrup.episode23.mock.authorizer.InvalidUserID;
import com.ruppyrup.episode23.mock.authorizer.UserID;

public class FakeAuthorizer implements Authorizer {
  public UserID authorize(String username, String password) {
    if (username.toLowerCase().startsWith("good"))
      return new UserID(1);
    else
      return new InvalidUserID();
  }

  public void hold(String username) {
  }
}
