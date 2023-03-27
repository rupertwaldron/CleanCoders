package com.ruppyrup.episode34.bridge.discoverevents.inheritance;

public class DnSettlement extends Event {
    public DnSettlement() {
        super(eventScope.DN, eventType.SETTLEMENT);
    }

    @Override
    void process() {
        System.out.println("Processing a DN settlment event");
    }
}
