package com.ruppyrup.episode9.after;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PolicyUI {

  private Employee employee;

  public PolicyUI(final Employee employee) {
    this.employee = employee;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(final Employee employee) {
    this.employee = employee;
  }

  /**
   * Owner -  Policy
   * Sets the Pay policy
   */
  public void setPayPolicy(final PayPolicy payPolicy) {
    employee.setPayPolicy(payPolicy);
  }

  /**
   * Owner -  Policy
   * @return pay based on the current policy
   */
  public double calculatePay() {
    return employee.calculatePay();
  }
}

class PolicyTest {
  @Test
  void policyCalculatePay() {
    Employee employee = new Employee( "Bob", 30000.0);
    PolicyUI policy = new PolicyUI(employee);

    Assertions.assertEquals(2500.00, policy.calculatePay());
  }
}
