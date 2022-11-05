package com.ruppyrup.episode10;

public class LunchExpense extends Expense {

  public LunchExpense(int amount) {
    super(amount);
  }

  @Override
  public String getName() {
    return "Lunch";
  }

  @Override
  boolean isOverage() {
    return getAmount() > 3000;
  }

  @Override
  boolean isMeal() {
    return true;
  }
}
