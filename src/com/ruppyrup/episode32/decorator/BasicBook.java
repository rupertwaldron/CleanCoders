package com.ruppyrup.episode32.decorator;

public class BasicBook implements Book {
    @Override
    public String describe() {
        return "Book";
    }
}
