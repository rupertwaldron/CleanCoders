package com.ruppyrup.episode26.abstractFactory.enumexample.appmod;


public interface ShapeFactory {
    Shape make(ShapeType shapeName);
    String[] getShapeNames();
}
