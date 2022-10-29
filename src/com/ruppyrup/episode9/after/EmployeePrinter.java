package com.ruppyrup.episode9.after;

import java.text.DecimalFormat;

public interface EmployeePrinter {
  String describeEmployee(Employee employee);
}

class DefaultEmployeePrinter implements EmployeePrinter {
  @Override
  public String describeEmployee(Employee employee) {
    return   "Employee : " + employee.getId() + ", name : " +
                 employee.getName() + ", pay : £" +
                 new DecimalFormat("#0.00").format(employee.calculatePay());
  }
}
