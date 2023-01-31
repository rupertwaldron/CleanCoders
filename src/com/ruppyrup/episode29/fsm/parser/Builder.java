package com.ruppyrup.episode29.fsm.parser;

public interface Builder {
  void newHeaderWithName();
  void addHeaderWithValue();
  void setStateName();
  void done();
  void setEvent();
  void setNextState();
  void transitionWithAction();
  void transitionWithActions();
  void transitionNullAction();
  void addAction();
  void headerError(ParserState state, ParserEvent event, int line, int pos);
  void stateSpecError(ParserState state, ParserEvent event, int line, int pos);
  void transitionError(ParserState state, ParserEvent event, int line, int pos);
  void transitionGroupError(ParserState state, ParserEvent event, int line, int pos);
  void endError(ParserState state, ParserEvent event, int line, int pos);
  void syntaxError(int line, int pos);
  void setName(String name);
}
