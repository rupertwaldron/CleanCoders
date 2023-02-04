package com.ruppyrup.episode30.fsm.parser;


import com.ruppyrup.episode30.fsm.lexer.Lexer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.ruppyrup.episode30.fsm.parser.ParserEvent.EOF;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
  private Lexer lexer;
  private Parser parser;
  private SyntaxBuilder builder;

  @BeforeEach
  public void setUp() throws Exception {
    builder = new SyntaxBuilder();
    parser = new Parser(builder);
    lexer = new Lexer(parser);
  }

  private void assertParseResult(String s, String expected) {
    lexer.lex(s);
    parser.handleEvent(EOF, -1, -1);
    assertEquals(expected, builder.getFsm().toString());
  }

  private void assertParseError(String s, String expected) {
    lexer.lex(s);
    parser.handleEvent(EOF, -1, -1);
    assertEquals(expected, builder.getFsm().getError());
  }

  @Nested
  @DisplayName("Incremental tests")
  public class IncrementalTests {
    @Test
    public void parseOneHeader() throws Exception {
      assertParseResult("N:V{}", "N:V\n.\n");
    }

    @Test
    public void parseManyHeaders() throws Exception {
      assertParseResult("  N1 : V1\tN2 : V2\n{}", "N1:V1\nN2:V2\n.\n");
    }

    @Test
    public void noHeader() throws Exception {
      assertParseResult(" {}", ".\n");
    }

    @Test
    public void simpleTransition() throws Exception {
      assertParseResult("n : v {s { e ns a }}",
        "n:v\n" +
                "{\n" +
                "  s {\n" +
                " \te ns a \n" +
                "  }\n" +
                "}\n" +
                ".\n");
    }

    @Test
    public void transitionWithManyActions() throws Exception {
      assertParseResult("n : v {s { e ns {a1 a2} }}",
              "n:v\n" +
                      "{\n" +
                      "  s {\n" +
                      " \te ns {a1 a2} \n" +
                      "  }\n" +
                      "}\n" +
                      ".\n");
    }

    @Test
    public void manySubTransitions() throws Exception {
      assertParseResult("n : v {s { e1 ns1 a1 e2 ns2 a2 }}",
              "n:v\n" +
                      "{\n" +
                      "  s {\n" +
                      "  \te1 ns1 a1\n" +
                      " \te2 ns2 a2 \n" +
                      "  }\n" +
                      "}\n" +
                      ".\n");
    }

    @Test
    public void manySubTransitionsAndManyActions() throws Exception {
      String expected = """
              n:v
              {
                s {
                	e1 ns1 {a1 a3}
               	e2 ns2 {a2 a4}\s
                }
              }
              .
              """;
      assertParseResult("n : v {s { e1 ns1 {a1 a3} e2 ns2 {a2 a4} }}", expected);
    }

    @Test
    public void manyTransitions() throws Exception {
      String expected = """
              n:v
              {
                s1 {
               	e1 ns1 a1\s
                }
                s2 {
               	e2 ns2 a2\s
                }
              }
              .
              """;
      assertParseResult("n : v {s1 { e1 ns1 a1 } s2 { e2 ns2 a2 }}", expected);
    }

    @Test
    public void manyTransitionsWithManyActions() throws Exception {
      String expected = """
              n:v
              {
                s1 {
               	e1 ns1 {a1 a3}\s
                }
                s2 {
               	e2 ns2 {a2 a4}\s
                }
              }
              .
              """;
      assertParseResult("n : v {s1 { e1 ns1 {a1 a3} } s2 { e2 ns2 {a2 a4} }}", expected);
    }

    @Test
    public void manySubTransitionsWithManyActions() throws Exception {
      String expected = """
              n:v
              {
                s1 {
                	e1 ns1 {a1 a3}
               	e3 ns3 a5\s
                }
                s2 {
                	e2 ns2 a6
               	e4 ns4 {a2 a4}\s
                }
              }
              .
              """;
      assertParseResult("n : v {s1 { e1 ns1 {a1 a3} e3 ns3 a5 } s2 { e2 ns2 a6 e4 ns4 {a2 a4}}}", expected);
    }

    @Test
    public void stateWithNoSubTransitions() throws Exception {
      String expected = """
              {
                s {
               \s
                }
              }
              .
              """;
      assertParseResult("{s {}}", expected);
    }
  }

  @Nested
  @DisplayName("Acceptance tests")
  public class AcceptanceTests {
    @Test
    public void simpleOneCoinTurnstile() throws Exception {
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
              Actions:Turnstile
              FSM:OneCoinTurnstile
              Initial:Locked
              {
                Locked {
                	Coin Unlocked {alarmOff unlock}
               	Pass Locked alarmOn\s
                }
                Unlocked {
                	Coin Unlocked thankyou
               	Pass Locked lock\s
                }
              }
              .
              """;
      assertParseResult(input, expected);
    }
  }

  @Nested
  @DisplayName("Error tests")
  public class ErrorTests {
    @Test
    public void parseNothing() throws Exception {
      assertParseError("", "Syntax error: HEADER. HEADER|EOF. line -1, position -1.\n");
    }

    @Test
    public void headerWithNoColonOrValue() throws Exception {
      assertParseError("A {s e ns a}",
        "Syntax error: HEADER. HEADER_COLON|OPEN_BRACE. line 1, position 2.\n");
    }

    @Test
    public void headerWithNoValue() throws Exception {
      assertParseError("A: {s e ns a}",
        "Syntax error: HEADER. HEADER_VALUE|OPEN_BRACE. line 1, position 3.\n");
    }

    @Test
    public void transitionWayTooShort() throws Exception {
      assertParseError("{s}",
        "Syntax error: STATE. STATE_SPEC|EOF. line -1, position -1.\n");
    }

    @Test
    public void transitionTooShort() throws Exception {
      assertParseError("{s e}",
        "Syntax error: TRANSITION. SINGLE_EVENT|CLOSED_BRACE. line 1, position 4.\n");
    }

    @Test
    public void transitionNoAction() throws Exception {
      assertParseError("{s e ns}",
        "Syntax error: TRANSITION. SINGLE_NEXT_STATE|CLOSED_BRACE. line 1, position 7.\n");
    }

    @Test
    public void noClosingBrace() throws Exception {
      assertParseError("{",
        "Syntax error: STATE. STATE_SPEC|EOF. line -1, position -1.\n");
    }

    @Test
    public void lexicalError() throws Exception {
      assertParseError("{.}",
        "Syntax error: SYNTAX. . line 1, position 2.\n");
    }
  }
}
