package com.ruppyrup.episode28.decoupled;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TurnstileTest extends TurnstileFSM {
  TurnstileFSM fsm;
  String actions;

  @BeforeEach
  public void setup() {
    fsm = this;
    fsm.setState(OneCoinTurnstileState.LOCKED); // establish the logic.
    actions = "";
  }

  private void assertActions(String expected) {
    assertEquals(expected, actions);
  }

  @Test
  public void normalOperation() throws Exception {
    coin();
    assertActions("U");
    pass();
    assertActions("UL");
  }

  @Test
  public void forcedEntry() throws Exception {
    pass();
    assertActions("A");
  }

  @Test
  public void doublePayment() throws Exception {
    coin();
    coin();
    assertActions("UT");
  }

  @Test
  public void manyCoins() throws Exception {
    for (int i=0; i<5; i++)
      coin();
    assertActions("UTTTT");
  }

  @Test
  public void manyCoinsThenPass() throws Exception {
    for (int i=0; i<5; i++)
      coin();
    pass();
    assertActions("UTTTTL");
  }

  public void unlock() {
    actions += "U";
  }

  public void alarm() {
    actions += "A";
  }

  public void thankyou() {
    actions += "T";
  }

  public void lock() {
    actions += "L";
  }
}
