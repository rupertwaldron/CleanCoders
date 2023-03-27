package com.ruppyrup.episode34.bridge.discoverevents.inheritance;

public abstract class Event {
    private final eventScope eventScope;
    private final eventType eventType;

    protected Event(eventScope eventScope, eventType eventType) {
        this.eventScope = eventScope;
        this.eventType = eventType;
    }

    abstract void process();

    enum eventType {
        SETTLEMENT,
        SWITCH
    }

    enum eventScope {
        DN,
        PULSE
    }
}
