package ine.view;

import ine.controller.HoraryCtrl;
import ine.model.Horary;

import java.util.ArrayList;
import java.util.Scanner;

public class HoraryScreen {
	private HoraryCtrl horaryCtrl;
	private Scanner keyboard;
	
	public HoraryScreen(HoraryCtrl horaryCtrl) {
		this.horaryCtrl = horaryCtrl;
		this.keyboard = new Scanner(System.in);
	}

	public void menuAdd() {
		ArrayList<String> hourBegin = new ArrayList();
		ArrayList<String> hourFinish = new ArrayList();
		System.out.println("Welcome!");
		int option = 0;
		do {
			System.out.println("Please enter the hours of access to the financial sector allowed to the employee: ");
			System.out.println("Hour Begin: ");
			/*
			 * Formatar para Date depois;
			 */
			hourBegin.add(keyboard.nextLine());
			System.out.println("Hour Finish: ");
			hourFinish.add(keyboard.nextLine());
			System.out.println("0 to exit");
			System.out.println("1 to add anothe time");
			option = keyboard.nextInt();
		}while(option != 0);
		Horary horary = new Horary(hourBegin, hourFinish);
		horaryCtrl.setHorary(horary);
	}
	/**
	 * @author sadi
	 * @return
	 */
	
		
	
	    public void editHorary() {
		System.out.println("Please enter the number corresponding to the times you wish to edit");
		System.out.println("1 to horary begin");
		System.out.println("2 to horar finish");
		System.out.println("Or 0 to exit");
		int option = keyboard.nextInt();
		do {
			switch(option) {
			case 1:
				System.out.println("This is the current list of the selected time (s): ");
				horaryCtrl.listHorary(option);
				
				break;
			case 2:
				System.out.println("This is the current list of the selected time (s): ");
				horaryCtrl.listHorary(option);
				System.out.println("");
				System.out.println("");
				System.out.println("");
				break;
			case 0:
				System.out.println("Goodbye");
			default:
				System.out.println("The number you entered is not valid!");
			}
		}while(option != 0);
	}

}
