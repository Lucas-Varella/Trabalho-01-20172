package br.ufsc.ine5605.controller;

import java.io.Serializable;
import java.text.DateFormat; 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.ufsc.ine5605.model.Access;
import br.ufsc.ine5605.model.AccessDAO;
import br.ufsc.ine5605.model.MessageAccess;
import br.ufsc.ine5605.model.Reasons;
import br.ufsc.ine5605.view.AccessScreen;

/**
 * Classe responsável pela comunicação das classes Access e AccessScreen entre si e com as outras classes;
 * @author Sadi Júnior Domingos Jacinto;
 *
 */
public class AccessCtrl implements Serializable {
	private static final AccessCtrl instance = new AccessCtrl();
	private AccessScreen accessScreen;
	private AccessDAO accessDAO = new AccessDAO();
	//private ArrayList<Access> getMessage();
	//private ArrayList<MessageAccess> message;
	
	/**
	 * Construtor padrão da classe;
	 * @param mainController - Recebe uma instância do MainController, o que permite a comuncicação 
	 * desta classe com todas as outras através do MainController;
	 */
	public AccessCtrl() {
		this.accessScreen = new AccessScreen();
		//getMessage() = new ArrayList<Access>();
		//message = new ArrayList();
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
		accessDAO.put(a);
		//getMessage().add(a);
	}
	
	public void listAllDeniedAccess() throws IndexOutOfBoundsException {
		if(getMessage().size() > 0) {
			accessScreen.show(getMessage());
		}else {
			throw new IndexOutOfBoundsException();
		}
		
	}
	
	public ArrayList<Access> getMessage() {
		return new ArrayList<Access>(accessDAO.getList());
	}
	
	public void listDeniedAccessByNumRegistration(int numRegistration) throws IndexOutOfBoundsException {
		if(getMessage().size() > 0) {
			ArrayList<Access> access = new ArrayList();
			for(Access a : getMessage()) {
				if(a.getNumRegistration() == numRegistration) {
					access.add(a);
				}
			}
			if(access.size() > 0) {
				accessScreen.show(access);
			}else {
				throw new IndexOutOfBoundsException();
			}
		}else {
			throw new IndexOutOfBoundsException();
		}

	}
	
	public void listDeniedAccessByReason(Reasons reason) throws IndexOutOfBoundsException {
		if(getMessage().size() > 0) {
			ArrayList<Access> access = new ArrayList();
			for(Access a : getMessage()) {
				if(a.getReason().equals(reason)) {
					access.add(a);
				}
			}
			if(access.size() > 0) {
				accessScreen.show(access); 
			} else {
				accessScreen.noReason();
			}
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
		if(getMessage() != null ) {
			int cont = 0;
			for(Access a : getMessage()) {
				if(a.getNumRegistration() == numRegistration) {
					cont++;
				}
			}
			if(cont == 3) {
				return true;
			}
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
		return getMessage().get(getMessage().size() - 1).getReason();
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
