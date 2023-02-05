package com.ruppyrup.episode30.fsm.generators.implementers;



import com.ruppyrup.episode30.fsm.generators.nestedSwitchCaseGenerator.NSCNode;
import com.ruppyrup.episode30.fsm.generators.nestedSwitchCaseGenerator.NSCNodeVisitor;

import java.util.List;

public class KotlinNestedSwitchCaseImplementer implements NSCNodeVisitor {
  private String output = "";
  private String javaPackage;

  public KotlinNestedSwitchCaseImplementer(String javaPackage) {
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
    return "kt";
  }

  public void visit(NSCNode.SwitchCaseNode switchCaseNode) {
    output += String.format("when (%s) {\n", switchCaseNode.variableName);
    switchCaseNode.generateCases(this);
    output += "}\n";
  }

  public void visit(NSCNode.CaseNode caseNode) {
    output += String.format("%s -> {\n", caseNode.caseName);
    caseNode.caseActionNode.accept(this);
    output += "}\n";
  }

  public void visit(NSCNode.FunctionCallNode functionCallNode) {
    output += String.format("%s(", functionCallNode.functionName);
    if (functionCallNode.argument != null)
      functionCallNode.argument.accept(this);
    output += ")\n";
  }

  public void visit(NSCNode.EnumNode enumNode) {
    output += String.format("private enum class %s {%s}\n", enumNode.name, commaList(enumNode.enumerators));

  }

  public void visit(NSCNode.StatePropertyNode statePropertyNode) {
    output += String.format("private var state = State.%s\n", statePropertyNode.initialState);
    output += "private fun setState(s: State) {state = s}\n";
  }

  public void visit(NSCNode.EventDelegatorsNode eventDelegatorsNode) {
    for (String event : eventDelegatorsNode.events)
      output += String.format("fun %s() {handleEvent(Event.%s)}\n", event, event);
  }

  public void visit(NSCNode.FSMClassNode fsmClassNode) {
    if (javaPackage != null)
      output += "package " + javaPackage + "\n";

    String actionsName = fsmClassNode.actionsName;
    if (actionsName == null)
      output += String.format("abstract class %s {\n", fsmClassNode.className + "Kotlin");
    else
      output += String.format("abstract class %s implements %s {\n", fsmClassNode.className + "Kotlin", actionsName);

    output += "abstract fun unhandledTransition(state: String?, event: String?)\n";
    fsmClassNode.stateEnum.accept(this);
    fsmClassNode.eventEnum.accept(this);
    fsmClassNode.stateProperty.accept(this);
    fsmClassNode.delegators.accept(this);
    fsmClassNode.handleEvent.accept(this);
    if (actionsName == null) {
      for (String action : fsmClassNode.actions)
        output += String.format("protected abstract fun %s()\n", action);
    }
    output += "}\n";
  }

  public void visit(NSCNode.HandleEventNode handleEventNode) {
    output += "private fun handleEvent(event: Event) {\n";
    handleEventNode.switchCase.accept(this);
    output += "}\n";
  }

  public void visit(NSCNode.EnumeratorNode enumeratorNode) {
    output += String.format("%s.%s", enumeratorNode.enumeration, enumeratorNode.enumerator);
  }

  public void visit(NSCNode.DefaultCaseNode defaultCaseNode) {
    output += "else -> unhandledTransition(state.name, event.name)\n";
  }

  public String getOutput() {
    return output;
  }
}
