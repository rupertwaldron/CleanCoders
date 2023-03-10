package com.ruppyrup.episode23.mock.usecases;

import com.ruppyrup.episode23.mock.authorizer.UserID;
import com.ruppyrup.episode23.mock.entities.User;

public interface UserGateway {
  public User getUser(UserID id);
}
