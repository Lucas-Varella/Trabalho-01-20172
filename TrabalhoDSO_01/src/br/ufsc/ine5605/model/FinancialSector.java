package br.ufsc.ine5605.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import br.ufsc.ine5605.controller.FinancialSectorCtrl;


public class FinancialSector {
	private FinancialSectorCtrl financialSectorCtrl;

	public FinancialSector(FinancialSectorCtrl financialSectorCtrl) {
		this.financialSectorCtrl = financialSectorCtrl;
	}
	
	public boolean validAccess(int numRegistration, Date hour, Date dateAccess) {
		try {
			if(financialSectorCtrl.isAccessBloqued(numRegistration)) {
				financialSectorCtrl.addAccess(numRegistration, dateAccess, hour, Reasons.BLOCK);
				return false;
			}
			
			if(!financialSectorCtrl.validNumRegistration(numRegistration)) {
				financialSectorCtrl.addAccess(numRegistration, dateAccess, hour, Reasons.NONUMREGS);
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
			
			
		} catch(ParseException e) {
			System.out.println("Se eu chegar a ler isso, significa que deu merda na conversão das datas");
		}
		return false;
	}
	
	public boolean validHour(ArrayList<Horary> horarys, Date access) throws ParseException {
		for(Horary h : horarys) {
			Date hourBegin = financialSectorCtrl.strToDateHour(h.getHourBegin());
			Date hourFinish = financialSectorCtrl.strToDateHour(h.getHourFinish());
			if(access.after(hourBegin) && access.before(hourFinish)) {
				return true;
			}
		}
		return false;
	}
	
	

}
