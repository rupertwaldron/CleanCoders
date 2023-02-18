package com.ruppyrup.episode32.nullobject.payroll;

public class EmployeeDB {
  public Employee findEmployeeById(EmployeeId id) {
    if (id == null) {
      return Employee.NULL;
    } else {
      return (new RealEmployee());
    }
  }
}
