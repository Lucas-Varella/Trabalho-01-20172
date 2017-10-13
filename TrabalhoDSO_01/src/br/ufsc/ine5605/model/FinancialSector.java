package br.ufsc.ine5605.model;

import java.text.ParseException; 
import java.util.ArrayList;
import java.util.Date;

import br.ufsc.ine5605.controller.FinancialSectorCtrl;

/**
 * Classe responsável por validar a entrada do usuário no Setor Financeiro;
 * 
 * @author Sadi Júnior Domingos Jacinto;
 *
 */
public class FinancialSector {
	private FinancialSectorCtrl financialSectorCtrl;
	
	/**
	 * Contrutor padrão da classe;
	 * @param financialSectorCtrl - recebe uma instância do FinancialSectorCtrl, que permite à classe
	 * se comunicar com seu controlador, para obtenção de dados de outras classes;
	 * 
	 * @author Sadi Júnior Domingos Jacinto;
	 */
	public FinancialSector(FinancialSectorCtrl financialSectorCtrl) {
		this.financialSectorCtrl = financialSectorCtrl;
	}
	
	/**
	 * Método responsável por verificar se determinado número de registro, em determinado
	 * horário, possuí ou não acesso ao Setor Financeiro;
	 * 
	 * @param numRegistration -número de registro, sendo válido ou não, do funcionário;
	 * @param hour - hora da tentativa de acesso do usuário ao Setor Financeiro;
	 * @param dateAccess - data da tentativa de acesso do usuário ao Setor Financeiro;
	 * @return boolean - permitindo ou negando a entrada do usuário ao Setor Financeiro;
	 * 
	 * @throws ParseException se tal exceção ocorrer, contate o suporte;
	 *
	 * @author Sadi Júnior Domingos Jacinto;
	 */
	public boolean validAccess(int numRegistration, Date hour, Date dateAccess) {//método revisado, OK;
		
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
	
	/**
	 * Método que válida se o horário de tentativa de entrada no Setor Financeiro é correspondente ao
	 * intervalo de tempo cadastrado no Cargo;
	 * 
	 * @param horarys - os horários de acesso do Cargo;
	 * @param access - o horário da tentativa de acesso;
	 * 
	 * @return boolean - true se o horário de acesso for o correto, false se for incorreto
	 * 
	 * @throws ParseException pode ocorrer um erro na conversão dos horários;
	 * 
	 * @author Sadi Júnior Domingos Jacinto;
	 */
	public boolean validHour(ArrayList<Horary> horarys, Date access) throws ParseException {//Método Revisado, modificado, OK;
		for(Horary h : horarys) {
			Date hourBegin = financialSectorCtrl.strToDateHour(h.getHourBegin());
			Date hourFinish = financialSectorCtrl.strToDateHour(h.getHourFinish());
			if(access.after(hourFinish) && access.before(hourBegin)) {
				return false;
			}
		}
		return true;
	}
	
	
}
