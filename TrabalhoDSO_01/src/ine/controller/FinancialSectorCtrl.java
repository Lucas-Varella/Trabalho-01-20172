package ine.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import ine.controller.*;
import ine.model.*;
import ine.view.*;

public class FinancialSectorCtrl {
	private MainController mainCtrl;
	private FinancialSectorScreen financialSectorScreen;
	private FinancialSector financialSector;
	

	public FinancialSectorCtrl(MainController mainController) {
		this.mainCtrl = mainController;
		this.financialSectorScreen = new FinancialSectorScreen(this);
		this.financialSector = new FinancialSector(this);
	}
	
	public void mainMenu() {
		mainCtrl.showMainScreen();
	}
	
	public void menu() {
		financialSectorScreen.menuFinancialSector();
	}
	
	public boolean validAccess(int numRegistration, Date hourAccess, Date dateAccess) {
		return financialSector.validAccess(numRegistration, hourAccess, dateAccess);
	}
	
	public boolean validNumRegistration(int numRegistration) {
		return mainCtrl.validNumRegistration(numRegistration);
	}
	
	public int conversionStringToInt(String data) throws NumberFormatException {
		try {
			int num = Integer.parseInt(data);	
			return num;
		} catch(NumberFormatException e ) {
			throw new NumberFormatException();
		}
	}
	
	public Date strToDateHour(String data) throws ParseException {
//		try {
			if (data == null) {
	            return null;
	        }
	        Date dataF = null;
	        try {
	            DateFormat dateFormat = new SimpleDateFormat("HH:mm");
	            long time = dateFormat.parse(data).getTime();
	            dataF = new Date(time);
	        } catch (ParseException e) {
	        	throw new ParseException(data, 0);
	        }
	        return dataF;
//
//		}catch(ParseException e) {
//			
//		}
//		return null;
	}
	
	public Date strToDate(String data) throws ParseException {
		if (data == null) {
            return null;
        }
        Date dataF = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            long time = dateFormat.parse(data).getTime();
            dataF = new Date(time);
        } catch (ParseException e) {
            throw new ParseException(data, 0);
        }
        return dataF;
	}
	
	public void addAccess(int numRegistration, Date date, Date hour, Reasons reason) {
		mainCtrl.addAccess(numRegistration, date, hour, reason);
	}

	public boolean isAccessBloqued(int numRegistration) {
		return mainCtrl.isAccessBloqued(numRegistration);
	}

	public void showAllDeniedAccess() {
		mainCtrl.showAllDeniedAccess();
		
	}

	public void showDeniedAccessByNumRegistration(int numRegistration) {
		mainCtrl.showDeniedAccessByNumRegistration(numRegistration);
		
	}

	public void showDeniedAccessByReason(Reasons reason) {
		mainCtrl.showDeniedAccessByReason(reason);
	}
	
	public Date getCurrenteDate() throws ParseException {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		String dateFormat = df.format(c.getTime());
		return df.parse(dateFormat);
	}

	public Employment getEmploymentByNumRegistration(int numRegistration) {
		return mainCtrl.getEmploymentByNumRegistration(numRegistration);
	}

	public Privileges getPrivilegeByNumRegistration(int numRegistration) {
		return mainCtrl.getPrivilegeByNumRegistration(numRegistration);
	}

	public ArrayList<Horary> getHoraryAccess(int numRegistration) {
		return mainCtrl.getHoraryAccess(numRegistration);
	}

	public Reasons getReasonBy(int num, Date hourAccess, Date dateAccess) {
		return mainCtrl.getReasonBy(num, hourAccess, dateAccess);		
	}
	
}
