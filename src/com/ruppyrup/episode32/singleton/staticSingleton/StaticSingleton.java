package com.ruppyrup.episode32.singleton.staticSingleton;

public class StaticSingleton {
  public int someValue;

  private StaticSingleton() {
    someValue = 99;
  }

  public static StaticSingleton instance = new StaticSingleton();

  // Other methods and variables.
}
