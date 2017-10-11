package ine.view;

import ine.controller.AccessCtrl;
import ine.model.Access;

import java.text.ParseException;
import java.util.ArrayList;

public class AccessScreen {
	private AccessCtrl accessCtrl;
	
	public AccessScreen(AccessCtrl accessCtrl) {
		this.accessCtrl = accessCtrl;
	}

	public void listAllDeniedAccess(ArrayList<Access> deniedAccess) {
		try {
			int i = 1;
			for(Access a : deniedAccess) {
				System.out.println("--------------- "+ i + "º ---------------");
				System.out.println("Number of Registration - " + a.getNumRegistration());
				
				System.out.println("Date of Access - " + accessCtrl.dateToStringDate(a.getDate()));
				System.out.println("Hour of Access - " + accessCtrl.dateToStringHour(a.getHour()));
				System.out.println("Reason for denial of access - " + a.getReason());
				i++;
			}
		} catch(ParseException e) {
			System.out.println("Deu ruim");
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
