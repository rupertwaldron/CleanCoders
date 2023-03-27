package com.ruppyrup.episode34.cor;

import com.ruppyrup.episode34.cor.handlers.DNSwitchHandler;
import com.ruppyrup.episode34.cor.handlers.DisEventHandler;
import com.ruppyrup.episode34.cor.handlers.MetaDataHandler;
import com.ruppyrup.episode34.cor.handlers.PulseNameHandler;

import java.util.List;

public class CoRTest {
    public static void main(String[] args) {
        Event event1 = new Event(new DN(), new Settlement());
        Event event2 = new Event(new Pulse(), new Switch());
        Event event3 = new Event(new DN(), new Switch());
        Event event4 = new Event(new Pulse(), new Settlement());

        List<Event> events = List.of(event1, event2, event3, event4);

        DisEventHandler pulseEppHandler = new PulseNameHandler(null);
        DisEventHandler metaDataHandler = new MetaDataHandler(pulseEppHandler);
        DisEventHandler handler = new DNSwitchHandler(metaDataHandler);

        EventProcessor eventProcessor = new EventProcessor(handler);

        events.forEach(eventProcessor::process);

        events.forEach(System.out::println);
    }
}
