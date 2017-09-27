package ine.model;

public class StupidUserException extends Exception {
	private String message;
	
	
	public StupidUserException() {
		this.message = "Sorry, an error has occurred. This is due to the fact \n" + 
					   "that you have typed alphabetic characters, symbols, or \n" + 
					   "nonprinting characters (such as spaces) in a field destined \n" + 
					   "for integers.\nPlease try again by typing correctly.\nThank you for understanding";
	}
	
	public StupidUserException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
