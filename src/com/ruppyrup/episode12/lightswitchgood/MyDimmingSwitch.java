package com.ruppyrup.episode12.lightswitchgood;

public class MyDimmingSwitch {
  private Dimmable dimmable;

  public MyDimmingSwitch(final Dimmable dimmable) {
    this.dimmable = dimmable;
  }

  public void activate() {
    dimmable.turnOn();
  }

  public void deActivate() {
    dimmable.turnOff();
  }

  public void changePowerLevel(final int power) {
    dimmable.variPower(power);
  }

}
