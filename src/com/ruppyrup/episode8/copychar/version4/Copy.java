package com.ruppyrup.episode8.copychar.version4;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.APPEND;
import static org.assertj.core.api.Assertions.*;

/**
 * Copies text from keyboard to the console, until "quit" is typed.
 * Additional - make code read from file or keyboard
 */
public class Copy {


  private final CharWriter charWriter;
  private final CharReader charReader;

  public Copy(final CharWriter charWriter, final CharReader charReader) {
    this.charWriter = charWriter;
    this.charReader = charReader;
  }

  public void copyChars() {
    String inputChar;
    while (!(inputChar = charReader.getChar()).contains("quit")) {
        charWriter.printCharacter(inputChar);
    }
  }

  public static void main(String[] args) {
    CharReader reader = new Keyboard();
    CharWriter writer = new Printer();

    Copy copy = new Copy(writer, reader);
    copy.copyChars();
  }
}

interface CharReader {

  String getChar();
}

interface CharWriter {

  void printCharacter(String c);
}


class Keyboard implements CharReader {

  private final Scanner scanner = new Scanner(System.in);

  @Override
  public String getChar() {
    return scanner.nextLine();
  }

}

class Printer implements CharWriter{

  @Override
  public void printCharacter(String c) {
    System.out.println(c);
  }
}


class MyReader implements CharReader {

  private final Queue<String> lines = new LinkedList<>();

  @Override
  public String getChar() {
    if (lines.isEmpty()) {
      try (var reader = new BufferedReader(new FileReader("src/com/ruppyrup/episode8/copychar/version4/demo.text"))) {
        String line;
        while ((line = reader.readLine()) != null) {
          lines.add(line);
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    return lines.remove();
  }
}

class MyWriter implements CharWriter {
  Path path = Path.of("src/com/ruppyrup/episode8/copychar/version4/demowrite.text");

  @Override
  public void printCharacter(String c) {
    try {
      Files.writeString(path, c + "\n", APPEND);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}


class CopyTester {

  @Test
  void canReadAndWriteCharacter() {
    MockWriter writer = new MockWriter();
    MockReader reader = new MockReader();

    String testing1 = "testing1";
    String testing2 = "testing2";
    List<String> inputs = List.of(testing1, testing2);


    reader.setCharResponse(testing1);
    reader.setCharResponse(testing2);

    Copy copy = new Copy(writer, reader);
    copy.copyChars();

    assertThat(writer.getInputCapture()).hasSameElementsAs(inputs);
  }

  static class MockWriter implements CharWriter {

    private Queue<String> inputCapture = new LinkedList<>();

    @Override
    public void printCharacter(final String c) {
      inputCapture.add(c);
    }

    public Queue<String> getInputCapture() {
      return inputCapture;
    }
  }

  static class MockReader implements CharReader {

    private final Queue<String> charResponse = new LinkedList<>();

    @Override
    public String getChar() {
      if (charResponse.isEmpty()) {
        return "quit";
      }
      return charResponse.remove();
    }

    public void setCharResponse(final String charResponse) {
      this.charResponse.add(charResponse);
    }
  }
}
