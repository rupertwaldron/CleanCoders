package com.ruppyrup.episode12.lightswitchgood;

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

}
