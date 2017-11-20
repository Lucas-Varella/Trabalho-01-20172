package br.ufsc.ine5605.view;

public class IncorrectRegistrationException extends Exception {
	public IncorrectRegistrationException() {
		super("Incorrect Registration number! \n Please Check your info!");
	}
}
