package br.ufsc.ine5605.model;

import java.io.Serializable;
import java.util.ArrayList; 

import br.ufsc.ine5605.controller.EmploymentCtrl;

/**
 * Classe que contém os atributos e métodos responsáveis pela criação de um objeto 
 * do tipo EmploymentRestrictAccess. Essa classe extende da classe Employment;
 * @author Sadi Júnior Domingos Jacinto;
 */
public class EmploymentRestrictAccess extends Employment implements Serializable {
	private ArrayList<Horary> horarys;
	
	/**
	 * Construtor padrão da classe. Para informações mais detalhadas, consulte a documentação 
	 * do contrutor da classe Employment;
	 */
	public EmploymentRestrictAccess(int code, String name,
			Privileges privilege, EmploymentCtrl employmentCtrl) {
		super(code, name, privilege, employmentCtrl);
		horarys = new ArrayList();
		
	}
	
	public void addHorary(Horary horary) {
		horarys.add(horary);
	}
	
	public void addHoraryByIndex(Horary horary, int index) {
		horarys.set(index, horary);
	}
	
	public void delHorary(int index) {
		horarys.remove(index);
	}
	
	public ArrayList<Horary> getHorarys() {
		return horarys;
	}
	
	public Horary getHorary(int index) {
		return horarys.get(index);
	}

}
