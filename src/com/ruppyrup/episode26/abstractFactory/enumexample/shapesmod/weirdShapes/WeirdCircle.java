package com.ruppyrup.episode26.abstractFactory.enumexample.shapesmod.weirdShapes;

import com.ruppyrup.episode26.abstractFactory.enumexample.appmod.Shape;

public class WeirdCircle implements Shape {
    public WeirdCircle(final Point origin, final double side) {
        super();
    }

    @Override
    public void draw() {
        System.out.println("Drawing a weird circle");
    }
}