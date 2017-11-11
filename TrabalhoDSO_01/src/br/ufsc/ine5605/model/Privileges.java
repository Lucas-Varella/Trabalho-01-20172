package br.ufsc.ine5605.model;

import java.io.Serializable;

/**
 * Enum contendo os tipos existentes de privilégio de acesso ao Setor Financeiro; 
 * @author Sadi Júnior Domingos Jacinto;
 *
 */
public enum Privileges implements Serializable {
	
	Full,
	Restricted,
	No;

	private static final long serialVersionUID = 1L;
}
