package com.ruppyrup.episode2;

public class Months {

    private static String[] DATE_FORMAT_SYMBOLS_SHORT = new String[] {"Jan", "Feb", "etc"};
    private static String[] DATE_FORMAT_SYMBOLS = new String[] {"January", "February", "etc"};


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
