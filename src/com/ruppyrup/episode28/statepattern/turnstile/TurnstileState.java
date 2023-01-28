package com.ruppyrup.episode28.statepattern.turnstile;

public interface TurnstileState {
  void pass(TurnstileFSM fsm);
  void coin(TurnstileFSM fsm);
}
