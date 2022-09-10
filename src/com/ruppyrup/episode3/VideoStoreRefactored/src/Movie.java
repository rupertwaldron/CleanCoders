package com.ruppyrup.episode3.VideoStoreRefactored.src;

/**
 * The Movie class contains much of the functionality required for the rental store.
 * The same movie instance can be passed used in multiple rentals - the movie can be considered a flyweight
 */
public abstract class Movie {
  private String title;

  public Movie(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public abstract double determineAmount(int daysRented);

  public abstract int determinePoints(int daysRented);
}
