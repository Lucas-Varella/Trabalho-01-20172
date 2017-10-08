package ine.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import ine.controller.*;
import ine.model.*;

public class FinancialSectorScreen {
	private FinancialSectorCtrl financialSectorCtrl;
	private Scanner keyboard;

	public FinancialSectorScreen(FinancialSectorCtrl financialSectorCtrl) {
		this.financialSectorCtrl = financialSectorCtrl;
		keyboard = new Scanner(System.in);
	}
	
	public void menuFinancialSector() {
		try {
			int option = 0;
			do {
				System.out.println("Please, enter the number corresponding to your choice: ");
				System.out.println("1 - Entering the Financial Sector");
				System.out.println("2 - Denied Access Report");
				System.out.println("Or 0 to exit");
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
	
	public void enterFinancialSector() {
		try {
			System.out.println("Welcome to the entrance of the Financial Sector, noble adventurer");
			System.out.println("To continue your quest for capital, please enter your registration number and," + 
								"if you are worthy, you may enter this sacred place");
			System.out.println("Number of registration: ");
			int num = financialSectorCtrl.conversionStringToInt(keyboard.nextLine());
			System.out.println("Time of access: ");
			Date hourAccess = financialSectorCtrl.strToDateHour(keyboard.nextLine());
			
			//aqui eu pego a hora atual do sistema, no caso o dia
			
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
			String date = formatador.format(new Date());
			long time = formatador.parse(date).getTime();
			Date dateAccess = new Date(time);
			
			boolean valid = financialSectorCtrl.validAccess(num, hourAccess, dateAccess);
			if(valid) {
				System.out.println("You are worthy to enter this sacred place");
			}else {
				System.out.println("You are not worthy to enter this place, adventurer, for:");
				
			}
		}catch(NumberFormatException e) {
			System.out.println("Please enter only valid numbers");
			enterFinancialSector();
		}catch(ParseException e) {
			
			enterFinancialSector();
		}
		

	}
	
	public void showDeniedAccess() {
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
		}

	}
}