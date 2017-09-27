package ine.controller;

/*
 * Refazer os imports em todas as classes do projeto, detalhando os
 * que são usados e excluindo os não utilizados;
 * 
 * Lembre-me de mudar o nome do package pai(ine) para algo melhor(o padrão
 * que o Jean explicou mas como eu não prestei atenção, não me lembro 
 * qual era);
 */

import java.util.ArrayList;

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
	
	public void showHoraryMenu() {
		horaryCtrl.menuAdd();
	}
	
	public ArrayList<Employment> listEmployments() {
		return employmentCtrl.listEmployments();
	}
	
	public Employment findEmploymentByIndex(int index) throws StupidUserException {
		try {
			return employmentCtrl.getEmployment(index);
		} catch(StupidUserException e) {
			throw e;
		}
			
	}
	
	public Employee validNumRegistration(int numRegistration) {
		return employeeCtrl.validNumRegistration(numRegistration);

	}
	
	public void editHours(int array, int index, String newHorary) {
		horaryCtrl.editHorary(array, index, newHorary);
	}
	
	public Horary addHorary() {
		return horaryCtrl.menuAdd();
	}
	
	public void setHorary(Horary horary) {
		employeeCtrl.setHorary(horary);
	}
	
}
