package com.ruppyrup.episode29.visitor.breakcoupling;

public class VisitorTest {
    public static void main(String[] args) {
        Shape circle = new Circle();
        Shape square = new Square();
        Visitor htmlVisitor = new HtmlVisitor();
        Visitor asciiVisitor = new AsciiVisitor();

        circle.accept(htmlVisitor);
        square.accept(htmlVisitor);

        circle.accept(asciiVisitor);
        square.accept(asciiVisitor);
    }
}
