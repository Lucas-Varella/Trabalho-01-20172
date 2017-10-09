package ine.model;

import java.util.ArrayList;
import java.util.Date;

import ine.controller.*;

public class FinancialSector {
	private FinancialSectorCtrl financialSectorCtrl;

	public FinancialSector(FinancialSectorCtrl financialSectorCtrl) {
		this.financialSectorCtrl = financialSectorCtrl;
	}
	
	public boolean validAccess(int numRegistration, Date hour, Date dateAccess) {
		if(financialSectorCtrl.validNumRegistration(numRegistration)) {
			if(financialSectorCtrl.isAccessBloqued(numRegistration)) {
				financialSectorCtrl.addAccess(numRegistration, dateAccess, hour, Reasons.BLOCK);
				return false;
			}
			Privileges p = financialSectorCtrl.getPrivilegeByNumRegistration(numRegistration);
		
			if(p.equals(Privileges.Full)) {
				return true;
				
			}else if(p.equals(Privileges.Restricted)) {
				ArrayList<Horary> horaryAccess = financialSectorCtrl.getHoraryAccess(numRegistration);
				if(validHour(horaryAccess, hour)) {
					return true;
				}else {
					financialSectorCtrl.addAccess(numRegistration, dateAccess, hour, Reasons.INCTIME);
					return false;
				}
			}else if(p.equals(Privileges.No)) {
				financialSectorCtrl.addAccess(numRegistration, dateAccess, hour, Reasons.NOACCESS);
				return false;
			}
			
		}
		financialSectorCtrl.addAccess(numRegistration, dateAccess, hour, Reasons.NONUMREGS);
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
		return false;
	}
	
	

}
