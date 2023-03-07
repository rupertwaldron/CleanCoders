package com.ruppyrup.episode23.dummy;

import java.util.Date;

// Intellij will do this for you!
public class DummySession implements Session {
  public Authorizations getAuthorizations() {
    return null;
  }

  public UserID getLoggedInUser() {
    return null;
  }

  public Date getLoginTime() {
    return null;
  }

  public PayrollContext getPayrollContext() {
    return null;
  }
}
