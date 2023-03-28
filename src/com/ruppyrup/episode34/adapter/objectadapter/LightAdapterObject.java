package com.ruppyrup.episode34.adapter.objectadapter;

public class LightAdapterObject implements Switchable {
    private Light light;
    public void setLight(Light light) {
        this.light = light;
    }

    @Override
    public void turnOn() {
        light.ampsOn();
    }

    @Override
    public void turnOff() {
        light.ampsOff();
    }
}
