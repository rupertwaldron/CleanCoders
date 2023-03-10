package com.ruppyrup.episode23.mock.tests;


import com.ruppyrup.episode23.mock.authorizer.UserID;
import com.ruppyrup.episode23.mock.entities.User;
import com.ruppyrup.episode23.mock.entities.UserStub;
import com.ruppyrup.episode23.mock.usecases.UserGateway;


public class UserGatewayStub implements UserGateway {
  public User getUser(UserID id) {
    return new UserStub();
  }
}
