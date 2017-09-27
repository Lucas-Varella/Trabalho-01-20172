package ine.model;

import java.util.ArrayList;

import ine.controller.*;
import ine.model.*;
import ine.view.*;

public class Employment {
	private int code;
	private String name;
	private Privileges privilege;
	private ArrayList<Contract> employees;
	private EmploymentCtrl employmentCtrl;
	
	public Employment(int code, String name, Privileges privilege, EmploymentCtrl employmentCtrl) {
		this.code = code;
		this.name = name;
		this.privilege = privilege;
		this.employmentCtrl = employmentCtrl;
	}
	
	public void addContract(Contract contract) throws StupidUserException {
		Employee employee = contract.getEmployee();
		if(findContractByEmployee(employee) == null) {
			employees.add(contract);
			if(employee.getEmployment() != contract) {
				employee.setEmployment(contract.getEmployment());
			}
		}else {
			throw new StupidUserException("Attempted duplication of contract. The employee " +employee.getName() + 
					" already has a contract with the employment " + contract.getEmployment().getName());
		}
		
	}
	
	public Contract findContractByEmployee(Employee employee) {
		for(Contract c : employees) {
			if(c.getEmployee().equals(employee)) {
				return c;
			}
		}
		return null;
	}
	
	public void delContract(Employee employee) {
		if(employee != null) {
			Contract c = findContractByEmployee(employee);
			if(c != null) {
				employees.remove(c);
				employee.setEmployment(null);
			}
			
		}
	}
	
	public void listEmployees(Employment employment) {
		int i = 1;
		for(Contract c : employees) {
			String name = c.getEmployee().getName();
			System.out.println(i+ "º - " + name);
			i++;
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Privileges getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privileges privilege) {
		this.privilege = privilege;
	}
	
	
}
