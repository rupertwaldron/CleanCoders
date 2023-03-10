package com.ruppyrup.episode23.mock.tests;


import com.ruppyrup.episode23.mock.authorizer.UserID;
import com.ruppyrup.episode23.mock.entities.User;
import com.ruppyrup.episode23.mock.entities.UserStub;
import com.ruppyrup.episode23.mock.usecases.UserGateway;


public class UserGatewaySpy implements UserGateway {
  private UserID requestedId;

  public User getUser(UserID id) {
    requestedId = id;
    return new UserStub();
  }

  public UserID getRequestedId() {
    return requestedId;
  }
}
