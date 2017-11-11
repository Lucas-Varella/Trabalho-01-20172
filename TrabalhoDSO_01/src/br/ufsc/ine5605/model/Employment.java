package br.ufsc.ine5605.model;

import java.io.Serializable;
import java.util.ArrayList; 

import br.ufsc.ine5605.controller.EmploymentCtrl;

/**
 * Classe que contém os métodos relativos ao Employment;
 * @author Sadi Júnior Domingos Jacinto;
 *
 */
public class Employment implements Serializable {
	
	private int code;
	private String name;
	private Privileges privilege;
	private ArrayList<Contract> employees;

	
	/**
	 * Construtor padrão da classe;
	 * @param code - int contendo o código do Employment;
	 * @param name - String contendo o nome do Employment;
	 * @param privilege - Privilégio concedido ao Employment;
	 * @param employmentCtrl - Instância do EmploymentCtrl, permitindo a comunicação dessa classe com as demais;
	 */
	public Employment(int code, String name, Privileges privilege) {
		this.code = code;
		this.name = name;
		this.privilege = privilege;
		employees = new ArrayList<Contract>();
	}
	
	/**
	 * Adiciona um novo Contract ao ArrayList de Contract;
	 * @param contract - Instância de Contract que será adicionada;
	 * @throws Exception Ocorre quanda há tentativa de duplicação de Contract;
	 */
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
	/**
	 * Busca uma instância específica de Contract que contém um Employee específico;
	 * @param employee - Employee usado na busca;
	 * @return Contract - Retorna a instância encontrada. Retorna null caso não encontre;
	 */
	public Contract findContractByEmployee(Employee employee) {
		for(Contract c : employees) {
			if(c.getEmployee().equals(employee)) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Excluí um Contract que possuí certo Employee;
	 * @param employee - Instância de Employee usada para a busca;
	 */
	public void delContract(Employee employee) {
		if(employee != null) {
			Contract c = findContractByEmployee(employee);
			if(c != null) {
				employees.remove(c);
				employee.setEmployment(null);
			}
			
		}
	}
	
	/**
	 * @deprecated
	 * @param employment
	 */
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
