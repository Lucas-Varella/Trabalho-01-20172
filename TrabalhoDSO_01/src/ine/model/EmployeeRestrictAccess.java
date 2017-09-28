package ine.model;

import java.sql.Date;

import ine.controller.EmployeeCtrl;
import ine.model.*;

public class EmployeeRestrictAccess extends Employee {
	private Horary hours;
	
	public EmployeeRestrictAccess(EmployeeCtrl employeeCtrl, int numRegistration, 
								  String name, Date dateBirth, int phone, double salary) {
		super(employeeCtrl, numRegistration, name, dateBirth, phone, salary);
		
		
	}

	public Horary getHours() {
		return hours;
	}

	public void setHours(Horary hours) {
		this.hours = hours;
	}

}
