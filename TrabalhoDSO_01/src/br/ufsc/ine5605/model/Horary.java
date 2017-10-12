package br.ufsc.ine5605.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.ufsc.ine5605.controller.*;


public class Horary {
	private HoraryCtrl horaryCtrl;
	private Date hourBegin;
	private Date hourFinish;
	/*
	 * Mudar os tipos do ArrayList para Date;
	 * Formatar as datas(como não sei fazer isso, vou deixar para alguém que saiba);
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
