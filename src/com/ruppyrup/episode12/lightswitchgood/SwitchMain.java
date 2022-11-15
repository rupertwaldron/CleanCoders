package com.ruppyrup.episode12.lightswitchgood;


import com.ruppyrup.episode12.lightswitchgood.electricitems.DimmingLight;
import com.ruppyrup.episode12.lightswitchgood.electricitems.Fan;
import com.ruppyrup.episode12.lightswitchgood.electricitems.Light;

public class SwitchMain {

  public static void main(String[] args) {
    Light light = new Light();
    Fan fan = new Fan();
    DimmingLight dimmingLight = new DimmingLight();

    MySwitch lightSwitch = new MySwitch(light);
    MySwitch fanSwitch = new MySwitch(fan);
    MyDimmingSwitch dimmingLightSwitch = new MyDimmingSwitch(dimmingLight);
//    MyDimmingSwitch dimmingFanSwitch = new MyDimmingSwitch(fan);
    MySwitch normalLightSwitch = new MySwitch(dimmingLight);

    lightSwitch.activate();
    fanSwitch.activate();
    dimmingLightSwitch.activate();

//    fanSwitch.changePowerLevel(10);
//    lightSwitch.changePowerLevel(5);
    dimmingLightSwitch.changePowerLevel(7);
//    normalLightSwitch.changePowerLevel(8);


    lightSwitch.deActivate();
    fanSwitch.deActivate();
    dimmingLightSwitch.deActivate();
  }
}
