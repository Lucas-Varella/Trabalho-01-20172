package ine.model;

import java.util.ArrayList;

import ine.controller.*;
import ine.model.*;
import ine.view.*;

public class Employment {
	private int codigo;
	private String name;
	private Privileges privilege;
	private ArrayList<Employee> employees;
	private EmploymentCtrl employmentCtrl;
	
	public Employment(int codigo, String name, Privileges privilege, EmploymentCtrl employmentCtrl) {
		this.setCodigo(codigo);
		this.setName(name);
		this.setPrivilege(privilege);
		this.employmentCtrl = employmentCtrl;
		employees = new ArrayList();
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
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
