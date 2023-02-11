package com.ruppyrup.episode31.unclebob.push;

public class UncleBobMain {

  public static void main(String[] args) {

  Clock clock = new Clock();
  RealTimeDisplay rtd = new RealTimeDisplay();

  clock.register(rtd);
  }
}
