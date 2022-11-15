package com.ruppyrup.episode12.lightswitch2.electricitems;

import com.ruppyrup.episode12.lightswitch2.Switchable;

public class Light implements Switchable {
  @Override
  public void turnOn() {
    System.out.println("Light turns on");
  }

  @Override
  public void turnOff() {
    System.out.println("Light turns off");
  }

  @Override
  public void variPower(final int dimValue) {
    // do nothing
  }
}
