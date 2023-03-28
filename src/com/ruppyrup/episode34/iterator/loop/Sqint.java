package com.ruppyrup.episode34.iterator.loop;

public class Sqint {
    public static void main(String[] args) {
        for (int i = 0; i < 25; i++) { // the loop dominates the algorithm and determins the order of the numbers
            System.out.println(i * i); // calculation is hidden within the loop
        }
    }
}
