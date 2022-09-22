package com.ruppyrup.episode5.datastructures;

public class Square extends Shape{
    double sideLength;

    public Square(final double sideLength) {
        this.sideLength = sideLength;
        typeOfShape = "square";
    }
}
