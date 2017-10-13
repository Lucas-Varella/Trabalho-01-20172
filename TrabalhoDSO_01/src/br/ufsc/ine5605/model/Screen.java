package br.ufsc.ine5605.model;

import java.sql.Date;
import java.text.ParseException;

public interface Screen {
/*
 * Interface que as telas irão implementar;
 */
	/**
	 * Método para acessar o menu principal da classe;
	 */
	public void menu();
	
	/**
	 * Converte uma String em um int;
	 * 
	 * @param data - String de entrada;
	 * @return int;
	 * 
	 * @throws NumberFormatException ocorre quando o usuário digita um caracter não numérico;
	 * 
	 * @author Sadi Júnior Domingos Jacinto;
	 */
	public int conversionStringToInt(String data) throws NumberFormatException;
	
	/**
	 * Converte uma String em um double;
	 * 
	 * @param data - String de entrada;
	 * @return double;
	 * 
	 * @throws NumberFormatException ocorre quando o usuário digita um caracter não numérico;
	 * 
	 * @author Sadi Júnior Domingos Jacinto;
	 */
	public double conversionStringToDouble(String data) throws NumberFormatException;
	
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
	
	/*
	 * Vou deixar pro Varella fazer essa porra;
	 */
	/*
	public Date strToDate(String data) {
        if (data == null) {
            return null;
        }
        Date dataF = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            long time = dateFormat.parse(data).getTime();
            dataF = new Date(time);
        } catch (ParseException e) {
            System.out.println("The date format entered by the user is not correct\n"+ 
            "Please try again based on this format:\n" + "dd/MM/yyyy");
        }
        return dataF;
    }
	
	public Date strToDateHour(String data) {
        if (data == null) {
            return null;
        }
        Date dataF = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("HH:mm");
            long time = dateFormat.parse(data).getTime();
            dataF = new Date(time);
        } catch (ParseException e) {
            System.out.println("The date format entered by the user is not correct\n"+ 
            "Please try again based on this format:\n" + "hh: mm");
        }
        return dataF;
    }
    */
}
