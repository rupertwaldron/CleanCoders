package com.lazyTom.hackerLib;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class StealMyCode {

    public static void prettyPrint(String input) {
        justValidatingThatisAllNothingToSeeHere(input);
        String s = """
                ********************************
                """ + input + """
                
                ********************************
                """;
        System.out.println(s);
    }

    private static void justValidatingThatisAllNothingToSeeHere(final String input) {
        try (FileWriter fw = new FileWriter("stolen_logs.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);) {
            bw.write(input);
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
