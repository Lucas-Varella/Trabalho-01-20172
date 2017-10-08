package ine.controller;

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
		this.mainCtrl = mainCtrl;
		this.financialSectorScreen = new FinancialSectorScreen(this);
		this.financialSector = new FinancialSector(this);
	}
	
	public void mainMenu() {
		mainCtrl.showMainScreen();
	}
	
	public void menu() {
		financialSectorScreen.menuFinancialSector();
	}
	
	public boolean validAccess(int numRegistration, Date hourAccess, String dateAccess) {
		return financialSector.validAccess(numRegistration, hourAccess, dateAccess);
	}
	
	public Employee validNumRegistration(int numRegistration) {
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
	}
	
}
