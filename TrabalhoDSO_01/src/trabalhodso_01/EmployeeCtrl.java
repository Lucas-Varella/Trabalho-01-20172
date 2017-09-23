package trabalhodso_01;

public class EmployeeCtrl {
	private MainController mainCtrl;
	private Employee employee;
	private EmployeeScreen employeeScreen;
	
	public EmployeeCtrl(MainController mainCtrl) {
		this.mainCtrl = mainCtrl;
		employeeScreen = new EmployeeScreen(this);
	}
	
	public void menu() {
		employeeScreen.showMenu();
	}
	/*
	 * Criar métodos que ligem a tela com a classe
	 */
	
	public void addEmployee() {
		Employee employee = new Employee();
	}
	
	

}
