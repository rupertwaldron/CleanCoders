package com.ruppyrup.episode12.lightswitch2;

public class MySwitch {
  private Switchable switchable;

  public MySwitch(final Switchable switchable) {
    this.switchable = switchable;
  }

  public void activate() {
    switchable.turnOn();
  }

  public void deActivate() {
    switchable.turnOff();
  }

  public void changePowerLevel(final int power) {
    switchable.variPower(power);
  }
}
