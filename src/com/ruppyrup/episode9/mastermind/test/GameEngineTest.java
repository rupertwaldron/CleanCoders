package com.ruppyrup.episode9.mastermind.test;

import com.ruppyrup.episode9.mastermind.gameplay.Console;
import com.ruppyrup.episode9.mastermind.gameplay.GameEngine;
import com.ruppyrup.episode9.mastermind.gameplay.GuessChecker;
import com.ruppyrup.episode9.mastermind.gameplay.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameEngineTest {

  private MockGuessChecker checker;
  private MockConsole console;

  @BeforeEach
  public void setUp() throws Exception {
    checker = new MockGuessChecker();
    GameEngine.guessChecker = checker;
  }

  @Test
  public void gotItOnFirstGuess() throws Exception {
    runGame(new FirstTryMockConsole());
    assertTrue(console.announcedGameOver());
    assertEquals("AAAA", console.getWinningCode());
    assertEquals(1, console.getTries());
    assertEquals(0, checker.guessesAdded());
  }

  @Test
  public void gotItOnSecondTry() throws Exception {
    runGame(new SecondTryMockConsole());
    assertTrue(console.announcedGameOver());
    assertEquals("AAAB", console.getWinningCode());
    assertEquals(2, console.getTries());
    assertEquals(1, checker.guessesAdded());
  }

  @Test
  public void neverGetIt() throws Exception {
    runGame(new NoTriesMockConsole());
    assertTrue(console.announcedGameOver());
    assertEquals(GameEngine.MAX_CODES, console.getTries());
    assertTrue(console.announcedBadScoring());
    assertEquals(GameEngine.MAX_CODES, checker.guessesAdded());
  }

  private void runGame(MockConsole mockConsole) {
    console = mockConsole;
    GameEngine.console = console;
    GameEngine engine = new GameEngine();
    engine.play();
  }
}

class FirstTryMockConsole extends MockConsole {
  public Score scoreGuess(String guess) {
    return new Score(4, 0);
  }
}

class SecondTryMockConsole extends MockConsole {
  boolean guessedYet = false;

  public Score scoreGuess(String guess) {
    if (guessedYet)
      return new Score(4, 0);
    else {
      guessedYet = true;
      return new Score(0, 0);
    }
  }
}

class NoTriesMockConsole extends MockConsole {
  public Score scoreGuess(String guess) {
    return new Score(0, 0);
  }

}

class MockConsole implements Console {
  private boolean gameOver;
  private String winningCode;
  protected int tries;
  private boolean badScoring;

  public Score scoreGuess(String guess) {
    return null;
  }

  public void announceGameOver() {
    gameOver = true;
  }

  public void announceWinningCode(String code) {
    winningCode = code;
  }

  public void announceTries(int tries) {
    this.tries = tries;
  }

  public void announceBadScoring() {
    badScoring = true;
  }

  public boolean announcedGameOver() {
    return gameOver;
  }

  public String getWinningCode() {
    return winningCode;
  }

  public int getTries() {
    return tries;
  }

  public boolean announcedBadScoring() {
    return badScoring;
  }
}

class MockGuessChecker implements GuessChecker {

  private int guessesAdded;

  public boolean shouldTry(String guess) {
    return true;
  }

  public void addScore(String guess, Score score) {
    guessesAdded++;
  }

  public int guessesAdded() {
    return guessesAdded;
  }
}
