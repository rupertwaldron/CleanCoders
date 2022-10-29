package com.ruppyrup.episode9.after;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Employee {
  private static AtomicInteger nextId = new AtomicInteger();
  private final long id;
  private final String name;
  private double salary;

  private PayPolicy payPolicy = new DefaultPayPolicy();
  private GateWay gateWay = new HashMapGateway();
  private EmployeePrinter printer = new DefaultEmployeePrinter();

  public Employee(final String name, final double salary) {
    this.id = nextId.getAndIncrement();
    this.name = name;
    this.salary = salary;
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
  public double calculatePay() {
    return payPolicy.calculatePay(salary);
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
  public void save() {
    gateWay.save(this);
  }

  /**
   * Owner Architecture / DBA
   * Gets the employee by ID
   */
  public Employee getEmployeeById(long id) {
    return gateWay.getEmployeeById(id);
  }

  /**
   * Owner is Operations
   * @return description of the employee details
   */
  public String describeEmployee() {
    return printer.describeEmployee(this);
  }

  /**
   * Owner is Operations
   * @return description of the employee details
   */
  public void updateSalary(final double salary) {
    this.salary = salary;
  }

  /**
   * Operations
   * @return employee Id
   */
  public Long getId() {
    return id;
  }

  /**
   * Operations
   * @return employee name
   */
  public String getName() {
    return name;
  }

  /**
   * Operations
   * @param printer set
   */
  public void setPrinter(final EmployeePrinter printer) {
    this.printer = printer;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    final Employee employee = (Employee) o;

    if (id != employee.id) return false;
    if (Double.compare(employee.salary, salary) != 0) return false;
    return Objects.equals(name, employee.name);
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = (int) (id ^ (id >>> 32));
    result = 31 * result + (name != null ? name.hashCode() : 0);
    temp = Double.doubleToLongBits(salary);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }
}

class EmployeeAfterTest {

  /**
   * Architecture / DBA
   */
  @Test
  void architecutureTestForSavingAndFetchingEmployees() {
    Employee employee1 = new Employee("Bob", 20000.0);
    Employee employee2 = new Employee("Trev", 40000.0);
    employee1.save();
    employee2.save();
    Employee returnedEmployee1 = employee1.getEmployeeById(employee1.getId());
    Employee returnedEmployee2 = employee2.getEmployeeById(employee2.getId());
    Assertions.assertEquals(employee1, returnedEmployee1);
    Assertions.assertEquals(employee2, returnedEmployee2);
  }

  /**
   * Operations
   */
  @Test
  void operationsGetEmployeeDescription() {
    Employee employee = new Employee( "Bob", 20000.0);
    Assertions.assertEquals("Employee : " + employee.getId() + ", name : Bob, pay : £1666.67", employee.describeEmployee());
  }


  /**
   * Operations
   */
  @Test
  void operationsUpdateSalary() {
    Employee employee = new Employee( "Bob", 20000.0);
    employee.updateSalary(30000.0);
    Assertions.assertEquals("Employee : " + employee.getId() + ", name : Bob, pay : £2500.00", employee.describeEmployee());
  }

  @Test
  void policyCalculatePay() {
    Employee employee = new Employee( "Bob", 30000.0);
    Assertions.assertEquals(2500.00, employee.calculatePay());
  }

}

