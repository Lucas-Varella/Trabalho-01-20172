package br.ufsc.ine5605.model;

import java.text.DateFormat; 
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Classe que contém os atributos e métodos responsáveis pela criação de um objeto Horary;
 * @author Sadi Júnior Domingos Jacinto;
 *
 */
public class Horary {
	private Date hourBegin;
	private Date hourFinish;
	
	/**
	 * Construtor padrão da classe;
	 * @param hourBegin - Date contendo a hora de início
	 * @param hourFinish - Date contendo a hora de fim;
	 */
	public Horary(Date hourBegin, Date hourFinish) {
		this.hourBegin = hourBegin;
		this.hourFinish = hourFinish;
	}
	
	public String getHourBegin() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		String returned = dateFormat.format(hourBegin);
		return returned;
	}
	
	public String getHourFinish() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		String returned = dateFormat.format(hourFinish);
		return returned;
	}
}
