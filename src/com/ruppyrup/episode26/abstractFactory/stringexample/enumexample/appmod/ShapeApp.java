package com.ruppyrup.episode26.abstractFactory.stringexample.enumexample.appmod;

import com.ruppyrup.episode26.abstractFactory.enumexample.appmod.ShapeType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShapeApp {
    private final ShapeFactory shapeFactory;
    private final List<Shape> shapes = new ArrayList<>();

    public ShapeApp(final ShapeFactory shapeFactory) {
        this.shapeFactory = shapeFactory;
    }

    public void run() {
        Shape circle = safeMakeShape("circle");
        Shape square = safeMakeShape("square");
        System.out.println("Creating shapes");
        shapes.add(circle);
        shapes.add(square);
        shapes.forEach(Shape::draw);
        shapeFactory.getShapeNames();
    }

    private Shape safeMakeShape(String shapeName) {
        return Arrays.stream(shapeFactory.getShapeNames())
                .filter(name -> shapeName.equals(name))
                .findFirst()
                .map(shapeFactory::make)
                .orElseThrow();
    }
}

