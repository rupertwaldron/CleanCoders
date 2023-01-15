package com.ruppyrup.episode26.factorymethod.concrete;

import com.ruppyrup.episode26.factorymethod.app.Shape;
import com.ruppyrup.episode26.factorymethod.app.ShapeApp;

public class AppImpl extends ShapeApp {

    private static final Point point = new Point(1.0, 1.0);
    @Override
    public Shape make(String shapeName) {
        return switch(shapeName) {
            case "circle" -> makeCircle(point, 3.0);
            case "square" -> makeSquare(point, 3.0);
            default -> throw new IllegalStateException("Unexpected value: " + shapeName);
        };
    }

    private Shape makeSquare(Point origin, double side) {
        return new Square(origin, side);
    }

    private Shape makeCircle(Point origin, double radius) {
        return new Circle(origin, radius);
    }
}
