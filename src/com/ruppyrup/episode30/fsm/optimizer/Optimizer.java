package com.ruppyrup.episode30.fsm.optimizer;

import com.ruppyrup.episode30.fsm.StateMachine;
import com.ruppyrup.episode30.fsm.semanticAnalyzer.AbstractSyntaxTree;

import java.util.*;

public class Optimizer {
  private StateMachine sm;
  private AbstractSyntaxTree ast;

  public StateMachine optimize(AbstractSyntaxTree ast) {
    this.ast = ast;
    sm = new StateMachine();
    addHeader(ast);
    addLists();
    addTransitions();
    return sm;
  }

  private void addTransitions() {
    for (AbstractSyntaxTree.State s : ast.states.values()) {
      StateMachine.Transition transition = new StateMachine.Transition();
      transition.currentState = s.name;
      for (AbstractSyntaxTree.SemanticTransition subTran : s.transitions) {
        StateMachine.SubTransition ss = new StateMachine.SubTransition();
        ss.actions.addAll(subTran.actions);
        ss.event = subTran.event;
        ss.nextState = subTran.nextState.name;
        transition.subTransitions.add(ss);
      }
      sm.transitions.add(transition);
    }
  }

  private void addHeader(AbstractSyntaxTree ast) {
    sm.header = new StateMachine.Header();
    sm.header.fsm = ast.fsmName;
    sm.header.initial = ast.initialState.name;
    sm.header.actions = ast.actionClass;
  }

  private void addLists() {
    addStates();
    addEvents();
    addActions();
  }

  private void addStates() {
    for (AbstractSyntaxTree.State s : ast.states.values())
      sm.states.add(s.name);
  }

  private void addEvents() {
    sm.events.addAll(ast.events);
  }

  private void addActions() {
    sm.actions.addAll(ast.actions);
  }
}
