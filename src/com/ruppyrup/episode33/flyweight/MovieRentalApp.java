package com.ruppyrup.episode33.flyweight;

public class MovieRentalApp {

  public static void main(String[] args) {
    MovieFlyWeightFactory movieFlyWeightFactory = new MovieFlyWeightFactory();

    RentalStatement rentalStatementBob = new RentalStatement("Bob");
    RentalStatement rentalStatementTed = new RentalStatement("Ted");

    Rental rentalBob1 = new Rental(movieFlyWeightFactory);
    Rental rentalBob2 = new Rental(movieFlyWeightFactory);
    Rental rentalTed1 = new Rental(movieFlyWeightFactory);

    rentalStatementBob.addRental(rentalBob1);
    rentalStatementBob.addRental(rentalBob2);
    rentalStatementTed.addRental(rentalTed1);

    rentalBob1.rentMovie("Warriors");
    rentalBob1.rentMovie("Step up");
    rentalBob2.rentMovie("Warriors");
    rentalTed1.rentMovie("Warriors");
    rentalTed1.rentMovie("Weekend at Bernie's");

    rentalStatementBob.printStatement();
    rentalStatementTed.printStatement();
  }

}
