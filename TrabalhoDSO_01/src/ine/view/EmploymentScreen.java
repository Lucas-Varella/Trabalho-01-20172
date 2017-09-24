package ine.view;

import ine.controller.EmploymentCtrl;
import ine.model.Employment;
import ine.model.Privileges;

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
					addEmployment();
					break;				
				
				case 2:
					editEmployment();
					break;
				
				case 3:
					
					//mudar o que é printado(com vocês, Varella e Marcos)
					
					System.out.println("Please enter the number corresponding to your choice: ");
					employmentCtrl.listEmployments();
					int choice = keyboard.nextInt() - 1; 
					employmentCtrl.delEmployment(employmentCtrl.getEmployment(choice));
					break;
				
				case 0:
					System.out.println("Goodbye and have a good day");
					employmentCtrl.mainMenu();
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
	/*
	 * Os métodos abaixo eram uma ideia que eu tinha de dividir cada ação tomada na tela(Adicionar, editar e excluir employments)
	 * em várias partes e apenas os invocar no tela da classe quando necessário, porém, conforme eu fui codando, acabei me esquecendo desta
	 * ideia, mas se alguém quiser a implementar, fique à vontade;
	 */
	
	/*
	 * Resolvi não dar ao usuário a alternativa de ele escolher o código 
	 * do cargo. Ao invés disso, criei(+/-) um método dentro do controlador 
	 * que gera o código automaticamente;
	 */
	public void addEmployment() {
		System.out.println("Please enter the name of employment:");
		String name = keyboard.nextLine();
		int option = 0;
		Privileges gen = null;
		do{
		System.out.println("Choice your privilege: ");
		System.out.println("1 - " +Privileges.Full);
		System.out.println("2 - " +Privileges.Restricted);
		System.out.println("3 - " +Privileges.No);
		//O erro estava neste caralho de linha logo abaixo;
		//keyboard.nextLine();
		option = keyboard.nextInt();
		
			switch(option) {
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
		}while(option > 3 || option == 0);
		employmentCtrl.addEmployment(name, gen);
		System.out.println("Congratulations, you just successfully registered a employment!");
		System.out.println("These are the employment data:");
		System.out.println("Code: " + EmploymentCtrl.getCode() + 1);		
		System.out.println("Name: " + name);		
		System.out.println("Privilege: " + gen);
	}
	
	public void editEmployment() {
		int option = 0;
		do{
			System.out.println("Please enter the number corresponding to your choice: ");
			employmentCtrl.listEmployments();
			
			/*
			 * Fazer um try catch aqui, caso o usuário seja filho da puta ao
			 * ponto de digitar 0;
			 */
			
			option = keyboard.nextInt() - 1; 
			Employment generic = employmentCtrl.getEmployment(option);
			
			System.out.println("Please enter the number corresponding to the characteristic you want to change: ");
			/*
			 * recomendo não dar ao usuário o poder de mudar o código do cargo
			 *
			 * System.out.println("1 - Code");
			 * System.out.println("Actually - " + generic.getCode());
			*/
			System.out.println("1 - Name");
			System.out.println("Actually - " + generic.getName());
			System.out.println("2 - Privilege");
			System.out.println("Actually - " + generic.getPrivilege());
			System.out.println("Or 0 to exit");
			option = keyboard.nextInt();
			switch(option) {
			
			/*
			case 1:
				System.out.println("Enter a new code: ");
				option = keyboard.nextInt();
				generic.setCode(option);
				break;
			*/
			case 1:
				System.out.println("Enter a new name: ");
				String name = keyboard.nextLine();
				generic.setName(name);
				break;
			
			case 2:
				System.out.println("Enter the number of a new privilege: ");
				do{
					System.out.println("1 - " +Privileges.Full);
					System.out.println("2 - " +Privileges.Restricted);
					System.out.println("3 - " +Privileges.No);
					keyboard.nextLine();
					option = keyboard.nextInt();
					switch(option) {
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
					
				}while(option != 1 && option !=2 && option != 3);
			
			case 0:

				System.out.println("Goodbye");
					
			}
		}while(option != 0);
	}
	
	public void delEMployment() {
		
	}
	
	

}
