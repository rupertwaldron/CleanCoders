package com.ruppyrup.episode33.flyweight;

import java.text.DecimalFormat;

public class Movie {
  private static final DecimalFormat df = new DecimalFormat("0.00");
  private static final double RENTAL_PRICE = 2.99;
  private static int id = 0;
  private final int movieId;
  private final String title;

  public Movie(String title) {
    this.title = title;
    movieId = id++;
  }

  public void getRentalInfo() {
    System.out.println("Movie :: " + movieId + " -> " + title + "   £" + df.format(RENTAL_PRICE));
  }

  public double getRentalPrice() {
    return RENTAL_PRICE;
  }
}
