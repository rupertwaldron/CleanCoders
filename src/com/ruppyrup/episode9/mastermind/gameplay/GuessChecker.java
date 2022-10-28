package com.ruppyrup.episode9.mastermind.gameplay;

public interface GuessChecker {
  boolean shouldTry(String guess);

  void addScore(String guess, Score score);
}
