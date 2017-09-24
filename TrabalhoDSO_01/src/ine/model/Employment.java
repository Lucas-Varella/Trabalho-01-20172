package ine.model;

import java.util.ArrayList;

import ine.controller.*;
import ine.model.*;
import ine.view.*;

public class Employment {
	private int code;
	private String name;
	private Privileges privilege;
	private Contract employees;
	private EmploymentCtrl employmentCtrl;
	
	public Employment(int code, String name, Privileges privilege, EmploymentCtrl employmentCtrl) {
		this.code = code;
		this.name = name; //setName(name);
		this.privilege = privilege;//setPrivilege(privilege);
		this.employmentCtrl = employmentCtrl;
	}
    /*
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
   */
	
	public void addContract(Contract contract) throws Exception {
			if(c.equals(contract)) {
				throw new Exception("This list of employees is already registered in the employment");
			}else{
				employees.add(contract);
			}
		}
	}
	/*
	 * IMplementar depois
	 */
	public void delContract() {
		
	}
	
	public void listEmployees() {
		for(Contract c : employees) {
			c.
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
