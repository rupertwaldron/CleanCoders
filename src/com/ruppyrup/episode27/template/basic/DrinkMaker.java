package com.ruppyrup.episode27.template.basic;

public abstract class DrinkMaker {
    public final void makeDrink() {
        putIngredientsInCup();
        brewDrink();
        serveDrink();
    }

    protected abstract void serveDrink();

    protected abstract void brewDrink();

    protected abstract void putIngredientsInCup();
}

