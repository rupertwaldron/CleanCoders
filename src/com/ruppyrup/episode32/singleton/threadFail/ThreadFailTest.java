package com.ruppyrup.episode32.singleton.threadFail;


import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThreadFailTest {
  S s1 = null;
  S s2 = null;

  @Test
  public void singletonsCreatedProperly() throws Exception {
    CountDownLatch readyCounter = new CountDownLatch(2);
    CountDownLatch threadBlocker = new CountDownLatch(1);

    new Thread(() -> {
      readyCounter.countDown();
      try {
        threadBlocker.await();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      s1 =S.getInstance();
    }).start();


    new Thread(() -> {
      try {
        Thread.sleep(2000);
        readyCounter.countDown();
        threadBlocker.await();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      s2 = S.getInstance();
    }).start();

    readyCounter.await();
    threadBlocker.countDown();

    while (s1 == null || s2 == null)
      Thread.sleep(1);


    assertEquals(s1, s2);
  }
}

class S {
  private static S instance = null;
  public static int K = 1;
  public int constant;

  private S(int n) {
    constant = n;
  }

  public static S getInstance() {
    if (instance == null)
      instance = new S(X.K);
    return instance;
  }
}

class X {
  public static int K=2;
}
