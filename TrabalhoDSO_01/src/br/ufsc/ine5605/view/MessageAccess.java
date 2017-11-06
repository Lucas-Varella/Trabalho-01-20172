package br.ufsc.ine5605.view;

import java.util.Date;

import br.ufsc.ine5605.model.Reasons;

public class MessageAccess {
	private int numRegistration;
	private	Date date;
	private	Date hour;
	private	Reasons reason;
		
	public MessageAccess(int numRegistration, Date date, Date hour, Reasons reason) {
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
