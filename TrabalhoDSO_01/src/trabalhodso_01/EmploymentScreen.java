package trabalhodso_01;

import java.util.Scanner;

public class EmploymentScreen {
	private EmploymentCtrl employmentCtrl;
	private Scanner keyboard;

	public EmploymentScreen(EmploymentCtrl employmentCtrl) {
		this.employmentCtrl = employmentCtrl;
		keyboard = new Scanner(System.in);
	}

	public void employmentMenu() {
		int numOption = 0;
		do {
			try {
				System.out.println("Welcome!");
				System.out.println("Please enter the number corresponding to your choice: ");
				System.out.println("1 - Register new employment");
				System.out.println("2 - Edit employment");
				System.out.println("3 - Delete employment");
				System.out.println("4 - List employments");
				System.out.println("0 - Get out");
				String option = keyboard.nextLine();
				numOption = Integer.parseInt(option);
				switch(numOption) {
				case 1:
					addEmployment();
					break;				
					mainCtrl.showEmploymentMenu();
					break;
				case 3:
					mainCtrl.showFinancialSectorMenu();
					break;
				case 0:
					System.out.println("Goodbye and have a good day");
					break;
				default:
					System.out.println("The number you entered is not valid. Please try again.");
				}
			}catch (Exception e) {
				System.out.println("Please enter only numbers");
				
			}
		}while(numOption != 0);
		
	}
	
	public void addEmployment() {
		int numOption = 0;
		do {
			/*
			 * Fazer um try catch aqui para caso o usuário digite
			 * o código como letras e não números
			 * 
			 * Ideia: criar uma classe abstrata que verifica isso
			 * e é extendida por todas as telas;
			 * 
			 */
				System.out.println("Welcome!");
				System.out.println("Please enter the required information: ");
				System.out.println("Code: ");
				int code = keyboard.nextInt();
				System.out.println("Name: ");
				String name = keyboard.nextLine();
				System.out.println("Privileges options: ");
				Privileges option = setPrivileges();
				employmentCtrl.addEmployment(code, name, option);
		}while(numOption != 0);
	}
	
	public void delEMployment() {
		System.out
	}
	
	public void editEmployment() {
		
	}
	
	public Privileges setPrivileges() {
		for(Privileges p : Privileges.values()) {
			int i = 1;
			System.out.println(i+"º - "+p.name());
		}
		int option = keyboard.nextInt();
		Privileges pri;
		switch(option) {
		case 1:
			pri = Privileges.Full;
			break;
		case 2:
			pri = Privileges.Restricted;
			break;
		case 3:
			pri = Privileges.No;
			break;
		default:
			/*
			 * Colocar uma exceção aqui:
			 */
			System.out.println("The number you entered is not valid. Please try again.");
		}
		return pri;
		
	}

}
