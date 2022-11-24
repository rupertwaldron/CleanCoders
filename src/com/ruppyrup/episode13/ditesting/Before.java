package com.ruppyrup.episode13.ditesting;

import java.util.Scanner;

public class Before {
    public void showInputInUppercase() {
        // Fetch keyboard input
        Scanner scanner = new Scanner(System.in);
        String inputText = scanner.nextLine();

        // Convert
        String upperCaseText = inputText.toUpperCase();

        // Display
        System.out.println(upperCaseText);
    }

    public static void main(String[] args) {
        Before before = new Before();
        before.showInputInUppercase();
    }
}
