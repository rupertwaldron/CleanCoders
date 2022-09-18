package com.ruppyrup.episode4;

import com.ruppyrup.episode2.Months;

public class EarlyReturns {

    public static void main(String[] args) {
        EarlyReturns er = new EarlyReturns();

        int age10YearsAgo = er.calculateAge10YearsAgo(5);
        int monthNumber = er.getMonthNumber("Jul");

        System.out.println("Age 10 years ago = " + age10YearsAgo);
        System.out.println("Month number = " + monthNumber);

        Integer safeMonthNumber = er.safeMonthNumberFetcher("Monkey");
        System.out.println("Safe Month number = " + safeMonthNumber );
    }

    private int calculateAge10YearsAgo(int currentAge) {
        if (currentAge < 10)
            return 0; // early returns are fine when they are easy to read

        return currentAge - 10;
    }

    private Integer safeMonthNumberFetcher(String month) {
        try {
            return getMonthNumber(month);
        } catch (InvalidMonthNameException ivmne){
            System.out.println("Invalid month name");
            return null;
        }
    }

    private int getMonthNumber(String month) {
        int result = 0;
        for (int i = 0; i < Months.getMonthNames().length; i++) {
            if (month.equals(Months.getMonthNamesShortened()[i])) {
                result = i + 1;
                break;
            }
            if (month.equals(Months.getMonthNames()[i])) {
                result = i + 1;
                break;
            }

        }

        if (result == 0)
            throw new InvalidMonthNameException();

        return result;
    }

    public class InvalidMonthNameException extends RuntimeException {}


}
