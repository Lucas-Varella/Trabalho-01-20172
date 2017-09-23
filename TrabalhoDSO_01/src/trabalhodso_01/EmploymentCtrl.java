package trabalhodso_01;

import java.util.ArrayList;

public class EmploymentCtrl {
	private MainController mainCtrl;
	private EmploymentScreen employmentScreen;
	private Employment employment;
	private ArrayList<Employment> employments;

	public EmploymentCtrl(MainController mainCtrl) {
		this.mainCtrl = mainCtrl;
		this.employmentScreen = new EmploymentScreen(this);
		this.employments = new ArrayList();
		}

	public void menu() {
		employmentScreen.employmentMenu();
	}
	
	public void addEmployment(int code, String name, Privileges option) {
		
	}
	
	public void delEmployment(Employment employment) {
		
	}
	
	public void editEmployment() {
		
	}
	
	public void findEmployeeByEmployment(Employment employment) {
		
	}
	
	public ArrayList<Employment> listEmployments() {
		return employments;
	}

}
