package ine.view;

import ine.controller.*;
import ine.model.*;
import ine.view.*;

import java.util.Scanner;


public class MainScreen {
	private MainScreenCtrl mainScreenCtrl;
	private Scanner keyboard;
	
	public MainScreen(MainScreenCtrl mainScreenCtrl) {
		this.mainScreenCtrl = mainScreenCtrl;
		keyboard = new Scanner(System.in);
	}
	
	public void showMenu() {
		try {
			int option = 0;
			do {
				System.out.println("Welcome!");
				System.out.println("Please enter the number corresponding to your choice: ");
				System.out.println("1 - Employee;");
				System.out.println("2 - Employment;");
				System.out.println("3 - Financial sector;");
				System.out.println("0 - Get out.");
				option = mainScreenCtrl.conversionStringToInt(keyboard.nextLine());
				switch(option) {

				case 1:
					mainScreenCtrl.employeeMenu();
					break;
				
				case 2:
					mainScreenCtrl.employmentMenu();
					break;
					
				case 3:
					mainScreenCtrl.financialSectorMenu();
					break;
					
				case 0:
					System.out.println("Goodbye and have a good day");
					break;
					
				default:
					System.out.println("The number you entered is not valid. Please try again.");
				}

			}while(option > 3 || option < 0);
			
		} catch(NumberFormatException e) {
			System.out.println("The number you entered is not valid");
			showMenu();
		}
		
	}
}