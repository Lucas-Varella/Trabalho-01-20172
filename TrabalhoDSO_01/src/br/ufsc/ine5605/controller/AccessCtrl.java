package br.ufsc.ine5605.controller;

import java.text.DateFormat; 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.ufsc.ine5605.model.Access;
import br.ufsc.ine5605.model.Reasons;
import br.ufsc.ine5605.view.AccessScreen;

/**
 * Classe responsável pela comunicação das classes Access e AccessScreen entre si e com as outras classes;
 * @author Sadi Júnior Domingos Jacinto;
 *
 */
public class AccessCtrl {
	private MainController mainCtrl;
	private AccessScreen accessScreen;
	private ArrayList<Access> deniedAccess;
	
	/**
	 * Construtor padrão da classe;
	 * @param mainController - Recebe uma instância do MainController, o que permite a comuncicação 
	 * desta classe com todas as outras através do MainController;
	 */
	public AccessCtrl(MainController mainController) {
		this.mainCtrl = mainController;
		this.accessScreen = new AccessScreen(this);
		deniedAccess = new ArrayList<Access>();
	}
	
	/**
	 * Cria e adiciona uma nova instância de Access no ArrayList<Access>
	 * @param numRegistration - int do número de registro usado para tentar acessar o Setor Financeiro;
	 * @param date - Date da tentativa de acesso;
	 * @param hour - Hora da tentativa de acesso;
	 * @param reason - Motivo da negação do acesso;
	 */
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
