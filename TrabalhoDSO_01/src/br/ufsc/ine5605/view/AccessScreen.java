package br.ufsc.ine5605.view;


import java.text.ParseException; 
import java.util.ArrayList;

import br.ufsc.ine5605.controller.AccessCtrl;
import br.ufsc.ine5605.model.Access;

/**
 * Classe responsável por exibir ao usuário um relatório de acessos negados;
 * @author Sadi Júnior Domingos Jacinto;
 *
 */
public class AccessScreen {
	private AccessCtrl accessCtrl;
	
	/**
	 * Contrutor padrão da classe;
	 * @param accessCtrl - Recebe uma instância do AccessCtrl, o que permite a comunicação com outras classes;
	 */
	public AccessScreen(AccessCtrl accessCtrl) {
		this.accessCtrl = accessCtrl;
	}
	/**
	 * Lista todos os acessos negados; 
	 * @param deniedAccess - Recebe um ArrayList de Access;
	 */
	public void listAllDeniedAccess(ArrayList<Access> deniedAccess) {
		try {
			int i = 1;
			for(Access a : deniedAccess) {
				System.out.println("--------------- "+ i + "º ---------------");
				System.out.println("Number of Registration - " + a.getNumRegistration());
				
				System.out.println("Date of Access - " + accessCtrl.dateToStringDate(a.getDate()));
				System.out.println("Hour of Access - " + accessCtrl.dateToStringHour(a.getHour()));
				System.out.println("Reason for denial of access - " + a.getReason());
				i++;
			}
		} catch(ParseException e) {
			System.out.println("An internal error occurred. Please contact support");
		}
	}
	
	/**
	 * Lista os acessos negados previamente filtrados por um número de matrícula;
	 * @param a - Recebe a instância de Access que deverá ser exibida ao usuário;
	 */
	public void listDeniedAccessByNumRegistration(Access a) {
		try {
			System.out.println("______________________________________________________");
			System.out.println("Number of Registration - " + a.getNumRegistration());
			System.out.println("Date of Access - " + accessCtrl.dateToStringDate(a.getDate()));
			System.out.println("Hour of Access - " + accessCtrl.dateToStringHour(a.getHour()));
			System.out.println("Reason for denial of access - " + a.getReason());
		} catch(ParseException e) {
			System.out.println("An internal error occurred. Please contact support");
		}
		
	}
	
	/**
	 * Lista os acessos negados previamente filtrados por um motivo de negação de acesso;
	 * @param a - Recebe a instância de Access que deverá ser exibida ao usuário;
	 */
	public void listDeniedAccessByReason(Access a) {
		try {
			System.out.println("______________________________________________________");
			System.out.println("Number of Registration - " + a.getNumRegistration());
			System.out.println("Date of Access - " + accessCtrl.dateToStringDate(a.getDate()));
			System.out.println("Hour of Access - " + accessCtrl.dateToStringHour(a.getHour()));
			System.out.println("Reason for denial of access - " + a.getReason());
		} catch(ParseException e) {
			System.out.println("An internal error occurred. Please contact support");
		}
		
	}
	
}
