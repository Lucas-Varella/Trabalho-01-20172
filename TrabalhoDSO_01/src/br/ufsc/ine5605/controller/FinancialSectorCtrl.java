package br.ufsc.ine5605.controller;

import java.util.ArrayList;  
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import sun.awt.RepaintArea;

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
public class FinancialSectorCtrl {
	private static final FinancialSectorCtrl instance = new FinancialSectorCtrl();
	private FinancialSectorScreen financialSectorScreen;
	private FinancialSector financialSector;
	
	/**
	 * Construtor padrão da classe;
	 * @param mainController - recebe uma instância do MainController, possibilitando a comunicação do mesmo
	 * com as outras classes, através do MainController;
	 * 
	 * @author Sadi Júnior Domingos Jacinto;
	 */
	public FinancialSectorCtrl() {
		//this.MainController.getInstance() = mainController;
		//this.financialSectorScreen = new FinancialSectorScreen(this);
		this.financialSector = new FinancialSector();
		this.financialSectorScreen = new FinancialSectorScreen();
	}
	
	
	public static FinancialSectorCtrl getInstance() {
		return instance;
	}


	public void mainMenu() {
		MainController.getInstance().showMainScreen();
	}
	
	
	public void menu() {
		financialSectorScreen.setVisible(true);
	}
	
	
	public boolean validAccess(int numRegistration, Date hourAccess) {
		try {
			Date dateAccess = getCurrenteDate();
			if(MainController.getInstance().isAccessBloqued(numRegistration)) {
				addAccess(numRegistration, dateAccess, hourAccess, Reasons.BLOCK);
				return false;
			}
			
			if(!MainController.getInstance().validNumRegistration(numRegistration)) {
				addAccess(numRegistration, dateAccess, hourAccess, Reasons.NONUMREGS);
				return false;
			}
			if(financialSector.isPrivilegeFull(getPrivilegeByNumRegistration(numRegistration))) {
				return true;
			} else 	if(financialSector.isPrivilegeNo(getPrivilegeByNumRegistration(numRegistration))) {
				addAccess(numRegistration, dateAccess, hourAccess, Reasons.NOACCESS);
				return false;
			} else if(!financialSector.isPrivilegeRestrict(getPrivilegeByNumRegistration(numRegistration), getHoraryAccess(numRegistration),hourAccess)) {
				addAccess(numRegistration, dateAccess, hourAccess, Reasons.INCTIME);
				return false;
			}
			
		}catch(Exception e) {
			
		}
		return true;
	}
	
	public void addAccess(int numRegistration, Date date, Date hour, Reasons reason) {
		MainController.getInstance().addAccess(numRegistration, date, hour, reason);
	}

	public void showAllDeniedAccess() {
		MainController.getInstance().showAllDeniedAccess();
		
	}

	public void showDeniedAccessByNumRegistration(int numRegistration) {
		MainController.getInstance().showDeniedAccessByNumRegistration(numRegistration);
		
	}

	public void showDeniedAccessByReason(Reasons reason) {
		MainController.getInstance().showDeniedAccessByReason(reason);
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
	
	public int conversionStringToInt(String data) throws NumberFormatException {
		try {
			int num = Integer.parseInt(data);	
			return num;
		} catch(NumberFormatException e ) {
			throw new NumberFormatException();
		}
	}

	/**
	 * Método que busca um Employment baseado no número de Registro de um funcionário;
	 * @deprecated - método criado, mas nunca usado;
	 * @param numRegistration - usado para returnar um Employment que possuí um funcionário com tal número;
	 * @return Employment
	 */
	public Employment getEmploymentByNumRegistration(int numRegistration) {
		return null;
	}

	public Privileges getPrivilegeByNumRegistration(int numRegistration) {
		return MainController.getInstance().getPrivilegeByNumRegistration(numRegistration);
	}

	public ArrayList<Horary> getHoraryAccess(int numRegistration) {
		return MainController.getInstance().getHoraryAccess(numRegistration);
	}
	

	public Reasons getReasonBy() {
		return MainController.getInstance().getReasonBy();		
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
