package com.ruppyrup.episode3.primeGenerator.src.literateProgramming;




import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PrintPrimesTest {
  private PrintStream out;

  @BeforeEach
  public void setup() throws Exception {
    out = System.out;
    System.setOut(new PrintStream(new FileOutputStream("lead")));
  }

  @AfterEach
  public void teardown() {
    System.setOut(out);
    new File("lead").delete();
  }

  @Test
  public void makeSureOutputMatchesGold() throws Exception {
    PrimePrinter.main(new String[0]);
    BufferedReader lead = new BufferedReader(new FileReader("lead"));
    BufferedReader gold = new BufferedReader(new FileReader("/Users/rupertwaldron/Documents/JavaDevelopment/Learn Programming at Discover/CleanCoders/src/com/ruppyrup/episode3/primeGenerator/src/literateProgramming/gold"));
    String line;
    while ((line = gold.readLine()) != null)
      assertEquals(line, lead.readLine());
    assertEquals(null, lead.readLine());
  }
}
