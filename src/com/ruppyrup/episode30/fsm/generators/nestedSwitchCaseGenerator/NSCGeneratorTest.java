package com.ruppyrup.episode30.fsm.generators.nestedSwitchCaseGenerator;


import com.ruppyrup.episode30.fsm.StateMachine;
import com.ruppyrup.episode30.fsm.lexer.Lexer;
import com.ruppyrup.episode30.fsm.optimizer.Optimizer;
import com.ruppyrup.episode30.fsm.parser.Parser;
import com.ruppyrup.episode30.fsm.parser.SyntaxBuilder;
import com.ruppyrup.episode30.fsm.semanticAnalyzer.AbstractSyntaxTree;
import com.ruppyrup.episode30.fsm.semanticAnalyzer.SemanticAnalyzer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.ruppyrup.episode30.fsm.generators.nestedSwitchCaseGenerator.NSCNode.*;
import static com.ruppyrup.episode30.fsm.parser.ParserEvent.EOF;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class NSCGeneratorTest {
  private Lexer lexer;
  private Parser parser;
  private SyntaxBuilder builder;
  private SemanticAnalyzer analyzer;
  private Optimizer optimizer;
  private String stdHead = "Initial: I FSM:f Actions:acts";
  private NSCGenerator generator;
  private NSCNodeVisitor implementer;
  private String output = "";

  @BeforeEach
  public void setUp() throws Exception {
    builder = new SyntaxBuilder();
    parser = new Parser(builder);
    lexer = new Lexer(parser);
    analyzer = new SemanticAnalyzer();
    optimizer = new Optimizer();
    generator = new NSCGenerator();
  }

  private StateMachine produceStateMachine(String fsmSyntax) {
    lexer.lex(fsmSyntax);
    parser.handleEvent(EOF, -1, -1);
    AbstractSyntaxTree ast = analyzer.analyze(builder.getFsm());
    return optimizer.optimize(ast);
  }

  private StateMachine headerAndSttToSm(String header, String stt) {
    return produceStateMachine(header + " " + stt);
  }

  private void assertGenerated(String stt, String switchCase) {
    StateMachine sm = headerAndSttToSm(stdHead, stt);
    generator.generate(sm).accept(implementer);
    assertThat(output, equalTo(switchCase));
  }

  private class EmptyVisitor implements NSCNodeVisitor {
    public void visit(SwitchCaseNode switchCaseNode) {

    }

    public void visit(CaseNode caseNode) {

    }

    public void visit(FunctionCallNode functionCallNode) {

    }

    public void visit(EnumNode enumNode) {

    }

    public void visit(StatePropertyNode statePropertyNode) {

    }

    public void visit(EventDelegatorsNode eventDelegatorsNode) {

    }

    public void visit(HandleEventNode handleEventNode) {
      handleEventNode.switchCase.accept(this);
    }


    public void visit(EnumeratorNode enumeratorNode) {
      output += enumeratorNode.enumeration + "." + enumeratorNode.enumerator;
    }

    public void visit(DefaultCaseNode defaultCaseNode) {
      output += " default unhandled;";
    }

    public void visit(FSMClassNode fsmClassNode) {
      fsmClassNode.delegators.accept(this);
      fsmClassNode.stateEnum.accept(this);
      fsmClassNode.eventEnum.accept(this);
      fsmClassNode.stateProperty.accept(this);
      fsmClassNode.handleEvent.accept(this);
    }
  }

  @Nested
  @DisplayName("SwitchCase Tests")
  public class SwitchCaseTests {
    @BeforeEach
    public void setup() {
      implementer = new TestVisitor();
    }

    @Test
    public void OneTransition() throws Exception {
      assertGenerated(
        "{I e I a}",
        "s state {case I {s event {case e {setState(State.I) a() } default unhandled;}}}");
    }

    @Test
    public void twoTransitions() throws Exception {
      assertGenerated("{I {e1 S a1} S {e2 I a2}}",
        "" +
          "s state {" +
          "case I {s event {case e1 {setState(State.S) a1() } default unhandled;}}" +
          "case S {s event {case e2 {setState(State.I) a2() } default unhandled;}}" +
          "}");
    }

    @Test
    public void twoStatesTwoEventsFourActions() throws Exception {
      assertGenerated(
        "" +
          "{" +
          "  I {e1 S a1 " +
                "e2 I a2}" +
          "  S {e1 I a3" +
          "  e2 S a4}" +
          "}",
        "" +
          "s state {" +
          "case I {s event {case e1 {setState(State.S) a1() }" +
          "case e2 {setState(State.I) a2() } default unhandled;}}" +
          "case S {s event {case e1 {setState(State.I) a3() }" +
          "case e2 {setState(State.S) a4() } default unhandled;}}}");
    }
  } // SwitchCase Tests.

  private class TestVisitor extends EmptyVisitor {
    public void visit(SwitchCaseNode switchCaseNode) {
      output += String.format("s %s {", switchCaseNode.variableName);
      switchCaseNode.generateCases(implementer);
      output += "}";
    }

    public void visit(CaseNode caseNode) {
      output += String.format("case %s {", caseNode.caseName);
      caseNode.caseActionNode.accept(implementer);
      output += "}";
    }

    public void visit(FunctionCallNode functionCallNode) {
      output += String.format("%s(", functionCallNode.functionName);
      if (functionCallNode.argument != null)
        functionCallNode.argument.accept(this);
      output += ") ";
    }
  }

  @Nested
  @DisplayName("EnumTests")
  public class EnumTests {
    @BeforeEach
    public void setup() {
      implementer = new EnumVisitor();
    }

    @Test
    public void statesAndEvents() throws Exception {
      assertGenerated(
              "" +
                "{" +
                "  I {e1 S a1 " +
                "     e2 I a2}" +
                "  S {e1 I a3" +
                "     e2 S a4}" +
                "}",
        "enum State [I, S] enum Event [e1, e2] ");
    }
  } //EnumTests

  private class EnumVisitor extends EmptyVisitor {
    public void visit(EnumNode enumNode) {
      output += String.format("enum %s %s ", enumNode.name, enumNode.enumerators);
    }
  }

  @Nested
  @DisplayName("State property tests")
  public class StatePropertyTest {
    @BeforeEach
    public void setup() {
      implementer = new StatePropertyVisitor();
    }

    @Test
    public void statePropertyIsCreated() throws Exception {
      assertGenerated("{I e I a}", "state property = I");
    }

  } // statePropertyTest

  private class StatePropertyVisitor extends EmptyVisitor {

    public void visit(StatePropertyNode statePropertyNode) {
      output += "state property = " + statePropertyNode.initialState;
    }
  }

  @Nested
  @DisplayName("Event Delegators Test")
  public class EventDelegators {
    @BeforeEach
    public void setup() {
      implementer = new EventDelegatorVisitor();
    }

    @Test
    public void eventDelegatorsAreGenerated() throws Exception {
      assertGenerated(
              "" +
                      "" +
                      "{" +
                      "  I {e1 S a1 " +
                      "     e2 I a2}" +
                      "  S {e1 I a3" +
                      "     e2 S a4}" +
                      "}",
              "delegators [e1, e2]");
    }
  } // EventDelegators

  private class EventDelegatorVisitor extends EmptyVisitor {

    public void visit(EventDelegatorsNode eventDelegatorsNode) {
      output += "delegators " + eventDelegatorsNode.events;
    }
  }

  @Nested
  @DisplayName("Handle Event Test")
  public class HandleEventTest {
    @BeforeEach
    public void setup() {
      implementer = new HandleEventVisitor();
    }

    @Test
    public void handleEventIsGenerated() throws Exception {
      assertGenerated("{I e I a}", "he(s)");
    }

  } // HandleEventTest

  private class HandleEventVisitor extends EmptyVisitor {

    public void visit(SwitchCaseNode switchCaseNode) {
      output += "s";
    }

    public void visit(HandleEventNode handleEventNode) {
      output += "he(";
      handleEventNode.switchCase.accept(this);
      output += ")";
    }
  }

  @Nested
  @DisplayName("Finite State Machine Class Test")
  public class FsmClassTest {
    @BeforeEach
    public void setup() {
      implementer = new FSMClassVisitor();
    }

    @Test
    public void fsmClassNodeIsGenerated() throws Exception {
      assertGenerated("{I e I a}", "class f:acts {d e e p he sc}");
    }

  } // FsmClassTest

  private class FSMClassVisitor extends EmptyVisitor {

    public void visit(SwitchCaseNode switchCaseNode) {
      output += "sc";
    }

    public void visit(EnumNode enumNode) {
     output += "e ";
    }

    public void visit(StatePropertyNode statePropertyNode) {
      output+="p ";
    }

    public void visit(EventDelegatorsNode eventDelegatorsNode) {
      output+="d ";
    }

    public void visit(HandleEventNode handleEventNode) {
      output+="he ";
    }

    public void visit(FSMClassNode fsmClassNode) {
      output += String.format("class %s:%s {", fsmClassNode.className, fsmClassNode.actionsName);
      fsmClassNode.delegators.accept(this);
      fsmClassNode.stateEnum.accept(this);
      fsmClassNode.eventEnum.accept(this);
      fsmClassNode.stateProperty.accept(this);
      fsmClassNode.handleEvent.accept(this);
      fsmClassNode.handleEvent.switchCase.accept(this);
      output += "}";
    }
  }
}
