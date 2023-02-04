package com.ruppyrup.episode30.fsm.parser;

/*
FSM::=<Header>*<Logic>
<Header>  ::= <Name> “:” <Name>
<Logic> ::= “{“ <Transition>* “}”
<Transition> ::= <State> ” ” <Event> ” ” <State> ” ” <Action>
<State> ::= <Name>
<Event> ::= <Name>
<Action> ::= <Name> | <Name> “ ” <Action>
<Name> ::= \w+


//OneCoin
Actions: Turnstile
        FSM: OneCoinTurnstile
        Initial: Locked
        {
        Locked	   {
        Coin	Unlocked	 {alarmOff unlock}
        Pass	Locked	 alarmOn
        }
        Unlocked  {
        Coin	Unlocked	 thankyou
        Unlocked  Pass	Locked	 lock
        }
        }
*/


import com.ruppyrup.episode30.fsm.lexer.TokenCollector;

import java.util.function.Consumer;

import static com.ruppyrup.episode30.fsm.parser.ParserEvent.*;
import static com.ruppyrup.episode30.fsm.parser.ParserState.*;

public class Parser implements TokenCollector {
  private ParserState state = HEADER;
  private Builder builder;

  public Parser(Builder builder) {
    this.builder = builder;
  }

  public void openBrace(int line, int pos) {
    handleEvent(OPEN_BRACE, line, pos);
  }

  public void closedBrace(int line, int pos) {
    handleEvent(CLOSED_BRACE, line, pos);
  }

  public void colon(int line, int pos) {
    handleEvent(COLON, line, pos);
  }

  public void name(String name, int line, int pos) {
    builder.setName(name);
    handleEvent(NAME, line, pos);
  }

  public void error(int line, int pos) {
    builder.syntaxError(line, pos);
  }

  class Transition {
    Transition(ParserState currentState, ParserEvent event,
               ParserState newState, Consumer<Builder> action) {
      this.currentState = currentState;
      this.event = event;
      this.newState = newState;
      this.action = action;
    }

    public ParserState currentState;
    public ParserEvent event;
    public ParserState newState;
    public Consumer<Builder> action;
  }

  Transition[] transitions = new Transition[]{
          new Transition(HEADER, NAME, HEADER_COLON, t -> t.newHeaderWithName()),
          new Transition(HEADER, OPEN_BRACE, STATE_SPEC, null), //1
          new Transition(HEADER_COLON, COLON, HEADER_VALUE, null),
          new Transition(HEADER_VALUE, NAME, HEADER, t -> t.addHeaderWithValue()),
          new Transition(STATE_SPEC, NAME, STATE_MODIFIER, t -> t.setStateName()), //2
          new Transition(STATE_SPEC, CLOSED_BRACE, END, t -> t.done()),
          new Transition(STATE_MODIFIER, OPEN_BRACE, STATE_MODIFIER, null), //3
          new Transition(STATE_MODIFIER, CLOSED_BRACE, STATE_SPEC, null), //3
          new Transition(STATE_MODIFIER, NAME, SINGLE_EVENT, t -> t.setEvent()), //7
          new Transition(SINGLE_EVENT, NAME, SINGLE_NEXT_STATE, t -> t.setNextState()), //4
          new Transition(SINGLE_NEXT_STATE, NAME, STATE_MODIFIER, t -> t.transitionWithAction()), //5
          new Transition(SINGLE_NEXT_STATE, OPEN_BRACE, GROUP_ACTION_GROUP, null),
          new Transition(GROUP_ACTION_GROUP, NAME, GROUP_ACTION_GROUP, t -> t.addAction()), //8
          new Transition(GROUP_ACTION_GROUP, CLOSED_BRACE, STATE_MODIFIER, t -> t.transitionNullAction()),
//          new Transition(GROUP_ACTION_GROUP_NAME, CLOSED_BRACE, STATE_MODIFIER, null),
          new Transition(END, ParserEvent.EOF, END, null)
  };

  /**
   * Scrolls through the table and matches the correct event and then applies the builder
   * @param event is the parser event
   * @param line number
   * @param pos number on the line
   */
  public void handleEvent(ParserEvent event, int line, int pos) {
    for (Transition t : transitions) {
      if (t.currentState == state && t.event == event) {
        state = t.newState;
        if (t.action != null)
          t.action.accept(builder);
        return;
      }
    }
    handleEventError(event, line, pos);
  }

  private void handleEventError(ParserEvent event, int line, int pos) {
    switch (state) {
      case HEADER:
      case HEADER_COLON:
      case HEADER_VALUE:
        builder.headerError(state, event, line, pos);
        break;

      case STATE_SPEC:
      case STATE_NAME:
      case STATE_MODIFIER:
        builder.stateSpecError(state, event, line, pos);
        break;

      case SINGLE_EVENT:
      case SINGLE_NEXT_STATE:
        builder.transitionError(state, event, line, pos);
        break;

      case GROUP_ACTION_GROUP:
      case GROUP_ACTION_GROUP_NAME:
        builder.transitionGroupError(state, event, line, pos);
        break;

      case END:
        builder.endError(state, event, line, pos);
        break;
    }
  }
}
