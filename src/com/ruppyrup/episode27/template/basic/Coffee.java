package com.ruppyrup.episode27.template.basic;

public class Coffee extends DrinkMaker {
    @Override
    protected void serveDrink() {
        System.out.println("Serving Coffee");
    }

    @Override
    protected void brewDrink() {
        System.out.println("Brewing Coffee");
    }

    @Override
    protected void putIngredientsInCup() {
        System.out.println("Grinding coffee");
    }
}
