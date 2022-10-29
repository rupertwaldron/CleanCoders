package com.ruppyrup.episode9.after;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArchitechUI {

  private Employee employee;

  public ArchitechUI(final Employee employee) {
    this.employee = employee;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(final Employee employee) {
    this.employee = employee;
  }

  /**
   * Owner Architecture / DBA
   * Sets the Gateway
   */
  public void setGateWay(final GateWay gateWay) {
    employee.setGateWay(gateWay);
  }

  /**
   * Owner Architecture / DBA
   * Stores the employee
   */
  public void save() {
    employee.save();
  }

  /**
   * Owner Architecture / DBA
   * Gets the employee by ID
   */
  public Employee getEmployeeById(long id) {
    return employee.getEmployeeById(id);
  }
}

class ArchitectTest {
  /**
   * Architecture / DBA
   */
  @Test
  void architecutureTestForSavingAndFetchingEmployees() {
    Employee employee1 = new Employee("Bob", 20000.0);
    ArchitechUI architechUI = new ArchitechUI(employee1);
    architechUI.save();
    Employee returnedEmployee1 = architechUI.getEmployeeById(employee1.getId());
    Assertions.assertEquals(employee1, returnedEmployee1);
  }
}
