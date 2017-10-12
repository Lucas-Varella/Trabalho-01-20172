package br.ufsc.ine5605.model;

import java.util.ArrayList;

import br.ufsc.ine5605.controller.EmploymentCtrl;


public class EmploymentRestrictAccess extends Employment {
	private ArrayList<Horary> horarys;

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
