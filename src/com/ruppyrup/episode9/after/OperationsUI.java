package com.ruppyrup.episode9.after;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OperationsUI {

  private EmployeePrinter employeePrinter;

  public OperationsUI(final EmployeePrinter employeePrinter) {
    this.employeePrinter = employeePrinter;
  }

  public EmployeePrinter getEmployeePrinter() {
    return employeePrinter;
  }

  public void setEmployeePrinter(final EmployeePrinter employeePrinter) {
    this.employeePrinter = employeePrinter;
  }

  /**
   * Owner is Operations
   * @return description of the employee details
   */
  public String describeEmployee(Employee employee) {
    return employeePrinter.describeEmployee(employee);
  }

  /**
   * Owner is Operations
   * @return description of the employee details
   */
  public void updateSalary(final double salary, Employee employee) {
    employee.setSalary(salary);
  }

}

class OperationsUITest {

  /**
   * Operations
   */
  @Test
  void operationsGetEmployeeDescription() {
    Employee employee = new Employee( "Bob", 20000.0);
    EmployeePrinter printer = new DefaultEmployeePrinter();
    OperationsUI operationsUI = new OperationsUI(printer);
    assertThat(operationsUI.describeEmployee(employee)).isEqualTo("Employee : " + employee.getId() + ", name : Bob, salary : £20000.0");
  }


  /**
   * Operations
   */
  @Test
  void operationsUpdateSalary() {
    Employee employee = new Employee( "Bob", 20000.0);
    EmployeePrinter printer = new DefaultEmployeePrinter();
    OperationsUI operationsUI = new OperationsUI(printer);
    operationsUI.updateSalary(30000.0, employee);
    assertThat(operationsUI.describeEmployee(employee)).isEqualTo("Employee : " + employee.getId() + ", name : Bob, salary : £30000.0");
  }
}
