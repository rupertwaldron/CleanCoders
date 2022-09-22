package com.ruppyrup.episode5;

import java.math.BigInteger;

public class Formatting {

    private boolean isPalindrome(BigInteger num) {
        String digits = num.toString();
        int lastIndex = digits.length() - 1;

        for (int i = 0; i < digits.length(); i++) {
            if (digits.charAt(i) != digits.charAt(lastIndex - i))
                return false;
        }

        return true;
    }
}
