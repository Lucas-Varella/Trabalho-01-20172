package ine.model;

public class StupidUserException extends Exception {
	private String message;
	
	
	public StupidUserException() {
		this.message = "Sorry, an error has occurred. This is due to the fact" + 
					   "that you have typed alphabetic characters, symbols, or" + 
					   "nonprinting characters (such as spaces) in a field destined" + 
					   "for integers. Please try again by typing correctly. Thank you for understanding";
	}
	
	public StupidUserException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
