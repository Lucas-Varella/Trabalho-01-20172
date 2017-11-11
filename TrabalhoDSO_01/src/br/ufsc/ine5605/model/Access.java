package br.ufsc.ine5605.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe responsável pela criação de instâncias de accessos negados;
 * @author Sadi Júnior Domingos Jacinto;
 *
 */
public class Access implements Serializable {
	private static final long serialVersionUID = 1L;
	private int numRegistration;
	private Date date;
	private Date hour;
	private Reasons reason;
	
	/**
	 * Construtor padrão da classe;
	 * @param numRegistration - int do número de registro digitado na tela do FinancialSectorScreen;
	 * @param date - Data da tentativa de acesso;
	 * @param hour - Hora da tentativa de acesso;
	 * @param reason - Motivo da negação de acesso;
	 */
	public Access(int numRegistration, Date date, Date hour, Reasons reason) {
		this.numRegistration = numRegistration;
		this.date = date;
		this.hour = hour;
		this.reason = reason;
	}

	public int getNumRegistration() {
		return numRegistration;
	}
	
	public Date getDate() {
		return date;
	}
	
	public Date getHour() {
		return hour;
	}
	
	public Reasons getReason() {
		return reason;
	}
}
