package com.ruppyrup.episode26.abstractFactory.stringexample.enumexample.main;

import com.ruppyrup.episode26.abstractFactory.stringexample.enumexample.appmod.ShapeApp;
import com.ruppyrup.episode26.abstractFactory.stringexample.enumexample.appmod.ShapeFactory;
import com.ruppyrup.episode26.abstractFactory.stringexample.enumexample.shapesmod.normalShapes.ShapeFactoryImpl;
import com.ruppyrup.episode26.abstractFactory.stringexample.enumexample.shapesmod.weirdShapes.WeirdShapeFactoryImpl;

public class Main {
    public static void main(String[] args) {
        System.out.println("Generating shapes...");
        ShapeFactory shapeFactory = new ShapeFactoryImpl();
        ShapeApp shapeApp = new ShapeApp(shapeFactory);
        shapeApp.run();

        System.out.println("Generating weird shapes...");
        ShapeFactory weirdShapeFactory = new WeirdShapeFactoryImpl();
        shapeApp = new ShapeApp(weirdShapeFactory);
        shapeApp.run();
    }
}


