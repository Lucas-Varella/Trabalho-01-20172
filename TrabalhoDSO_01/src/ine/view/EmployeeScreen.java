package ine.view;

import ine.controller.*;
import ine.model.*;
import ine.view.*;

import ine.controller.EmployeeCtrl;

import java.util.Scanner;


public class EmployeeScreen {
	private EmployeeCtrl employeeCtrl;
	private Scanner keyboard;

	public EmployeeScreen(EmployeeCtrl employeeCtrl) {
		this.employeeCtrl = employeeCtrl;
		keyboard = new Scanner(System.in);
	}

	/*
	 * Refazer o menu depois(ou o completar); Tratar direito as exceções;
	 */
	public void showMenu() {
		int numOption = 0;
		do {
			try {
				System.out.println("Welcome!");
				System.out.println("Please enter the number corresponding to your choice: ");
				System.out.println("1 - Add employee");
				System.out.println("2 - Edit employee");
				System.out.println("3 - Delete employee");
				System.out.println("4 - List employees");
				System.out.println("0 - Get out");
				String option = keyboard.nextLine();
				numOption = Integer.parseInt(option);
				switch (numOption) {
				
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
					employeeCtrl.listEmployees();
				
				case 0:
					System.out.println("Goodbye and have a good day");
					break;
				default:
					System.out.println("The number you entered is not valid. Please try again.");
				}

			} catch (Exception e) {
				System.out.println("Enter only numbers!");
			}

		} while (numOption != 0);
		employeeCtrl.mainMenu();
	}
	
	public void addEmployee() {
		System.out.println("Please enter the following information");
		System.out.println("Name: ");
		String name = keyboard.nextLine();
		System.out.println("Date of birth: ");
		/*
		 * Formatar as datas aqui depois(contigo Varella)
		 */
		String birthDay = keyboard.nextLine();
		System.out.println("Phone: ");
		int phone = keyboard.nextInt();
		System.out.println("Salary: ");
		int salary = keyboard.nextInt();
		Employee generic = employeeCtrl.addEmployee(name, birthDay, phone, salary);
		System.out.println("Please, enter the number corresponding to the chosen employment: ");
		employeeCtrl.listEmployments();
		int option = keyboard.nextInt() - 1;
		Employment gen = employeeCtrl.findEmploymentByIndex(option);
		Contract contract = employeeCtrl.addContract(gen, generic);
		//employeeCtrl.addContract(contract);
		System.out.println("Congratulations, you have created a new employee with the following characteristics: ");
		System.out.println("Number of registration - " + generic.getNumRegistration());
		System.out.println("Birthday - " + generic.getDateBirth());
		System.out.println("Phone - " + generic.getPhone());
		System.out.println("Salary - " + generic.getSalary());
		System.out.println("Employment - " + generic.getEmployment().getEmployment().getName());
	}
	
	public void editEmployee() {
		int option = 0;
		do {
			System.out.println("Please enter the number corresponding to your choice: ");
			employeeCtrl.listEmployees();

			/*
			 * Fazer um try catch aqui, caso o usuário seja filho da puta ao
			 * ponto de digitar 0;
			 */

			option = keyboard.nextInt() - 1;
			Employee generic = employeeCtrl.getEmployee(option);

			System.out.println("Please enter the number corresponding to the characteristic you want to change: ");
			/*
			 * recomendo não dar ao usuário o poder de mudar o número de matrícula
			 */
			System.out.println("1 - Name");
			System.out.println("Actually - " + generic.getName());
			System.out.println("2 - Birthday");
			System.out.println("Actually - " + generic.getDateBirth());
			System.out.println("3 - Phone");
			System.out.println("ACtually - " + generic.getPhone());
			System.out.println("4 - Salary");
			System.out.println("Actually - " + generic.getSalary());
			System.out.println("5 - Employment");
			System.out.println("Actually - " + generic.getEmployment().getEmployment().getName());
			System.out.println("Or 0 to exit");			
			option = keyboard.nextInt();
			switch (option) {

			/*
			 * Não sei se o Jean não vai reclamar de a tela poder setar um atributo da classe;
			 */
			case 1:
				System.out.println("Enter a new name: ");
				String name = keyboard.nextLine();
				generic.setName(name);
				break;

			case 2:
				System.out.println("Enter a new birthday: ");
				String dateBirth = keyboard.nextLine();
				generic.setDateBirth(dateBirth);
				break;
			case 3:
				System.out.println("Enter a new phone");
				int phone = keyboard.nextInt();
				generic.setPhone(phone);
				break;
			case 4:
				System.out.println("Enter a new salary");
				int salary = keyboard.nextInt();
				generic.setSalary(salary);
				break;
			case 5:
				System.out.println("Enter the number corresponding to the new employment");
				employeeCtrl.listEmployments();
				int employment = keyboard.nextInt();
				Employment gen = employeeCtrl.findEmploymentByIndex(employment);
				generic.setEmployment(gen);
				break;
			case 0:
				System.out.println("Goodbye");
				break;
			default:
				System.out.println("Please, enter a valid number");			
			}
		}while (option != 0);
	}
	
	public void delEmployee() throws Exception {
		System.out.println("Select an employee to fire");
		employeeCtrl.listEmployees();
		int option = keyboard.nextInt() - 1;
		Employee fired = employeeCtrl.getEmployee(option);
		fired.delContract();
		employeeCtrl.delEmployee(option);
		
	}
}
