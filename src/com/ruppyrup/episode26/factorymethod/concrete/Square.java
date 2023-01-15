package com.ruppyrup.episode26.factorymethod.concrete;


import com.ruppyrup.episode26.factorymethod.app.Shape;

public class Square implements Shape {
    public Square(final Point origin, final double side) {
        super();
    }

    @Override
    public void draw() {
        System.out.println("Drawing square");
    }
}
