package com.ruppyrup.episode32.decorator;

public class BookDescription {
    public static void main(String[] args) {
        BasicBook book = new BasicBook();

        FictionBookDecorator fictionBook = new FictionBookDecorator(book);

        Book mixedBook = new ScienceBookDecorator(fictionBook);

        System.out.println(mixedBook.describe());

        Book lambdaBook = () -> "Lambda " + mixedBook.describe();

        System.out.println(lambdaBook.describe());
    }
}
