package com.ruppyrup.episode23.spy.tests;


import com.ruppyrup.episode23.spy.authorizer.InvalidUserID;
import com.ruppyrup.episode23.spy.authorizer.UserID;

/**
 * Returns an invalid user whatever the login
 */
public class RejectingAuthorizerSpy extends AuthorizerSpy {
  protected UserID makeUser() {
    return new InvalidUserID();
  }
}
