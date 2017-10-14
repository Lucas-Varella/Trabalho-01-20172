package br.ufsc.ine5605.view;

import java.sql.Date; 
import java.text.ParseException;
import java.util.Scanner;

import br.ufsc.ine5605.controller.EmployeeCtrl;
import br.ufsc.ine5605.model.Employment;
import br.ufsc.ine5605.model.Employee;
import br.ufsc.ine5605.model.Contract;

/**
 * Tela responsável pela interação do usuário com as funcionalidades relacionadas aos Employees;
 * @author Sadi Júnior Domingos Jacinto;
 */
public class EmployeeScreen {
	private EmployeeCtrl employeeCtrl;
	private Scanner keyboard;
	
	/**
	 * Construtor padrão da classe
	 * @param employeeCtrl - Recebe uma instância do EmployeeCtrl, o que permite a comunicação com outras classes;
	 */
	public EmployeeScreen(EmployeeCtrl employeeCtrl) {
		this.employeeCtrl = employeeCtrl;
		keyboard = new Scanner(System.in);
	}

	/**
	 * Tela principal da classe;
	 */
	public void menu() {
		try {
			int option = 0;
			do {
			
				System.out.println("-------------------------------------------------------------------------");
				System.out.println("Welcome to the Employee Area!");
				System.out.println("Please enter the number corresponding to your choice: ");
				System.out.println("1 - Add employee");
				System.out.println("2 - Edit employee");
				System.out.println("3 - Delete employee");
				System.out.println("4 - List employees");
				System.out.println("0 - Back to Main Area");
				System.out.println("-------------------------------------------------------------------------");
				option = employeeCtrl.conversionStringToInt(keyboard.nextLine());
				
				switch (option) {
				
				case 1:
					addEmployee();
					break;
				
				case 2:
					editEmployee();
					break;
				
				case 3:
					delEmployee();
					break;
					
				case 4:
					listEmployees();
					break;
				
				case 0:
					System.out.println("-------------------------------------------------------------------------");
					break;
				
				default:
					System.out.println("The number you entered is not valid. Please try again.");
				}
				
			} while (option != 0);
			employeeCtrl.mainMenu();

		} catch(NumberFormatException e) {
			System.out.println("Please enter only integers");
			menu();
		} catch(IndexOutOfBoundsException e) {
			System.out.println("No employee registered. Please register a employee before attempting this option");
			menu();
		}

		
	}
	/**
	 * Armazena e envia ao EmployeeCtrl os dados necessários para a criação de um novo funcionário;
	 */
	public void addEmployee() {
		try {
			System.out.println("Please enter the following information");
			
			System.out.println("Name: ");
			String name = keyboard.nextLine();
			
			System.out.println("Date of birth: ");
		
			Date birthDay = employeeCtrl.strToDate(keyboard.nextLine());
			
			
			System.out.println("Phone: ");
			int phone = employeeCtrl.conversionStringToInt(keyboard.nextLine()); 
			
			
			System.out.println("Salary: ");
			double salary = employeeCtrl.conversionStringToDouble(keyboard.nextLine());
			
			
			System.out.println("Please, enter the number corresponding to the chosen employment: ");
			
			employeeCtrl.listEmployments();
			
			int option = employeeCtrl.conversionStringToInt(keyboard.nextLine()) - 1;
			Employment gen = employeeCtrl.findEmploymentByIndex(option);
		
			Employee generic = employeeCtrl.addEmployee(name, birthDay, phone, salary);
			employeeCtrl.addContract(gen, generic);
			
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("Congratulations, you have created a new employee with the following characteristics: ");
			System.out.println("Number of registration - " + generic.getNumRegistration());
			System.out.println("Name - " + generic.getName());
			System.out.println("Birthday - " + generic.getDateBirth());
			System.out.println("Phone - " + generic.getPhone());
			System.out.println("Salary - " + generic.getSalary());
			System.out.println("Employment - " + generic.getEmployment().getEmployment().getName());
		
		} catch(NullPointerException e) {
			System.out.println("An internal error occurred. Contact support urgently");
			System.out.println("-------------------------------------------------------------------------");
			addEmployee();
		
		} catch(NumberFormatException e) {
			System.out.println("The number you entered is not valid");
			System.out.println("-------------------------------------------------------------------------");
			addEmployee();
		
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Please register first a position before registering an employee");
			System.out.println("-------------------------------------------------------------------------");
			addEmployee();
		
		} catch(IndexOutOfBoundsException e) {
			System.out.println("No charge registered. Please register a position before attempting this option");
			menu();
		
		} catch(ParseException e) {
			System.out.println("The date format entered by the user is not correct\n"+ 
							   "Please try again based on this format:\n" + "dd/MM/yyyy");
			System.out.println("-------------------------------------------------------------------------");
			addEmployee();
			//Retirar o catch abaixo depois;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("-------------------------------------------------------------------------");
			addEmployee();
		}
	}	
	/**
	 * Tela responsável pela interação do usuário com os métodos de edição dos atributos do Funcionário;
	 */
	public void editEmployee() {
		try {
			int option = 0;
			do {
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("Please enter the number corresponding to your choice: ");
				listEmployees();
				option = employeeCtrl.conversionStringToInt(keyboard.nextLine()) - 1;
				Employee generic = employeeCtrl.getEmployee(option);
				
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("Please enter the number corresponding to the characteristic you want to change: ");
		
				System.out.println("1 - Name");
				System.out.println("Actually - " + generic.getName());
				System.out.println("2 - Birthday");
				System.out.println("Actually - " + generic.getDateBirth());
				System.out.println("3 - Phone");
				System.out.println("Actually - " + generic.getPhone());
				System.out.println("4 - Salary");
				System.out.println("Actually - " + generic.getSalary());
				System.out.println("5 - Employment");
				System.out.println("Actually - " + generic.getEmployment().getEmployment().getName());
				System.out.println("Or 0 to exit");
				System.out.println("--------------------------------------------------------------------------------");

				option = employeeCtrl.conversionStringToInt(keyboard.nextLine());
					
				switch (option) {
		
				case 1:
					System.out.println("--------------------------------------------------------------------------------");
					System.out.println("Enter a new name: ");
					String name = keyboard.nextLine();
					generic.setName(name);
					break;
		
				case 2:
					System.out.println("--------------------------------------------------------------------------------");
					System.out.println("Enter a new birthday: ");
					Date dateBirth = employeeCtrl.strToDate(keyboard.nextLine());
					generic.setDateBirth(dateBirth);
					break;
						
				case 3:
					System.out.println("--------------------------------------------------------------------------------");
					System.out.println("Enter a new phone");
					int phone = keyboard.nextInt();
					generic.setPhone(phone);
					break;
				
				case 4:
					System.out.println("--------------------------------------------------------------------------------");
					System.out.println("Enter a new salary");
					int salary = keyboard.nextInt();
					generic.setSalary(salary);
					break;
				
				case 5:
					System.out.println("--------------------------------------------------------------------------------");
					System.out.println("Enter the number corresponding to the new employment");
					employeeCtrl.listEmployments();
					System.out.println("--------------------------------------------------------------------------------");
					int employment = employeeCtrl.conversionStringToInt(keyboard.nextLine()) - 1;
					Employment gen = employeeCtrl.findEmploymentByIndex(employment);
					generic.setEmployment(gen);
					break;
					
				case 0:
					System.out.println("Goodbye");
					break;
				
				default:
					System.out.println("Please, enter a valid number");			
				}
				
			} while (option != 0);
		
		
		} catch(NullPointerException e) {
			System.out.println("An internal error occurred. Contact support urgently");
			editEmployee();
			
		} catch(NumberFormatException e) {
			System.out.println("The number you entered is not valid");
			editEmployee();
		
		} catch(ParseException e) {
			System.out.println("The date format entered by the user is not correct\n"+ 
							   "Please try again based on this format:\n" + "dd/MM/yyyy");
			editEmployee();
			
		} catch(IndexOutOfBoundsException e) {
			System.out.println("No registered employees. Please register an employee first before choosing this option");
			menu();
		}
		
	}
	
	/**
	 * Tela responsável pela interação do usuário com o método de remoção de Employees;
	 */
	public void delEmployee() {
		try {
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("Select an employee to fire");
			listEmployees();
			int option = employeeCtrl.conversionStringToInt(keyboard.nextLine()) - 1;
			employeeCtrl.delEmployee(option);
		
		} catch(NullPointerException e) {
			System.out.println("An internal error occurred. Contact support urgently");
			menu();
			
		} catch(NumberFormatException e) {
			System.out.println("The number you entered is not valid");
			delEmployee();
		} catch(IndexOutOfBoundsException e) {
			System.out.println("No registered employees. Please register an employee first before choosing this option");
			menu();
		}
		
	}
	
	/**
	 * Método responsável por listar todos os Employees registrados;
	 * @throws IndexOutOfBoundsException Ocorre quando não existe nenhum Employee cadastrado no sistema;
	 */
	public void listEmployees() throws IndexOutOfBoundsException {
		int i = 1;
		System.out.println("--------------------------------------------------------------------------------");
		if(employeeCtrl.listEmployees().size() > 0) {
			for(Employee e: employeeCtrl.listEmployees()) {
				System.out.println(i + "º - " + e.getName());
				i++;
			}
		}else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	
	
}