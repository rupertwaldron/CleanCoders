package com.ruppyrup.episode34.bridge.discoverevents.eventbridge;

public class Event {
    private final EventScope eventScope;
    private final EventType eventType;

    public Event(EventScope eventScope, EventType eventType) {
        this.eventScope = eventScope;
        this.eventType = eventType;
    }

    public void process() {
        System.out.println("Processing " + eventScope + " " + eventType + ".");
    }
}
