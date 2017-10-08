package ine.view;

import java.text.ParseException;
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
			boolean valid = financialSectorCtrl.validAccess(num, hourAccess);
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
	}
}