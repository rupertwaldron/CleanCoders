package com.ruppyrup.episode23.spy.tests;

import com.ruppyrup.episode23.spy.login.LoginPresenter;
import com.ruppyrup.episode23.spy.login.LoginResponse;

public class LoginPresenterSpy implements LoginPresenter {
  private LoginResponse invokedResponse;

  public void presentResponse(LoginResponse response) {
    invokedResponse = response;
  }

  public LoginResponse getInvokedResponse() {
    return invokedResponse;
  }
}
