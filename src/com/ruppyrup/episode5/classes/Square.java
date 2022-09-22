package com.ruppyrup.episode5.classes;

public class Square implements Shape {
    private final double sideLength;

    public Square(final double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public void calculateArea() {
        System.out.println("Square area = " + sideLength * sideLength);
    }

    @Override
    public void calculatePerimeter() {
        System.out.println("Square perimeter = " + sideLength * 4);
    }
}
