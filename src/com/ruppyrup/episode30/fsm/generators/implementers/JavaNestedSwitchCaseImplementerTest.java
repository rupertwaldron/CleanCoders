package com.ruppyrup.episode30.fsm.generators.implementers;



import com.ruppyrup.episode30.fsm.StateMachine;
import com.ruppyrup.episode30.fsm.lexer.Lexer;
import com.ruppyrup.episode30.fsm.optimizer.Optimizer;
import com.ruppyrup.episode30.fsm.parser.Parser;
import com.ruppyrup.episode30.fsm.parser.SyntaxBuilder;
import com.ruppyrup.episode30.fsm.generators.nestedSwitchCaseGenerator.NSCGenerator;
import com.ruppyrup.episode30.fsm.generators.nestedSwitchCaseGenerator.NSCNode;
import com.ruppyrup.episode30.fsm.semanticAnalyzer.AbstractSyntaxTree;
import com.ruppyrup.episode30.fsm.semanticAnalyzer.SemanticAnalyzer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.ruppyrup.episode30.fsm.parser.ParserEvent.EOF;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


public class JavaNestedSwitchCaseImplementerTest {
  private Lexer lexer;
  private Parser parser;
  private SyntaxBuilder builder;
  private SemanticAnalyzer analyzer;
  private Optimizer optimizer;
  private NSCGenerator generator;

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

  @Test
  public void oneTransitionWithPackageAndActions() throws Exception {
    JavaNestedSwitchCaseImplementer implementer = new JavaNestedSwitchCaseImplementer("thePackage");
    StateMachine sm = produceStateMachine("" +
        "Initial: I\n" +
        "Fsm: fsm\n" +
        "Actions: acts\n" +
        "{" +
        "  I {E I A}" +
        "}");
    NSCNode generatedFsm = generator.generate(sm);
    generatedFsm.accept(implementer);
    assertThat(implementer.getOutput(), equalTo("" +
        "package thePackage;\n" +
        "public abstract class fsmJava implements acts {\n" +
        "public abstract void unhandledTransition(String state, String event);\n" +
        "private enum State {I}\n" +
        "private enum Event {E}\n" +
        "private State state = State.I;\n" +
        "private void setState(State s) {state = s;}\n" +
        "public void E() {handleEvent(Event.E);}\n" +
        "private void handleEvent(Event event) {\n" +
        "switch(state) {\n" +
        "case I:\n" +
        "switch(event) {\n" +
        "case E:\n" +
        "setState(State.I);\n" +
        "A();\n" +
        "break;\n" +
        "default: unhandledTransition(state.name(), event.name()); break;\n" +
        "}\n" +
        "break;\n" +
        "}\n" +
        "}\n" +
        "}\n"));
  }

  @Test
  public void oneTransitionWithActionsButNoPackage() throws Exception {
    JavaNestedSwitchCaseImplementer implementer = new JavaNestedSwitchCaseImplementer(null);
    StateMachine sm = produceStateMachine("" +
        "Initial: I\n" +
        "Fsm: fsm\n" +
        "Actions: acts\n" +
        "{" +
        "  I {E I A}" +
        "}");
    NSCNode generatedFsm = generator.generate(sm);
    generatedFsm.accept(implementer);
    assertThat(implementer.getOutput(), startsWith("" +
      "public abstract class fsmJava implements acts {\n"));
  }

  @Test
  public void oneTransitionWithNoActionsAndNoPackage() throws Exception {
    JavaNestedSwitchCaseImplementer implementer = new JavaNestedSwitchCaseImplementer(null);
    StateMachine sm = produceStateMachine("" +
        "Initial: I\n" +
        "Fsm: fsm\n" +
        "{" +
        "  I {E I A}" +
        "}");
    NSCNode generatedFsm = generator.generate(sm);
    generatedFsm.accept(implementer);
    String output = implementer.getOutput();
    assertThat(output, startsWith("" +
      "public abstract class fsmJava {\n"));
    assertThat(output, containsString("protected abstract void A();\n"));
  }

}
