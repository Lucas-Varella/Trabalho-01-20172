package br.ufsc.ine5605.model;

import java.text.DateFormat;
import java.text.ParseException; 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.ufsc.ine5605.controller.FinancialSectorCtrl;

/**
 * Classe responsável por validar a entrada do usuário no Setor Financeiro;
 * 
 * @author Sadi Júnior Domingos Jacinto;
 *
 */
public class FinancialSector implements ConversionDates {
	
	/**
	 * Contrutor padrão da classe;
	 * 
	 * @author Sadi Júnior Domingos Jacinto;
	 */
	public FinancialSector() {
		
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
	
	public boolean isPrivilegeFull(Privileges p) {
		if(p.equals(Privileges.Full)) {
			return true;
		
		}
		return false;
	}
	
	public boolean isPrivilegeRestrict(Privileges p, ArrayList<Horary> horaryAccess, Date hour) throws ParseException {
		if(p.equals(Privileges.Restricted)) {
			
			if(validHour(horaryAccess, hour)) {
				return true;
			
			}
		}
		return false;
	}
		
		public boolean isPrivilegeNo(Privileges p) {
			if(p.equals(Privileges.No)) {
				return true;
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
	public boolean validHour(ArrayList<Horary> horarys, Date access) throws ParseException {
		boolean valid = false;
		
		for(Horary h : horarys) {
			Date hourBegin = strToDateHour(h.getHourBegin());
			Date hourFinish = strToDateHour(h.getHourFinish());
			if(hourFinish.equals(strToDateHour("00:00"))) {
				hourFinish = strToDateHour("24:00");
			}
			if(access.compareTo(hourBegin) >= 0 && access.compareTo(hourFinish) <= 0) {
				valid = true; 
			}
			if(valid) {
				return valid;
			}
			
		}
		
		return valid;
	}
	
	/**
	 * Converte uma String em um Date no formato HH:mm;
	 * @param data - String de entrada
	 * @return Date;
	 * @throws ParseException ocorre quando o input do usuário não corresponde ao formato esperado;
	 */
	public Date strToDateHour(String data) throws ParseException {
			if (data == null) {
	            return null;
	        }
	        Date dataF = null;
	        try {
	            DateFormat dateFormat = new SimpleDateFormat("HH:mm");
	            long time = dateFormat.parse(data).getTime();
	            dataF = new Date(time);
	        } catch (ParseException e) {
	        	throw new ParseException(data, 0);
	        }
	        return dataF;
	}
	
	/**
	 * Converte uma String em um Date no formato HH:mm;
	 * @param data - String de entrada
	 * @return Date;
	 * @throws ParseException ocorre quando o input do usuário não corresponde ao formato esperado;
	 */
	public Date strToDate(String data) throws ParseException {
		if (data == null) {
            return null;
        }
        Date dataF = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            long time = dateFormat.parse(data).getTime();
            dataF = new Date(time);
        } catch (ParseException e) {
            throw new ParseException(data, 0);
        }
        return dataF;
	}
	
	
}
