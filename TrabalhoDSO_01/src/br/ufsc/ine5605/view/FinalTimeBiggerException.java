package br.ufsc.ine5605.view;

public class FinalTimeBiggerException extends Exception {
	public FinalTimeBiggerException() {
		super("Ending hour cannot be greater than starting. \\n Please create two Access times, splitting at Midnight.");
		
	}
}
