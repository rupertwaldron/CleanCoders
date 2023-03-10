package com.ruppyrup.episode23.mock.tests;

import com.ruppyrup.episode23.mock.usecases.LoginPresenter;
import com.ruppyrup.episode23.mock.usecases.LoginResponse;

public class LoginPresenterSpy implements LoginPresenter {
  private LoginResponse invokedResponse;

  public void presentResponse(LoginResponse response) {
    invokedResponse = response;
  }

  public LoginResponse getInvokedResponse() {
    return invokedResponse;
  }
}
