package br.ufsc.ine5605.model;

import java.util.ArrayList;

import br.ufsc.ine5605.controller.*;
import br.ufsc.ine5605.model.*;
import br.ufsc.ine5605.view.*;


public class Employment {
	protected int code;
	protected String name;
	protected Privileges privilege;
	protected ArrayList<Contract> employees;
	protected EmploymentCtrl employmentCtrl;
	
	public Employment(int code, String name, Privileges privilege, EmploymentCtrl employmentCtrl) {
		this.code = code;
		this.name = name;
		this.privilege = privilege;
		this.employmentCtrl = employmentCtrl;
		employees = new ArrayList<Contract>();
	}
	
	public void addContract(Contract contract) throws Exception {
		Employee employee = contract.getEmployee();
		if(findContractByEmployee(employee) == null) {
			employees.add(contract);
			if(employee.getEmployment() != contract) {
				employee.setEmployment(contract.getEmployment());
			}
		}else {
			throw new Exception("Attempted duplication of contract. The employee " +employee.getName() + 
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
	
	public ArrayList<Contract> getEmployees() {
		return employees;
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
	
	public int getCode() {
		return code;
	}
	
	
}
