package com.ruppyrup.episode32.singleton.dependencyCycle;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrokenStaticSingletonTest {
  @Test
  public void singletonsCreatedProperly() throws Exception {
    assertEquals(2, S1.instance.constant);
    assertEquals(1, S2.instance.constant);
  }
}

class S1 {
  public static S1 instance = new S1(S2.K);
  public static int K=1;
  public int constant;

  private S1(int n) {
    constant = n;
  }
}

class S2 {
  public static S2 instance = new S2(S1.K);
  public static int K=2;
  public int constant;

  private S2(int n) {
    constant = n;
  }
}