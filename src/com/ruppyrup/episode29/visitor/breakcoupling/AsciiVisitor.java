package com.ruppyrup.episode29.visitor.breakcoupling;

public class AsciiVisitor implements Visitor {

    public void visit(Visitable o) {
        String shape = """
            ****************
            I am a %s
            ***************
                """;
        System.out.println(shape.formatted(o.getName()));
    }
}
