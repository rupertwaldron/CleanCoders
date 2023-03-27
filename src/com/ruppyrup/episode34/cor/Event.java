package com.ruppyrup.episode34.cor;

public class Event {
    private EventScope eventScope;

    private EventType eventType;

    private String metaData;

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public String getEventScope() {
        return eventScope.getScope();
    }

    public String getEventType() {
        return eventType.getType();
    }

    public void setEventScope(EventScope eventScope) {
        this.eventScope = eventScope;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Event(EventScope eventScope, EventType eventType) {
        this.eventScope = eventScope;
        this.eventType = eventType;
    }

    public void process() {
        System.out.println("Processing " + eventScope + " " + eventType + ".");
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventScope=" + eventScope.getScope() +
                ", eventType=" + eventType.getType() +
                ", metaData=" + metaData +
                '}';
    }
}
