package ine.view;

import ine.controller.EmploymentCtrl;
import ine.model.Employment;
import ine.model.Privileges;

import java.util.ArrayList;
import java.util.Scanner;

public class EmploymentScreen {
	private EmploymentCtrl employmentCtrl;
	private Scanner keyboard;

	public EmploymentScreen(EmploymentCtrl employmentCtrl) {
		this.employmentCtrl = employmentCtrl;
		keyboard = new Scanner(System.in);
	}

	public void employmentMenu() throws Exception {
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
				/*
				 * Criar um método separado que gera os códigos dos cargos
				 */
				case 1:
					System.out.println("Please enter the name of employment:");
					String name = keyboard.nextLine();
					int optionAccess = 0;
					Privileges gen = null;
					do{
					System.out.println("Choice your privilege: ");
					System.out.println("1 - " +Privileges.Full);
					System.out.println("2 - " +Privileges.Restricted);
					System.out.println("3 - " +Privileges.No);
					//O erro estava neste caralho de linha logo abaixo;
					//keyboard.nextLine();
					optionAccess = keyboard.nextInt();
					
						switch(optionAccess) {
						case 1:
							gen = Privileges.Full;
							break;
						case 2:
							gen = Privileges.Restricted;
							break;
						case 3:
							gen = Privileges.No;
							break;
						default:
							System.out.println("The number you entered is not valid. Please try again.");	
						}
					}while(optionAccess > 3);
					employmentCtrl.addEmployment(0, name, gen);
					break;				
				
				case 2:
					System.out.println("Please enter the number corresponding to your choice: ");
					printEmployments();
					int op = keyboard.nextInt(); 
					Employment generic = employmentCtrl.listEmployments().get(op - 1);
					do {
						System.out.println("Please enter the number corresponding to the characteristic you want to change: ");
						/*
						 * recomendo não dar ao usuário o poder de mudar o código do cargo
						 */
						System.out.println("1 - Code");
						System.out.println("Actually - " + generic.getClass());
						System.out.println("2 - Name");
						System.out.println("Actually - " + generic.getName());
						System.out.println("3 - Privilege");
						System.out.println("Actually - " + generic.getPrivilege());
						System.out.println("Or 0 to exit");
						op = keyboard.nextInt();
						switch(op) {
						case 1:
							System.out.println("Enter a new code: ");
							op = keyboard.nextInt();
							generic.setCodigo(op);
							break;
						case 2:
							System.out.println("Enter a new name: ");
							name = keyboard.nextLine();
							generic.setName(name);
							break;
						case 3:
							System.out.println("Enter the number of a new privilege: ");
							System.out.println("1 - " +Privileges.Full);
							System.out.println("2 - " +Privileges.Restricted);
							System.out.println("3 - " +Privileges.No);
							keyboard.nextLine();
							op = keyboard.nextInt();
							switch(op) {
							case 1:
								generic.setPrivilege(Privileges.Full);
								break;
							case 2: 
								generic.setPrivilege(Privileges.Restricted);
								break;
							case 3:
								generic.setPrivilege(Privileges.No);
								break;
							default:
								System.out.println("Choice a valid number mother fucker!");	
							}
						case 0:

							System.out.println("Goodbye");
								
						}
					}while(op == 1 || op == 2 || op == 3 || op == 0);
					break;
				
				case 3:
					
					//mudar o que é printado(com vocês, Varella e Marcos)
					
					System.out.println("Please enter the number corresponding to your choice: ");
					printEmployments();
					int choice = keyboard.nextInt(); 
					employmentCtrl.delEmployment(employmentCtrl.listEmployments().get(choice - 1));
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
		}while(numOption == 0 || numOption == 1 || numOption == 2 || numOption == 3);
		employmentCtrl.mainMenu();
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
		return null;
		
	}
	
	public void printEmployments() {
		ArrayList<Employment> employments = employmentCtrl.listEmployments();
		for(Employment e : employments) {
			int i = 1;
			System.out.println(i + " - " + e.getName());
		}
	}

}
