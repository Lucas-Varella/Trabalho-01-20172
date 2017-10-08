package ine.controller;

import ine.model.Horary; 
import ine.model.Screen;
import ine.view.HoraryScreen;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class HoraryCtrl implements Screen {
	private MainController mainCtrl;
	private HoraryScreen horaryScreen;
	private Horary horary;
	
	public HoraryCtrl(MainController mainController) {
		this.mainCtrl = mainCtrl;
		this.horaryScreen = new HoraryScreen(this);
	}
	
	public Horary menuAdd() {
		return horaryScreen.menuAdd();
	}

	public void mainMenu() {
		mainCtrl.showMainScreen();
	}
	
	public Horary addHorary(Date hourBegin, Date hourFinish) {
		return (new Horary(hourBegin, hourFinish));
	}

	public void menu() {
		// TODO Auto-generated method stub
		
	}

	public int conversionStringToInt(String data) throws NumberFormatException {
		try {
			int num = Integer.parseInt(data);	
			return num;
		} catch(NumberFormatException e ) {
			throw new NumberFormatException();
		}
	}

	public double conversionStringToDouble(String data)
			throws NumberFormatException {
		// TODO Auto-generated method stub
		return 0;
	}

	public Date strToDate(String data) throws ParseException {
		// TODO Auto-generated method stub
		return null;
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

	public Horary editHorary(Horary horary) {
		return horaryScreen.edit(horary);
		
	}

}
