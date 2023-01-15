package com.ruppyrup.episode26.factorymethod.concrete;

import com.ruppyrup.episode26.factorymethod.app.Shape;

public class Circle implements Shape {
    public Circle(final Point origin, final double side) {
        super();
    }

    @Override
    public void draw() {
        System.out.println("Drawing circle");
    }
}
