package com.ruppyrup.episode29.visitor.traditional;

public class AsciiVisitor implements Visitor {

    public void visit(Circle o) {
        String circle = """
            ****************
            I am a circle
            ***************
                """;
        System.out.println(circle);
    }

    public void visit(Square o) {
        String square = """
              ***************
              I am a square
              ***************
                """;
        System.out.println(square);
    }
}
