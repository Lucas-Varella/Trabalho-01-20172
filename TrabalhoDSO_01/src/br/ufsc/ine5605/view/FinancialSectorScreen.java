package br.ufsc.ine5605.view;

import java.text.ParseException;   
import java.util.Date;
import java.util.Scanner;

import br.ufsc.ine5605.controller.FinancialSectorCtrl;
import br.ufsc.ine5605.model.Reasons;

/**
 * Classe responsável por realizar o input e output de dados referentes as Setor Financeiro;
 * 
 * @author Sadi Júnior Domingos Jacinto;
 */
public class FinancialSectorScreen {
	private FinancialSectorCtrl financialSectorCtrl;
	private Scanner keyboard;

	/**
	 * Construtor padrão da classe FinancialSectorScrenn;
	 * 
	 * @param financialSectorCtrl - recebe uma instância do FinancialSectorCtrl, para que possa 
	 * transferir o input para que o programa os trate;
	 * 
	 * @author Sadi Júnior Domingos Jacinto;
	 */
	public FinancialSectorScreen(FinancialSectorCtrl financialSectorCtrl) {
		this.financialSectorCtrl = financialSectorCtrl;
		keyboard = new Scanner(System.in);
	}
	
	/**
	 * Menu principal do Setor Financeiro;
	 * Redireciona o usuário para outros métodos baseado no input do mesmo;
	 * 
	 * @throws NumberFormatException sendo que tal exceção ocorre quando o usuário digita um 
	 * caracter alfabético ou não-imprimível num campo destinado à números inteiros;
	 * 
	 * @author Sadi Júnior Domingos Jacinto;
	 */
	public void menuFinancialSector() {//Método já revisado;
		
		try {
		
			int option = 0;
			do {
			
				System.out.println("Please, enter the number corresponding to your choice: ");
				System.out.println("1 - Entering the Financial Sector");
				System.out.println("2 - Denied Access Report");
				System.out.println("Or 0 to exit");
				System.out.println("-------------------------------------------------------------------------");
				option = financialSectorCtrl.conversionStringToInt(keyboard.nextLine());
				
				switch(option) {
				
				case 1:
					enterFinancialSector();
					break;
					
				case 2:
					showDeniedAccess();
					break;
				
				case 0:
					System.out.println("Goodbye");
					break;
				
				default:
					System.out.println("The number you entered is not valid");	
				}
				
			}while(option != 0);
			financialSectorCtrl.mainMenu();
			
		}catch(NumberFormatException e) {
			System.out.println("Please enter only valid numbers");
			menuFinancialSector();
		}
	}
	
	/**
	 * Menu de entrada do Setor Financeiro;
	 * Durante a excecução do método, são requeridos dois dados:
	 * 1º - Número de registro, sendo válido ou não e 
	 * 2º - Hora de acesso;
	 * 
	 * @throws NumberFormatException sendo que tal exceção ocorre quando o usuário digita um 
	 * caracter alfabético ou não-imprimível num campo destinado à números inteiros;
	 * 
	 * @throws ParseException ocorre caso o usuário digite um horário fora do padrão HH:mm 
	 * no momento onde é requerido ao mesmo a hora de acesso;
	 * 
	 * @throws NullPointerException caso esse erro ocorra, por favor, contate urgentemente o suporte;
	 * 
	 * @author Sadi Júnior Domingos Jacinto;
	 */
	public void enterFinancialSector() {//Método revisado, OK;
		
		try {
			System.out.println("Welcome to the entrance of the Financial Sector, noble adventurer");
			System.out.println("To continue your quest for capital, please enter your registration number and," + 
								"\nif you are worthy, you may enter this sacred place");
			System.out.println("Number of registration: ");
			int num = financialSectorCtrl.conversionStringToInt(keyboard.nextLine());
			System.out.println("Time of access: ");
			Date hourAccess = financialSectorCtrl.strToDateHour(keyboard.nextLine());
			
			//aqui eu pego a hora atual do sistema, no caso o dia
			
			Date dateAccess = financialSectorCtrl.getCurrenteDate();
			
			boolean valid = financialSectorCtrl.validAccess(num, hourAccess, dateAccess);
			
			if(valid) {
				System.out.println("You are worthy to enter this sacred place");
			}else {
				System.out.println("You are not worthy to enter this place, adventurer, for:");
				System.out.println(financialSectorCtrl.getReasonBy(num, hourAccess, dateAccess));
			}
		
		}catch(NumberFormatException e) {
			System.out.println("Please enter only valid numbers");
			enterFinancialSector();
		
		}catch(ParseException e) {
			System.out.println("A conversion error has occurred, make sure you have entered the required data correctly");
			enterFinancialSector();
		
		}catch(NullPointerException e) {
			System.out.println("If you are reading this message, \nit means that an unexpected error has occurred. \nPlease contact support for help");
			enterFinancialSector();
		}
		

	}
	
	/**
	 * Menu que exibe um relatório de acessos negados, sendo que tais acessos podem ser filtrados de 
	 * acordo com a vontade do usuário;
	 * 
	 * @throws NumberFormatException ocorrendo quando o usuário digita um caracter 
	 * alfabético ou não-imprimível num campo destinado à números inteiros;
	 * 
	 * @throws IndexOutOfBoundsException ocorre quando o usuário tenta listar os acessos negados
	 * quando não existe nenhum acesso negado cadastrado no sistema;
	 * 
	 * @throws NullPointerException caso esse erro ocorra, por favor, contate urgentemente o suporte;
	 * 
	 * @author Sadi Júnior Domingos Jacinto;
	 */
	public void showDeniedAccess() {//Método revisado, Ok;
		try {
			System.out.println("Please enter the number corresponding to your choice: ");
			System.out.println("1 - List all denied access");
			System.out.println("2 - List the denied accesses given a number of registration");
			System.out.println("3 - List the access denied by the motive of denial");
			System.out.println("0 - Exit");
			
			int option = financialSectorCtrl.conversionStringToInt(keyboard.nextLine());
			switch(option) {
			
			case 1:
				financialSectorCtrl.showAllDeniedAccess();
				break;
				
			case 2: 
				System.out.println("Please enter the registration number: ");
				int numRegistration = financialSectorCtrl.conversionStringToInt(keyboard.nextLine());
				financialSectorCtrl.showDeniedAccessByNumRegistration(numRegistration);
				break;
				
			case 3:
				System.out.println("Please enter the corresponding number for the denial of access: ");
				System.out.println("1 - The number registration does not exist");
				System.out.println("2 - You do not have access");
				System.out.println("3 - The access time is not allowed");
				System.out.println("4 - Access blocked");
				int reason = financialSectorCtrl.conversionStringToInt(keyboard.nextLine());
				
				switch(reason) {
				
				case 1:
					financialSectorCtrl.showDeniedAccessByReason(Reasons.NONUMREGS);
					break;
					
				case 2:
					financialSectorCtrl.showDeniedAccessByReason(Reasons.NOACCESS);
					break;
					
				case 3:
					financialSectorCtrl.showDeniedAccessByReason(Reasons.INCTIME);
					break;
					
				case 4:
					financialSectorCtrl.showDeniedAccessByReason(Reasons.BLOCK);
					break;
					
				default:
					System.out.println("The number you entered is not valid");
				}
				break;
				
			case 0:
				financialSectorCtrl.mainMenu();
				break;
				
			default:
				System.out.println("The number you entered is not valid");

			}
			
		} catch(NumberFormatException e) {
			System.out.println("Please enter only a valid number");
			showDeniedAccess();
		
		} catch(IndexOutOfBoundsException e) {
			System.out.println("There is no denied access to the system");
			showDeniedAccess();
		
		} catch(NullPointerException e) {
			System.out.println("There is no denied access to the system");
			showDeniedAccess();
		}
		

	}
}