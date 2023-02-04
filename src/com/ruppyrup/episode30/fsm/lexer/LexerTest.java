package com.ruppyrup.episode30.fsm.lexer;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LexerTest implements TokenCollector {
  String tokens = "";
  Lexer lexer;
  private boolean firstToken = true;

  @BeforeEach
  public void setUp() throws Exception {
    lexer = new Lexer(this);
  }

  private void addToken(String token) {
    if (!firstToken)
      tokens += ",";
    tokens += token;
    firstToken = false;
  }

  private void assertLexResult(String input, String expected) {
    lexer.lex(input);
    assertEquals(expected, tokens);
  }

  public void openBrace(int line, int pos) {
    addToken("OB");
  }

  public void closedBrace(int line, int pos) {
    addToken("CB");
  }

  public void colon(int line, int pos) {
    addToken("C");
  }

  public void name(String name, int line, int pos) {
    addToken("#" + name + "#");
  }

  public void error(int line, int pos) {
    addToken("E" + line + "/" + pos);
  }

  @Nested
  @DisplayName("Single Token Tests")
  public class SingleTokenTests {
    @Test
    public void findsOpenBrace() throws Exception {
      assertLexResult("{", "OB");
    }

    @Test
    public void findsClosedBrace() throws Exception {
      assertLexResult("}", "CB");
    }

    @Test
    public void findsColon() throws Exception {
      assertLexResult(":", "C");
    }

    @Test
    public void findsSimpleName() throws Exception {
      assertLexResult("name", "#name#");
    }

    @Test
    public void findComplexName() throws Exception {
      assertLexResult("Room_222", "#Room_222#");
    }

    @Test
    public void error() throws Exception {
      assertLexResult(".", "E1/1");
    }

    @Test
    public void nothingButWhiteSpace() throws Exception {
      assertLexResult(" ", "");
    }

    @Test
    public void whiteSpaceBefore() throws Exception {
      assertLexResult("  \t\n  }", "CB");
    }
  }

  @Nested
  @DisplayName("Multiple Token Tests")
  public class MultipleTokenTests {
    @Test
    public void simpleSequence() throws Exception {
      assertLexResult("{}", "OB,CB");
    }

    @Test
    public void complexSequence() throws Exception {
      assertLexResult("FSM:fsm{this}", "#FSM#,C,#fsm#,OB,#this#,CB");
    }

    @Test
    public void allTokens() throws Exception {
      assertLexResult("{}: name .", "OB,CB,C,#name#,E1/10");
    }

    @Test
    public void multipleLines() throws Exception {
      assertLexResult("FSM:fsm.\n{bob.}", "#FSM#,C,#fsm#,E1/8,OB,#bob#,E2/5,CB");
    }
  }

}
