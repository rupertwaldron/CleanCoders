package com.ruppyrup.episode8.copychar.version3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.APPEND;

/**
 * Copies text from keyboard to the console, until "quit" is typed.
 * Additional - make code read from file or keyboard
 */
public class Copy {

  /**
   * Don't forget to reset the flag depending on what you want to read from
   */
  public static boolean GptFlag = false;

  public static boolean GpunchFlag = false;

  Keyboard keyboard = new Keyboard();
  Printer printer = new Printer();
  MyReader fileReader = new MyReader();
  MyWriter fileWriter = new MyWriter();

  public void copyChars() {
    String inputChar;
    while (!(inputChar = GptFlag ? fileReader.getChar() : keyboard.getChar()).contains("quit")) {
      if (GpunchFlag) {
        fileWriter.printCharacter(inputChar);
      } else {
        printer.printCharacter(inputChar);
      }
    }
  }

  public static void main(String[] args) {
    Copy.GptFlag = true;
    Copy.GpunchFlag = true;
    Copy copy = new Copy();
    copy.copyChars();
  }
}


class Keyboard {

  private final Scanner scanner = new Scanner(System.in);

  public String getChar() {
    return scanner.nextLine();
  }

}

class Printer {

  public void printCharacter(String c) {
    System.out.println(c);
  }
}


class MyReader {

  private final Queue<String> lines = new LinkedList<>();

  public String getChar() {
    if (lines.isEmpty()) {
      try (var reader = new BufferedReader(new FileReader("src/com/ruppyrup/episode8/copychar/version3/demo.text"))) {
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

class MyWriter {
  Path path = Path.of("src/com/ruppyrup/episode8/copychar/version3/demowrite.text");

  public void printCharacter(String c) {
    try {
      Files.writeString(path, c + "\n", APPEND);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
