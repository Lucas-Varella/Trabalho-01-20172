package br.ufsc.ine5605.model;

public interface Screen2 {
	
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
	 */
	public int conversionStringToInt(String data) throws NumberFormatException;
}
