package com.ruppyrup.episode12.lightswitch.electricitems;

import com.ruppyrup.episode12.lightswitch.Switchable;

public class Light implements Switchable  {
  @Override
  public void turnOn() {
    System.out.println("Light turns on");
  }

  @Override
  public void turnOff() {
    System.out.println("Light turns off");
  }
}
