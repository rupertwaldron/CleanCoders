package com.ruppyrup.episode34.iterator.basic;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class TestGridIterator {
    public static void main(String[] args) {
        int[][] myGrid = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        GridIterator gridIterator = new GridIterator(myGrid);
        Iterator<Integer> iterator = gridIterator.iterator();

        while (iterator.hasNext()) {
            System.out.println("Iteration = " + iterator.next());
        }
    }
}

class GridIterator implements Iterable<Integer> {

    private final int[][] grid;
    private final int side;
    private int i = 0;
    private int j = -1;
    private int value = 0;


    private int getI() {
        i = value++ % side;
        System.out.println(" I = " + i);
        return i;
    }

    private int getJ() {
        if (i == 0) j++;
        System.out.println(" J = " + j);
        return j;
    }
    public GridIterator(int[][] grid) {
        this.grid = grid;
        this.side = grid[0].length;
    }

    @NotNull
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {

            @Override
            public boolean hasNext() {
                return value <= 8;
            }

            @Override
            public Integer next() {
                return grid[getI()][getJ()];
            }
        };
    }
}
