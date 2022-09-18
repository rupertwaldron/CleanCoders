package com.ruppyrup.episode2;

public class Months {

    private static String[] DATE_FORMAT_SYMBOLS_SHORT = new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private static String[] DATE_FORMAT_SYMBOLS = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};


    public static String[] getMonths(final boolean shortened) {
        if (shortened) {
            return DATE_FORMAT_SYMBOLS_SHORT;
        } else {
            return DATE_FORMAT_SYMBOLS;
        }
    }

    public static String[] getMonthNamesShortened() {
        return DATE_FORMAT_SYMBOLS_SHORT;
    }

    public static String[] getMonthNames() {
        return DATE_FORMAT_SYMBOLS;
    }
}
