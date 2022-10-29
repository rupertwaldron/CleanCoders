package com.ruppyrup.episode9.after;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PolicyUI {

  private PayPolicy payPolicy;


  public PolicyUI(final PayPolicy payPolicy) {
    this.payPolicy = payPolicy;
  }

  public PayPolicy getPayPolicy() {
    return payPolicy;
  }

  /**
   * Owner -  Policy
   * Sets the Pay policy
   */
  public void setPayPolicy(final PayPolicy payPolicy) {
    this.payPolicy = payPolicy;
  }

  /**
   * Owner -  Policy
   * @return pay based on the current policy
   */
  public String calculatePay(Employee employee) {
    return payPolicy.calculatePay(employee);
  }
}

class PolicyTest {
  @Test
  void policyCalculatePay() {
    Employee employee = new Employee( "Bob", 30000.0);
    PayPolicy weeklyPolicy = new WeeklyPayPolicy();
    PolicyUI policy = new PolicyUI(weeklyPolicy);
    assertThat(policy.calculatePay(employee)).isEqualTo("576.92");
  }
}
