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
	private EmployeeScreen employeeScreen;
	private ArrayList<Employee> employees;
	private static int code = 17200000;
	
	public EmployeeCtrl(MainController mainCtrl) {
		this.mainCtrl = mainCtrl;
		employeeScreen = new EmployeeScreen(this);
		employees = new ArrayList();
	}
	
	public void menu() {
		employeeScreen.showMenu();
	}
	/*
	 * Criar métodos que ligem a tela com a classe
	 */
	
	public Employee addEmployee(String name, String dateBirth, int phone, int salary) {
		Employee generic = new Employee(this, getCode(), name, dateBirth, phone, salary);
		employees.add(generic);
		setCode(getCode() + 1);
		return generic;
	}
	
	public Contract addContract(Employment employment, Employee employee) {
		return new Contract(employment, employee);
	}
	
	public void addContract(Contract contract) {
		employee.addContract(contract);
	}
	
	public void delEmployee(int index) {
		employees.remove(index);
	}
	
	public void listEmployees() {
		for(Employee e : employees) {
			int i = 1;
			System.out.println(i+"º - "+ e.getName());
			i++;
		}
	}
	
	public Employee getEmployee(int index) {
		return employees.get(index);
	}
	
	public void listEmployments() {
		mainCtrl.listEmployments();
	}
	
	public Employment findEmploymentByIndex(int index) {
		return mainCtrl.findEmploymentByIndex(index);
	}

	public static int getCode() {
		return code;
	}

	public static void setCode(int code) {
		EmployeeCtrl.code = code;
	}
	
	

}
