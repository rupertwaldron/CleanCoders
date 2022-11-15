package com.ruppyrup.episode12.lightswitch3;


import com.ruppyrup.episode12.lightswitch3.electricitems.DimmingLight;
import com.ruppyrup.episode12.lightswitch3.electricitems.Fan;
import com.ruppyrup.episode12.lightswitch3.electricitems.Light;

public class SwitchMain {

  public static void main(String[] args) {
    Light light = new Light();
    Fan fan = new Fan();
    DimmingLight dimmingLight = new DimmingLight();

    MySwitch lightSwitch = new MySwitch(light);
    MySwitch fanSwitch = new MySwitch(fan);
    MySwitch dimmingLightSwitch = new MySwitch(dimmingLight);

    lightSwitch.activate();
    fanSwitch.activate();
    dimmingLightSwitch.activate();

    fanSwitch.changePowerLevel(10);
    lightSwitch.changePowerLevel(5);
    dimmingLightSwitch.changePowerLevel(7);

    lightSwitch.deActivate();
    fanSwitch.deActivate();
    dimmingLightSwitch.deActivate();
  }
}
