package br.ufsc.ine5605.model;

import java.sql.Date;
import java.text.ParseException;

public interface Screen {
/*
 * Interface que as telas irão implementar;
 */
	/**
	 * Método para acessar o menu da tela;
	 */
	public void menu();
	
	/**
	 * Converte os dados inseridos pelo usuário em inteiros
	 * @param data
	 * @return
	 */
	public int conversionStringToInt(String data) throws NumberFormatException;
	
	/**
	 * Converte os dados inseridos pelo usuário em doubles;
	 * @param data
	 * @return
	 */
	public double conversionStringToDouble(String data) throws NumberFormatException;
	
	/**
	 * Formata a String inserida pelo usuário para o formato de data escolhido
	 * neste caso dd/MM/yyyy 
	 * @param data
	 * @return Date
	 * @throws ParseException
	 */
	public Date strToDate(String data) throws ParseException;
	
	/**
	 * Formata a String inserida pelo usuário para o formato de data escolhido
	 * neste caso HH:mm
	 * @param data
	 * @return Date
	 * @throws ParseException
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
