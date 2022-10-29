package com.ruppyrup.episode9.after;

import java.text.DecimalFormat;

public interface PayPolicy {

  String calculatePay(Employee salary);
}


class DefaultPayPolicy implements PayPolicy {

  @Override
  public String calculatePay(Employee employee) {
    return new DecimalFormat("#0.00").format(employee.getSalary() / 12);
  }
}

class WeeklyPayPolicy implements PayPolicy {

  @Override
  public String calculatePay(Employee employee) {
    return new DecimalFormat("#0.00").format(employee.getSalary() / 52 - 0.001);
  }
}
