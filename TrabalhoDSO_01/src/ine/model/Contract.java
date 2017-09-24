package ine.model;

import java.util.ArrayList;

import ine.controller.*;
import ine.model.*;
import ine.view.*;

public class Contract {
	private Employment employment;
	private ArrayList<Employee> employees;
	
	public Contract(Employment employment, Employee employee) {
		this.setEmployment(employment);
		this.employees = new ArrayList();
		employees.add(employee);
	}

	public Employment getEmployment() {
		return employment;
	}

	public void setEmployment(Employment employment) {
		this.employment = employment;
	}
	
	/*public void listEmployees() {
		int i = 1;
		for(Employee e : employees) {
			System.out.println(i+"º - " + e.getName());
		}
	}*/
	
	public void addEmployee(Employee employee) throws Exception {
		for(Employee e : employees) {
			if(e.equals(employee)) {
				throw new Exception("The employee is already registered");
			}else{
				employees.add(employee);
			}
		}
	}
	
	public void delEmployee(Employee employee) throws Exception {
		for(Employee e : employees) {
			if(e.equals(employee)) {
				employees.remove(employee);
			}else{
				throw new Exception("The employee is not registered");
			}
		}
	}
	
	

}
