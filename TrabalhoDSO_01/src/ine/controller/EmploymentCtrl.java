package ine.controller;

import ine.controller.*;
import ine.model.*;
import ine.view.*;

import ine.model.Employment;
import ine.model.Horary;
import ine.model.Privileges;
import ine.view.EmploymentScreen;

import java.util.ArrayList;




public class EmploymentCtrl {
	private MainController mainCtrl;
	private EmploymentScreen employmentScreen;
	private Employment employment;
	private EmploymentRestrictAccess employmentRestrictAccess;
	private ArrayList<Employment> employments;
	private static int code = 999;
	
	public EmploymentCtrl(MainController mainCtrl) {
		this.mainCtrl = mainCtrl;
		this.employmentScreen = new EmploymentScreen(this);
		this.employments = new ArrayList();
		}

	public void mainMenu() {
		mainCtrl.showMainScreen();
	}
	
	public void menu() {
		employmentScreen.menu();
	}
	
	
	public void addEmployment(String name, Privileges option) {
		Employment employment = new Employment(getCode(), name, option, this);
		employments.add(employment);
		setCode(getCode() + 1 );
	}
	
	public EmploymentRestrictAccess addEmploymentRestrictAccess(String name, Privileges option) {
		EmploymentRestrictAccess em = new EmploymentRestrictAccess(getCode(), name, option, this);
		employments.add(em);
		setCode(getCode() + 1 );
		return em;
	}
	
	public void delEmployment(Employment employment) {
		
	}
	
	public ArrayList<Employment> listEmployments() {
		return employments;
	}
	
	public Horary addHorary() {
		return mainCtrl.addHorary();
	}
	
	public void listEmployees(Employment employment) {
		employment.listEmployees(employment);
		
	}
	
	public Employment getEmployment(int num) throws NullPointerException {
		try {
			return employments.get(num);
		} catch(NullPointerException e) {
			throw new NullPointerException();
		}		
	}

	public static int getCode() {
		return code;
	}

	public static void setCode(int code) {
		EmploymentCtrl.code = code;
	}

	public void setHorary(EmploymentRestrictAccess e, Horary horary) {
		e.addHorary(horary);
	}

	public ArrayList<Horary> getHorarys(EmploymentRestrictAccess e)  {
		return e.getHorarys();
	}

	public Horary editHorary(Horary horary) {
		return mainCtrl.editHorary(horary);
		
	} 

}
