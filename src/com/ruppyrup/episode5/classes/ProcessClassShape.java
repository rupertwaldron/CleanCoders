package com.ruppyrup.episode5.classes;

import java.util.ArrayList;
import java.util.List;

// Adding new types is easy, adding new methods is much harder - all the classes and derivaties will need to be changed
// Use this approach when types are more likely to be added.
public class ProcessClassShape {

    public static void main(String[] args) {
        Circle circle = new Circle(3.0);
        Square square = new Square(5.0);

        List<Shape> shapes = new ArrayList<>();

        shapes.add(circle);
        shapes.add(square);

        calculateArea(shapes);
        calculatePerimeter(shapes);
    }

    public static void calculateArea(List<Shape> shapes) {
        shapes.forEach(Shape::calculateArea);
    }

    public static void calculatePerimeter(List<Shape> shapes) {
        shapes.forEach(Shape::calculatePerimeter);
    }
}
