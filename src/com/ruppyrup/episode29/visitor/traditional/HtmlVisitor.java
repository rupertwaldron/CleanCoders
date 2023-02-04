package com.ruppyrup.episode29.visitor.traditional;

public class HtmlVisitor implements Visitor {

    public void visit(Circle o) {
        String circle = """
              <p>I am a circle<p>
                """;
        System.out.println(circle);
    }

    public void visit(Square o) {
        String square = """
              <p>I am a square<p>
                """;
        System.out.println(square);
    }
}
