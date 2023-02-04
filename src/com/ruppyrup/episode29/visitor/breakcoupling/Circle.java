package com.ruppyrup.episode29.visitor.breakcoupling;

public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing circle");
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Circle{}";
    }
}
