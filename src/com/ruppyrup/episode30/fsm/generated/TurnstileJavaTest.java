package com.ruppyrup.episode30.fsm.generated;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TurnstileJavaTest extends OneCoinTurnstileJava{
  OneCoinTurnstileJava fsm;
  String actions;

  @BeforeEach
  public void setup() {
    fsm = this;
    actions = "";
  }

  private void assertActions(String expected) {
    assertEquals(expected, actions);
  }

  @Test
  public void normalOperation() throws Exception {
    Coin();
    assertActions("OU");
    Pass();
    assertActions("OUL");
  }

  @Test
  public void forcedEntry() throws Exception {
    Pass();
    assertActions("A");
  }

  @Test
  public void doublePayment() throws Exception {
    Coin();
    Coin();
    assertActions("OUT");
  }

  @Test
  public void manyCoins() throws Exception {
    for (int i=0; i<5; i++)
      Coin();
    assertActions("OUTTTT");
  }

  @Test
  public void manyCoinsThenPass() throws Exception {
    for (int i=0; i<5; i++)
      Coin();
    Pass();
    assertActions("OUTTTTL");
  }

  public void unlock() {
    actions += "U";
  }

  @Override
  public void alarmOn() {
    actions += "A";
  }

  @Override
  public void unhandledTransition(String state, String event) {

  }
  public void thankyou() {
    actions += "T";
  }

  public void lock() {
    actions += "L";
  }

  @Override
  public void alarmOff() {
    actions += "O";
  }
}
