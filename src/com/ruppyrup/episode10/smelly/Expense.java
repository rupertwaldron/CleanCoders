package com.ruppyrup.episode10.smelly;

import com.ruppyrup.episode10.ReportPrinter;

public class Expense {
  public enum Type {DINNER, BREAKFAST, CAR_RENTAL};
  public Type type;
  public int amount;

  public Expense(final Type type, final int amount) {
    this.type = type;
    this.amount = amount;
  }

}
