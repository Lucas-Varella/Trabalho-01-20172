package br.ufsc.ine5605.controller;


import br.ufsc.ine5605.controller.*;
import br.ufsc.ine5605.model.*;
import br.ufsc.ine5605.view.*;

public class MainScreenCtrl {
	private MainController mainCtrl;
	private MainScreen mainScreen;
	
	public MainScreenCtrl(MainController mainCtrl) {
		this.mainCtrl = mainCtrl;
		mainScreen = new MainScreen(this);
	}
	
	public void menu() {
		mainScreen.showMenu();
	}
	
	public void employeeMenu() {
		mainCtrl.showEmployeeMenu();
	}
	
	public void employmentMenu() {
		mainCtrl.showEmploymentMenu();
	}
	
	public void financialSectorMenu() {
		mainCtrl.showFinancialSectorMenu();
	}
	
	public int conversionStringToInt(String data) throws NumberFormatException {
		try {
			int num = Integer.parseInt(data);	
			return num;
		} catch(NumberFormatException e ) {
			throw new NumberFormatException();
		}
	}
}
