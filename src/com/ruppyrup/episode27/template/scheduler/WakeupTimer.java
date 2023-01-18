package com.ruppyrup.episode27.template.scheduler;

import java.time.LocalTime;

public class WakeupTimer extends AppScheduler {

  protected WakeupTimer(LocalTime startTime, long interval, int count) {
    super(startTime, interval, count);
  }

  @Override
  void run() {
    System.out.println("Wake up");
  }

  @Override
  void cleanup() {
    System.out.println("Finished");
  }

  public static void main(String[] args) throws InterruptedException {
    WakeupTimer wakeupTimer = new WakeupTimer(LocalTime.of(8, 17), 20, 5);
    wakeupTimer.start();
  }
}
