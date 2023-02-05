package com.ruppyrup.episode30.fsm.generated;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GumBallJavaTest extends GumBallJava {
  GumBallJava fsm;
  String actions;

  private int gumballs = 0;

  @BeforeEach
  public void setup() {
    fsm = this;
    actions = "";
    gumballs = 5;
  }

  private void assertActions(String expected) {
    assertEquals(expected, actions);
  }

  @Test
  public void normalOperation() throws Exception {
    Coin();
    assertActions("O");
    TurnLeaver();
    assertActions("OGO");
  }

  @Test
  public void turnLeaverWithoutCoin() throws Exception {
    TurnLeaver();
    assertActions("A");
  }

  @Test
  public void doublePayment() throws Exception {
    Coin();
    Coin();
    assertActions("OR");
  }

  @Test
  public void manyCoins() throws Exception {
    for (int i=0; i<5; i++)
      Coin();
    assertActions("ORRRR");
  }

  @Test
  public void runOutOfGumBalls() throws Exception {
    for (int i=0; i<5; i++) {
      Coin();
      TurnLeaver();
    }
    assertActions("OGOOGOOGOOGOOGA");
  }

  @Override
  public void unhandledTransition(String state, String event) {

  }

  @Override
  protected void alarm() {
    actions += "A";
  }

  @Override
  protected void gumball() {
    gumballs--;
    actions += "G";
  }

  @Override
  public void alarmOff() {
    actions += "O";
  }

  @Override
  protected void returnCoin() {
    actions += "R";
  }

  @Override
  public void TurnLeaver() {
    super.TurnLeaver();
    if( gumballs <= 0) CountIsZero();
    else CountNotZero();
  }
}
