package br.ufsc.ine5605.model;

public enum Reasons {
	NONUMREGS("The number registration does not exist"),
	NOACCESS("You do not have access"),
	INCTIME("The access time is not allowed"),
	BLOCK("Access blocked");
	String value;
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
