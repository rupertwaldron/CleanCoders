package com.ruppyrup.episode5.classes;

public class Circle implements Shape {
    private final double radius;

    public Circle(final double radius) {
        this.radius = radius;
    }

    @Override
    public void calculateArea() {
        System.out.println("Circle area = " + 3.14 * radius * radius);
    }

    @Override
    public void calculatePerimeter() {
        System.out.println("Circle perimeter = " + 2 * 3.14 * radius);
    }
}
