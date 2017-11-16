package br.ufsc.ine5605.controller;

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
	
	/**
	 * Construtor padrão da classe;
	 * @param mainCtrl - Recebe uma instância do MainController, o que permite a comunicação com outras classes;
	 */
	public HoraryCtrl() {
		this.horaryScreen = new HoraryScreen();
	}
	
	public static HoraryCtrl getInstance() {
		return instance;
	}

	
	public void menuAdd() {
		horaryScreen.setVisible(true);
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
	public Horary addHorary(Date hourBegin, Date hourFinish) {
		return (new Horary(hourBegin, hourFinish));
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

	public Horary editHorary(Horary horary) {
		return horaryScreen.edit(horary);
		
	}

}
