package br.ufsc.ine5605.model;

import br.ufsc.ine5605.controller.MainController;

/**
 * Classe que contem o método public static void main(String[] args);
 * @author Sadi Júnior Domingos Jacinto;
 *
 */
public class Main {

	/**
	 * Método responsável por iniciar o programa;
	 * @param args
	 */
	public static void main(String[] args) {
		MainController mainCtrl = new MainController();
		mainCtrl.showMainScreen();

	}

}
