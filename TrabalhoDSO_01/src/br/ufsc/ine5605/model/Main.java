package br.ufsc.ine5605.model;

import br.ufsc.ine5605.view.MainScreenI;
import br.ufsc.ine5605.controller.MainController;

/**
 * Classe que contem o metodo public static void main(String[] args);
 * @author Sadi Junior Domingos Jacinto;
 *
 */
public class Main {

	/**
	 * Method responsible for starting the program.;
	 * @param args
	 */
	public static void main(String[] args) {
		MainController mainCtrl = new MainController();
		mainCtrl.showMainScreen();

	}

}
