package br.ufsc.ine5605.controller;

import java.text.DateFormat; 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.ufsc.ine5605.model.Access;
import br.ufsc.ine5605.model.MessageAccess;
import br.ufsc.ine5605.model.Reasons;
import br.ufsc.ine5605.view.AccessScreen;

/**
 * Classe responsável pela comunicação das classes Access e AccessScreen entre si e com as outras classes;
 * @author Sadi Júnior Domingos Jacinto;
 *
 */
public class AccessCtrl {
	private static final AccessCtrl instance = new AccessCtrl();
	private AccessScreen accessScreen;
	private ArrayList<Access> deniedAccess;
	private ArrayList<MessageAccess> message;
	
	/**
	 * Construtor padrão da classe;
	 * @param mainController - Recebe uma instância do MainController, o que permite a comuncicação 
	 * desta classe com todas as outras através do MainController;
	 */
	public AccessCtrl() {
		this.accessScreen = new AccessScreen();
		deniedAccess = new ArrayList<Access>();
		message = new ArrayList();
	}
	
	public static AccessCtrl getInstance() {
		return instance;
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
//		if(deniedAccess.size() > 0) {
//			for(Access a: deniedAccess) {
//				message.add(new MessageAccess(a.getNumRegistration(), a.getDate(), a.getHour(), a.getReason()));
//			}
//			//accessScreen.show(message);
//		}else {
//			throw new IndexOutOfBoundsException();
//		}
		accessScreen.setVisible(true);
	}
	
	public ArrayList<MessageAccess> getMessage() {
		return message;
	}
	
	public void listDeniedAccessByNumRegistration(int numRegistration) throws IndexOutOfBoundsException {
		if(deniedAccess.size() > 0) {
			for(Access a: deniedAccess) {
				if(a.getNumRegistration() == numRegistration) {
					//accessScreen.listDeniedAccessByNumRegistration(a);
				}
			}
			System.out.println("There is no denied access that has this registration number");
		}else {
			throw new IndexOutOfBoundsException();
		}

	}
	
	public void listDeniedAccessByReason(Reasons reason) throws IndexOutOfBoundsException {
		if(deniedAccess.size() > 0) {
			for(Access a : deniedAccess) {
				if(a.getReason().equals(reason)) {
					//accessScreen.listDeniedAccessByReason(a);
				}
			}
			System.out.println("There is no denied access registered with this denial motive");
		}else {
			throw new IndexOutOfBoundsException();
		}
	}


	/**
	 * Verifica se determinado número de registro não possuí o acesso bloqueado ao Setor Financeiro;
	 * @param numRegistration - int contendo o número de acesso que se deseja saber se está bloqueado; 
	 * @return boolean - Retorna true se o acesso está bloqueado, false caso contrário;
	 */
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
	
	/**
	 * Busca o motivo de negação de acesso baseado nos parâmetros de entrada
	 * @param num - int contendo o número de registro do funcionário;
	 * @param hourAccess - Date contendo a hora da tentativa de acesso;
	 * @param dateAccess - Date contendo a data da tentativa de acesso;
	 * @return Reasons - Retorna o motivo da negação de acesso;
	 */
	public Reasons getReasonsBy() {
		return deniedAccess.get(deniedAccess.size() - 1).getReason();
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
