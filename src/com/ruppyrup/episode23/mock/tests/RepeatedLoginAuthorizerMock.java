package com.ruppyrup.episode23.mock.tests;

import com.ruppyrup.episode23.mock.authorizer.Authorizer;
import com.ruppyrup.episode23.mock.authorizer.InvalidUserID;
import com.ruppyrup.episode23.mock.authorizer.UserID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RepeatedLoginAuthorizerMock implements Authorizer {
  private List<String> actions = new ArrayList<String>();

  public UserID authorize(String username, String password) {
    actions.add("Authorize:" + username);
    return new InvalidUserID();
  }

  public void hold(String username) {
    actions.add("Hold:"+username);
  }

  public boolean verifyHeldOnThirdAttempt(String username) {
    final List<String> expectedActions = Arrays.asList(
      "Authorize:"+username,
      "Authorize:"+username,
      "Authorize:"+username,
      "Hold:"+username);

    return actions.equals(expectedActions); // the mock checks on what happened is correct a Spy wont do this
  }
}
