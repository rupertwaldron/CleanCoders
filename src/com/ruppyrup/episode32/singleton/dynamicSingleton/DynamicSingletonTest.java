package com.ruppyrup.episode32.singleton.dynamicSingleton;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DynamicSingletonTest {
  @Test
  public void singletonsCreatedProperly() throws Exception {
    assertEquals(2, S1.getInstance().constant);
    assertEquals(1, S2.getInstance().constant);
  }
}

class S1 {
  private static S1 instance = null;
  public static int K=1;
  public int constant;

  private S1(int n) {
    constant = n;
  }

  public static S1 getInstance() {
    if (instance == null)
      instance = new S1(S2.K);
    return instance;
  }
}

class S2 {
  private static S2 instance = null;
  public static int K=2;
  public int constant;

  private S2(int n) {
    constant = n;
  }

  public static S2 getInstance() {
    if (instance == null)
      instance = new S2(S1.K);
    return instance;
  }
}