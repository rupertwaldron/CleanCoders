package com.ruppyrup.episode27.template.basic;

public class Tea extends DrinkMaker {
    @Override
    protected void serveDrink() {
        System.out.println("Serving tea in tea cup");
    }

    @Override
    protected void brewDrink() {
        System.out.println("Brewing in tea pot");
    }

    @Override
    protected void putIngredientsInCup() {
        System.out.println("Adding tea bag");
    }
}
