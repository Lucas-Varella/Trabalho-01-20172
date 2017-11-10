package br.ufsc.ine5605.model;

import java.io.Serializable;
import java.sql.Date; 

import br.ufsc.ine5605.controller.EmployeeCtrl;

/**
 * Classe que contém os atributos e métodos necessários para a criação de um Employee;
 * @author Sadi Júnior Domingos Jacinto
 *
 */
public class Employee implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient EmployeeCtrl employeeCtrl;
	private int numRegistration;
	private String name;
	private Date dateBirth;
	private int phone;
	private double salary;
	private Contract employment;
	
	/**
	 * Construtor padrão da classe;
	 * @param employeeCtrl - Recebe uma instância de EmployeeCtrl para permitir a comunicação com outras classes;
	 * @param numRegistration - int contendo o número de registro do Employee;
	 * @param name - String contendo o nome do Employee;
	 * @param dateBirth - Date contendo a data de nascimento do Employee;
	 * @param phone - int contendo o número de telefone do Employee;
	 * @param salary - double contendo o salário do Employee;
	 */
	public Employee(EmployeeCtrl employeeCtrl, int numRegistration, String name, Date dateBirth, int phone, double salary) {
		this.employeeCtrl = employeeCtrl;
		this.numRegistration = numRegistration;
		this.name = name;
		this.dateBirth = dateBirth;
		this.phone = phone;
		this.salary = salary;
	}
	
	public void addContract(Contract contract) {
		this.employment = contract;
	}
	
	public void delContract()  {
		this.employment.setEmployee(null);
		this.employment.setEmployment(null);
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
