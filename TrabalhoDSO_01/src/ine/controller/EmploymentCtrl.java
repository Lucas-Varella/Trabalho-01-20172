package ine.controller;

import ine.controller.*;
import ine.model.*;
import ine.view.*;

import ine.model.Employment;
import ine.model.Privileges;
import ine.view.EmploymentScreen;

import java.util.ArrayList;




public class EmploymentCtrl {
	private MainController mainCtrl;
	private EmploymentScreen employmentScreen;
	private Employment employment;
	private ArrayList<Employment> employments;

	public EmploymentCtrl(MainController mainCtrl) {
		this.mainCtrl = mainCtrl;
		this.employmentScreen = new EmploymentScreen(this);
		this.employments = new ArrayList();
		}

	public void menu() throws Exception {
		employmentScreen.employmentMenu();
	}
	public void mainMenu() throws Exception {
		mainCtrl.showMainScreen();
	}
	
	public void addEmployment(int code, String name, Privileges option) {
		employments.add(new Employment(code, name, option, this));
	}
	
	public void delEmployment(Employment employment) {
		
	}
	
	public void editEmployment() {
		employment.getCodigo();
		employment.getName();
		employment.getPrivilege();
		
		
	}
	
	public void findEmployeeByEmployment(Employment employment) {
		
	}
	
	public ArrayList<Employment> listEmployments() {
		return employments;
	}

}
