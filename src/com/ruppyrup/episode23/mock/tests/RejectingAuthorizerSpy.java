package com.ruppyrup.episode23.mock.tests;


import com.ruppyrup.episode23.mock.authorizer.InvalidUserID;
import com.ruppyrup.episode23.mock.authorizer.UserID;

public class RejectingAuthorizerSpy extends AuthorizerSpy {
  protected UserID makeUser() {
    return new InvalidUserID();
  }
}
