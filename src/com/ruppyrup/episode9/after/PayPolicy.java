package com.ruppyrup.episode9.after;

import java.text.DecimalFormat;

public interface PayPolicy {

  String calculatePay(Employee salary);

  //todo - formatPat has been separated out from the operations section - they were previously combined
  default String formatPay(double salary) {
    return new DecimalFormat("#0.00").format(salary);
  }
}


class DefaultPayPolicy implements PayPolicy {

  @Override
  public String calculatePay(Employee employee) {
    return formatPay(employee.getSalary() /12);
  }
}

class WeeklyPayPolicy implements PayPolicy {

  @Override
  public String calculatePay(Employee employee) {
    return formatPay(employee.getSalary() / 52);
  }
}
