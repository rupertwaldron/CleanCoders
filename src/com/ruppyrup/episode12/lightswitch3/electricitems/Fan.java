package com.ruppyrup.episode12.lightswitch3.electricitems;

import com.ruppyrup.episode12.lightswitch3.Switchable;

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
