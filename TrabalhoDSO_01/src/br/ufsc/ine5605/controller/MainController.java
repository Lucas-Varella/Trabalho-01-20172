package br.ufsc.ine5605.controller;

import java.util.ArrayList;  
import java.util.Date;

import br.ufsc.ine5605.model.Reasons;
import br.ufsc.ine5605.model.Privileges;
import br.ufsc.ine5605.model.Horary;
import br.ufsc.ine5605.model.Employment;
import br.ufsc.ine5605.model.Employee;

/**
 * Classe responsável por gerenciar a comunicação entre os demais controladores; 
 * @author Sadi Júnior Domingos Jacinto;
 *
 */
public class MainController {
	private static final MainController instance = new MainController();
	
	/**
	 * Construtor padrão da classe, no qual todos os outros controladores do sistema são instânciados,
	 * passando uma instância desta classe como parâmetro; 
	 */
	public MainController() {
		
	}
	
	public static MainController getInstance() {
		return instance;
	}
	
	public ArrayList<Employment> getEmployments() {
		return EmploymentCtrl.getInstance().getEmployments();
	}

	public void showMainScreen() {
		MainScreenCtrl.getInstance().menu();
	}
	
	public void showEmployeeMenu() {
		EmployeeCtrl.getInstance().menu();
	}
	
	public void showEmploymentMenu() {
		EmploymentCtrl.getInstance().menu();
	}
	
	public void showFinancialSectorMenu() {
		FinancialSectorCtrl.getInstance().menu();
	}
	@Deprecated
	public void showHoraryMenu() {
		HoraryCtrl.getInstance().menuAdd();
	}
	
	public void listEmployments() {
		EmploymentCtrl.getInstance().listAllEmployments();
	}
	
	public Employment findEmploymentByIndex(int index) throws NullPointerException {
		try {
			return EmploymentCtrl.getInstance().getEmployment(index);
		} catch(NullPointerException e) {
			throw e;
		}
			
	}
	
	
	public boolean validNumRegistration(int numRegistration) {
		return EmployeeCtrl.getInstance().validNumRegistration(numRegistration);

	}
	public void addHorary() {
		HoraryCtrl.getInstance().menuAdd();
	}
/*
	public void editHorary(Horary horary) {
		HoraryCtrl.getInstance().editHorary(horary);
		
	}
*/
	public void delEmployee(Employee employee) {
		EmployeeCtrl.getInstance().delEmployee(employee);
		
	}

	public void addAccess(int numRegistration, Date date, Date hour, Reasons reason) {
		AccessCtrl.getInstance().addAccess(numRegistration, date, hour, reason);
	}

	public boolean isAccessBloqued(int numRegistration) {
		return AccessCtrl.getInstance().isAccessBloqued(numRegistration);
		
	}

	public void showAllDeniedAccess() {
		AccessCtrl.getInstance().listAllDeniedAccess();
	}

	public void showDeniedAccessByNumRegistration(int numRegistration) {
		AccessCtrl.getInstance().listDeniedAccessByNumRegistration(numRegistration);
		
	}

	public void showDeniedAccessByReason(Reasons reason) {
		AccessCtrl.getInstance().listDeniedAccessByReason(reason);	
	}
	
	

	public Privileges getPrivilegeByNumRegistration(int numRegistration) {
		return EmployeeCtrl.getInstance().getPrivilegeByNumRegistration(numRegistration);
	}

	public ArrayList<Horary> getHoraryAccess(int numRegistration) {
		return EmployeeCtrl.getInstance().getHoraryAccess(numRegistration);
	}

	public Reasons getReasonBy() {
		return AccessCtrl.getInstance().getReasonsBy();
		
	}
	
}
