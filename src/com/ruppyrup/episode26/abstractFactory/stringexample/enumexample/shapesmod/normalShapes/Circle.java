package com.ruppyrup.episode26.abstractFactory.stringexample.enumexample.shapesmod.normalShapes;

import com.ruppyrup.episode26.abstractFactory.stringexample.enumexample.appmod.Shape;

public class Circle implements Shape {
    public Circle(final Point origin, final double side) {
        super();
    }

    @Override
    public void draw() {
        System.out.println("Drawing circle");
    }
}
