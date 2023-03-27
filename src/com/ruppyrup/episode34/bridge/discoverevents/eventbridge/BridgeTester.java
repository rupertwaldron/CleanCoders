package com.ruppyrup.episode34.bridge.discoverevents.eventbridge;

/**
 * Bridge lets you develop eventScopes and eventTypes independently of one another
 * Easier to add DCI and dispute
 */
public class BridgeTester {
    public static void main(String[] args) {
        EventScope dn = new DN();
        EventScope pulse = new Pulse();
        EventType settle = new Settlement();
        EventType swit = new Switch();

        Event event1 = new Event(dn, settle);
        Event event2 = new Event(pulse, swit);

        event1.process();
        event2.process();
    }
}
