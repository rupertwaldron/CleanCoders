package com.ruppyrup.episode31.unclebob.pull;

public class UncleBobMain {

  public static void main(String[] args) {

  Clock clock = new Clock();
  RealTimeDisplay rtd = new RealTimeDisplay(clock);

  clock.register(rtd);
  }
}
