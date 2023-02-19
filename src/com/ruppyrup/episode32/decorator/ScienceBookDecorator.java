package com.ruppyrup.episode32.decorator;

public class ScienceBookDecorator extends BookDecorator {

    ScienceBookDecorator(Book book) {
        super(book);
    }

    @Override
    public String describe() {
        return ("Science " + book.describe());
    }
}

