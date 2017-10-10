package ine.model;

import java.text.ParseException;
import java.util.ArrayList;

import ine.controller.*;
import ine.model.*;
import ine.view.*;

public class Main {

	/**
	 * @param args
	 * @throws ParseException 
	 * @throws Exception 
	 */
	public static void main(String[] args) throws ParseException {
		MainController mainCtrl = new MainController();
		mainCtrl.showMainScreen();

	}

}
