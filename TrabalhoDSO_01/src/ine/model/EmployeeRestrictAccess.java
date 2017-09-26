package ine.model;

import ine.controller.EmployeeCtrl;
import ine.model.*;

public class EmployeeRestrictAccess extends Employee {
	private EmployeeCtrl employeeCtrl;
	private Horary hours;
	
	public EmployeeRestrictAccess(EmployeeCtrl employeeCtrl, int numRegistration, 
								  String name, String dateBirth, int phone, int salary) {
		super(employeeCtrl, numRegistration, name, dateBirth, phone, salary);
		
		
	}

	public Horary getHours() {
		return hours;
	}

	public void setHours(Horary hours) {
		this.hours = hours;
	}

}
