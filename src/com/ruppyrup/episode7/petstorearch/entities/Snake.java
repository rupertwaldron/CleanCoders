package com.ruppyrup.episode7.petstorearch.entities;

public class Snake extends Pet {

  public Snake(final String name) {
    super(name);
  }

  @Override
  public boolean isPetType(String petType) {
    return "Snake".equals(petType);
  }

  @Override
  public String toString() {
    return "Snake" + super.toString();
  }
}
