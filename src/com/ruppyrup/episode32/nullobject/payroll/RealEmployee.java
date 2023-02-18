package com.ruppyrup.episode32.nullobject.payroll;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RealEmployee implements Employee{
    @Override
    public Money calculatePay() {
        return new Money();
    }

    @Override
    public boolean isPayDay() {
        return false;
    }

    @Override
    public Money calculateDeductions() {
        return new Money();
    }

    @Override
    public PayCheck makePaycheck(Money netPay) {
        return new PayCheck();
    }

    @Override
    public void sendPayCheck(PayCheck payCheck) {
        System.out.println("Sending paycheck");
    }

    @Test
    void testNullEmployee() {
        EmployeeDB db = new EmployeeDB();
        Employee nullEmployee = db.findEmployeeById(null);
        Assertions.assertEquals(Employee.NULL, nullEmployee);
    }
}
