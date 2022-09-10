package com.ruppyrup.episode3.VideoStore.src;

/**
 * The rental should really provide the rental amount
 * Class has fields with getters which will ultimately be used to make decisions - violation of Tell Don't Ask Principle
 */
public class Rental
{
	public Rental (Movie movie, int daysRented) {
		this.movie 		= movie;
		this.daysRented = daysRented;
	}
	
	public int getDaysRented () {
		return daysRented;
	}
	
	public Movie getMovie () {
		return movie;
	}
	
	private Movie movie;
	private int daysRented;
}
