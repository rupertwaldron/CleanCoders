package com.ruppyrup.episode10;

public class CarRentalExpense extends Expense {
  public CarRentalExpense(int amount) {
    super(amount);
  }

  @Override
  public String getName() {
    return "Car Rental";
  }

  boolean isOverage() {
    return false;
  }

  boolean isMeal() {
    return false;
  }
}


