package br.ufsc.ine5605.model;


import br.ufsc.ine5605.model.Employment; 
import br.ufsc.ine5605.model.Employee;

/**
 * Classe associativa entre Employment e Employee;
 * @author Sadi Júnior Domingos Jacinto;
 *
 */
public class Contract {
	private Employment employment;
	private Employee employee;
	
	/**
	 * Construtor padrão da classe. Setta a si mesma como atributo das instâncias recebidas como parâmetros de entrada;
	 * @param employment - Instância de um objeto Employment;
	 * @param employee - Instância de um objeto Employee;
	 * @throws Exception Ocorre quando há a tentativa de duplicação de um Contract;
	 */
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
