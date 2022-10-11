package com.ruppyrup.episode7.petstorearch.entities;

public class Dog extends Pet {

  public Dog(final String name) {
    super(name);
  }

  /**
   * Tell don't ask - not extracting petType and doing the comparison elsewhere
   * @param petType
   * @return
   */
  @Override
  public boolean isPetType(String petType) {
    return "Dog".equals(petType);
  }

  /**
   * Basic use of strategy design pattern, the exercise() method will be different for each pet
   * The commonality is covered by calling the superclass method
   */
  @Override
  public void exercise() {
    super.exercise();
    LOGGER.info("Dog called " + getPetName() + " is taken for a walk");
  }

  @Override
  public String toString() {
    return "Dog" + super.toString();
  }
}
