package com.ruppyrup.episode34.cor.handlers;

import com.ruppyrup.episode34.cor.Event;
import com.ruppyrup.episode34.cor.Pulse;

public class DNSwitchHandler extends DisEventHandler {
    public DNSwitchHandler(DisEventHandler handler) {
        super(handler);
    }

    @Override
    public void handle(Event event) {
        if (event != null && canHandle(event)) {
            System.out.println("DN Switch Handler");
            event.setEventScope(new Pulse());
        }
        if (handler != null) {
            handler.handle(event);
        }
    }

    private boolean canHandle(Event event) {
        return "Switch".equals(event.getEventType()) && "DN".equals(event.getEventScope());
    }
}
