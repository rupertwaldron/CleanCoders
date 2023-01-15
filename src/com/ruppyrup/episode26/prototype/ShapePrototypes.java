package com.ruppyrup.episode26.prototype;

public class ShapePrototypes {

    private static final Point origin = new Point(0,0);
    public static Shape squarePrototype = new Square(origin, 1);
    public static Shape circlePrototype = new Circle(origin, 1);
}
