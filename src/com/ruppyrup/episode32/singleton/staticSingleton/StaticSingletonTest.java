package com.ruppyrup.episode32.singleton.staticSingleton;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StaticSingletonTest {
  @Test
  public void canGetStaticSingleton() throws Exception {
    StaticSingleton s1 = StaticSingleton.instance;
    assertEquals(99, s1.someValue);
    s1.someValue = 100;
    StaticSingleton s2 = StaticSingleton.instance;
    assertEquals(100, s1.someValue);
    assertEquals(100, s2.someValue);
  }
}
