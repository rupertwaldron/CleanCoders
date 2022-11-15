package com.ruppyrup.episode12.lightswitch2.electricitems;

import com.ruppyrup.episode12.lightswitch2.Switchable;

public class Fan implements Switchable {
  @Override
  public void turnOn() {
    System.out.println("Fan turns on");
  }

  @Override
  public void turnOff() {
    System.out.println("Fan turns off");
  }

  @Override
  public void variPower(final int dimValue) {

  }
}
