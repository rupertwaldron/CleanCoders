package com.ruppyrup.episode28.statepattern.turnstile;

public class TurnstileFSM {
  private TurnstileState state;

  public void pass() {
    state.pass(this);
  }

  public void coin() {
    state.coin(this);
  }

  public void setState(TurnstileState state) {
    this.state = state;
  }

  public void alarm() {
    System.out.println("Alarming the turnsile");
  }
  public void lock() {
    System.out.println("Locking the turnsile");
  }
  public void unlock() {
    System.out.println("UnLocking the turnsile");
  }
  public void thankyou() {
    System.out.println("Turnstile says thank you");
  }
}
