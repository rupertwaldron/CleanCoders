package com.ruppyrup.episode31.unclebob.pull;

public class RealTimeDisplay implements Observer {

  private final Clock clock;
  public RealTimeDisplay(Clock clock) {
    this.clock = clock;
  }

  public void showTime() {
      System.out.println("Time = " + clock.getTime());
  }

  @Override
  public void update() {
    showTime();
  }
}

