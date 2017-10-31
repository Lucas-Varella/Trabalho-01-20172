package br.ufsc.ine5605.controller;

import java.util.ArrayList;  
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.ufsc.ine5605.model.Employment;
import br.ufsc.ine5605.model.FinancialSector;
import br.ufsc.ine5605.model.Horary;
import br.ufsc.ine5605.model.Privileges;
import br.ufsc.ine5605.model.Reasons;
import br.ufsc.ine5605.view.FinancialSectorScreen;
import br.ufsc.ine5605.model.ConversionDates;

/**
 * Classe responsável por transmitir o input do usuário para a classe FinancialSector, onde
 * os mesmos serão devidamente tratados. Também é responsável pela comunicação entre as classes 
 * FinancialSector e FinancialSectorScreen entre si e com as outras classes, quando necessário;
 *  
 * @author Sadi Júnior Domingos Jacinto;
 * @author Marcos Laydner;
 * @author Lucas Varella
 */
public class FinancialSectorCtrl implements ConversionDates {
	private MainController mainCtrl;
	private FinancialSectorScreen financialSectorScreen;
	private FinancialSector financialSector;
	
	/**
	 * Construtor padrão da classe;
	 * @param mainController - recebe uma instância do MainController, possibilitando a comunicação do mesmo
	 * com as outras classes, através do MainController;
	 * 
	 * @author Sadi Júnior Domingos Jacinto;
	 */
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
	
	/**
	 * Converte uma String em um Date no formato HH:mm;
	 * @param data - String de entrada
	 * @return Date;
	 * @throws ParseException ocorre quando o input do usuário não corresponde ao formato esperado;
	 */
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
	
	/**
	 * Converte uma String em um Date no formato HH:mm;
	 * @param data - String de entrada
	 * @return Date;
	 * @throws ParseException ocorre quando o input do usuário não corresponde ao formato esperado;
	 */
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
	/**
	 * Método que, quando invocado, registra e retorna a data atual do sistema no qual 
	 * o mesmo está sendo rodado;
	 * @return Date;
	 * @throws ParseException se ocorrer tal erro, contate o suporte;
	 *
	 *@author Sadi Júnior Domingos Jacinto;
	 */
	public Date getCurrenteDate() throws ParseException {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		String dateFormat = df.format(c.getTime());
		return df.parse(dateFormat);
	}
	
	/**
	 * Método que busca um Employment baseado no número de Registro de um funcionário;
	 * @deprecated - método criado, mas nunca usado;
	 * @param numRegistration - usado para returnar um Employment que possuí um funcionário com tal número;
	 * @return Employment
	 */
	public Employment getEmploymentByNumRegistration(int numRegistration) {
		return mainCtrl.getEmploymentByNumRegistration(numRegistration);
	}

	public Privileges getPrivilegeByNumRegistration(int numRegistration) {
		return mainCtrl.getPrivilegeByNumRegistration(numRegistration);
	}

	public ArrayList<Horary> getHoraryAccess(int numRegistration) {
		return mainCtrl.getHoraryAccess(numRegistration);
	}
	

	public Reasons getReasonBy() {
		return mainCtrl.getReasonBy();		
	}
	
}
