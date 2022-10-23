package com.ruppyrup.episode8.copychar.version1;

import java.util.Scanner;

/**
 * Copies text from keyboard to the console, until "quit" is typed.
 */
public class Copy {

  Keyboard keyboard = new Keyboard();
  Printer printer = new Printer();


  public void copyChars() {
    String inputChar;
    while(!"quit".equals(inputChar = keyboard.getChar()))  {
      printer.printCharacter(inputChar);
    }
  }

  public static void main(String[] args) {
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
