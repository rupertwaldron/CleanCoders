package com.ruppyrup.episode32.singleton.synchronizedThreadedSingleton;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SynchronizedDynamicSingletonTest {
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
  private static S instance = null;
  public static int K = 1;
  public int constant;

  private S(int n) {
    constant = n;
  }

  public static synchronized S getInstance() {
    if (instance == null)
      instance = new S(X.K);
    return instance;
  }
}

class X {
  public static int K=2;
}
