package com.ruppyrup.episode28.statepattern.turnstile;

public class LockedState implements TurnstileState {

    private static LockedState instance;

    private LockedState() {
    }

    public static LockedState getInstance() {
        if (instance == null)
            instance = new LockedState();
        return instance;
    }

    public void pass(TurnstileFSM fsm) {
        fsm.alarm();
    }

    public void coin(TurnstileFSM fsm) {
        fsm.setState(UnlockedState.getInstance());
        fsm.unlock();
    }
}
