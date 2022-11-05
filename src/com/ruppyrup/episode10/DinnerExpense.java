package com.ruppyrup.episode10;

// extends Expense and implements the isOverage() and isMeal()
public class DinnerExpense extends Expense {
  public DinnerExpense(int amount) {
    super(amount);
  }

  @Override
  public String getName() {
    return "Dinner";
  }

  boolean isOverage() {
    return getAmount() > 5000;
  }

  boolean isMeal() {
    return true;
  }
}


