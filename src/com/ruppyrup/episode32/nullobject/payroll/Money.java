package com.ruppyrup.episode32.nullobject.payroll;

public class Money {
  public static final Money NULL = new Money() {
    public Money minus(Money subtrahend) {
      return Money.NULL;
    }
  };

  public Money minus(Money subtrahend) {
    return null;
  }
}
