package com.ruppyrup.episode12.lightswitchgood.electricitems;

import com.ruppyrup.episode12.lightswitchgood.Switchable;

public class Fan implements Switchable {
  @Override
  public void turnOn() {
    System.out.println("Fan turns on");
  }

  @Override
  public void turnOff() {
    System.out.println("Fan turns off");
  }
}
