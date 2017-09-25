package ine.model;

import ine.controller.EmployeeCtrl;

public class EmployeeRestrictAccess extends Employee {
	private Horary hours;
	
	public EmployeeRestrictAccess(EmployeeCtrl employeeCtrl, int numRegistration, 
								  String name, String dateBirth, int phone, int salary) {
		super(employeeCtrl, numRegistration, name, dateBirth, phone, salary);
		
	}

}
