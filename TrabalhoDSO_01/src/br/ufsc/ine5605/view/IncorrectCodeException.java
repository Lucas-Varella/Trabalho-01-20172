package br.ufsc.ine5605.view;

public class IncorrectCodeException extends Exception {
	public IncorrectCodeException() {
		super("Incorrect Employee Code! \n Please Check info.");
	}
}
