package com.ruppyrup.episode26.abstractFactory.stringexample.enumexample.appmod;


public interface ShapeFactory {
    Shape make(String shapeType);
    String[] getShapeNames();
}
