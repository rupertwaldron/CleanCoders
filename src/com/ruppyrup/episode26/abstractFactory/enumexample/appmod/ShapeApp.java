package com.ruppyrup.episode26.abstractFactory.enumexample.appmod;

import java.util.ArrayList;
import java.util.List;

public class ShapeApp {

    private final ShapeFactory shapeFactory;
    private final List<Shape> shapes = new ArrayList<>();

    public ShapeApp(final ShapeFactory shapeFactory) {
        this.shapeFactory = shapeFactory;
    }

    public void run() {
        Shape circle = shapeFactory.make(ShapeType.CIRCLE);
        Shape square = shapeFactory.make(ShapeType.SQUARE);
        System.out.println("Creating shapes");
        shapes.add(circle);
        shapes.add(square);
        shapes.forEach(Shape::draw);
        shapeFactory.getShapeNames();
    }
}

