package ine.model;

public class StupidUserException extends NumberFormatException{
	/**
	 * 
	 */
	private String message;
	
	public StupidUserException() {
		this.message = "Sorry, an error has occurred. This is due to the fact" + 
					   "that you have typed alphabetic characters, symbols, or" + 
					   "nonprinting characters (such as spaces) in a field destined" + 
					   "for integers. Please try again by typing correctly. Thank you for understanding";
	}


}
