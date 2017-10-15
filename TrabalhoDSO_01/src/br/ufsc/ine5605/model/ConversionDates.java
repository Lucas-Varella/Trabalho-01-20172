package br.ufsc.ine5605.model;

import java.util.Date;
import java.text.ParseException;

public interface ConversionDates {

	/**
	 * Converte uma String em um Date no formato HH:mm;
	 * @param data - String de entrada
	 * @return Date;
	 * @throws ParseException ocorre quando o input do usuário não corresponde ao formato esperado;
	 */
	public Date strToDate(String data) throws ParseException;
	
	/**
	 * Converte uma String em um Date no formato HH:mm;
	 * @param data - String de entrada
	 * @return Date;
	 * @throws ParseException ocorre quando o input do usuário não corresponde ao formato esperado;
	 */
	public Date strToDateHour(String data) throws ParseException;
}
