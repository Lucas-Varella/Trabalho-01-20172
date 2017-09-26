package ine.model;

import ine.controller.*;
import ine.model.*;
import ine.view.*;

public class Contract {
	private Employment employment;
	private Employee employee;
	
	public Contract(Employment employment, Employee employee) throws Exception {
		this.setEmployment(employment);
		this.employee = employee;
		employee.addContract(this);
		employment.addContract(this);
	}
	
	public Contract(Employment employment, EmployeeRestrictAccess employee) throws Exception {
		this.setEmployment(employment);
		this.employee = employee;
		employee.addContract(this);
		employment.addContract(this);
	}

	public Employment getEmployment() {
		return employment;
	}

	public void setEmployment(Employment employment) {
		this.employment = employment;
	}
	
	public Employee getEmployee(){
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	

}
