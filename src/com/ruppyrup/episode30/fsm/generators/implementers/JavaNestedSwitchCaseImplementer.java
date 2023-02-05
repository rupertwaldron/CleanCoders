package com.ruppyrup.episode30.fsm.generators.implementers;



import com.ruppyrup.episode30.fsm.generators.nestedSwitchCaseGenerator.NSCNode;
import com.ruppyrup.episode30.fsm.generators.nestedSwitchCaseGenerator.NSCNodeVisitor;

import java.util.List;

public class JavaNestedSwitchCaseImplementer implements NSCNodeVisitor {
  private String output = "";
  private String javaPackage;

  public JavaNestedSwitchCaseImplementer(String javaPackage) {
    this.javaPackage = javaPackage;
  }

  private String commaList(List<String> names) {
    String commaList = "";
    boolean first = true;
    for (String name : names) {
      commaList += (first ? "" : ",") + name;
      first = false;
    }
    return commaList;
  }

  @Override
  public String getCodeType() {
    return "java";
  }

  public void visit(NSCNode.SwitchCaseNode switchCaseNode) {
    output += String.format("switch(%s) {\n", switchCaseNode.variableName);
    switchCaseNode.generateCases(this);
    output += "}\n";
  }

  public void visit(NSCNode.CaseNode caseNode) {
    String name = caseNode.caseName.split("\\.")[1];

    output += String.format("case %s:\n", name);
    caseNode.caseActionNode.accept(this);
    output += "break;\n";
  }

  public void visit(NSCNode.FunctionCallNode functionCallNode) {
    output += String.format("%s(", functionCallNode.functionName);
    if (functionCallNode.argument != null)
      functionCallNode.argument.accept(this);
    output += ");\n";
  }

  public void visit(NSCNode.EnumNode enumNode) {
    output += String.format("private enum %s {%s}\n", enumNode.name, commaList(enumNode.enumerators));

  }

  public void visit(NSCNode.StatePropertyNode statePropertyNode) {
    output += String.format("private State state = State.%s;\n", statePropertyNode.initialState);
    output += "private void setState(State s) {state = s;}\n";
  }

  public void visit(NSCNode.EventDelegatorsNode eventDelegatorsNode) {
    for (String event : eventDelegatorsNode.events)
      output += String.format("public void %s() {handleEvent(Event.%s);}\n", event, event);
  }

  public void visit(NSCNode.FSMClassNode fsmClassNode) {
    if (javaPackage != null)
      output += "package " + javaPackage + ";\n";

    String actionsName = fsmClassNode.actionsName;
    if (actionsName == null)
      output += String.format("public abstract class %s {\n", fsmClassNode.className + "Java");
    else
      output += String.format("public abstract class %s implements %s {\n", fsmClassNode.className + "Java", actionsName);

    output += "public abstract void unhandledTransition(String state, String event);\n";
    fsmClassNode.stateEnum.accept(this);
    fsmClassNode.eventEnum.accept(this);
    fsmClassNode.stateProperty.accept(this);
    fsmClassNode.delegators.accept(this);
    fsmClassNode.handleEvent.accept(this);
    if (actionsName == null) {
      for (String action : fsmClassNode.actions)
        output += String.format("protected abstract void %s();\n", action);
    }
    output += "}\n";
  }

  public void visit(NSCNode.HandleEventNode handleEventNode) {
    output += "private void handleEvent(Event event) {\n";
    handleEventNode.switchCase.accept(this);
    output += "}\n";
  }

  public void visit(NSCNode.EnumeratorNode enumeratorNode) {
    output += String.format("%s.%s", enumeratorNode.enumeration, enumeratorNode.enumerator);
  }

  public void visit(NSCNode.DefaultCaseNode defaultCaseNode) {
    output += "default: unhandledTransition(state.name(), event.name()); break;\n";
  }

  @Override
  public String getOutput() {
    return output;
  }
}
