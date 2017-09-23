package ine.view;

import ine.controller.*;
import ine.model.*;
import ine.view.*;

import ine.controller.EmployeeCtrl;

import java.util.Scanner;


public class EmployeeScreen {
	private EmployeeCtrl employeeCtrl;
	private Scanner keyboard;

	public EmployeeScreen(EmployeeCtrl employeeCtrl) {
		this.employeeCtrl = employeeCtrl;
		keyboard = new Scanner(System.in);
	}

	/*
	 * Refazer o menu depois(ou o completar); Tratar direito as exceções;
	 */
	public void showMenu() {
		int numOption = 0;
		do {
			try {
				System.out.println("Welcome!");
				System.out
						.println("Please enter the number corresponding to your choice: ");
				System.out.println("1 - Add employee");
				System.out.println("2 - Edit employee");
				System.out.println("3 - Delete employee");
				System.out.println("4 - List employees");
				System.out.println("0 - Get out your mother fucker!");
				String option = keyboard.nextLine();
				numOption = Integer.parseInt(option);
				switch (numOption) {
				case 1:
					System.out.println("Name: ");
					String name = keyboard.nextLine();
					System.out.println("Date of birth: ");
					String birthDay = keyboard.nextLine();
					System.out.println("1 - Add employee");
					System.out.println("1 - Add employee");
					System.out.println("1 - Add employee");

					employeeCtrl.addEmployee();
					break;
				case 2:

					break;
				case 3:

					break;
				case 0:
					System.out.println("Goodbye and have a good day");
					break;
				default:
					System.out
							.println("The number you entered is not valid. Please try again.");
				}

			} catch (Exception e) {

			}

		} while (numOption != 0);

	}

}
