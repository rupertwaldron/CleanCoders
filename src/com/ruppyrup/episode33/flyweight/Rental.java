package com.ruppyrup.episode33.flyweight;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Rental {
  private static final DecimalFormat df = new DecimalFormat("0.00");

  private final MovieFlyWeightFactory movieFlyWeightFactory;

  public Rental(MovieFlyWeightFactory movieFlyWeightFactory) {
    this.movieFlyWeightFactory = movieFlyWeightFactory;
  }

  private final List<Movie> movies = new ArrayList<>();

  public void rentMovie(String movieTitle) {
    Movie movie = movieFlyWeightFactory.getMovie(movieTitle);
    movies.add(movie);
  }

  public void printStatement() {
    movies.forEach(Movie::getRentalInfo);
    System.out.println("Total Cost = £" + df.format(getRentalPrice()));
  }

  private double getRentalPrice() {
    return movies.stream()
        .mapToDouble(Movie::getRentalPrice)
        .sum();
  }
}
