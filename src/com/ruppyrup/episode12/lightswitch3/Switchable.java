package com.ruppyrup.episode12.lightswitch3;

public interface Switchable {
  void turnOn();
  void turnOff();
  default void variPower(int dimValue) {
    // do nothing
  }
}
