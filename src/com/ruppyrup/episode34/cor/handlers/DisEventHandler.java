package com.ruppyrup.episode34.cor.handlers;

import com.ruppyrup.episode34.cor.Event;

public abstract class DisEventHandler {
    protected DisEventHandler handler;

    protected DisEventHandler(DisEventHandler handler) {
        this.handler = handler;
    }

    public abstract void handle(Event event);
}
