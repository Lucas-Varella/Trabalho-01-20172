package ine.model;

import java.util.ArrayList;

import ine.controller.*;
import ine.model.*;
import ine.view.*;

public class Employment {
	private int code;
	private String name;
	private Privileges privilege;
	private ArrayList<Employee> employees;
	private EmploymentCtrl employmentCtrl;
	
	public Employment(int code, String name, Privileges privilege, EmploymentCtrl employmentCtrl) {
		this.setCode(code);
		this.setName(name);
		this.setPrivilege(privilege);
		this.employmentCtrl = employmentCtrl;
		employees = new ArrayList();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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
