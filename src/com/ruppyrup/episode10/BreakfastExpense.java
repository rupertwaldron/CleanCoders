package com.ruppyrup.episode10;

public class BreakfastExpense extends Expense {
  public BreakfastExpense(int amount) {
    super(amount);
  }

  @Override
  public String getName() {
    return "Breakfast";
  }

  boolean isOverage() {
    return getAmount() > 1000;
  }

  boolean isMeal() {
    return true;
  }
}
