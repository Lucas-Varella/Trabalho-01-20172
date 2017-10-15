package br.ufsc.ine5605.model;

/**
 * Enum que contém todos os motivos possíveis de negação de acesso ao setor financeiro;
 * @author Sadi Júnior Domingos Jacinto;
 *
 */
public enum Reasons {
	NONUMREGS("The number registration does not exist"),
	NOACCESS("You do not have access"),
	INCTIME("The access time is not allowed"),
	BLOCK("Access blocked");
	String value;
	
	/**
	 * Construtor padrão do enum
	 * @param value - String de entrada, que determinada qual será o motivo de negação de acesso;
	 */
	Reasons(String value) {
		this.value = value;
	}
	
	public String value(){
		return value;
	}
	public String show(String... params){
		int i = 1;
		String retorno = this.value;
		for(String param : params){
			retorno = retorno.replace("${"+i+"}", param);
			i++;
		}
		return retorno;
	}
	public String toString(){
		return value;
	}
}
