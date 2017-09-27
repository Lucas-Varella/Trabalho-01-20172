package ine.controller;

import java.util.ArrayList;

import ine.controller.*;
import ine.model.*;
import ine.view.*;

import ine.model.Employee;
import ine.view.EmployeeScreen;

public class EmployeeCtrl {
	private MainController mainCtrl;
	private Employee employee;
	private EmployeeRestrictAccess employeeRestrict;
	private EmployeeScreen employeeScreen;
	private ArrayList<Employee> employees;
	private static int code = 17200000;
	
	public EmployeeCtrl(MainController mainCtrl) {
		this.mainCtrl = mainCtrl;
		employeeScreen = new EmployeeScreen(this);
		employees = new ArrayList<Employee>();
	}
	
	public void menu() {
		employeeScreen.showMenu();
	}
	/*
	 * Criar métodos que ligem a tela com a classe
	 */
	
	public Employee addEmployee(String name, String dateBirth, int phone, double salary) {
		Employee generic = new Employee(this, getCode(), name, dateBirth, phone, salary);
		employees.add(generic);
		setCode(getCode() + 1);
		return generic;
	}
	
	public EmployeeRestrictAccess addEmployeeRestrict(String name, String dateBirth, int phone, double salary) {
		EmployeeRestrictAccess generic = new EmployeeRestrictAccess(this, getCode(), name, dateBirth, phone, salary);
		employees.add(generic);
		setCode(getCode() + 1);
		return generic;
	}
	
	public Contract addContract(Employment employment, Employee employee) throws StupidUserException {
		return new Contract(employment, employee);
	}
	
	public Contract addContract(Employment employment, EmployeeRestrictAccess employee) throws StupidUserException {
		return new Contract(employment, employee);
	}
	
	public void delEmployee(int index) {
		employees.remove(index);
	}
	
	public ArrayList<Employee> listEmployees() {
		return employees;
	}
	
	public Employee getEmployee(int index) throws StupidUserException {
		if(employees.size() >= index && index > -1) {
			return employees.get(index);
		}
		throw new StupidUserException();
			
	}
	
	
	public ArrayList<Employment> listEmployments() {
		return mainCtrl.listEmployments();
	}
	
	public Employment findEmploymentByIndex(int index) throws StupidUserException {
		try {
			return mainCtrl.findEmploymentByIndex(index);
		} catch(StupidUserException e) {
			throw e;
		}
	}
	
	public static int getCode() {
		return code;
	}

	public static void setCode(int code) {
		EmployeeCtrl.code = code;
	}

	public void mainMenu() {
		mainCtrl.showMainScreen();
		
	}
	
	public Employee validNumRegistration(int numRegistration) {
		for(Employee e : employees) {
			if(e.getNumRegistration() == numRegistration) {
				return e;
			}
		}
		return null;
	}
	
	public Horary getHours(EmployeeRestrictAccess res) {
		return employee.getHours(res);
	}
	
	public void editHour(int array, int index, String newHorary) {
		mainCtrl.editHours(array, index, newHorary);
	}
	
	public Horary addHorary() {
		return mainCtrl.addHorary();
	}
	
	public void setHorary(Horary horary) {
		employeeRestrict.setHours(horary);
	}
	
	public void setName(String name) {
		employee.setName(name);
	}
	
	// Mudar para Date depois;
	
	public void setDateBirth(String dateBirth) {
		employee.setDateBirth(dateBirth);
	}
	
	public void setPhone(int phone) {
		employee.setPhone(phone);
	}
	
	public void setSalary(double salary) {
		employee.setSalary(salary);
	}
	
	public void setEmployment(Employment employment) {
		employee.setEmployment(employment);
	}
	
	

}
