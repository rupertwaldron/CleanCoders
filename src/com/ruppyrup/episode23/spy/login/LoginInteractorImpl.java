package com.ruppyrup.episode23.spy.login;


import com.ruppyrup.episode23.spy.authorizer.Authorizer;
import com.ruppyrup.episode23.spy.authorizer.UserID;
import com.ruppyrup.episode23.spy.entities.User;

import java.util.HashMap;

public class LoginInteractorImpl implements LoginInteractor {
  private Authorizer authorizer;
  private LoginPresenter presenter;
  private HashMap<String, Integer> loginAttemptCounter = new HashMap<String, Integer>();

  public void setAuthorizer(Authorizer authorizer) {
    this.authorizer = authorizer;
  }

  public void setPresenter(LoginPresenter presenter) {
    this.presenter = presenter;
  }

  public void login(LoginRequest request) {
    LoginResponse response = new LoginResponse();

    UserID userID = authorizer.authorize(request.username, request.password);
    if (!userID.isValid()) {
      response.message = LOGIN_FAILURE_MESSAGE;

      // Want to check this functionality
      if (countInvalidLoginAttempts(request.username) >= 3) {
        authorizer.hold(request.username);
      }
    } else {
      response.message = LOGIN_SUCCESS_MESSAGE;
    }
    // use presenter spy to see what presentResponse is called with
    presenter.presentResponse(response);
  }

  private int countInvalidLoginAttempts(String username) {
    Integer loginAttempts = loginAttemptCounter.get(username);
    if (loginAttempts == null)
      loginAttempts = 0;
    loginAttempts++;
    loginAttemptCounter.put(username, loginAttempts);
    return loginAttempts;
  }
}
