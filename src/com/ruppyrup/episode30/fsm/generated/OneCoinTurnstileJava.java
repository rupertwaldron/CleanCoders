package com.ruppyrup.episode30.fsm.generated;

public abstract class OneCoinTurnstileJava {
    public abstract void unhandledTransition(String state, String event);

    private enum State {Locked, Unlocked}

    private enum Event {Coin, Pass}

    private State state = State.Locked;

    private void setState(State s) {
        state = s;
    }

    public void Coin() {
        handleEvent(Event.Coin);
    }

    public void Pass() {
        handleEvent(Event.Pass);
    }

    private void handleEvent(Event event) {
        switch (state) {
            case Locked:
                switch (event) {
                    case Coin:
                        setState(State.Unlocked);
                        alarmOff();
                        unlock();
                        break;
                    case Pass:
                        setState(State.Locked);
                        alarmOn();
                        break;
                    default:
                        unhandledTransition(state.name(), event.name());
                        break;
                }
                break;
            case Unlocked:
                switch (event) {
                    case Coin:
                        setState(State.Unlocked);
                        thankyou();
                        break;
                    case Pass:
                        setState(State.Locked);
                        lock();
                        break;
                    default:
                        unhandledTransition(state.name(), event.name());
                        break;
                }
                break;
        }
    }

    protected abstract void thankyou();

    protected abstract void unlock();

    protected abstract void alarmOn();

    protected abstract void lock();

    protected abstract void alarmOff();
}
