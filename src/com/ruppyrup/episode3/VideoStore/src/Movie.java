package com.ruppyrup.episode3.VideoStore.src;

/**
 * The Movie has several types determined by the price code with are static variables in the Movie class.
 * This price code is then retreived in a switch statement to perform actions - violation of Tell Don't Ask
 * The use of a switch statement and a priceCode being unique to a move should signal that polymorphism could be an option
 */
public class Movie
{
	public static final int CHILDRENS	= 2;
	public static final int REGULAR 	= 0;
	public static final int NEW_RELEASE = 1;
	
	private String title;
	private int priceCode;
	
	public Movie (String title, int priceCode) {
		this.title 		= title;
		this.priceCode 	= priceCode;
	}
	
	public int getPriceCode () {
		return priceCode;
	}
	
	public void setPriceCode (int code) {
		priceCode = code;
	}
	
	public String getTitle () {
		return title;
	}
	
}
