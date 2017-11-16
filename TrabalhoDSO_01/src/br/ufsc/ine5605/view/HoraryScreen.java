package br.ufsc.ine5605.view;


import java.text.ParseException; 
import java.util.Date;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufsc.ine5605.controller.HoraryCtrl;
import br.ufsc.ine5605.model.Horary;

/**
 * Classe responsavel pela interacao do usuario com os metodos de criacao, edicao e remocao de horarios de 
 * acesso ao Setor Financeiro;
 * @author Sadi Junior Domingos Jacinto;
 *
 */
public class HoraryScreen extends JFrame {
	
	private ButtonManager btManager;
	private JPanel screen;
	private JPanel pnMain;
	private JComboBox<String> cbHorarys;
	private JButton btAdd;
	private JButton btEdit;
	private JButton btMainMenu;


	public HoraryScreen() {
		super("Access time management");
		btManager = new ButtonManager();
		config();
		
	}
	
	
	public void config() {
		Container container = getContentPane();
		GridBagConstraints cons = new GridBagConstraints();
		container.setLayout(new GridBagLayout());
		
		cons.fill = GridBagConstraints.BOTH;

		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 300);
		setLocationRelativeTo(null);
		setResizable(true);
		cons.gridheight = 2;
		cons.gridwidth = 4;
		
		screen = new JPanel(new CardLayout());
		
		//main panel
		pnMain = new JPanel(new GridBagLayout());
		//Screen label
		cons.gridx = 0;
		cons.gridy = 0;
		pnMain.add(new JLabel("Please Select desired time range to manage, or add a new one."), cons);
		
		//Add new button
		btAdd = new JButton("Add new Access time");
		cons.gridx = 0;
		cons.gridy = 50;
		btAdd.addActionListener(btManager);
		pnMain.add(btAdd, cons);		
		
		//edit button
		btEdit = new JButton("Edit Times");
		cons.gridx = 4;
		cons.gridy = 50;
		btEdit.addActionListener(btManager);
		pnMain.add(btEdit, cons);		
		
		//Main Menu Button
		btMainMenu = new JButton("Main Menu");
		cons.gridx = 8;
		cons.gridy = 50;
		btMainMenu.addActionListener(btManager);
		pnMain.add(btMainMenu, cons);
						
		
		//Access time cb (to edit.)
		cbHorarys = new JComboBox<String>();
		cons.gridheight = 2;
		cons.gridwidth = 4;
		
		screen.add(pnMain, "Main Screen");
		
		
		
	}


	private class ButtonManager implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			
		}
		
		
	}
	
	/*
	private Scanner keyboard;
	
	public HoraryScreen() {
		this.keyboard = new Scanner(System.in);
	}
	
	public Horary menuAdd() {
		try {
			
			System.out.println("Please enter the times of access to the financial sector allowed to this position: ");
			System.out.println("Hour Start: ");
			
			Date hourBegin = HoraryCtrl.getInstance().strToDateHour(keyboard.nextLine());
			System.out.println("Hour Finish: ");
			Date hourFinish = HoraryCtrl.getInstance().strToDateHour(keyboard.nextLine());
			return HoraryCtrl.getInstance().addHorary(hourBegin, hourFinish);
				
		}catch(ParseException e) {
			System.out.println("The typed time does not follow the formatting standard hh:mm");
			menuAdd();
		}catch(NullPointerException e) {
			System.out.println("The typed time does not follow the formatting standard hh:mm");
			menuAdd();
		}
		return null;
		
	}
	
	
	public Horary edit(Horary horary) {
		try {
			int option = 0;
			do {
				System.out.println("Please select parameter to edit:");
				System.out.println("1 - " + horary.getHourBegin());
				System.out.println("2 - " + horary.getHourFinish());
				
				option = HoraryCtrl.getInstance().conversionStringToInt(keyboard.nextLine());
				switch(option) {
				case 1:
					System.out.println("Enter the new time");
					Date newHorary = HoraryCtrl.getInstance().strToDateHour(keyboard.nextLine());
					Date horaryConvert = HoraryCtrl.getInstance().strToDateHour(horary.getHourFinish());
					return HoraryCtrl.getInstance().addHorary(newHorary, horaryConvert);
					
				case 2:
					System.out.println("Enter the new time");
					newHorary = HoraryCtrl.getInstance().strToDateHour(keyboard.nextLine());
					horaryConvert = HoraryCtrl.getInstance().strToDateHour(horary.getHourBegin());
					return HoraryCtrl.getInstance().addHorary(horaryConvert, newHorary);
					
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
*/
}
