package com.ruppyrup.episode28.decoupled;

public enum OneCoinTurnstileState implements TurnstileState {
  LOCKED {
    public void pass(TurnstileFSM fsm) {
      fsm.alarm();
    }

    public void coin(TurnstileFSM fsm) {
      fsm.setState(UNLOCKED);
      fsm.unlock();
    }
  },

  UNLOCKED {
    public void pass(TurnstileFSM fsm) {
      fsm.setState(LOCKED);
      fsm.lock();
    }

    public void coin(TurnstileFSM fsm) {
      fsm.thankyou();
    }
  }
}
