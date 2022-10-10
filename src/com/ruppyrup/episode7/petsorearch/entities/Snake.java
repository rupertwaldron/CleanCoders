package com.ruppyrup.episode7.petsorearch.entities;

public class Snake extends Pet {

  public Snake(final String name) {
    super(name);
  }

  @Override
  public boolean isPetType(String petType) {
    return "Snake".equals(petType);
  }

  @Override
  public void exercise() {
    super.exercise();
    LOGGER.info("Snake called " + getPetName() + " is let out in the shop");
  }
}
