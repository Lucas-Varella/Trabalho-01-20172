package ine.view;

import ine.controller.EmploymentCtrl; 
import ine.model.Contract;
import ine.model.Employment;
import ine.model.EmploymentRestrictAccess;
import ine.model.Horary;
import ine.model.Privileges;
import ine.model.Screen;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
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
					listEmployments();
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
		} catch(IndexOutOfBoundsException e) {
			System.out.println("No charge registered. Please register a position before attempting this option");
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
					int choice = 0;
					gen = Privileges.Restricted;
					EmploymentRestrictAccess e = employmentCtrl.addEmploymentRestrictAccess(name, gen);
					do{
						Horary h = employmentCtrl.addHorary();
						employmentCtrl.setHorary(e, h);
						System.out.println("Do you want to add another access time?");
						System.out.printf("Enter 1 for yes \nEnter 0 for no");
						choice = conversionStringToInt(keyboard.nextLine());
						if(choice < 0 || choice > 1) {
							System.out.println("The number you entered is not valid");
						}
					}while(choice != 0);
					System.out.println("Congratulations, you just successfully registered a employment!");
					System.out.println("These are the employment data:");
					System.out.println("Code: " + (EmploymentCtrl.getCode() - 1 ));
					System.out.println("Name: " + name);
					System.out.println("Privilege: " + gen);
					System.out.println("Horary Access: " );
					for(Horary h : employmentCtrl.getHorarys(e)) {
						System.out.println(h.getHourBegin() + " - " + h.getHourFinish());
					}
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
				System.out.println("Congratulations, you just successfully registered a employment!");
				System.out.println("These are the employment data:");
				System.out.println("Code: " + (EmploymentCtrl.getCode() - 1 ));
				System.out.println("Name: " + name);
				System.out.println("Privilege: " + gen);
			}
		
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
				listEmployments();
				option = conversionStringToInt(keyboard.nextLine()) - 1;
				Employment generic = employmentCtrl.getEmployment(option);
				
				if(generic.getPrivilege().equals(Privileges.Restricted)) {
					EmploymentRestrictAccess gen = (EmploymentRestrictAccess) generic;
					System.out.println("Please enter the number corresponding to the characteristic you want to change: ");
					System.out.println("1 - Name");
					System.out.println("Actually - " + gen.getName());
					System.out.println("2 - Privilege");
					System.out.println("Actually - " + gen.getPrivilege());
					System.out.println("3 - Horary Access");
					System.out.println("Actually - ");
					listHorary(gen);
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
						int option2 = 0;
						do {
							System.out.println("1 - " + Privileges.Full);
							System.out.println("2 - " + Privileges.Restricted);
							System.out.println("3 - " + Privileges.No);
							option2 = conversionStringToInt(keyboard.nextLine());
							switch (option2) {
							
							case 1:
								int code = gen.getCode();
								name = gen.getName();
								ArrayList<Contract> c = gen.getEmployees();
								employmentCtrl.delEmployment(gen);
								Employment newEmployment = null;
								newEmployment = employmentCtrl.addEmployment(code, name, Privileges.Full);
								for(Contract ct : c) {
								try {
									newEmployment.addContract(ct);
								} catch (Exception e) {								
									System.out.println(e.getMessage());
								}
							}
								
								break;
							
							case 2:
								generic.setPrivilege(Privileges.Restricted);
								break;
						
							case 3:
								code = gen.getCode();
								name = gen.getName();
								employmentCtrl.delEmployment(gen);
								employmentCtrl.addEmployment(code, name, Privileges.No);
								break;
							
							default:
								System.out.println("Choice a valid number!");
							}
		
						} while (option2 > 3 && option2 < 1);
						
					case 3:
						System.out.println("Enter 1 to edit a time");
						System.out.println("Enter 2 to delete a time");
						
						int choice = conversionStringToInt(keyboard.nextLine());
						switch(choice) {
						
						case 1:
							System.out.println("Please enter the number associated with the time interval you wish to edit");
							listHorary(gen);
							choice = conversionStringToInt(keyboard.nextLine()) - 1;
							Horary newHorary = employmentCtrl.editHorary(gen.getHorary(choice));
							gen.addHoraryByIndex(newHorary, choice);
							
						case 2:
							System.out.println("Please enter the number associated with the time interval you wish to edit");
							listHorary(gen);
							choice = conversionStringToInt(keyboard.nextLine()) - 1;
							gen.delHorary(choice);
							break;
						
						default:
							System.out.println("Please enter a valid option ");	
						}
		
					case 0:
		
						System.out.println("Goodbye");
		
					}


				}else {
						
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
							int option2 = 0;
							do {
							
								System.out.println("1 - " + Privileges.Full);
								System.out.println("2 - " + Privileges.Restricted);
								System.out.println("3 - " + Privileges.No);
								
								option2 = conversionStringToInt(keyboard.nextLine());
								
								switch (option2) {
								case 1:
									generic.setPrivilege(Privileges.Full);
									break;
								
								case 2:
									int code = generic.getCode();
									name = generic.getName();
									employmentCtrl.delEmployment(generic);
									EmploymentRestrictAccess e = employmentCtrl.addEmploymentRestrictAccess(code, name, Privileges.Restricted);
									int choice = 0;
									do{
										Horary h = employmentCtrl.addHorary();
										employmentCtrl.setHorary(e, h);
										System.out.println("Do you want to add another access time?");
										System.out.printf("Enter 1 for yes \nEnter 0 for no");
										choice = conversionStringToInt(keyboard.nextLine());
										if(choice < 0 || choice > 1) {
											System.out.println("The number you entered is not valid");
										}
									}while(choice != 0);
									break;
								
								case 3:
									generic.setPrivilege(Privileges.No);
									break;
								
								default:
									System.out.println("Choice a valid number!");
								}
			
							} while (option2 > 3 && option2 < 1);
			
						case 0:
			
							System.out.println("Goodbye");
			
						}
					}
		
			
			}while (option != 0);
				
			
		} catch(IndexOutOfBoundsException e) {

			editEmployment();
		} catch(InputMismatchException e) {
			
			editEmployment();
		} catch(NumberFormatException e) {
			System.out.println("Please enter only valid numbers");
			editEmployment();
		}
	}

	public void delEmployment() {
		/*
		 * Criar um método que verifica se o cargo não possuí funcionários associados a ele
		 */
		try {
			
			
			System.out.println("Please, enter the number corresponding to your choice");
			listEmployments();
			int option = conversionStringToInt(keyboard.nextLine()) - 1;
			Employment e = employmentCtrl.getEmployment(option);
			if(e.getEmployees().size() > 0) {
				System.out.printf("The selected position has employees associated with it. \nIf you continue with this action, all of these employees will be deleted.");
				System.out.printf("\nEnter 1 to continue \nEnter 2 to abort action");
				option = conversionStringToInt(keyboard.nextLine());
				
				switch(option) {
					
				case 1: 
					employmentCtrl.delEmployment(e);
				}
			}
			
		}catch(NumberFormatException e) {
			System.out.println("Please enter only valid numbers");
			delEmployment();
		}catch(IndexOutOfBoundsException e) {
			System.out.println("No charge registered. Please register a position before attempting this option");
			delEmployment();
		}
	}
	
	public void listEmployments() throws IndexOutOfBoundsException {
		int i = 1;
		
		if(employmentCtrl.listEmployments().size() > 0) {
			for(Employment e : employmentCtrl.listEmployments()) {
				System.out.println(i+"º - "+ e.getName());
				i++;
			}
		}else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	
	public void listHorary(EmploymentRestrictAccess gen) {
		int i = 1;
		for(Horary h : gen.getHorarys()) {
			System.out.println(i + "º - " + h.getHourBegin() +" - " + h.getHourFinish() );
			i++;
		}
	}
	
	public void findEmployeesByEmployment() {
		System.out.println("Enter the number corresponding to your choice:");
		listEmployments();
		int choice = conversionStringToInt(keyboard.nextLine()) - 1;
		employmentCtrl.listEmployees(employmentCtrl.getEmployment(choice));
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
