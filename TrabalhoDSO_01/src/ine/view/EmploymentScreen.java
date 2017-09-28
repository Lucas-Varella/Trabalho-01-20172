package ine.view;

import ine.controller.EmploymentCtrl; 
import ine.model.Employment;
import ine.model.Privileges;
import ine.model.Screen;

import java.sql.Date;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EmploymentScreen implements Screen {
	private EmploymentCtrl employmentCtrl;
	private Scanner keyboard;

	public EmploymentScreen(EmploymentCtrl employmentCtrl) {
		this.employmentCtrl = employmentCtrl;
		keyboard = new Scanner(System.in);
	}

	public void menu() {
		try {
			int option = 0;
			do {
			/*
			 * Verificar por que motivo o catch é executado sempre;
			 * Refazer esse try catch;
			 */
			
				System.out.println("Welcome!");
				System.out.println("Please enter the number corresponding to your choice: ");
				System.out.println("1 - Register new employment");
				System.out.println("2 - Edit employment");
				System.out.println("3 - Delete employment");
				System.out.println("4 - List employments");
				System.out.println("5 - List employees in a employment ");
				System.out.println("0 - Get out");
				option = Integer.parseInt(keyboard.nextLine());
				
				switch (option) {
				
				case 1:
					addEmployment();
					break;

				case 2:
					editEmployment();
					break;

				case 3:
					delEmployment();
					break;
					
				case 4:
					employmentCtrl.listEmployments();
					break;

				case 5:
					findEmployeesByEmployment();
					break;
				
				case 0:
					System.out.println("Goodbye and have a good day");
					break;

				default:
					System.out.println("The number you entered is not valid. Please try again.");
				}
			
			} while(option != 0);
			employmentCtrl.mainMenu();
		
		} catch (NumberFormatException e) {
			System.out.println("Please enter only numbers");
			menu();
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("The number entered is not valid");
			menu();
		} 
		
	}

	/*
	 * Resolvi não dar ao usuário a alternativa de ele escolher o código do
	 * cargo. Ao invés disso, criei(+/-) um método dentro do controlador que
	 * gera o código automaticamente;
	 */
	public void addEmployment() {
		try {
			
			System.out.println("Please enter the name of employment:");
			String name = keyboard.nextLine();
			int option = 0;
			Privileges gen = null;
			
			do {
				System.out.println("Choice your privilege: ");
				System.out.println("1 - " + Privileges.Full);
				System.out.println("2 - " + Privileges.Restricted);
				System.out.println("3 - " + Privileges.No);
				option = conversionStringToInt(keyboard.nextLine());
	
				switch (option) {
				
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
			
			} while (option > 3 || option == 0);
			
			employmentCtrl.addEmployment(name, gen);
			System.out.println("Congratulations, you just successfully registered a employment!");
			System.out.println("These are the employment data:");
			System.out.println("Code: " + EmploymentCtrl.getCode() + 1);
			System.out.println("Name: " + name);
			System.out.println("Privilege: " + gen);
		
		} catch(NumberFormatException e) {
			System.out.println("Please enter only integers");
			addEmployment();
		}
	}

	public void editEmployment() {
		try {
	
			int option = 0;
			do {
				System.out.println("Please enter the number corresponding to your choice: ");
				int i = 1;
				
				if(employmentCtrl.listEmployments().size() > 0) {
					for(Employment e : employmentCtrl.listEmployments()) {
						System.out.println(i+"º - "+ e.getName());
						i++;
					}
				}else {
					throw new IndexOutOfBoundsException();
				}
				
				option = keyboard.nextInt() - 1;
				Employment generic = employmentCtrl.getEmployment(option);
				System.out.println("Please enter the number corresponding to the characteristic you want to change: ");
				System.out.println("1 - Name");
				System.out.println("Actually - " + generic.getName());
				System.out.println("2 - Privilege");
				System.out.println("Actually - " + generic.getPrivilege());
				System.out.println("Or 0 to exit");
				option = conversionStringToInt(keyboard.nextLine());
				
				switch (option) {
	
				case 1:
					System.out.println("Enter a new name: ");
					String name = keyboard.nextLine();
					generic.setName(name);
					break;
	
				case 2:
					System.out.println("Enter the number of a new privilege: ");
					do {
						System.out.println("1 - " + Privileges.Full);
						System.out.println("2 - " + Privileges.Restricted);
						System.out.println("3 - " + Privileges.No);
						keyboard.nextLine();
						option = conversionStringToInt(keyboard.nextLine());
						switch (option) {
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
							System.out.println("Choice a valid number!");
						}
	
					} while (option != 1 && option != 2 && option != 3);
	
				case 0:
	
					System.out.println("Goodbye");
	
				}
			}while (option != 0);
		 
		} catch(IndexOutOfBoundsException e) {
			
			editEmployment();
		} catch(InputMismatchException e) {
			
			editEmployment();
		} catch(NumberFormatException e) {
			
			editEmployment();
		}
	}

	public void delEmployment() {
		// mudar o que é printado(com vocês, Varella e Marcos)
		boolean getOut = false;
		int option = 0;
		do{
			System.out.println("Please enter the number corresponding to your choice: ");
			employmentCtrl.listEmployments();
			option = keyboard.nextInt() - 1;
			employmentCtrl.delEmployment(employmentCtrl.getEmployment(option));
			System.out.println("Do you want to fire another employee?");
			System.out.println("Enter 1 if the answer is yes");
			System.out.println("Enter 0 if the answer is no");
			option = keyboard.nextInt();
			switch(option) {
			case 0:
				getOut = true;
				break;
			case 1:
				getOut = false;
				break;
			default:
				System.out.println("Choice a valid number!");
			}
		}while(!getOut);
	}
	
	public void findEmployeesByEmployment() {
		System.out.println("Enter the number corresponding to your choice:");
		employmentCtrl.listEmployments();
		int choice = keyboard.nextInt() - 1;
		Employment generic = employmentCtrl.getEmployment(choice);
		employmentCtrl.listEmployees(generic);
	}

	public int conversionStringToInt(String data) throws NumberFormatException {
		try {
			int num = Integer.parseInt(data);	
			return num;
		} catch(NumberFormatException e ) {
			throw new NumberFormatException();
		}
	}

	public double conversionStringToDouble(String data)
			throws NumberFormatException {
		try {
			double num = Double.parseDouble(data);	
			return num;
		} catch(NumberFormatException e ) {
			throw new NumberFormatException();
		}
	}

	public Date formatStringToDate(String data) {
		// TODO Auto-generated method stub
		return null;
	}

	public Date strToDate(String data) throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}

	public Date strToDateHour(String data) throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}
}
