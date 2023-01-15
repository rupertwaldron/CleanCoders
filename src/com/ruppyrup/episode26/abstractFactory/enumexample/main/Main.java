package com.ruppyrup.episode26.abstractFactory.enumexample.main;

import com.ruppyrup.episode26.abstractFactory.enumexample.appmod.ShapeApp;
import com.ruppyrup.episode26.abstractFactory.enumexample.appmod.ShapeFactory;
import com.ruppyrup.episode26.abstractFactory.enumexample.shapesmod.normalShapes.ShapeFactoryImpl;
import com.ruppyrup.episode26.abstractFactory.enumexample.shapesmod.weirdShapes.WeirdShapeFactoryImpl;

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


