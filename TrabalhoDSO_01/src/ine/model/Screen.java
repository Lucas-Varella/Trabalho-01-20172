package ine.model;

import java.sql.Date;

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
	 * @param data
	 * @return
	 */
	/*
	 * Vou deixar pro Varella fazer essa porra;
	 */
	public Date formatStringToDate(String data);
}
