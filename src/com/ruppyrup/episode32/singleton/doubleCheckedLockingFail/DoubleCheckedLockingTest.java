package com.ruppyrup.episode32.singleton.doubleCheckedLockingFail;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoubleCheckedLockingTest {
  S s1 = null;
  S s2 = null;

  @Test
  public void singletonsCreatedProperly() throws Exception {
    new Thread(() -> s1 = S.getInstance()).start();
    new Thread(() -> s2 = S.getInstance()).start();
    while (s1 == null || s2 == null)
      Thread.sleep(1);
    assertEquals(s1, s2);
  }
}

class S {
  static S instance = null;
  public static int K = 1;
  public int constant;

  private S(int n) {
    constant = n;
  }

  public static S getInstance() {
    if (instance == null) {
      synchronized (Lock.class) {
        if (instance == null)
          instance = new S(X.K);
      }
    }
    return instance;
  }
}

class Lock {
}

class X {
  public static int K = 2;
}
