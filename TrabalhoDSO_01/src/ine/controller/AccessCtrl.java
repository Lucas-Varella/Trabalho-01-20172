package ine.controller;

import java.util.ArrayList;

import ine.controller.MainController;
import ine.model.Reasons;
import ine.model.Access;
import ine.view.AccessScreen;

import java.util.Date;

public class AccessCtrl {
	private MainController mainCtrl;
	private AccessScreen accessScreen;
	private ArrayList<Access> deniedAccess;
	
	public AccessCtrl(MainController mainController) {
		this.mainCtrl = mainController;
		deniedAccess = new ArrayList<Access>();
	}
	
	public void addAccess(int numRegistration, Date date, Date hour, Reasons reason) {
		deniedAccess.add(new Access(numRegistration, date, hour, reason ));
	}
	
	public void listAllDeniedAccess() {
		accessScreen.listAllDeniedAccess(deniedAccess);
	}
	
	public void listDeniedAccessByNumRegistration(int numRegistration) {
		for(Access a: deniedAccess) {
			if(a.getNumRegistration() == numRegistration) {
				accessScreen.listDeniedAccessByNumRegistration(a);
			}
		}

	}
	
	public void listDeniedAccessByReason(Reasons reason) {
		for(Access a : deniedAccess) {
			if(a.getReason().equals(reason)) {
				accessScreen.listDeniedAccessByReason(a);
			}
		}
	}

	public boolean isAccessBloqued(int numRegistration) {
		int cont = 0;
		for(Access a : deniedAccess) {
			if(a.getNumRegistration() == numRegistration) {
				cont++;
			}
		}
		if(cont == 3) {
			return true;
		}
		return false;
	}

	public Reasons getReasonsBy(int num, Date hourAccess, Date dateAccess) {
		for(Access a : deniedAccess) {
			if(a.getNumRegistration() == num && a.getHour().equals(hourAccess) && a.getDate().equals(dateAccess)) {
				return a.getReason();
			}
		}
		return null;
	}

}
