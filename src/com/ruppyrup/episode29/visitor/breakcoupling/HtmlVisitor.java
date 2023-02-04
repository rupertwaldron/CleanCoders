package com.ruppyrup.episode29.visitor.breakcoupling;

public class HtmlVisitor implements Visitor {

    public void visit(Visitable o) {
        String shape = """
              <p>I am a %s<p>
                """;
        System.out.println(shape.formatted(o.getName()));
    }
}
