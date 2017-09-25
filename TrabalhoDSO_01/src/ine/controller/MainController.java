package ine.controller;

/*
 * Refazer os imports em todas as classes do projeto, detalhando os
 * que são usados e excluindo os não utilizados;
 * 
 * Lembre-me de mudar o nome do package pai(ine) para algo melhor(o padrão
 * que o Jean explicou mas como eu não prestei atenção, não me lembro 
 * qual era);
 */

import ine.controller.*;
import ine.model.*;
import ine.view.*;

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
	
	public void listEmployments() {
		employmentCtrl.listEmployments();
	}
	
	public Employment findEmploymentByIndex(int index) {
		return employmentCtrl.getEmployment(index);
	}
	
	public Employee validNumRegistration(int numRegistration) {
		employeeCtrl.validNumRegistration(numRegistration);
		return false;
	}
	
}
