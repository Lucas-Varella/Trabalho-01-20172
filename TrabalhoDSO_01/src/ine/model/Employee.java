package ine.model;

import ine.controller.*;
import ine.model.*;
import ine.view.*;
import java.sql.Date;

public class Employee {
	protected EmployeeCtrl employeeCtrl;
	protected int numRegistration;
	protected String name;
	protected Date dateBirth;
	protected int phone;
	protected double salary;
	protected Contract employment;
	
	public Employee(EmployeeCtrl employeeCtrl, int numRegistration, String name, Date dateBirth, int phone, double salary) {
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
	
	public void delContract()  {
		this.employment = null;
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
	
	public Date getDateBirth() {
		return dateBirth;
	}
	
	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}
	
	public int getPhone(){
		return phone;
	}
	
	public void setPhone(int phone) {
		this.phone = phone;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public Contract getEmployment() {
		return employment;
	}
	
	public void setEmployment(Employment employment) {
		this.employment.setEmployment(employment);
	}

	
}
