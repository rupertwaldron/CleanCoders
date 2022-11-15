package com.ruppyrup.episode12.lightswitch3.electricitems;

import com.ruppyrup.episode12.lightswitch3.Switchable;

public class DimmingLight implements Switchable {
  @Override
  public void turnOn() {
    System.out.println("Dimming Light turns on");
  }

  @Override
  public void turnOff() {
    System.out.println("Dimming Light turns off");
  }

  @Override
  public void variPower(final int dimValue) {
    System.out.println("Dimming Light is set to level " + dimValue);
  }
}
