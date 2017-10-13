package br.ufsc.ine5605.controller;
 



import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;

import br.ufsc.ine5605.model.*;
import br.ufsc.ine5605.view.EmploymentScreen;




public class EmploymentCtrl {
	private MainController mainCtrl;
	private EmploymentScreen employmentScreen;
	private Employment employment;
	private EmploymentRestrictAccess employmentRestrictAccess;
	private ArrayList<Employment> employments;
	private static int code = 1000;
	
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
		for(Contract c : employment.getEmployees()) {
			c.getEmployee().delContract();
			mainCtrl.delEmployee(c.getEmployee());
		}
		employments.remove(employment);
	}
	
	public ArrayList<Employment> listEmployments() {
		return employments;
	}
	
	public void listAllEmployments() {
		employmentScreen.listEmployments();
	}
	
	public Horary addHorary() {
		return mainCtrl.addHorary();
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

	public Employment addEmployment(int code2, String name, Privileges privilege) {
		Employment e = new Employment(code2, name, privilege, this);
		employments.add(e);
		return e;
	}

	public EmploymentRestrictAccess addEmploymentRestrictAccess(int code2,
			String name, Privileges privilege) {
		EmploymentRestrictAccess e = new EmploymentRestrictAccess(code, name, privilege, this);
		employments.add(e);
		return e;
	}

	public Employment getEmploymentByNumRegistration(int numRegistration) {
		Employment e = null;
		for(Employment en : employments) {
			for(Contract c : en.getEmployees()) {
				if(c.getEmployee().getNumRegistration() == numRegistration) {
					e = en;
					return e;
				}
			}
				
		}
		return null;
	}
	
	public int conversionStringToInt(String data) throws NumberFormatException {
		try {
			int num = Integer.parseInt(data);	
			return num;
		} catch(NumberFormatException e ) {
			throw new NumberFormatException();
		}
	}

	public double conversionStringToDouble(String data)
			throws NumberFormatException {
		try {
			double num = Double.parseDouble(data);	
			return num;
		} catch(NumberFormatException e ) {
			throw new NumberFormatException();
		}
	}

	public Date formatStringToDate(String data) {
		// TODO Auto-generated method stub
		return null;
	}

	public Date strToDate(String data) throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}

	public Date strToDateHour(String data) throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}
}
