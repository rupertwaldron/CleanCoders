package com.ruppyrup.episode28.statepattern.turnstile;

public class UnlockedState implements TurnstileState{

    private static UnlockedState instance;

    private UnlockedState() {
    }

    public static UnlockedState getInstance() {
        if (instance == null)
            instance = new UnlockedState();
        return instance;
    }
    public void pass(TurnstileFSM fsm) {
        fsm.setState(LockedState.getInstance());
        fsm.lock();
    }

    public void coin(TurnstileFSM fsm) {
        fsm.thankyou();
    }
}
