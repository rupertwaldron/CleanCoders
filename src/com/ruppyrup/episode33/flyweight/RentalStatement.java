package com.ruppyrup.episode33.flyweight;

import java.util.ArrayList;
import java.util.List;

public class RentalStatement {
  private final List<Rental> rentals = new ArrayList<>();
  private String name;

  public RentalStatement(String name) {
    this.name = name;
  }

  public void addRental(Rental rental) {
    rentals.add(rental);
  }

  public void printStatement() {
    System.out.println("Rental for " + name);
    rentals.forEach(Rental::printStatement);
    System.out.println("---------------------");
  }
}
