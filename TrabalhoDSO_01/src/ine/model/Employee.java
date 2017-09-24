package ine.model;

import ine.controller.*;
import ine.model.*;
import ine.view.*;

public class Employee {
	private EmployeeCtrl employeeCtrl;
	private int numRegistration;
	private String name;
	private String dateBirth;
	private int phone;
	private int salary;
	private Contract employment;
	
	public Employee(EmployeeCtrl employeeCtrl, int numRegistration, String name, String dateBirth, int phone, int salary) {
		this.employeeCtrl = employeeCtrl;
		this.numRegistration = numRegistration;
		this.name = name;
		this.dateBirth = dateBirth;
		this.phone = phone;
		this.salary = salary;
	}
	/*
	 * Criar verificação para o caso do contrato já existir;
	 */
	public void addContract(Contract contract) {
		this.employment = contract;
	}
	
	public void delContract() throws Exception {
		this.employment = null;
//		employment.setEmployment(null);
	}

	public int getNumRegistration() {
		return numRegistration;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDateBirth() {
		return dateBirth;
	}
	
	public void setDateBirth(String dateBirth) {
		this.dateBirth = dateBirth;
	}
	
	public int getPhone(){
		return phone;
	}
	
	public void setPhone(int phone) {
		this.phone = phone;
	}
	
	public int getSalary() {
		return salary;
	}
	
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public Contract getEmployment() {
		return employment;
	}
	
	public void setEmployment(Employment employment) {
		this.employment.setEmployment(employment);
	}

	
}
