package com.ruppyrup.episode9.after;

import java.text.DecimalFormat;

public interface EmployeePrinter {
  String describeEmployee(Employee employee);

  //todo - calculatePay was previously combined and now resides in the PayPolicy module - separated out what we need.
  default double calculatePay(Employee employee) {
    return employee.getSalary() / 12;
  }
}

class DefaultEmployeePrinter implements EmployeePrinter {
  @Override
  public String describeEmployee(Employee employee) {
    return  "Employee : " + employee.getId() + ", name : " + employee.getName() +
                ", pay : £" + new DecimalFormat("#0.00").format(calculatePay(employee));
  }

}

class PrettyEmployeePrinter implements EmployeePrinter {
  @Override
  public String describeEmployee(Employee employee) {

    String prettyString = """
        Employee ID : %d
        Name : %s
        Pay : £%,.2f
        """;

    return  String.format(prettyString, employee.getId(), employee.getName(), calculatePay(employee));
  }

}
