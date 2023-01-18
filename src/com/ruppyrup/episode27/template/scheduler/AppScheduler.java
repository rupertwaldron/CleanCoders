package com.ruppyrup.episode27.template.scheduler;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public abstract class AppScheduler {

  private LocalTime startTime;
  private long interval;
  private int count;

  private int currentCount = 0;

  protected AppScheduler(final LocalTime startTime, final long interval, final int count) {
    initialize(startTime, interval, count);
  }

  private void initialize(final LocalTime startTime, final long interval, final int count) {
    this.startTime = startTime;
    this.interval = interval;
    this.count = count;
  }


  void start() throws InterruptedException {
    while (currentCount < count) {
      if (startTime.compareTo(LocalTime.now()) <= 0) {
        run();
        currentCount++;
        TimeUnit.SECONDS.sleep(interval);
      }
    }
    cleanup();
  }

  abstract void run();

  abstract void cleanup();

}
