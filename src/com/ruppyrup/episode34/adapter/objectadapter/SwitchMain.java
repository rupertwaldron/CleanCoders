package com.ruppyrup.episode34.adapter.objectadapter;


import com.ruppyrup.episode34.adapter.classadapter.LightAdapterClass;

public class SwitchMain {

  public static void main(String[] args) {
    //------------------------------------ object adapter
    Light light = new Light();
    LightAdapterObject lightAdapterObject = new LightAdapterObject();
    lightAdapterObject.setLight(light);
    Button button1 = new Button(lightAdapterObject);

    button1.activate();
    button1.deActivate();

    //------------------------------------ class Adapter
    LightAdapterClass lightAdapterClass = new LightAdapterClass();
    Button button2 = new Button(lightAdapterClass);

    button2.activate();
    button2.deActivate();

  }
}
