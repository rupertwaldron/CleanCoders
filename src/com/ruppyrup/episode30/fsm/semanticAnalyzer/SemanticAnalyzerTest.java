package com.ruppyrup.episode30.fsm.semanticAnalyzer;

import com.ruppyrup.episode30.fsm.lexer.Lexer;
import com.ruppyrup.episode30.fsm.parser.Parser;
import com.ruppyrup.episode30.fsm.parser.SyntaxBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.ruppyrup.episode30.fsm.parser.FsmSyntax.Header;
import static com.ruppyrup.episode30.fsm.parser.ParserEvent.EOF;
import static com.ruppyrup.episode30.fsm.semanticAnalyzer.AbstractSyntaxTree.AnalysisError;
import static com.ruppyrup.episode30.fsm.semanticAnalyzer.AbstractSyntaxTree.AnalysisError.ID.*;
import static com.ruppyrup.episode30.fsm.semanticAnalyzer.AbstractSyntaxTree.State;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SemanticAnalyzerTest {
    private Lexer lexer;
    private Parser parser;
    private SyntaxBuilder builder;
    private SemanticAnalyzer analyzer;

    @BeforeEach
    public void setUp() throws Exception {
        builder = new SyntaxBuilder();
        parser = new Parser(builder);
        lexer = new Lexer(parser);
        analyzer = new SemanticAnalyzer();
    }

    private AbstractSyntaxTree produceAst(String s) {
        lexer.lex(s);
        parser.handleEvent(EOF, -1, -1);
        return analyzer.analyze(builder.getFsm());
    }

    private void assertSemanticResult(String s, String expected) {
        AbstractSyntaxTree abstractSyntaxTree = produceAst(s);
        assertEquals(expected, abstractSyntaxTree.toString());
    }

    @Nested
    @DisplayName("Semantic Errors")
    public class SemanticErrors {
        @Nested
        @DisplayName("Header Erros")
        public class HeaderErrors {
            @Test
            public void noHeaders() throws Exception {
                List<AnalysisError> errors = produceAst("{}").errors;
                assertThat(errors, hasItems(
                        new AnalysisError(NO_FSM),
                        new AnalysisError(NO_INITIAL)));
            }

            @Test
            public void missingActions() throws Exception {
                List<AnalysisError> errors = produceAst("FSM:f Initial:i {}").errors;
                assertThat(errors, not(hasItems(
                        new AnalysisError(NO_FSM),
                        new AnalysisError(NO_INITIAL))));
            }

            @Test
            public void missingFsm() throws Exception {
                List<AnalysisError> errors = produceAst("actions:a Initial:i {}").errors;
                assertThat(errors, not(hasItems(
                        new AnalysisError(NO_INITIAL))));
                assertThat(errors, hasItems(new AnalysisError(NO_FSM)));
            }

            @Test
            public void missingInitial() throws Exception {
                List<AnalysisError> errors = produceAst("Actions:a Fsm:f {}").errors;
                assertThat(errors, not(hasItems(new AnalysisError(NO_FSM))));
                assertThat(errors, hasItems(new AnalysisError(NO_INITIAL)));
            }

            @Test
            public void nothingMissing() throws Exception {
                List<AnalysisError> errors = produceAst("Initial: f Actions:a Fsm:f {}").errors;
                assertThat(errors, not(hasItems(
                        new AnalysisError(NO_INITIAL),
                        new AnalysisError(NO_FSM))));
            }

            @Test
            public void unexpectedHeader() throws Exception {
                List<AnalysisError> errors = produceAst("X: x{s - - -}").errors;
                assertThat(errors, hasItems(
                        new AnalysisError(INVALID_HEADER, new Header("X", "x"))));
            }

            @Test
            public void duplicateHeader() throws Exception {
                List<AnalysisError> errors = produceAst("fsm:f fsm:x{s - - -}").errors;
                assertThat(errors, hasItems(
                        new AnalysisError(EXTRA_HEADER_IGNORED, new Header("fsm", "x"))));
            }

            @Test
            public void initialStateMustBeDefined() throws Exception {
                List<AnalysisError> errors = produceAst("initial: i {s - - -}").errors;
                assertThat(errors, hasItems(
                        new AnalysisError(UNDEFINED_STATE, "initial: i")));
            }
        } // Header Errors

        @Nested
        @DisplayName("State Errors")
        public class StateErrors {
            @Test
            public void nullNextStateIsNotUndefined() throws Exception {
                List<AnalysisError> errors = produceAst("{s - - -}").errors;
                assertThat(errors, not(hasItems(new AnalysisError(UNDEFINED_STATE, null))));
            }

            @Test
            public void noUndefinedStates() throws Exception {
                List<AnalysisError> errors = produceAst("{s - s -}").errors;
                assertThat(errors, not(hasItems(new AnalysisError(UNDEFINED_STATE, "s2"))));
            }

            @Test
            public void unusedStates() throws Exception {
                List<AnalysisError> errors = produceAst("{s e n -}").errors;
                assertThat(errors, hasItems(new AnalysisError(UNUSED_STATE, "s")));
            }

            @Test
            public void usedAsInitialIsValidUsage() throws Exception {
                List<AnalysisError> errors = produceAst("initial: b {b e n -}").errors;
                assertThat(errors, not(hasItems(new AnalysisError(UNUSED_STATE, "b"))));
            }

            @Test
            public void noErrorForOverriddenTransition() throws Exception {
                List<AnalysisError> errors = produceAst(
                        "" +
                                "FSM: f Actions: act Initial: s" +
                                "{" +
                                "  (ss1) e1 s1 -" +
                                "  s :ss1 e1 s3 a" +
                                "  s1 e s -" +
                                "  s3 e s -" +
                                "}").errors;
                assertThat(errors, not(hasItems(new AnalysisError(CONFLICTING_SUPERSTATES, "s|e1"))));
            }

            @Test
            public void noErrorIfSuperStatesHaveIdenticalTransitions() throws Exception {
                List<AnalysisError> errors = produceAst(
                        "" +
                                "FSM: f Actions: act Initial: s" +
                                "{" +
                                "  (ss1) e1 s1 ax" +
                                "  (ss2) e1 s1 ax" +
                                "  s :ss1 :ss2 e2 s3 a" +
                                "  s1 e s -" +
                                "  s3 e s -" +
                                "}").errors;
                assertThat(errors, not(hasItems(new AnalysisError(CONFLICTING_SUPERSTATES, "s|e1"))));
            }
        } // State Errors

        @Nested
        @DisplayName("Transition Errors")
        public class TransitionErrors {
            @Test
            public void duplicateTransitions() throws Exception {
                List<AnalysisError> errors = produceAst("{s {e s1 a e s1 a}}").errors;
                assertThat(errors, hasItems(new AnalysisError(DUPLICATE_TRANSITION, "s(e)")));
            }

            @Test
            public void noDuplicateTransitions() throws Exception {
                List<AnalysisError> errors = produceAst("{s e - -}").errors;
                assertThat(errors, not(hasItems(new AnalysisError(DUPLICATE_TRANSITION, "s(e)"))));
            }
        } // Transition Errors
    }// Semantic Errors.

    @Nested
    @DisplayName("Lists")
    public class Lists {
        @Test
        public void oneState() throws Exception {
            AbstractSyntaxTree ast = produceAst("{s - - -}");
            assertThat(ast.states.values(), contains(new State("s")));
        }

        @Test
        public void manyStates() throws Exception {
            AbstractSyntaxTree ast = produceAst("{s1 {- - -} s2 {- - -} s3 {- - -}}");
            assertThat(ast.states.values(), hasItems(
                    new State("s1"),
                    new State("s2"),
                    new State("s3")));
        }

        @Test
        public void statesAreKeyedByName() throws Exception {
            AbstractSyntaxTree ast = produceAst("{s1 {- - -} s2 {- - -} s3 {- - -}}");
            assertThat(ast.states.get("s1"), equalTo(new State("s1")));
            assertThat(ast.states.get("s2"), equalTo(new State("s2")));
            assertThat(ast.states.get("s3"), equalTo(new State("s3")));
        }

        @Test
        public void manyEvents() throws Exception {
            AbstractSyntaxTree ast = produceAst("{s1 { e1 s2 a1 } s2 { e2 s3 a2 } s3 { e3 s1 a3}}");
            assertThat(ast.events, hasItems("e1", "e2", "e3"));
            assertThat(ast.events, hasSize(3));
        }

        @Test
        public void manyEventsButNoDuplicates() throws Exception {
            AbstractSyntaxTree ast = produceAst("{s1 { e1 s2 a1 } s2 { e2 s3 a2 } s3 { e1 s1 a3}}");
            assertThat(ast.events, hasItems("e1", "e2"));
            assertThat(ast.events, hasSize(2));
        }

        @Test
        public void noNullEvents() throws Exception {
            AbstractSyntaxTree ast = produceAst("{(s1) - - -}");
            assertThat(ast.events, hasSize(0));
        }

        @Test
        public void manyActionsButNoDuplicates() throws Exception {
            AbstractSyntaxTree ast = produceAst("{s1 { e1 s2 {a1 a2}} s2 { e2 s1 {a3 a1}}}");
            assertThat(ast.actions, hasItems("a1", "a2", "a3"));
            assertThat(ast.actions, hasSize(3));
        }
    } // Lists

    @Nested
    @DisplayName("Logic")
    public class Logic {
        private String addHeader(String s) {
            return "initial: s fsm:f actions:a " + s;
        }

        private void assertSyntaxToAst(String syntax, String ast) {
            String states = produceAst(addHeader(syntax)).statesToString();
            assertThat(states, equalTo(ast));
        }

        @Test
        public void oneTransition() throws Exception {
            assertSyntaxToAst("{s e s a}",
                    "" +
                            "{\n" +
                            "  s {\n" +
                            "    e s {a}\n" +
                            "  }\n" +
                            "}\n");
        }

        @Test
        public void twoTransitionsAreAggregated() throws Exception {
            assertSyntaxToAst("{s {e1 s a e2 s a}}",
                    "" +
                            "{\n" +
                            "  s {\n" +
                            "    e1 s {a}\n" +
                            "    e2 s {a}\n" +
                            "  }\n" +
                            "}\n");
        }

        @Test
        public void actionsRemainInOrder() throws Exception {
            assertSyntaxToAst("{s { e s {the quick brown fox jumped over the lazy dogs back}}}",
                    "" +
                            "{\n" +
                            "  s {\n" +
                            "    e s {the quick brown fox jumped over the lazy dogs back}\n" +
                            "  }\n" +
                            "}\n");
        }

        @Nested
        @DisplayName("Acceptance Tests")
        public class AcceptanceTests {
            @Test
            public void subwayTurnstileOne() throws Exception {
                String input = """
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
                         """;
                String expected = """
                        Actions: Turnstile
                        FSM: OneCoinTurnstile
                        Initial: Locked{
                          Locked {
                            Coin Unlocked {alarmOff unlock}
                            Pass Locked {alarmOn}
                          }
                                                
                          Unlocked {
                            Coin Unlocked {thankyou}
                            Pass Locked {lock}
                          }
                        }
                        """;
                AbstractSyntaxTree ast = produceAst(input);
                assertThat(ast.toString(), equalTo(expected));
            }
        }

    }
}
