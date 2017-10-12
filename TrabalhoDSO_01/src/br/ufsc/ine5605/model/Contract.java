package br.ufsc.ine5605.model;

import br.ufsc.ine5605.controller.*;
import br.ufsc.ine5605.model.*;
import br.ufsc.ine5605.view.*;

public class Contract {
	private Employment employment;
	private Employee employee;
	
	public Contract(Employment employment, Employee employee) throws Exception {
		this.employment = employment;
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
