package br.ufsc.ine5605.view;


import java.text.ParseException; 
import java.util.Date;
import java.util.Scanner;

import br.ufsc.ine5605.controller.HoraryCtrl;
import br.ufsc.ine5605.model.Horary;

/**
 * Classe responsavel pela interacao do usuario com os metodos de criacao, edicao e remocao de horarios de 
 * acesso ao Setor Financeiro;
 * @author Sadi Junior Domingos Jacinto;
 *
 */
public class HoraryScreen {
	private HoraryCtrl horaryCtrl;
	private Scanner keyboard;
	
	/**
	 * Construtor padrao da classe;
	 * @param horaryCtrl - Recebe uma instancia do HoraryCtrl, permitindo a comunicacao entre classes;
	 */
	public HoraryScreen(HoraryCtrl horaryCtrl) {
		this.horaryCtrl = horaryCtrl;
		this.keyboard = new Scanner(System.in);
	}
	/**
	 * Tela responsavel pela interacao do usuario com a criacao de um objeto do tipo Horary;
	 * @return Horary - Retorna a instancia do objeto criada;
	 */
	public Horary menuAdd() {
		try {
			
			System.out.println("Please enter the times of access to the financial sector allowed to this position: ");
			System.out.println("Hour Start: ");
			/*
			 * Formatar para Date depois;
			 * Criar um try catch aqui para caso o usuario digite um horario
			 * que não se encaixa no padrão Date formatado(hh:mm);
			 */
			Date hourBegin = horaryCtrl.strToDateHour(keyboard.nextLine());
			System.out.println("Hour Finish: ");
			Date hourFinish = horaryCtrl.strToDateHour(keyboard.nextLine());
			return horaryCtrl.addHorary(hourBegin, hourFinish);
				
		}catch(ParseException e) {
			System.out.println("The typed time does not follow the formatting standard hh:mm");
			menuAdd();
		}catch(NullPointerException e) {
			System.out.println("The typed time does not follow the formatting standard hh:mm");
			menuAdd();
		}
		return null;
		
	}
	
	/**
	 * Tela responsavel pela interacao do usuario com a edicao de um objeto Horary;
	 * @param horary - Instancia de Horary que sera modificada;
	 * @return Horary - Retorna a instancia modificada;
	 */
	public Horary edit(Horary horary) {
		try {
			int option = 0;
			do {
				System.out.println("Please select parameter to edit:");
				System.out.println("1 - " + horary.getHourBegin());
				System.out.println("2 - " + horary.getHourFinish());
				
				option = horaryCtrl.conversionStringToInt(keyboard.nextLine());
				switch(option) {
				case 1:
					System.out.println("Enter the new time");
					Date newHorary = horaryCtrl.strToDateHour(keyboard.nextLine());
					Date horaryConvert = horaryCtrl.strToDateHour(horary.getHourFinish());
					return horaryCtrl.addHorary(newHorary, horaryConvert);
					
				case 2:
					System.out.println("Enter the new time");
					newHorary = horaryCtrl.strToDateHour(keyboard.nextLine());
					horaryConvert = horaryCtrl.strToDateHour(horary.getHourBegin());
					return horaryCtrl.addHorary(horaryConvert, newHorary);
					
				default:
					System.out.println("Please enter a valid option");
				}
			} while(option != 1 || option != 2);
			
		}catch(NumberFormatException e) {
			System.out.println("Please enter only numbers");
			edit(horary);
		}catch(ParseException e) {
			System.out.println("The typed time does not follow the formatting standard hh:mm");
			edit(horary);
		}
		return null;
		
	}

}
