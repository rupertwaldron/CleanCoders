package com.ruppyrup.episode30.fsm.generated;

public abstract class GumBallJava {
    public abstract void unhandledTransition(String state, String event);

    private enum State {CountGumBalls, Dispense, Empty, Full}

    private enum Event {Coin, TurnLeaver, CountNotZero, CountIsZero}

    private State state = State.Full;

    private void setState(State s) {
        state = s;
    }

    public void Coin() {
        handleEvent(Event.Coin);
    }

    public void TurnLeaver() {
        handleEvent(Event.TurnLeaver);
    }

    public void CountNotZero() {
        handleEvent(Event.CountNotZero);
    }

    public void CountIsZero() {
        handleEvent(Event.CountIsZero);
    }

    private void handleEvent(Event event) {
        switch (state) {
            case CountGumBalls:
                switch (event) {
                    case CountIsZero:
                        setState(State.Empty);
                        alarm();
                        break;
                    case CountNotZero:
                        setState(State.Full);
                        alarmOff();
                        break;
                    default:
                        unhandledTransition(state.name(), event.name());
                        break;
                }
                break;
            case Dispense:
                switch (event) {
                    case Coin:
                        setState(State.Dispense);
                        returnCoin();
                        break;
                    case TurnLeaver:
                        setState(State.CountGumBalls);
                        gumball();
                        break;
                    default:
                        unhandledTransition(state.name(), event.name());
                        break;
                }
                break;
            case Empty:
                switch (event) {
                    case Coin:
                        setState(State.CountGumBalls);
                        returnCoin();
                        break;
                    case TurnLeaver:
                        setState(State.CountGumBalls);
                        alarm();
                        break;
                    default:
                        unhandledTransition(state.name(), event.name());
                        break;
                }
                break;
            case Full:
                switch (event) {
                    case Coin:
                        setState(State.Dispense);
                        alarmOff();
                        break;
                    case TurnLeaver:
                        setState(State.Full);
                        alarm();
                        break;
                    default:
                        unhandledTransition(state.name(), event.name());
                        break;
                }
                break;
        }
    }

    protected abstract void alarm();

    protected abstract void gumball();

    protected abstract void alarmOff();

    protected abstract void returnCoin();
}
