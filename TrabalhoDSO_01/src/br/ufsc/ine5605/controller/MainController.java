package br.ufsc.ine5605.controller;

import java.util.ArrayList;  
import java.util.Date;

import br.ufsc.ine5605.model.Reasons;
import br.ufsc.ine5605.model.Privileges;
import br.ufsc.ine5605.model.Horary;
import br.ufsc.ine5605.model.Employment;
import br.ufsc.ine5605.model.Employee;

public class MainController {
	private MainScreenCtrl mainScreenCtrl;
	private EmployeeCtrl employeeCtrl;
	private EmploymentCtrl employmentCtrl;
	private FinancialSectorCtrl financialSectorCtrl;
	private AccessCtrl accessCtrl;
	private HoraryCtrl horaryCtrl;
	
	public MainController() {
		mainScreenCtrl = new MainScreenCtrl(this);
		employeeCtrl = new EmployeeCtrl(this);
		employmentCtrl = new EmploymentCtrl(this);
		financialSectorCtrl = new FinancialSectorCtrl(this);
		accessCtrl = new AccessCtrl(this);
		horaryCtrl = new HoraryCtrl(this);
	}
	
	public void showMainScreen() {
		mainScreenCtrl.menu();
	}
	
	public void showEmployeeMenu() {
		employeeCtrl.menu();
	}
	
	public void showEmploymentMenu() {
		employmentCtrl.menu();
	}
	
	public void showFinancialSectorMenu() {
		financialSectorCtrl.menu();
	}
	
	public void showHoraryMenu() {
		horaryCtrl.menuAdd();
	}
	
	public ArrayList<Employment> listEmployments() {
		return employmentCtrl.listEmployments();
	}
	
	public Employment findEmploymentByIndex(int index) throws NullPointerException {
		try {
			return employmentCtrl.getEmployment(index);
		} catch(NullPointerException e) {
			throw e;
		}
			
	}
	
	public boolean validNumRegistration(int numRegistration) {
		return employeeCtrl.validNumRegistration(numRegistration);

	}
	
	public Horary addHorary() {
		return horaryCtrl.menuAdd();
	}

	public Horary editHorary(Horary horary) {
		return horaryCtrl.editHorary(horary);
		
	}

	public void delEmployee(Employee employee) {
		employeeCtrl.delEmployee(employee);
		
	}

	public void addAccess(int numRegistration, Date date, Date hour, Reasons reason) {
		accessCtrl.addAccess(numRegistration, date, hour, reason);
	}

	public boolean isAccessBloqued(int numRegistration) {
		return accessCtrl.isAccessBloqued(numRegistration);
		
	}

	public void showAllDeniedAccess() {
		accessCtrl.listAllDeniedAccess();
	}

	public void showDeniedAccessByNumRegistration(int numRegistration) {
		accessCtrl.listDeniedAccessByNumRegistration(numRegistration);
		
	}

	public void showDeniedAccessByReason(Reasons reason) {
		accessCtrl.listDeniedAccessByReason(reason);		
	}

	public Employment getEmploymentByNumRegistration(int numRegistration) {
		return employmentCtrl.getEmploymentByNumRegistration(numRegistration);
	}

	public Privileges getPrivilegeByNumRegistration(int numRegistration) {
		return employeeCtrl.getPrivilegeByNumRegistration(numRegistration);
	}

	public ArrayList<Horary> getHoraryAccess(int numRegistration) {
		return employeeCtrl.getHoraryAccess(numRegistration);
	}

	public Reasons getReasonBy(int num, Date hourAccess, Date dateAccess) {
		return accessCtrl.getReasonsBy(num, hourAccess, dateAccess);
		
	}
	
}
