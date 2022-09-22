package com.ruppyrup.episode5.datastructures;

import java.util.ArrayList;
import java.util.List;

// Adding new methods is easy, adding new types is much harder - all the methods need to be updated
// Use this approach when methods are more likely to be added.
public class ProcessDataShape {

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
        for(Shape shape : shapes) {
            switch (shape.typeOfShape) {
                case ("circle") ->
                        System.out.println("Circle area = " + 3.14 * ((Circle) shape).radius * ((Circle) shape).radius);
                case ("square") ->
                        System.out.println("Square area = " + ((Square) shape).sideLength * ((Square) shape).sideLength);
                default -> throw new RuntimeException("No such shape");
            }
        }
    }

    public static void calculatePerimeter(List<Shape> shapes) {
        for(Shape shape : shapes) {
            switch (shape.typeOfShape) {
                case ("circle") ->
                        System.out.println("Circle perimeter = " + 3.14 * ((Circle) shape).radius * 2);
                case ("square") ->
                        System.out.println("Square perimeter = " + ((Square) shape).sideLength * 4);
                default -> throw new RuntimeException("No such shape");
            }
        }
    }
}
