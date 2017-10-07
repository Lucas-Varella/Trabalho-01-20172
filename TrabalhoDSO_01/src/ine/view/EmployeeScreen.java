package ine.view;

import ine.controller.*;
import ine.model.*;
import ine.view.*;

import ine.controller.EmployeeCtrl;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;


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
			
				System.out.println("Welcome!");
				System.out.println("Please enter the number corresponding to your choice: ");
				System.out.println("1 - Add employee");
				System.out.println("2 - Edit employee");
				System.out.println("3 - Delete employee");
				System.out.println("4 - List employees");
				System.out.println("0 - Get out");
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
					employeeCtrl.listEmployees();
					break;
				
				case 0:
					System.out.println("Goodbye and have a good day");
					break;
				
				default:
					System.out.println("The number you entered is not valid. Please try again.");
				}
				
			} while (option != 0);
			employeeCtrl.mainMenu();

		} catch(NumberFormatException e) {
			System.out.println(e.getMessage());
			menu();
		}

		
	}
	/**
	 * O método addEmployee() adiciona um novo funcionário
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
			
			/*
			 * Verifica qual é o privilégio do Cargo do Funcionário;
			 * Se o privilegio for Restrict cai no if, senão cai no else;
			 */
			if(gen.getPrivilege().equals(Privileges.Restricted)) {
				EmployeeRestrictAccess generic = employeeCtrl.addEmployeeRestrict(name, birthDay, phone, salary);
				Contract contract = employeeCtrl.addContract(gen, generic);
				Horary horary = employeeCtrl.addHorary();
				employeeCtrl.setHorary(horary);
				System.out.println("Congratulations, you have created a new employee with the following characteristics: ");
				System.out.println("Number of registration - " + generic.getNumRegistration());
				System.out.println("Name - " + generic.getName());
				System.out.println("Birthday - " + generic.getDateBirth());
				System.out.println("Phone - " + generic.getPhone());
				System.out.println("Salary - " + generic.getSalary());
				System.out.println("Employment - " + generic.getEmployment().getEmployment().getName());
				System.out.println("Horary - ");
				employeeCtrl.getHours(generic).listHorary();
				
			}else {
				Employee generic = employeeCtrl.addEmployee(name, birthDay, phone, salary);
				Contract contract = employeeCtrl.addContract(gen, generic);
				
				System.out.println("Congratulations, you have created a new employee with the following characteristics: ");
				System.out.println("Number of registration - " + generic.getNumRegistration());
				System.out.println("Name - " + generic.getName());
				System.out.println("Birthday - " + generic.getDateBirth());
				System.out.println("Phone - " + generic.getPhone());
				System.out.println("Salary - " + generic.getSalary());
				System.out.println("Employment - " + generic.getEmployment().getEmployment().getName());
			} 
		
		} catch(NullPointerException e) {
			System.out.println("O erro tá no ponteiro que não aponta pra nada");
			addEmployee();
		} catch(NumberFormatException e) {
			System.out.println("The number you entered is not valid");
			addEmployee();
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Please register first a position before registering an employee");
			addEmployee();
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Sorry, an error has occurred. This was due to the fact that an employee's \n" + 
							   "registration was being made, without first registering a charge.\nPlease " + 
							   "correct this error by registering a position before registering an employee");
			addEmployee();
		} catch(ParseException e) {
			System.out.println("The date format entered by the user is not correct\n"+ 
							   "Please try again based on this format:\n" + "dd/MM/yyyy");
			addEmployee();
		} catch (Exception e) {
			
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
				System.out.println("Please enter the number corresponding to your choice: ");
				// Listando todos os Funcionários;
				int i = 1;
				for(Employee e : employeeCtrl.listEmployees()) {
					System.out.println(i + "º - " + e.getName());
					i++;
				}
				option = conversionStringToInt(keyboard.nextLine()) - 1;
				Employee generic = employeeCtrl.getEmployee(option);
				
				System.out.println("Please enter the number corresponding to the characteristic you want to change: ");
				/*
				 * Verifica se o funcionário escolhido possue um cargo cujo
				 * privilégio é Restrict;
				 */
				if(generic.getEmployment().getEmployment().getPrivilege().equals(Privileges.Restricted)) {
					EmployeeRestrictAccess gen = (EmployeeRestrictAccess) generic;
					System.out.println("1 - Name");
					System.out.println("Actually - " + gen.getName());
					System.out.println("2 - Birthday");
					System.out.println("Actually - " + gen.getDateBirth());
					System.out.println("3 - Phone");
					System.out.println("Actually - " + gen.getPhone());
					System.out.println("4 - Salary");
					System.out.println("Actually - " + gen.getSalary());
					System.out.println("5 - Employment");
					System.out.println("Actually - " + gen.getEmployment().getEmployment().getName());
					System.out.println("6 - Horary Access");
					System.out.println("Actually - ");
					employeeCtrl.getHours(gen).listHorary();
					System.out.println("Or 0 to exit");
					
					option = conversionStringToInt(keyboard.nextLine());
					switch(option) {
					
					case 1:
						System.out.println("Enter a new name");
						String name = keyboard.nextLine();
						employeeCtrl.setName(name);
						break;
						
					case 2:
						System.out.println("Enter a new birthday");
						Date birthday = strToDate(keyboard.nextLine());
						employeeCtrl.setDateBirth(birthday);
						break;
					
					case 3:
						System.out.println("Enter a new phone");
						int phone = conversionStringToInt(keyboard.nextLine());
						employeeCtrl.setPhone(phone);
						break;
					
					case 4:
						System.out.println("Enter a new salary");
						double salary = conversionStringToDouble(keyboard.nextLine());
						employeeCtrl.setSalary(salary);
						break;
					
					case 5:
						System.out.println("Enter the number corresponding to the new employment");
						i = 1;
						for(Employment e : employeeCtrl.listEmployments()) {
							System.out.println(i + "º - " + e.getName());
							i++;
						}
						int employment = conversionStringToInt(keyboard.nextLine());
						Employment e = employeeCtrl.findEmploymentByIndex(employment);
						employeeCtrl.setEmployment(e);
						break;
					
					case 6:
						System.out.println("Enter 1 to change start times");
						System.out.println("Enter 2 to change end times");
						int choice = conversionStringToInt(keyboard.nextLine());
					
						switch(choice) {
						
						case 1:
							System.out.println("Please enter the number for the time that you wish to change");
							int array = conversionStringToInt(keyboard.nextLine());
							System.out.println("Please enter the new time");
							String horary = keyboard.nextLine();
							employeeCtrl.editHour(choice, array, horary);
							break;
						
						case 2:
							System.out.println("Please enter the number for the time that you wish to change");
							array = conversionStringToInt(keyboard.nextLine());
							System.out.println("Please enter the new time");
							horary = keyboard.nextLine();
							employeeCtrl.editHour(choice, array, horary);
							break;
						
						default:
							System.out.println("The number you entered is not valid");
						}
					
					}
					
				} else {
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
					option = conversionStringToInt(keyboard.nextLine());
					
					switch (option) {
		
					case 1:
						System.out.println("Enter a new name: ");
						String name = keyboard.nextLine();
						employeeCtrl.setName(name);
						break;
		
					case 2:
						System.out.println("Enter a new birthday: ");
						Date dateBirth = strToDate(keyboard.nextLine());
						employeeCtrl.setDateBirth(dateBirth);
						break;
						
					case 3:
						System.out.println("Enter a new phone");
						int phone = keyboard.nextInt();
						employeeCtrl.setPhone(phone);
						break;
					
					case 4:
						System.out.println("Enter a new salary");
						int salary = keyboard.nextInt();
						employeeCtrl.setSalary(salary);
						break;
					
					case 5:
						System.out.println("Enter the number corresponding to the new employment");
						i = 1;
						for(Employment e : employeeCtrl.listEmployments()) {
							System.out.println(i + "º - " + e.getName());
							i++;
						}
						int employment = conversionStringToInt(keyboard.nextLine());
						Employment gen = employeeCtrl.findEmploymentByIndex(employment);
						employeeCtrl.setEmployment(gen);
						break;
						
					case 0:
						System.out.println("Goodbye");
						break;
					
					default:
						System.out.println("Please, enter a valid number");			
					}
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
			System.out.println("Select an employee to fire");
	
			int i = 1;
			for(Employee e : employeeCtrl.listEmployees()) {
				System.out.println(i + "º - " + e.getName());
				i++;
			}
			
			int option = conversionStringToInt(keyboard.nextLine()) - 1;
			employeeCtrl.delEmployee(option);
		
		} catch(NullPointerException e) {
			System.out.println(e.getMessage());
			delEmployee();
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