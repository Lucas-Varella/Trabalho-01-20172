package ine.controller;

import ine.controller.*;
import ine.model.*;
import ine.view.*;

public class FinancialSectorCtrl {
	private MainController mainCtrl;
	private FinancialSectorScreen financialSectorScreen;
	private FinancialSector financialSector;
	

	public FinancialSectorCtrl(MainController mainController) {
		this.mainCtrl = mainCtrl;
		this.financialSectorScreen = new FinancialSectorScreen(this);
		this.financialSector = new FinancialSector(this);
	}
	
	public void mainMenu() {
		mainCtrl.showMainScreen();
	}
	
	public void menu() {
		financialSectorScreen.menuFinancialSector();
	}
	
	public boolean validAccess(int numRegistration) {
		return false;
	}
	
	

}
