package com.ruppyrup.episode32.nullobject.payroll;

public interface Employee {
  Money calculatePay();
  boolean isPayDay();
  Money calculateDeductions();
  PayCheck makePaycheck(Money netPay);
  void sendPayCheck(PayCheck payCheck);

  Employee NULL = new Employee() {
     public Money calculatePay() {
       return Money.NULL;
     }
     public boolean isPayDay() {
       return false;
     }
     public Money calculateDeductions() {
       return Money.NULL;
     }
     public PayCheck makePaycheck(Money netPay) {
       return PayCheck.NULL;
     }
     public void sendPayCheck(PayCheck payCheck) {
     }
   };
}
