package com.ruppyrup.episode32.nullobject.payroll;

import java.util.List;

public class PayRoll {
  private List<EmployeeId> employeeIds;
  private EmployeeDB employeeDB;

  public PayRoll(List<EmployeeId> employeeIds, EmployeeDB employeeDB) {
    this.employeeIds = employeeIds;
    this.employeeDB = employeeDB;
  }

  public void runPayroll() {
    for (EmployeeId id : employeeIds) {
      Employee e = employeeDB.findEmployeeById(id);
      if (e.isPayDay()) {
        Money gross = e.calculatePay();
        Money deductions = e.calculateDeductions();
        PayCheck check = e.makePaycheck(gross.minus(deductions));
        e.sendPayCheck(check);
      }
    }
  }
}
