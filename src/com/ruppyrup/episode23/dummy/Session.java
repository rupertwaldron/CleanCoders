package com.ruppyrup.episode23.dummy;

import java.util.Date;

public interface Session {
  public Authorizations getAuthorizations();
  public UserID getLoggedInUser();
  public Date getLoginTime();
  public PayrollContext getPayrollContext();
}
