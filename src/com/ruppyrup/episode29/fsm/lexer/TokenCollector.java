package com.ruppyrup.episode29.fsm.lexer;

public interface TokenCollector {
  void openBrace(int line, int pos);
  void closedBrace(int line, int pos);
  void colon(int line, int pos);
  void name(String name, int line, int pos);
  void error(int line, int pos);
}
