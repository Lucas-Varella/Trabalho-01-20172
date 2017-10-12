package br.ufsc.ine5605.view;



import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import br.ufsc.ine5605.controller.*;
import br.ufsc.ine5605.model.*;
import br.ufsc.ine5605.view.*;


public class EmployeeScreen implements Screen {
	private EmployeeCtrl employeeCtrl;
	private Scanner keyboard;

	public EmployeeScreen(EmployeeCtrl employeeCtrl) {
		this.employeeCtrl = employeeCtrl;
		keyboard = new Scanner(System.in);
	}

	
	public void menu() {
		int option = 0;
		/*
		 * Mudar as mensagens das exceptions para que toda exceção não repita a mesma mensagem;
		 */
		try {
			
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
				option = Integer.parseInt(keyboard.nextLine());
				
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
			System.out.println(e.getMessage());
			menu();
		} catch(IndexOutOfBoundsException e) {
			System.out.println("No employee registered. Please register a employee before attempting this option");
			menu();
		}

		
	}
	/**
	 * O método addEmployee() adiciona um novo funcionário
	 * @throws Exception 
	 */
	public void addEmployee() {
		try {
			System.out.println("Please enter the following information");
			
			/*
			 * Aqui se pega os dados fundamentais(dados que todos os funcionários,
			 * tem em comum : nome, data de nascimento, telefone e salário); 
			 */
			System.out.println("Name: ");
			String name = keyboard.nextLine();
			
			System.out.println("Date of birth: ");
			/*
			 * Formatar as datas aqui depois(contigo Varella)
			 */
			Date birthDay = strToDate(keyboard.nextLine());
			
			
			System.out.println("Phone: ");
			int phone = conversionStringToInt(keyboard.nextLine()); 
			
			
			System.out.println("Salary: ");
			double salary = conversionStringToDouble(keyboard.nextLine());
			
			
			System.out.println("Please, enter the number corresponding to the chosen employment: ");
			
			/*
			 * Listando todas os possíveis cargos que o funcionário pode ter;
			 * E verificando se o usuário escolheu uma opção válida;
			 * 
			 * PS: Não sei se o View pode percorrer e listar ArrayList ou se essa função
			 * pertence única e exclusivamente ao Model;
			 * 
			 */
			int i = 1;
			if(employeeCtrl.listEmployments().size() > 0) {
/*
 * Listar os cargos na tela Cargo
*/
				for(Employment e : employeeCtrl.listEmployments()) {
					System.out.println(i+"º - "+ e.getName());
					i++;
				}
			}else {
				throw new IndexOutOfBoundsException();
			}
			int option = conversionStringToInt(keyboard.nextLine()) - 1;
			Employment gen = employeeCtrl.findEmploymentByIndex(option);
		
			Employee generic = employeeCtrl.addEmployee(name, birthDay, phone, salary);
			Contract contract = employeeCtrl.addContract(gen, generic);
			
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("Congratulations, you have created a new employee with the following characteristics: ");
			System.out.println("Number of registration - " + generic.getNumRegistration());
			System.out.println("Name - " + generic.getName());
			System.out.println("Birthday - " + generic.getDateBirth());
			System.out.println("Phone - " + generic.getPhone());
			System.out.println("Salary - " + generic.getSalary());
			System.out.println("Employment - " + generic.getEmployment().getEmployment().getName());
		
		} catch(NullPointerException e) {
			System.out.println("O erro tá no ponteiro que não aponta pra nada");
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
		} catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("-------------------------------------------------------------------------");
			addEmployee();
		}
	}	
	/**
	 * Edita(seta) os atributos do Funcionário;
	 * @throws StupidUserException
	 */
	public void editEmployee() {
		try {
			int option = 0;
			do {
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("Please enter the number corresponding to your choice: ");
				// Listando todos os Funcionarios;
				listEmployees();
				option = conversionStringToInt(keyboard.nextLine()) - 1;
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

				option = conversionStringToInt(keyboard.nextLine());
					
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
					Date dateBirth = strToDate(keyboard.nextLine());
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
					/*
					 * Listar os cargos apenas na tela Cargo e não na tela funcionário
					 */
					System.out.println("--------------------------------------------------------------------------------");
					System.out.println("Enter the number corresponding to the new employment");
					int i = 1;
					for(Employment e : employeeCtrl.listEmployments()) {
						System.out.println(i + "º - " + e.getName());
						i++;
					}
					System.out.println("--------------------------------------------------------------------------------");
					int employment = conversionStringToInt(keyboard.nextLine());
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
			System.out.println(e.getMessage());
			editEmployee();
		} catch(NumberFormatException e) {
			System.out.println(e.getMessage());
			editEmployee();
		} catch(ParseException e) {
			
		}
		
	}
	
	public void delEmployee() {
		try {
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("Select an employee to fire");
	
			listEmployees();
			
			int option = conversionStringToInt(keyboard.nextLine()) - 1;
			employeeCtrl.delEmployee(option);
		
		} catch(NullPointerException e) {
			System.out.println(e.getMessage());
			delEmployee();
		} catch(NumberFormatException e) {
			System.out.println("");
		}
		
	}
	
	public void listEmployees() {
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
	
	public int conversionStringToInt(String data) throws NumberFormatException {
		try {
			int num = Integer.parseInt(data);
			return num;
		} catch(NumberFormatException e) {
			throw new NumberFormatException();
		}
	}
	
	public double conversionStringToDouble(String data) throws NumberFormatException {
		try {
			double num = Double.parseDouble(data);	
			return num;
		} catch(NumberFormatException e ) {
			throw new NumberFormatException();
		}
	}

	public Date strToDate(String data) throws ParseException {
		if (data == null) {
            return null;
        }
        Date dataF = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            long time = dateFormat.parse(data).getTime();
            dataF = new Date(time);
        } catch (ParseException e) {
            throw new ParseException(data, 0);
        }
        return dataF;
	}


	public Date strToDateHour(String data) throws ParseException {
		if (data == null) {
            return null;
        }
        Date dataF = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("HH:mm");
            long time = dateFormat.parse(data).getTime();
            dataF = new Date(time);
        } catch (ParseException e) {
        	throw new ParseException(data, 0);
        }
        return dataF;
	}
	
}