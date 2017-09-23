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
	
	public void showMenu() throws Exception {
		int numOption = 0;
		do {
			System.out.println("Welcome!");
			System.out.println("Please enter the number corresponding to your choice: ");
			System.out.println("1 - Employee;");
			System.out.println("2 - Employment;");
			System.out.println("3 - Financial sector;");
			System.out.println("0 - Get out.");
			/*
			 * Lembre-me de terminar o tratamento de exceção 
			 * caso o usuário seja retardado e digite uma letra 
			 * ou símbolo;
			 */
			try {
				String option = keyboard.nextLine();
				numOption = Integer.parseInt(option);
				switch(numOption) {
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
			}catch(Exception e) {
				System.out.println("Please enter only numbers");
			}
		}while(numOption == 0 || numOption == 1 || numOption == 2 || numOption == 3);
		keyboard.close(); //fecha o Scanner para economizar memória;
	}
	
}