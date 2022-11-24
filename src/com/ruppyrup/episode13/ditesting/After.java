package com.ruppyrup.episode13.ditesting;


import java.util.Scanner;

interface Transformer {
    String transform(String input);
}

class UpperCaseTransformer implements Transformer {

    @Override
    public String transform(String input) {
        return input.toUpperCase();
    }
}

interface Input {
    String fetch();
}

class ScannerInput implements Input {

    @Override
    public String fetch() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}

interface Output {
    void display(String output);
}

class DisplayOutput implements Output {

    @Override
    public void display(String output) {
        System.out.println(output);
    }
}

public class After {
    private Input input;
    private Output output;

    public After(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    public void transformInput(Transformer transformer) {
        String s1 = input.fetch();
        String s2 = transformer.transform(s1);
        output.display(s2);
    }


    public static void main(String[] args) {
        Input input = new ScannerInput();
        Output output = new DisplayOutput();
        Transformer transformer = new UpperCaseTransformer();
        After after = new After(input, output);
        after.transformInput(transformer);
    }
}
