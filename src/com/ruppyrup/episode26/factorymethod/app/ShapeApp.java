package com.ruppyrup.episode26.factorymethod.app;


import java.util.ArrayList;
import java.util.List;

public abstract class ShapeApp {

    private final List<Shape> shapes = new ArrayList<>();

    public void run() {
        Shape circle = make("circle");
        Shape square = make("square");
        System.out.println("Creating shapes");
        shapes.add(circle);
        shapes.add(square);
        shapes.forEach(Shape::draw);
    }

    protected abstract Shape make(String shapeType);

}

