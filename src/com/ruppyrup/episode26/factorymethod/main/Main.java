package com.ruppyrup.episode26.factorymethod.main;


import com.ruppyrup.episode26.factorymethod.app.ShapeApp;
import com.ruppyrup.episode26.factorymethod.concrete.AppImpl;

public class Main {
    public static void main(String[] args) {
        System.out.println("Generating shapes...");
        ShapeApp shapeApp = new AppImpl();
        shapeApp.run();
    }
}


