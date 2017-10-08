package ine.model;

import java.util.ArrayList;
import java.util.Date;

import ine.controller.*;

public class FinancialSector {
	private FinancialSectorCtrl financialSectorCtrl;

	public FinancialSector(FinancialSectorCtrl financialSectorCtrl) {
		this.financialSectorCtrl = financialSectorCtrl;
	}
	
	public boolean validAccess(int numRegistration, Date hour) {
		Employee generic = financialSectorCtrl.validNumRegistration(numRegistration);
		if(generic != null) {
			Privileges p = generic.getEmployment().getEmployment().getPrivilege();
			if(p.equals(Privileges.Full)) {
				return true;
			}else if(p.equals(Privileges.Restricted)) {
				EmploymentRestrictAccess e = (EmploymentRestrictAccess) generic.getEmployment().getEmployment();
				if(validHour(e.getHorarys(), hour)) {
					return true;
				}else {
					//Criar um novo access aqui;
					return false;
				}
			}
			
		}
		// criar um novo access aqui
		return false;
	}
	
	public boolean validHour(ArrayList<Horary> horarys, Date access) {
		String hour = access.toString();
		double hourAccess = Double.parseDouble(hour);
		for(Horary h : horarys) {
			double hourBegin = Double.parseDouble(h.getHourBegin());
			double hourFinish = Double.parseDouble(h.getHourFinish());
			if(hourAccess >= hourBegin && hourAccess <= hourFinish) {
				return true;
			}
		}
		//criar outro access aqui
		return false;
	}
	
	

}
