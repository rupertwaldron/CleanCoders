package com.ruppyrup.episode9.before;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Employee {

  private final long id;
  private final String name;
  private double salary;
  private static Map<Long, Employee> employees = new HashMap<>();

  public Employee(final long id, final String name, final double salary) {
    this.id = id;
    this.name = name;
    this.salary = salary;
    save();
  }

  /**
   * Owner -  Policy
   * @return pay based on the current policy
   */
  public double calculatePay() {
    return salary / 12;
  }

  /**
   * Owner Architecture / DBA
   * Stores the employee
   */
  public void save() {
    employees.put(id, this);
  }

  /**
   * Owner Architecture / DBA
   * Gets the employee by ID
   */
  public Employee getEmployeeById(long id) {
    return employees.get(id);
  }

  /**
   * Owner is Operations
   * @return description of the employee details
   */
  public String describeEmployee() {
    return "Employee : " + id + ", name : " + name + ", pay : £" + new DecimalFormat("#0.00").format(calculatePay());
  }

  /**
   * Owner is Operations
   * @return description of the employee details
   */
  public void updateSalary(final double salary) {
    this.salary = salary;
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

class EmployeeBeforeTest {

  /**
   * Architecture / DBA
   */
  @Test
  void architecutureTestForSavingAndFetchingEmployees() {
    Employee employee1 = new Employee(1, "Bob", 20000.0);
    Employee employee2 = new Employee(2, "Bob", 20000.0);
    Employee returnedEmployee1 = employee1.getEmployeeById(1);
    Employee returnedEmployee2 = employee2.getEmployeeById(2);
    Assertions.assertEquals(employee1, returnedEmployee1);
    Assertions.assertEquals(employee2, returnedEmployee2);
  }

  /**
   * Operations
   */
  @Test
  void operationsGetEmployeeDescription() {
    Employee employee = new Employee(1, "Bob", 20000.0);
    Assertions.assertEquals("Employee : 1, name : Bob, pay : £1666.67", employee.describeEmployee());
  }


  /**
   * Operations
   */
  @Test
  void operationsUpdateSalary() {
    Employee employee = new Employee(1, "Bob", 20000.0);
    employee.updateSalary(30000.0);
    Assertions.assertEquals("Employee : 1, name : Bob, pay : £2500.00", employee.describeEmployee());
  }

  @Test
  void policyCalculatePay() {
    Employee employee = new Employee(1, "Bob", 30000.0);
    Assertions.assertEquals(2500.00, employee.calculatePay());
  }

}

