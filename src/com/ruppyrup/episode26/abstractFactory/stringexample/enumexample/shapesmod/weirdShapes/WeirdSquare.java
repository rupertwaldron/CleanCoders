package com.ruppyrup.episode26.abstractFactory.stringexample.enumexample.shapesmod.weirdShapes;

import com.ruppyrup.episode26.abstractFactory.stringexample.enumexample.appmod.Shape;

public class WeirdSquare implements Shape {
    public WeirdSquare(final Point origin, final double side) {
        super();
    }

    @Override
    public void draw() {
        System.out.println("Drawing a weird square");
    }
}
