package com.ruppyrup.episode33.flyweight;

import java.util.HashMap;
import java.util.Map;

public class MovieFlyWeightFactory {
  private final Map<String, Movie> movies = new HashMap<>();

  public Movie getMovie(final String title) {
    if (movies.containsKey(title))
      return movies.get(title);

    movies.put(title, new Movie(title));
    return movies.get(title);
  }
}
