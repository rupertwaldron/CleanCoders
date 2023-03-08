package com.ruppyrup.episode23.spy.authorizer;

public interface Authorizer {
  public UserID authorize(String username, String password);
  public void hold(String username);
}

