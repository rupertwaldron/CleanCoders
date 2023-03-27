package com.ruppyrup.episode34.cor.handlers;

import com.ruppyrup.episode34.cor.Event;
import com.ruppyrup.episode34.cor.Pulse;

public class MetaDataHandler extends DisEventHandler {
    public MetaDataHandler(DisEventHandler handler) {
        super(handler);
    }

    @Override
    public void handle(Event event) {
        if (event != null && canHandle(event)) {
            System.out.println("MetaData Handler");
            event.setMetaData("Processed with metaData");
        }
        if (handler != null) {
            handler.handle(event);
        }
    }

    private boolean canHandle(Event event) {
        return true;
    }
}
