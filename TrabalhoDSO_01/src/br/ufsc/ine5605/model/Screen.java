package br.ufsc.ine5605.model;

public interface Screen {

	
	
	
	
	/**
	 * Converte uma String em um double;
	 * 
	 * @param data - String de entrada;
	 * @return double;
	 * 
	 * @throws NumberFormatException ocorre quando o usuário digita um caracter não numérico;
	 */
	public double conversionStringToDouble(String data) throws NumberFormatException;
	
}
