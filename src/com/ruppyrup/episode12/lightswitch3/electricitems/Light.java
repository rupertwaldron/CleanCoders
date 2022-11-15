package com.ruppyrup.episode12.lightswitch3.electricitems;

import com.ruppyrup.episode12.lightswitch3.Switchable;

public class Light implements Switchable {
  @Override
  public void turnOn() {
    System.out.println("Light turns on");
  }

  @Override
  public void turnOff() {
    System.out.println("Light turns off");
  }

}
