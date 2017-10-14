package br.ufsc.ine5605.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.ufsc.ine5605.controller.EmploymentCtrl;
import br.ufsc.ine5605.model.Contract;
import br.ufsc.ine5605.model.Employee;
import br.ufsc.ine5605.model.Employment;
import br.ufsc.ine5605.model.EmploymentRestrictAccess;
import br.ufsc.ine5605.model.Horary;
import br.ufsc.ine5605.model.Privileges;

/**
 * Classe responsável pela interação entre o usuário com o sistema de cadastro, 
 * edição, listagem e remoção de cargos;
 * 
 * @author Sadi Júnior Domingos Jacinto;
 */
public class EmploymentScreen {
	private EmploymentCtrl employmentCtrl;
	private Scanner keyboard;
	
	/**
	 * Construtor padrão da classe;
	 * @param employmentCtrl - Recebe uma instância do EmploymentCtrl, o que permite
	 * a comunicação desta classe com as demais;
	 */
	public EmploymentScreen(EmploymentCtrl employmentCtrl) {
		this.employmentCtrl = employmentCtrl;
		keyboard = new Scanner(System.in);
	}
	/**
	 * Menu principal da classe EmploymentScreen. Redireciona o usuário para telas
	 * secundárias de acordo com o input do usuário
	 */
	public void menu() {
		
		try {
			
			int option = 0;
			do {
				
				System.out.println("-------------------------------------------------------------------------");
				System.out.println("Welcome!");
				System.out.println("Please enter the number corresponding to your choice: ");
				System.out.println("1 - Register new employment");
				System.out.println("2 - Edit employment");
				System.out.println("3 - Delete employment");
				System.out.println("4 - List employments");
				System.out.println("5 - List employees in a employment ");
				System.out.println("0 - Back to main Screen");
				System.out.println("-------------------------------------------------------------------------");
				option = employmentCtrl.conversionStringToInt(keyboard.nextLine());
				
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
					listEmployments();
					break;

				case 5:
					findEmployeesByEmployment();
					break;
				
				case 0:
					System.out.println("-------------------------------------------------------------------------");
					break;

				default:
					System.out.println("The number you entered is not valid. Please try again.");
				}
			
			} while(option != 0);
			employmentCtrl.mainMenu();
		
		} catch (NumberFormatException e) {
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("Please enter only numbers");
			menu();
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("The number entered is not valid");
			menu();
		} catch(IndexOutOfBoundsException e) {
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("No charge registered. Please, register a position before choosing this option"); 
			menu();
		}
		
	}

	/**
	 * Obtém do usuário os dados necessários para a criação de um Cargo e
	 * os envia para a classe EmploymentCtrl;
	 */
	public void addEmployment() {
	 
		try {
			
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("Please enter the name of employment:");
			String name = keyboard.nextLine();
			int option = 0;
			Privileges gen = null;
			
			do {
				System.out.println("-------------------------------------------------------------------------");
				System.out.println("Choose your privilege: ");
				System.out.println("1 - " + Privileges.Full);
				System.out.println("2 - " + Privileges.Restricted);
				System.out.println("3 - " + Privileges.No);
				option = employmentCtrl.conversionStringToInt(keyboard.nextLine());
	
				switch (option) {
				
				case 1:
					gen = Privileges.Full;
					break;
				
				case 2:
					int choice = 0;
					gen = Privileges.Restricted;
					EmploymentRestrictAccess e = employmentCtrl.addEmploymentRestrictAccess(name, gen);
					do{
						Horary h = employmentCtrl.addHorary();
						employmentCtrl.setHorary(e, h);
						System.out.println("-------------------------------------------------------------------------");
						System.out.println("Do you want to add another access time?");
						System.out.printf("Enter 1 for yes \nEnter 0 for no");
						choice = employmentCtrl.conversionStringToInt(keyboard.nextLine());
						if(choice < 0 || choice > 1) {
							System.out.println("The number you entered is not valid");
							System.out.println("-------------------------------------------------------------------------");
						}
					}while(choice != 0);
					System.out.println("-------------------------------------------------------------------------");
					System.out.println("Congratulations, you just successfully registered a employment!");
					System.out.println("These are the employment data:");
					System.out.println("Code: " + (EmploymentCtrl.getCode() - 1 ));
					System.out.println("Name: " + name);
					System.out.println("Privilege: " + gen);
					System.out.println("Horary Access: " );
					listHorary(e);
					break;
				
				case 3:
					gen = Privileges.No;
					break;
				
				default:
					System.out.println("The number you entered is not valid. Please try again.");
				}
			
			} while (option > 3 || option <= 0);
			
			if(option == 1 || option == 3) {
				employmentCtrl.addEmployment(name, gen);
				System.out.println("-------------------------------------------------------------------------");
				System.out.println("Congratulations, you just successfully registered a employment!");
				System.out.println("These are the employment data:");
				System.out.println("Code: " + (EmploymentCtrl.getCode() - 1 ));
				System.out.println("Name: " + name);
				System.out.println("Privilege: " + gen);
			}
		
		} catch(NumberFormatException e) {
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("Please enter only integers");
			addEmployment();
		}
	}
	
	/**
	 * Tela responsável pela interação do usuário com os métodos de edição dos atributos de um Employment;
	 */
	public void editEmployment() {
		try {
	
			int option = 0;
			do {
				
				System.out.println("-------------------------------------------------------------------------");
				System.out.println("Please enter the number corresponding to your choice: ");
				listEmployments();
				option = employmentCtrl.conversionStringToInt(keyboard.nextLine()) - 1;
				Employment generic = employmentCtrl.getEmployment(option);
				
				if(generic.getPrivilege().equals(Privileges.Restricted)) {
					EmploymentRestrictAccess gen = (EmploymentRestrictAccess) generic;
					System.out.println("--------------------------------------------------------------------------------");
					System.out.println("Please enter the number corresponding to the characteristic you want to change: ");
					System.out.println("1 - Name");
					System.out.println("Actually - " + gen.getName());
					System.out.println("2 - Privilege");
					System.out.println("Actually - " + gen.getPrivilege());
					System.out.println("3 - Horary Access");
					System.out.println("Actually - ");
					listHorary(gen);
					System.out.println("Or 0 to exit");
					System.out.println("--------------------------------------------------------------------------------");
					option = employmentCtrl.conversionStringToInt(keyboard.nextLine());
					
					switch (option) {
		
					case 1:
						System.out.println("-------------------------------------------------------------------------");
						System.out.println("Enter a new name: ");
						String name = keyboard.nextLine();
						generic.setName(name);
						break;
		
					case 2:
						System.out.println("-------------------------------------------------------------------------");
						System.out.println("Enter the number of a new privilege: ");
						int option2 = 0;
						do {
							System.out.println("1 - " + Privileges.Full);
							System.out.println("2 - " + Privileges.Restricted);
							System.out.println("3 - " + Privileges.No);
							option2 = employmentCtrl.conversionStringToInt(keyboard.nextLine());
							switch (option2) {
							
							case 1:
								int code = gen.getCode();
								name = gen.getName();
								ArrayList<Contract> c = gen.getEmployees();
								Employment newEmployment = null;
								newEmployment = employmentCtrl.addEmployment(code, name, Privileges.Full);
								for(Contract ct : c) {
									try {
										Employee e = ct.getEmployee();
										Contract newC = new Contract(newEmployment, e);
									} catch (Exception e) {								
										System.out.println(e.getMessage());
									}
								}
								employmentCtrl.delEmployment(gen);
								break;
							
							case 2:
								generic.setPrivilege(Privileges.Restricted);
								break;
						
							case 3:
								
								code = gen.getCode();
								name = gen.getName();
								c = gen.getEmployees();
								newEmployment = null;
								newEmployment = employmentCtrl.addEmployment(code, name, Privileges.Full);
								for(Contract ct : c) {
									try {
										Employee e = ct.getEmployee();
										Contract newC = new Contract(newEmployment, e);
									} catch (Exception e) {								
										System.out.println("-------------------------------------------------------------------------");
										System.out.println(e.getMessage());
									}
								}
								employmentCtrl.delEmployment(gen);
								break;
								
							default:
								System.out.println("-------------------------------------------------------------------------");
								System.out.println("Choose a valid number!");
							}
		
						} while (option2 > 3 && option2 < 1);
						
					case 3:
						System.out.println("-------------------------------------------------------------------------");
						System.out.println("Enter 1 to edit a time");
						System.out.println("Enter 2 to delete a time");
						
						int choice = employmentCtrl.conversionStringToInt(keyboard.nextLine());
						switch(choice) {
						
						case 1:
							System.out.println("---------------------------------------------------------------------------");
							System.out.println("Please enter the number associated with the time interval you wish to edit");
							listHorary(gen);
							choice = employmentCtrl.conversionStringToInt(keyboard.nextLine()) - 1;
							Horary newHorary = employmentCtrl.editHorary(gen.getHorary(choice));
							gen.addHoraryByIndex(newHorary, choice);
							
						case 2:
							System.out.println("---------------------------------------------------------------------------");
							System.out.println("Please enter the number associated with the time interval you wish to edit");
							listHorary(gen);
							choice = employmentCtrl.conversionStringToInt(keyboard.nextLine()) - 1;
							gen.delHorary(choice);
							break;
						
						default:
							System.out.println("---------------------------------------------------------------------------");
							System.out.println("Please enter a valid option");	
						}
		
					case 0:
						System.out.println("Goodbye");
					}

				}else {
						
						System.out.println("------------------------------------------------------------------------------");
						System.out.println("Please enter the number corresponding to the characteristic you want to change: ");
						System.out.println("1 - Name");
						System.out.println("Actually - " + generic.getName());
						System.out.println("2 - Privilege");
						System.out.println("Actually - " + generic.getPrivilege());
						System.out.println("Or 0 to exit");
						System.out.println("------------------------------------------------------------------------------");
						option = employmentCtrl.conversionStringToInt(keyboard.nextLine());
						switch (option) {
			
						case 1:
							System.out.println("------------------------------------------------------------------------------");
							System.out.println("Enter a new name: ");
							String name = keyboard.nextLine();
							generic.setName(name);
							break;
			
						case 2:
							System.out.println("------------------------------------------------------------------------------");
							System.out.println("Enter the number of a new privilege: ");
							int option2 = 0;
							do {
							
								System.out.println("1 - " + Privileges.Full);
								System.out.println("2 - " + Privileges.Restricted);
								System.out.println("3 - " + Privileges.No);
								
								option2 = employmentCtrl.conversionStringToInt(keyboard.nextLine());
								
								switch (option2) {
								case 1:
									generic.setPrivilege(Privileges.Full);
									break;
								
								case 2:
									int code = generic.getCode();
									name = generic.getName();
									ArrayList<Contract> c = generic.getEmployees();
									EmploymentRestrictAccess newEmployment = null;
									newEmployment = employmentCtrl.addEmploymentRestrictAccess(code, name, Privileges.Restricted);
									for(Contract ct : c) {
										try {
											Employee e = ct.getEmployee();
											Contract newC = new Contract(newEmployment, e);
										} catch (Exception e) {								
											System.out.println(e.getMessage());
										}
									}
									employmentCtrl.delEmployment(generic);
									int choice = 0;
									do{
										Horary h = employmentCtrl.addHorary();
										employmentCtrl.setHorary(newEmployment, h);
										System.out.println("Do you want to add another access time?");
										System.out.printf("Enter 1 for yes \nEnter 0 for no");
										choice = employmentCtrl.conversionStringToInt(keyboard.nextLine());
										if(choice < 0 || choice > 1) {
											System.out.println("The number you entered is not valid");
										}
									}while(choice != 0);
									
									break;
								
								case 3:
									generic.setPrivilege(Privileges.No);
									break;
								
								default:
									System.out.println("Choose a valid number!");
								}
			
							} while (option2 > 3 && option2 < 1);
			
						case 0:
							System.out.println("Goodbye");			
						}
					}
		
			
			}while (option != 0);
				
			
		} catch(IndexOutOfBoundsException e) {
			System.out.println("No charge registered. Please register a position before attempting this option");
			menu();
		
		} catch(InputMismatchException e) {
			System.out.println("An internal error occurred. Please contact support");
			editEmployment();
		} catch(NumberFormatException e) {
			System.out.println("Please enter only valid numbers");
			editEmployment();
		} catch(StackOverflowError e) {
			System.out.println("No charge registered. Please register a position before attempting this option");
			menu();
		}
	}
	
	/**
	 * Tela responsável pela interação do usuário com o método de remoção de Employments do sistema;
	 * 
	 * @author Sadi Júnior Domingos Jacinto;
	 */
	public void delEmployment() {

		try {
			
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("Please, enter the number corresponding to your choice");
			listEmployments();
			int option = employmentCtrl.conversionStringToInt(keyboard.nextLine()) - 1;
			Employment e = employmentCtrl.getEmployment(option);
			if(e.getEmployees().size() > 0) {
				System.out.printf("The selected position has employees associated with it. \nIf you continue with this option," +" " +
								  "employees will be disassociated from the job and deleted from the system.");
				System.out.printf("\nEnter 1 to continue \nEnter 2 to abort action");
				option = employmentCtrl.conversionStringToInt(keyboard.nextLine());
				
				switch(option) {
					
				case 1: 
					employmentCtrl.delEmployment(e);
					System.out.println("Employment deleted successfully");
				case 2:
					System.out.println("Operation canceled");
					menu();
				default:
					System.out.println("The option you entered is not valid");
					delEmployment();
				}
			}else {
				employmentCtrl.delEmployment(e);
				System.out.println("Employment deleted successfully");
			}
			
		}catch(NumberFormatException e) {
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("Please enter only valid numbers");
			delEmployment();
		}catch(IndexOutOfBoundsException e) {
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("No charge registered. Please register a position before attempting this option");
			menu();
		}
	}
	
	/**
	 * Método responsável por listar os nomes de todos os cargos cadastrados no sistema;
	 * 
	 * @throws IndexOutOfBoundsException ocorrendo quando não existe nenhum cargo cadastrado no sistema;
	 */
	public void listEmployments() throws IndexOutOfBoundsException {
		int i = 1;
		
		if(employmentCtrl.getEmployments().size() > 0) {
			for(Employment e : employmentCtrl.getEmployments()) {
				System.out.println(i+"º - "+ e.getName());
				i++;
			}
		}else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	/**
	 * Método que lista os horários de acesso ao Setor Financeiro que um Cargo com privilégio Restrict tem;
	 * @param gen - Recebe um instância de um EmploymentRestrictAccess
	 */
	public void listHorary(EmploymentRestrictAccess gen) {
		for(Horary h : gen.getHorarys()) {
			System.out.println(h.getHourBegin() +" - " + h.getHourFinish() );
			
		}
	}
	
	/**
	 * Método que lista os funcionários associados a um Cargo;
	 * @throws IndexOutOfBoundsException ocorre quando o cargo não possui nenhum funcionário associado a ele;
	 */
	public void findEmployeesByEmployment() {
		try {
			System.out.println("---------------------------------------------------------------------------");
			System.out.println("Enter the number corresponding to your choice:");
			listEmployments();
			int choice = employmentCtrl.conversionStringToInt(keyboard.nextLine()) - 1;
			Employment employment = employmentCtrl.getEmployment(choice);
			if(employment.getEmployees().size() > 0 ){
				int i = 1;
				for(Contract c : employment.getEmployees()) {
					String name = c.getEmployee().getName();
					System.out.println(i+ "º - " + name);
					i++;
				}
			}else {
				throw new IndexOutOfBoundsException();
			}
			
		} catch(IndexOutOfBoundsException e) {
			System.out.println("No employee associated with this position. Please register an \nemployee who holds this position before selecting this option");
			menu();
		}
		
		
	}

	
}
