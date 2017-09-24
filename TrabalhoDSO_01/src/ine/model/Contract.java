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
	
	
	

}
