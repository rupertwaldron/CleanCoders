package com.ruppyrup.episode26.prototype;

public class ShapeApplicationUsingPrototype {
    public void runShapeApplication() throws Exception {
        Shape myCircle = (Shape) ShapePrototypes.circlePrototype.clone();
        ((Circle) myCircle).setRadius(42);
        ((Circle) myCircle).setOrigin(new Point(3, 4));

        Shape mySquare = (Shape) ShapePrototypes.squarePrototype.clone();
        ((Square) mySquare).setSide(42);
        ((Square) mySquare).setTopLeft(new Point(10, 12));

        Shape mySquare2 = (Shape) ShapePrototypes.squarePrototype.clone();
        ((Square) mySquare2).setSide(5);
        ((Square) mySquare2).setTopLeft(new Point(7, 5));

        System.out.println(myCircle.area());
        System.out.println(mySquare2.area());
        System.out.println(mySquare.area());
    }
}
