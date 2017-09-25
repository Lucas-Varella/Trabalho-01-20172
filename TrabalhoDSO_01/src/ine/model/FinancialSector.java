package ine.model;

import ine.controller.*;

public class FinancialSector {
	private FinancialSectorCtrl financialSectorCtrl;

	public FinancialSector(FinancialSectorCtrl financialSectorCtrl) {
		this.financialSectorCtrl = financialSectorCtrl;
	}
	
	public boolean validAccess(int numRegistration) {
		Employee generic = financialSectorCtrl.validNumRegistration(numRegistration);
		if(generic != null) {
			Privileges p = generic.getEmployment().getEmployment().getPrivilege();
			if(p.equals("Full")) {
				return true;
			}else if(p.equals("Restricted")) {
				
			}
			
		}
		return false;
	}
	
	public boolean validHour(String hourAccess, String hourRegistred) {
		return false;
	}

}
