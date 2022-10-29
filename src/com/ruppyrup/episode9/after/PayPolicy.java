package com.ruppyrup.episode9.after;

public interface PayPolicy {

  double calculatePay(double salary);
}


class DefaultPayPolicy implements PayPolicy {

  @Override
  public double calculatePay(final double salary) {
    return salary / 12;
  }
}

class WeeklyPayPolicy implements PayPolicy {

  @Override
  public double calculatePay(final double salary) {
    return salary / 52;
  }
}
