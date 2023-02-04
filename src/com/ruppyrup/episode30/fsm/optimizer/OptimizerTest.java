package com.ruppyrup.episode30.fsm.optimizer;

import com.ruppyrup.episode30.fsm.StateMachine;
import com.ruppyrup.episode30.fsm.lexer.Lexer;
import com.ruppyrup.episode30.fsm.parser.Parser;
import com.ruppyrup.episode30.fsm.parser.SyntaxBuilder;
import com.ruppyrup.episode30.fsm.semanticAnalyzer.AbstractSyntaxTree;
import com.ruppyrup.episode30.fsm.semanticAnalyzer.SemanticAnalyzer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.ruppyrup.episode30.fsm.parser.ParserEvent.EOF;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


public class OptimizerTest {
  private Lexer lexer;
  private Parser parser;
  private SyntaxBuilder builder;
  private SemanticAnalyzer analyzer;
  private Optimizer optimizer;

  @BeforeEach
  public void setUp() throws Exception {
    builder = new SyntaxBuilder();
    parser = new Parser(builder);
    lexer = new Lexer(parser);
    analyzer = new SemanticAnalyzer();
    optimizer = new Optimizer();
  }

  private StateMachine produceStateMachineWithHeader(String s) {
    String fsmSyntax = "fsm:f initial:i actions:a " + s;
    return produceStateMachine(fsmSyntax);
  }

  private StateMachine produceStateMachine(String fsmSyntax) {
    lexer.lex(fsmSyntax);
    parser.handleEvent(EOF, -1, -1);
    AbstractSyntaxTree ast = analyzer.analyze(builder.getFsm());
    return optimizer.optimize(ast);
  }

  private void assertOptimization(String syntax, String stateMachine) {
    StateMachine sm = produceStateMachineWithHeader(syntax);
    assertThat(sm.transitionsToString(), equalTo(stateMachine));
  }



  @Nested
  @DisplayName("Acceptance Tests")
  public class AcceptanceTests {
    @Test
    public void turnstyle3() throws Exception {
      StateMachine sm = produceStateMachine("""
                        Actions : Turnstile
                        FSM : OneCoinTurnstile
                        Initial : Locked
                        {
                        Locked	   {
                                 Coin	Unlocked {alarmOff unlock}
                                 Pass	Locked	 alarmOn
                        }
                        Unlocked  {
                                 Coin	Unlocked thankyou
                                 Pass	Locked	 lock
                        }
                        }
                         """);
      assertThat(sm.toString(), equalTo(
              """
                       Initial: Locked
                       Fsm: OneCoinTurnstile
                       Actions:Turnstile
                       {
                         Locked {
                           Coin Unlocked {alarmOff unlock}
                           Pass Locked {alarmOn}
                         }
                         Unlocked {
                           Coin Unlocked {thankyou}
                           Pass Locked {lock}
                         }
                       }
                       """));
    }
  } // Acceptance Tests
}
