package com.ruppyrup.episode3.VideoStoreRefactored.src;

public class Rental {
  private Movie movie;
  private int daysRented;

  public Rental(Movie movie, int daysRented) {
    this.movie = movie;
    this.daysRented = daysRented;
  }

  public String getTitle() {
    return movie.getTitle();
  }

  public double determineAmount() {
    return movie.determineAmount(daysRented);
  }

  public int determinePoints() {
    return movie.determinePoints(daysRented);
  }
}
