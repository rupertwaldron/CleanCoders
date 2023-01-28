package com.ruppyrup.episode28.decoupled;

public interface TurnstileState {
  void pass(TurnstileFSM fsm);
  void coin(TurnstileFSM fsm);
}
