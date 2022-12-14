package com.ruppyrup.episode3.primeGenerator.src.literateProgramming;


public class PrimePrinter {
    private static int numberOfPrimes = 1000;

    public static void main(String[] args) {
        var primeHelper = new PrimeGenerator();
        int[] primes = primeHelper.generate(numberOfPrimes);
        var primePrinter = new PrintNumbers(50, 4);
        primePrinter.printNumbers(primes, numberOfPrimes);
    }
}

class PrimeGenerator {
    private int[] primes;
    private static final int ORDMAX = 30;
    private int primeIndex = 1;
    private int ord = 2;
    private int square = 9;
    private int n = 0;
    private final int[] multiples = new int[ORDMAX + 1];

    public int[] generate(int numberOfPrimes) {

        int candidate = 1;
        primes = new int[numberOfPrimes + 1];
        primes[1] = 2;

        while (primeIndex < numberOfPrimes) {
            do {
                candidate += 2;
                checkCandidateIsSquare(candidate);
            } while (!iSCandidateIsPrime(candidate));
            primeIndex++;
            primes[primeIndex] = candidate;
        }
        return primes;
    }

    private boolean iSCandidateIsPrime(final int candidate) {
        n = 2;
        boolean possiblyPrime = true;
        while (n < ord && possiblyPrime) {
            while (multiples[n] < candidate)
                multiples[n] += primes[n] + primes[n];
            if (multiples[n] == candidate)
                possiblyPrime = false;
            n++;
        }
        return possiblyPrime;
    }

    private void checkCandidateIsSquare(int candidate) {
        if (candidate == square) {
            ord++;
            square = primes[ord] * primes[ord];
            multiples[ord - 1] = candidate;
        }
    }
}

class PrintNumbers {
    private int pagenumber = 1;
    private int pageoffset = 1;
    private final int linesPerPage;
    private final int columns;

    PrintNumbers(final int linesPerPage, final int columns) {
        this.linesPerPage = linesPerPage;
        this.columns = columns;
    }

    public void printNumbers(final int[] numbers, final int noOfPrimes) {
        while (pageoffset <= noOfPrimes) {
            System.out.print("The First ");
            System.out.print(noOfPrimes);
            System.out.print(" Prime Numbers --- Page ");
            System.out.print(pagenumber);
            System.out.println("\n");
            for (int rowoffset = pageoffset; rowoffset <= pageoffset + linesPerPage - 1; rowoffset++) {
                for (int column = 0; column <= columns - 1; column++)
                    if (rowoffset + column * linesPerPage <= noOfPrimes)
                        System.out.printf("%10d", numbers[rowoffset + column * linesPerPage]);
                System.out.println();
            }
            System.out.println("\f");
            pagenumber++;
            pageoffset += linesPerPage * columns;
        }
    }
}

