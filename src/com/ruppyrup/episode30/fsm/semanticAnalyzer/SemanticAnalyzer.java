package com.ruppyrup.episode30.fsm.semanticAnalyzer;


import com.ruppyrup.episode30.fsm.parser.FsmSyntax;

import java.util.*;

import static com.ruppyrup.episode30.fsm.parser.FsmSyntax.*;
import static com.ruppyrup.episode30.fsm.semanticAnalyzer.AbstractSyntaxTree.*;
import static com.ruppyrup.episode30.fsm.semanticAnalyzer.AbstractSyntaxTree.AnalysisError.ID.*;

public class SemanticAnalyzer {
  private AbstractSyntaxTree ast;
  private Header fsmHeader = Header.NullHeader();
  private Header actionsHeader = new Header();
  private Header initialHeader = new Header();

  public AbstractSyntaxTree analyze(FsmSyntax fsm) {
    ast = new AbstractSyntaxTree();
    analyzeHeaders(fsm);
    checkForErrorsAndWarnings(fsm);
    compile(fsm);
    return ast;
  }

  private void analyzeHeaders(FsmSyntax fsm) {
    setHeaders(fsm);
    checkMissingHeaders();
  }

  private void setHeaders(FsmSyntax fsm) {
    for (Header header : fsm.headers) {
      if (isNamed(header, "fsm"))
        setHeader(fsmHeader, header);
      else if (isNamed(header, "actions"))
        setHeader(actionsHeader, header);
      else if (isNamed(header, "initial"))
        setHeader(initialHeader, header);
      else
        ast.addError(new AnalysisError(INVALID_HEADER, header));
    }
  }

  private boolean isNamed(Header header, String headerName) {
    return header.name.equalsIgnoreCase(headerName);
  }

  private void setHeader(Header targetHeader, Header header) {
    if (isNullHeader(targetHeader)) {
      targetHeader.name = header.name;
      targetHeader.value = header.value;
    } else
      ast.addError(new AnalysisError(EXTRA_HEADER_IGNORED, header));
  }

  private void checkMissingHeaders() {
    if (isNullHeader(fsmHeader))
      ast.addError(new AnalysisError(AnalysisError.ID.NO_FSM));
    if (isNullHeader(initialHeader))
      ast.addError(new AnalysisError(AnalysisError.ID.NO_INITIAL));
  }

  private boolean isNullHeader(Header header) {
    return header.name == null;
  }

  private void checkForErrorsAndWarnings(FsmSyntax fsm) {
    createStateEventAndActionLists(fsm);
    checkUndefinedStates(fsm);
    checkForUnusedStates(fsm);
    checkForDuplicateTransitions(fsm);
  }

  private void createStateEventAndActionLists(FsmSyntax fsm) {
    addStateNamesToStateList(fsm);
    addEventsToEventList(fsm);
    addTransitionActionsToActionList(fsm);
  }

  private void addTransitionActionsToActionList(FsmSyntax fsm) {
    for (Transition t : fsm.logic)
      for (SubTransition st : t.subTransitions)
        for (String action : st.actions)
          ast.actions.add(action);
  }

  private void addEventsToEventList(FsmSyntax fsm) {
    for (Transition t : fsm.logic)
      for (SubTransition st : t.subTransitions)
        if (st.event != null)
          ast.events.add(st.event);
  }

  private void addStateNamesToStateList(FsmSyntax fsm) {
    for (Transition t : fsm.logic) {
      State state = new State(t.state.name);
      ast.states.put(state.name, state);
    }
  }

  private void checkUndefinedStates(FsmSyntax fsm) {
    for (Transition t : fsm.logic) {

      for (SubTransition st : t.subTransitions)
        checkUndefinedState(st.nextState, UNDEFINED_STATE);
    }

    if (initialHeader.value != null && !ast.states.containsKey(initialHeader.value))
      ast.errors.add(new AnalysisError(UNDEFINED_STATE, "initial: " + initialHeader.value));
  }

  private void checkForUnusedStates(FsmSyntax fsm) {
    findStatesDefinedButNotUsed(findUsedStates(fsm));
  }

  private Set<String> findUsedStates(FsmSyntax fsm) {
    Set<String> usedStates = new HashSet<>();
    usedStates.add(initialHeader.value);
    usedStates.addAll(getNextStates(fsm));
    return usedStates;
  }

  private Set<String> getNextStates(FsmSyntax fsm) {
    Set<String> nextStates = new HashSet<>();
    for (Transition t : fsm.logic)
      for (SubTransition st : t.subTransitions)
        if (st.nextState == null) // implicit use of current state.
          nextStates.add(t.state.name);
        else
          nextStates.add(st.nextState);
    return nextStates;
  }

  private void findStatesDefinedButNotUsed(Set<String> usedStates) {
    for (String definedState : ast.states.keySet())
      if (!usedStates.contains(definedState))
        ast.errors.add(new AnalysisError(UNUSED_STATE, definedState));
  }

  private void checkForDuplicateTransitions(FsmSyntax fsm) {
    Set<String> transitionKeys = new HashSet<>();
    for (Transition t : fsm.logic) {
      for (SubTransition st : t.subTransitions) {
        String key = String.format("%s(%s)", t.state.name, st.event);
        if (transitionKeys.contains(key))
          ast.errors.add(new AnalysisError(DUPLICATE_TRANSITION, key));
        else
          transitionKeys.add(key);
      }
    }
  }

  private void checkUndefinedState(String referencedState, AnalysisError.ID errorCode) {
    if (referencedState != null && !ast.states.containsKey(referencedState)) {
      ast.errors.add(new AnalysisError(errorCode, referencedState));
    }
  }

  private void compile(FsmSyntax fsm) {
    if (ast.errors.size() == 0) {
      compileHeaders();
      for (Transition t : fsm.logic) {
        State state = compileState(t);
        compileTransitions(t, state);
      }
    }
  }

  private void compileHeaders() {
    ast.initialState = ast.states.get(initialHeader.value);
    ast.actionClass = actionsHeader.value;
    ast.fsmName = fsmHeader.value;
  }

  private State compileState(Transition t) {
    return ast.states.get(t.state.name);
  }

  private void compileTransitions(Transition t, State state) {
    for (SubTransition st : t.subTransitions)
      compileTransition(state, st);
  }

  private void compileTransition(State state, SubTransition st) {
    SemanticTransition semanticTransition = new SemanticTransition();
    semanticTransition.event = st.event;
    semanticTransition.nextState = st.nextState == null ? state : ast.states.get(st.nextState);
    semanticTransition.actions.addAll(st.actions);
    state.transitions.add(semanticTransition);
  }
}
