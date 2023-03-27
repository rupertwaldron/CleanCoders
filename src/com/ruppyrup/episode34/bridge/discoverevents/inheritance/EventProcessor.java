package com.ruppyrup.episode34.bridge.discoverevents.inheritance;

import java.util.List;

public class EventProcessor {
    public static void main(String[] args) {
        Event dnSettle = new DnSettlement();
        Event dnSwitch = new DnSwitch();
        Event pulseSettle = new PulseSettlement();
        Event pulseSwitch = new PulseSwitch();

        List<Event> events = List.of(dnSettle, dnSwitch, pulseSettle, pulseSwitch);
        events.forEach(Event::process);
    }
}
