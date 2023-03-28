package com.ruppyrup.episode34.iterator.loop.solution;

public class Integers implements RRIterator<Integer> {
    private int value;
    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        System.out.println("Sending next value -> " + value);
        return value++;
    }
}
