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

  @Override
  public String toString() {
    return "Dog" + super.toString();
  }
}
