package com.ruppyrup.episode26.abstractFactory.enumexample.shapesmod.weirdShapes;


import com.ruppyrup.episode26.abstractFactory.enumexample.appmod.Shape;
import com.ruppyrup.episode26.abstractFactory.enumexample.appmod.ShapeFactory;
import com.ruppyrup.episode26.abstractFactory.enumexample.appmod.ShapeType;

public class WeirdShapeFactoryImpl implements ShapeFactory {

    private static final Point point = new Point(1.0, 1.0);

    public Shape make(ShapeType shapeName) {
        return switch(shapeName) {
            case CIRCLE -> makeCircle(point, 3.0);
            case SQUARE -> makeSquare(point, 3.0);
            default -> throw new IllegalStateException("Unexpected value: " + shapeName);
        };
    }

    @Override
    public String[] getShapeNames() {
        return new String[]{"weirdCircle", "Weirdsquare"};
    }


    protected Shape makeSquare(Point origin, double side) {
        return new WeirdSquare(origin, side);
    }

    protected Shape makeCircle(Point origin, double radius) {
        return new WeirdCircle(origin, radius);
    }
}
