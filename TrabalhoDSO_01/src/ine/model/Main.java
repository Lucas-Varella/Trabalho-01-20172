package ine.model;

import java.text.ParseException;
import java.util.ArrayList;

import ine.controller.*;
import ine.model.*;
import ine.view.*;

public class Main {

	/**
	 * @param args
	 * @throws ParseException 
	 * @throws Exception 
	 */
	public static void main(String[] args) throws ParseException {
		MainController mainCtrl = new MainController();
		FinancialSectorCtrl f = new FinancialSectorCtrl(mainCtrl);
		Access a = new Access(012, f.getCurrenteDate() ,f.strToDateHour("08:00"), Reasons.BLOCK);
		ArrayList<Access> deniedAccess = new ArrayList();
		deniedAccess.add(a);
		
		
		//Access s = new Access(012, f.getCurrenteDate() ,f.strToDateHour("08:00"), Reasons.BLOCK);
		//f.addAccess(012, f.getCurrenteDate(), f.strToDateHour("08:00"), Reasons.BLOCK);
		mainCtrl.showMainScreen();

	}

}
