package com.ruppyrup.episode32.singleton.timedSynchronizedDynamicSingleton;

import org.junit.jupiter.api.Test;

public class TimedSynchronizedDynamicSingletonTest {

  @Test
  public void singletonsCreatedProperly() throws Exception {
    long t0 = System.currentTimeMillis();
    for (int i = 0; i < 10000000; i++)
      SS.getInstance();
    long t1 = System.currentTimeMillis();
    for (int i = 0; i < 10000000; i++)
      NS.getInstance();
    long t2 = System.currentTimeMillis();
    for (int i = 0; i < 10000000; i++)
      DCS.getInstance();
    long t3 = System.currentTimeMillis();
    System.out.println("Sync:" + (t1 - t0) + " Nonsync:" + (t2 - t1) + " Double Checked Sync:" + (t3 - t2));
  }
}

class SS {
  private static SS instance = null;
  public static int K = 1;
  public int constant;

  private SS(int n) {
    constant = n;
  }

  public synchronized static SS getInstance() {
    if (instance == null)
      instance = new SS(X.K);
    return instance;
  }
}

class NS {
  private static NS instance = null;
  public static int K = 1;
  public int constant;

  private NS(int n) {
    constant = n;
  }

  public static NS getInstance() {
    if (instance == null)
      instance = new NS(X.K);
    return instance;
  }
}

class DCS {
  private static DCS instance = null;
  public static int K = 1;
  public int constant;

  private DCS(int n) {
    constant = n;
  }

  public static DCS getInstance() {
    if (instance == null) {
      synchronized (Lock1.class) {
        if (instance == null)
          instance = new DCS(X.K);
      }
    }
    return instance;
  }
}

class Lock1{}

class X {
  public static int K = 2;
}
