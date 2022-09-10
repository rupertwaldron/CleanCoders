package com.ruppyrup.episode3.VideoStore.src;

import java.util.Vector;
import java.util.Enumeration;

/**
 * The Customer is calculating all the amounts and then creating the statement i.e. doing too much
 */
public class Customer 
{
	public Customer (String name) {
		this.name = name;
	}
	
	public void addRental (Rental rental) {
		rentals.addElement (rental);
	}
	
	public String getName () {
		return name;
	}
	
	public String statement () {
		double 				totalAmount 			= 0;
		int					frequentRenterPoints 	= 0;
		Enumeration 		rentals 				= this.rentals.elements ();
		String 				result 					= "Rental Record for " + getName () + "\n";
		
		while (rentals.hasMoreElements ()) {
			double 		thisAmount = 0;
			Rental 		each = (Rental)rentals.nextElement ();

			/**
			 * The switch statement extracts data and then performs an action, violating Tell Don't Ask
			 * The movie should know how much it costs and the rental knows how many days it was rented for
			 */

			// determines the amount for each line
			switch (each.getMovie ().getPriceCode ()) {
				case Movie.REGULAR:
					thisAmount += 2;
					if (each.getDaysRented () > 2)
						thisAmount += (each.getDaysRented () - 2) * 1.5;
					break;
				case Movie.NEW_RELEASE:
					thisAmount += each.getDaysRented () * 3;
					break;
				case Movie.CHILDRENS:
					thisAmount += 1.5;
					if (each.getDaysRented () > 3)
						thisAmount += (each.getDaysRented () - 3) * 1.5;
					break;
			}
			
			frequentRenterPoints++;
			
			if (each.getMovie ().getPriceCode () == Movie.NEW_RELEASE 
					&& each.getDaysRented () > 1)
				frequentRenterPoints++;
				
			result += "\t" + each.getMovie ().getTitle () + "\t"
								+ String.valueOf (thisAmount) + "\n";
			totalAmount += thisAmount;
				
		}
		
		result += "You owed " + String.valueOf (totalAmount) + "\n";
		result += "You earned " + String.valueOf (frequentRenterPoints) + " frequent renter points\n";
		
		
		return result;
	}
	

	private String name;
	private Vector rentals = new Vector ();
}
