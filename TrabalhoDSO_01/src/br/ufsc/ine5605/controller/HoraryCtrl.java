package br.ufsc.ine5605.controller;

import java.util.ArrayList;
import java.util.Date; 
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.ufsc.ine5605.model.Horary;
import br.ufsc.ine5605.view.HoraryScreen;

/**
 * Classe responsável pela comuncicação das classes Horary e HoraryScreen entre
 * si e com as outras classes;
 * @author Sadi Júnior Domingos Jacinto;
 *
 */
public class HoraryCtrl {
	private static final HoraryCtrl instance = new HoraryCtrl();
	private HoraryScreen horaryScreen;
	private ArrayList<Horary> times;
	
	/**
	 * Construtor padrão da classe;
	 * @param mainCtrl - Recebe uma instância do MainController, o que permite a comunicação com outras classes;
	 */
	public HoraryCtrl() {
		this.horaryScreen = new HoraryScreen();
		times = new ArrayList<>();
		try {
			Horary commercial = new Horary("Commercial",  strToDateHour("08:00"), strToDateHour("18:00"));
			Horary night = new Horary("Nocturnal", strToDateHour("18:00"), strToDateHour("24:00"));
			Horary day = new Horary("Diurnal", strToDateHour("08:00"), strToDateHour("12:00"));
			Horary afternoon = new Horary("Evening", strToDateHour("12:00"), strToDateHour("18:00"));
			
			times.add(day);
			times.add(afternoon);
			times.add(night);
			times.add(commercial);
			
		} catch (ParseException e) {
			System.out.println("not gonna happen.");
		}
	}
	
	public static HoraryCtrl getInstance() {
		return instance;
	}

	
	public void menuAdd() {
		horaryScreen.setVisible(true);
	}
	public void updateData() {
		horaryScreen.updateData();
	}

	public void mainMenu() {
		MainController.getInstance().showMainScreen();
	}
	
	/**
	 * Cria uma nova instância da classe Horary;
	 * @param hourBegin - Date contendo a hora inicial;
	 * @param hourFinish - Date contendo a hora final;
	 * @return Horary - Retorna o objeto criado;
	 */
	public void addHorary(String name, Date hourBegin, Date hourFinish) {
		times.add(new Horary(name, hourBegin, hourFinish));
	}
	public void delHoraryName(String name) {
		for(Horary h : times) {
			if(h.getName().equals(name)) {
				times.remove(h);
			}
		}
	}
	public void delHoraryInt(int index) {
		times.remove(index);
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
/*
	public Horary editHorary(Horary horary) {
		return horaryScreen.edit(horary);
		
	}
*/

//	public void setIndexSelectedEmployment(int index) {
//		horaryScreen.setIndexSelectedEmployment(index);
//	}
	
	public ArrayList<Horary> getTimes() {
		// TODO Auto-generated method stub
		return times;
	}

	public Horary getTime(int index) {
		if(getTimes().size() >= index && index > -1) {
			return getTimes().get(index);
		}
		return null;
	}
}
