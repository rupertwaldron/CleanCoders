package com.ruppyrup.episode31.unclebob.pull;

import java.time.LocalTime;
import java.util.Scanner;

public class Clock extends Subject {
  private final Scanner scanner = new Scanner(System.in);

  private LocalTime currentTime;
  private volatile boolean running = true;

  public Clock() {
    this.currentTime = LocalTime.now();
    tic();
    new Thread(this::keyboard).start();
  }

  private void keyboard() {
    while(running) {
      if (scanner.nextLine().equals("q")) {
        running = false;
      }
    }
  }

  private void tic() {
    Thread thread = new Thread(() -> {
      while (running) {
        try {
          Thread.sleep(1000);
          updateTime();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });
    thread.start();
  }

  private void updateTime() {
    currentTime = LocalTime.now();
    notifyObservers();
  }

  // used for pull observer model
  public LocalTime getTime() {
    return currentTime;
  }
}
