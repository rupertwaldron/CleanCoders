package com.ruppyrup.episode34.cor;


import com.ruppyrup.episode34.cor.handlers.DisEventHandler;

public class EventProcessor {

    private DisEventHandler eventHandler;

    public EventProcessor(DisEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public void process(Event event) {
        eventHandler.handle(event);
    }

    public void setEventHandler(DisEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }
}
