package com.ruppyrup.episode26.abstractFactory.enumexample.shapesmod.normalShapes;

import com.ruppyrup.episode26.abstractFactory.enumexample.appmod.Shape;

public class Square implements Shape {
    public Square(final Point origin, final double side) {
        super();
    }

    @Override
    public void draw() {
        System.out.println("Drawing square");
    }
}
