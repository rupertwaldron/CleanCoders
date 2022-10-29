package com.ruppyrup.episode9.after;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ArchitechUI {

  public ArchitechUI(final GateWay gateWay) {
    this.gateWay = gateWay;
  }

  private GateWay gateWay;

  public GateWay getGateWay() {
    return gateWay;
  }

  /**
   * Owner Architecture / DBA
   * Sets the Gateway
   */
  public void setGateWay(final GateWay gateWay) {
    this.gateWay = gateWay;
  }

  /**
   * Owner Architecture / DBA
   * Stores the employee
   */
  public void saveEmployee(Employee employee) {
    gateWay.save(employee);
  }

  /**
   * Owner Architecture / DBA
   * Gets the employee by ID
   */
  public Employee getEmployeeById(long id) {
    return gateWay.getEmployeeById(id);
  }
}

class ArchitectTest {
  /**
   * Architecture / DBA
   */
  @Test
  void architecutureTestForSavingAndFetchingEmployees() {
    Employee employee1 = new Employee("Bob", 20000.0);
    Employee employee2 = new Employee("Trev", 30000.0);
    GateWay gateWay = new TreeMapGateway();
    ArchitechUI architechUI = new ArchitechUI(gateWay);
    architechUI.saveEmployee(employee1);
    architechUI.saveEmployee(employee2);
    Employee returnedEmployee1 = architechUI.getEmployeeById(employee1.getId());
    Employee returnedEmployee2 = architechUI.getEmployeeById(employee2.getId());
    assertThat(returnedEmployee1).isEqualTo(employee1);
    assertThat(returnedEmployee2).isEqualTo(employee2);

  }
}
