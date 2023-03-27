package com.ruppyrup.episode34.bridge.discoverevents.inheritance;

public class PulseSwitch extends Event {
    public PulseSwitch() {
        super(eventScope.PULSE, eventType.SWITCH);
    }

    @Override
    void process() {
        System.out.println("Processing a Pulse switch event");
    }
}
