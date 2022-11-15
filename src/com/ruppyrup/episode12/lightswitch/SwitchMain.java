package com.ruppyrup.episode12.lightswitch;

import com.ruppyrup.episode12.lightswitch.electricitems.Fan;
import com.ruppyrup.episode12.lightswitch.electricitems.Light;

public class SwitchMain {

  public static void main(String[] args) {
    Light light = new Light();
    Fan fan = new Fan();

    MySwitch lightSwitch = new MySwitch(light);
    MySwitch fanSwitch = new MySwitch(fan);

    lightSwitch.activate();
    fanSwitch.activate();

    lightSwitch.deActivate();
    fanSwitch.deActivate();
  }
}
