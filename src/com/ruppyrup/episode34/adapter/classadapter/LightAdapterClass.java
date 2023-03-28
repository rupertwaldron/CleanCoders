package com.ruppyrup.episode34.adapter.classadapter;

import com.ruppyrup.episode34.adapter.objectadapter.Light;
import com.ruppyrup.episode34.adapter.objectadapter.Switchable;

public class LightAdapterClass extends Light implements Switchable {
    @Override
    public void turnOn() {
        ampsOn();
    }

    @Override
    public void turnOff() {
        ampsOff();
    }
}
