package com.ruppyrup.episode30.fsm.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FsmSyntax {
  public List<Header> headers = new ArrayList<>();
  public List<Transition> logic = new ArrayList<>();
  public List<SyntaxError> errors = new ArrayList<>();
  public boolean done = false;

  public static class Header {
    public String name;
    public String value;

    public static Header NullHeader() {
      Header header = new Header(null, null);
      return header;
    }

    public Header() {
    }

    public Header(String name, String value) {
      this.name = name;
      this.value = value;
    }

    public int hashCode() {
      return Objects.hash(name, value);
    }

    public boolean equals(Object obj) {
      if (obj instanceof Header) {
        Header other = (Header) obj;
        return Objects.equals(other.name, name) && Objects.equals(other.value, value);
      }
      return false;
    }

  }

  public static class Transition {
    public StateSpec state;
    public List<SubTransition> subTransitions = new ArrayList<>();
  }

  public static class StateSpec {
    public String name;
  }

  public static class SubTransition {
    public String event;
    public String nextState;
    public List<String> actions = new ArrayList<>();

    public SubTransition(String event) {
      this.event = event;
    }
  }

  public static class SyntaxError {
    public Type type;
    public String msg;
    public int lineNumber;
    public int position;

    public SyntaxError(Type type, String msg, int lineNumber, int position) {
      this.type = type;
      this.msg = msg;
      this.lineNumber = lineNumber;
      this.position = position;
    }

    public String toString() {
      return String.format("Syntax Error Line: %d, Position: %d.  (%s) %s", lineNumber, position, type.name(), msg);
    }

    public enum Type {HEADER, STATE, TRANSITION, TRANSITION_GROUP, END, SYNTAX}
  }

  public String toString() {
    return
      formatHeaders() +
        formatLogic() +
        (done ? ".\n" : "") +
        formatErrors();
  }

  private String formatHeaders() {
    String formattedHeaders = "";
    for (Header header : headers)
      formattedHeaders += formatHeader(header);
    return formattedHeaders;
  }

  private String formatHeader(Header header) {
    return String.format("%s:%s\n", header.name, header.value);
  }

  private String formatLogic() {
    if (logic.size() > 0)
      return String.format("{\n%s\n}\n", formatTransitions());
    else
      return "";
  }

  private String formatTransitions() {
    String transitions = "";
    int count = 0;
    for (Transition transition : logic) {
      transitions += formatTransition(transition);
      if (count++ < logic.size() - 1) {
        transitions += "\n";
      }
    }
    return transitions;
  }

  private String formatTransition(Transition transition) {
    return
            String.format("  %s {\n %s \n  }",
                    formatStateName(transition.state),
                    formatSubTransitions(transition));
  }

  private String formatStateName(StateSpec stateSpec) {
    return String.format("%s", stateSpec.name);
  }

  private String formatSubTransitions(Transition transition) {
    if (transition.subTransitions.size() == 1)
      return formatSubTransition(transition.subTransitions.get(0));
    else {
      String formattedSubTransitions = "";

      int count = 0;
      for (SubTransition subtransition : transition.subTransitions) {
        formattedSubTransitions += " " + formatSubTransition(subtransition);
        if (count++ < transition.subTransitions.size() - 1) {
          formattedSubTransitions += "\n";
        }
      }

      return formattedSubTransitions;
    }
  }

  private String formatSubTransition(SubTransition subtransition) {
    return String.format(
            "\t%s %s %s",
            subtransition.event,
            subtransition.nextState,
            formatActions(subtransition));
  }

  private String formatActions(SubTransition subtransition) {
    if (subtransition.actions.size() == 1)
      return subtransition.actions.get(0);
    else {
      String actions = "{";
      boolean first = true;
      for (String action : subtransition.actions) {
        actions += (first ? "" : " ") + action;
        first = false;
      }

      return actions + "}";
    }
  }

  private String formatErrors() {
    if (errors.size() > 0)
      return formatError(errors.get(0));
    else
      return "";
  }

  private String formatError(SyntaxError error) {
    return String.format(
            "Syntax error: %s. %s. line %d, position %d.\n",
            error.type.toString(),
            error.msg,
            error.lineNumber,
            error.position);
  }

  public String getError() {
    return formatErrors();
  }

}