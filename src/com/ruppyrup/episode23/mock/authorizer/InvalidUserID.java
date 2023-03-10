package com.ruppyrup.episode23.mock.authorizer;

public class InvalidUserID extends UserID {
  public InvalidUserID() {
    super(-1);
  }

  public boolean isValid() {
    return false;
  }
}
