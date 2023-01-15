package com.ruppyrup.episode26.prototype;


public class Square implements Shape {
    private double side;
    private Point topLeft;

    public Square(final Point topLeft, final double side) {
        this.side = side;
        this.topLeft = topLeft;
    }

    public Square(Square square) {
        this.topLeft = square.topLeft;
        this.side = square.side;
    }

    @Override
    public double area() {
        return side * side;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Square(this);
    }

    public void setSide(double side) {
        this.side = side;
    }

    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }
}
