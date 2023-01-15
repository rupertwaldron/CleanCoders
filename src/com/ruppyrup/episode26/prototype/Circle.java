package com.ruppyrup.episode26.prototype;

public class Circle implements Shape {

    private Point origin;
    private double radius;
    public Circle(final Point origin, final double radius) {
        this.origin = origin;
        this.radius = radius;
    }

    public Circle(Circle circle) {
        this.radius = circle.radius;
        this.origin = circle.origin;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Circle(this);
    }

    @Override
    public double area() {
        return 3.14 * radius * radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
    }
}
