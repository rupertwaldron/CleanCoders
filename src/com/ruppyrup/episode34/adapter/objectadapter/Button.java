package com.ruppyrup.episode34.adapter.objectadapter;

public class Button {
  private final Switchable switchable;

  public Button(final Switchable switchable) {
    this.switchable = switchable;
  }

  public void activate() {
    switchable.turnOn();
  }

  public void deActivate() {
    switchable.turnOff();
  }

}
