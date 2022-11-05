package com.ruppyrup.episode10;


// abstract class to hold the expense and provide simple business rules isOverage() isMeal()
public abstract class Expense {
  private int amount;

  public Expense(int amount) {
    this.amount = amount;
  }

  public int getAmount() {
    return amount;
  }

  public String getName() { return "TILT"; }

  abstract boolean isOverage();

  abstract boolean isMeal();
}

