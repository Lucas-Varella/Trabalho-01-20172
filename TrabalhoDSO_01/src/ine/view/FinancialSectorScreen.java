package ine.view;

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
		int option = 0;
		do {
			System.out.println("Please, enter the number corresponding to your choice: ");
			System.out.println("1 - Entering the Financial Sector");
			System.out.println("2 - Denied Access Report");
			System.out.println("Or 0 to exit");
			option = keyboard.nextInt();
			switch(option) {
			case 1:
				enterFinancialSector();
				break;
				
			case 2:
				
				break;
			
			case 0:
				System.out.println("Goodbye");
				break;
			
			default:
				System.out.println("The number you entered is not valid");	
			}
		}while(option != 0);
		financialSectorCtrl.mainMenu();
	}
	
	public void enterFinancialSector() {
		System.out.println("Welcome to the entrance of the Financial Sector, noble adventurer");
		System.out.println("To continue your quest for capital, please enter your registration number and," + 
							"if you are worthy, you may enter this sacred place");
		System.out.println("Number of registration: ");
		int num = keyboard.nextInt();
		boolean valid = financialSectorCtrl.validAccess(num);
		if(valid) {
			System.out.println("You are worthy to enter this sacred place");
		}else {
			System.out.println("You are not worthy to enter this place, adventurer, for:");
			/*
			 * Colocar o reasons aqui;
			 */
		}

	}
}