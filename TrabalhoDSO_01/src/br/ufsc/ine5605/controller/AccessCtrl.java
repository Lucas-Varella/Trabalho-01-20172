package br.ufsc.ine5605.controller;

import java.text.DateFormat; 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.ufsc.ine5605.model.Access;
import br.ufsc.ine5605.model.Reasons;
import br.ufsc.ine5605.view.AccessScreen;

public class AccessCtrl {
	private MainController mainCtrl;
	private AccessScreen accessScreen;
	private ArrayList<Access> deniedAccess;
	
	public AccessCtrl(MainController mainController) {
		this.mainCtrl = mainController;
		this.accessScreen = new AccessScreen(this);
		deniedAccess = new ArrayList<Access>();
	}
	
	public void addAccess(int numRegistration, Date date, Date hour, Reasons reason) {
		Access a = new Access(numRegistration, date, hour, reason );
		deniedAccess.add(a);
	}
	
	public void listAllDeniedAccess() throws IndexOutOfBoundsException {
		if(deniedAccess != null) {
			accessScreen.listAllDeniedAccess(deniedAccess);
		}else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	public void listDeniedAccessByNumRegistration(int numRegistration) throws IndexOutOfBoundsException {
		if(deniedAccess != null) {
			for(Access a: deniedAccess) {
				if(a.getNumRegistration() == numRegistration) {
					accessScreen.listDeniedAccessByNumRegistration(a);
				}
			}
		}else {
			throw new IndexOutOfBoundsException();
		}

	}
	
	public void listDeniedAccessByReason(Reasons reason) throws IndexOutOfBoundsException {
		if(deniedAccess != null) {
			for(Access a : deniedAccess) {
				if(a.getReason().equals(reason)) {
					accessScreen.listDeniedAccessByReason(a);
				}
			}
		}else {
			throw new IndexOutOfBoundsException();
		}
	}

	public boolean isAccessBloqued(int numRegistration) {
		if(deniedAccess != null ) {
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
	
	public String dateToStringHour(Date data) throws ParseException {
		if (data == null) {
            return null;
        }
        String dataF = "";
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
         dataF = dateFormat.format(data);
        return dataF;
	}

	public String dateToStringDate(Date data) throws ParseException {
		if (data == null) {
	        return null;
	    }
	    String dataF = "";
	    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dataF = dateFormat.format(data);
	    return dataF;
	}

}
