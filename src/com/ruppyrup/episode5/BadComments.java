package com.ruppyrup.episode5;

/* Added by Rick */
public class BadComments {

    //*********************
    // Instance Variables
    //*********************

    /** The name */
    private String name;

    // Returns x + y or, if x or y is less than zero, throws an exception

    /**
     * Default constructor
     */
    public BadComments() {
    }

    /** sets port on which application should run */
    public void setPort(int port) {

    }
    /**
     * Adds two integers together
     * @param x
     * @param y
     * @return the sum of two integers
     */
    public int Add(int x, int y) {

        /* x is incremented by one
it is called pre-increment
first increase the value of x
by 1 and then use it
*/
        x++;

        /*
set the value of the age integer to 32
*/
        int age = 32;
        return x + y;


    }

    /**
     Dear maintainer:

     Once you are done trying to 'optimize' this routine,
     and have realized what a terrible mistake that was,
     please increment the following counter as a warning
     to the next guy:

     total_hours_wasted_here = 42
     **/
    public int solve(int... ints) {
        return 1;
    }
}
