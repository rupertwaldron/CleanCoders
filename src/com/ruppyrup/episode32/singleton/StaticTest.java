package com.ruppyrup.episode32.singleton;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StaticTest {
  T t;
  T t1;

  @Test
  public void nothing() throws Exception {
    for (int i=1; i<1000; i++) {
      t=null;
      t1=null;
      new Thread(() -> t = T.getInstance()).start();
      new Thread(() -> t1 = T.getInstance()).start();
      while (t1 == null || t == null)
        Thread.sleep(1);
      assertEquals(t, t1);
    }
  }
}

class T {
  int v;
  static Object lock = new Object();

  public T(int x) {
    v = x;
  }

  static volatile T instance = null;

  static T getInstance() {
    if (instance == null)
      synchronized (lock) {
        if (instance == null)
          instance = new T(T1.x);
      }
    return instance;
  }

  static int x = 1;
}

class T1 {
  int v;

  public T1(int x) {
    v = x;
  }

  static T1 instance = null;

  static T1 getInstance() {
    if (instance == null)
      instance = new T1(T.x);
    return instance;
  }

  static int x = 2;
}
