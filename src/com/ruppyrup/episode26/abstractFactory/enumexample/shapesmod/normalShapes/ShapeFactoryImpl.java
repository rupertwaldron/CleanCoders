package com.ruppyrup.episode26.abstractFactory.enumexample.shapesmod.normalShapes;


import com.ruppyrup.episode26.abstractFactory.enumexample.appmod.Shape;
import com.ruppyrup.episode26.abstractFactory.enumexample.appmod.ShapeFactory;
import com.ruppyrup.episode26.abstractFactory.enumexample.appmod.ShapeType;

public class ShapeFactoryImpl implements ShapeFactory {

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
        return new String[]{"circle", "square"};
    }


    protected Shape makeSquare(Point origin, double side) {
        return new Square(origin, side);
    }

    protected Shape makeCircle(Point origin, double radius) {
        return new Circle(origin, radius);
    }
}
