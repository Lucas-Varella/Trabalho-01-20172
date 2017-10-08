package ine.view;

import ine.model.Access;

import java.util.ArrayList;

public class AccessScreen {

	public void listAllDeniedAccess(ArrayList<Access> deniedAccess) {
		int i = 1;
		for(Access a : deniedAccess) {
			System.out.println("--------------- "+ i + "º ---------------");
			System.out.println("Number of Registration - " + a.getNumRegistration());
			System.out.println("Date of Access - " + a.getDate().toString());
			System.out.println("Hour of Access - " + a.getHour().toString());
			System.out.println("Reason for denial of access - " + a.getReason());
			i++;
		}
		
	}

	public void listDeniedAccessByNumRegistration(Access a) {
		System.out.println("Number of Registration - " + a.getNumRegistration());
		System.out.println("Date of Access - " + a.getDate().toString());
		System.out.println("Hour of Access - " + a.getHour().toString());
		System.out.println("Reason for denial of access - " + a.getReason());
	}

	public void listDeniedAccessByReason(Access a) {
		System.out.println("Number of Registration - " + a.getNumRegistration());
		System.out.println("Date of Access - " + a.getDate().toString());
		System.out.println("Hour of Access - " + a.getHour().toString());
		System.out.println("Reason for denial of access - " + a.getReason());
	}
	
}
