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
	private JButton ok;
	private JButton add;
	private JButton edit;
	private JButton delete;
	private JButton cancel;
	private JButton cancel2;
	private JButton cancel3;
	private JPanel cards;
	private CardLayout cardLayout;
	private JPanel pMain;
	private JPanel pAdd;
	private JPanel pEdit;
	private JLabel hour1;
	private JLabel hour2;
	private JTextField hourBegin;
	private JTextField hourFinish;
	private JList list;
	private ButtonManager btManager;
	
	/*porra, sadi...
<<<<<<< HEAD
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
		
=======
*/
	public HoraryScreen() {
		super("Timetable screen");
		btManager = new ButtonManager();
		initComponents();
//>>>>>>> branch 'master' of https://github.com/Raspbwolf/Trabalho-01-20172.git
	}
	
	private void initComponents() {
		
		// Config container;
		Container container = getContentPane();
		GridBagConstraints cons = new GridBagConstraints();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		cards = new JPanel(new CardLayout());
		cardLayout = new CardLayout();
		
		// Instantiating JButtons;
		ok = new JButton("OK");
		add = new JButton("Add new time");
		edit = new JButton("Edit time");
		delete = new JButton("Delete time");
		cancel = new JButton("Cancel");
		cancel2 = new JButton("Cancel");
		cancel3 = new JButton("Cancel");
		
		// Add action listeners;
		ok.addActionListener(btManager);
		add.addActionListener(btManager);
		edit.addActionListener(btManager);
		delete.addActionListener(btManager);
		cancel.addActionListener(btManager);
		cancel2.addActionListener(btManager);
		cancel3.addActionListener(btManager);
		
		// Instantiating JLabels;
		hour1 = new JLabel("Enter the start time");
		hour2 = new JLabel("Enter the end time");
		
		// Instantiating JTextFields;
		hourBegin = new JTextField(5);
		hourFinish = new JTextField(5);
		
		pMain = new JPanel(new GridBagLayout());
		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(10);
		cons.gridheight = 2;
		cons.gridwidth = 8;
		cons.gridx = 0;
		cons.gridy = 2;
		pMain.add(list, cons);
		cons.gridx = 0;
		cons.gridy = 6;
		pMain.add(add, cons);
		cons.gridx = 2;
		cons.gridy = 6;
		pMain.add(edit);
		cons.gridx = 4;
		cons.gridy = 6;
		pMain.add(delete);
		cons.gridx = 6;
		cons.gridy = 6;
		pMain.add(cancel);
		cards.add(pMain, "Main");
		
		pAdd = new JPanel(new GridBagLayout());
		cons.gridx = 0;
		cons.gridy = 0;
		pAdd.add(hour1);
		cons.gridx = 0;
		cons.gridy = 1;
		pAdd.add(hourBegin);
		cons.gridx = 0;
		cons.gridy = 2;
		pAdd.add(hour2);
		cons.gridx = 0;
		cons.gridy = 3;
		pAdd.add(hourFinish);
		cons.gridx = 0;
		cons.gridy = 4;
		pAdd.add(ok);
		cons.gridx = 1;
		cons.gridy = 4;
		pAdd.add(cancel2);
		cards.add(pAdd, "Add");
		
		pEdit = new JPanel(new GridBagLayout());
		

		// Another config container;
		setSize(1000, 250);
		setLocationRelativeTo(null);
		setResizable(true);
		
		/*
		
		// JList de Employments
		lsEmployments = new JList();
		lsEmployments.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lsEmployments.setLayoutOrientation(JList.VERTICAL);
		lsEmployments.setVisibleRowCount(10);
		cons.gridheight = 2;
		cons.gridwidth = 8;
		cons.gridx = 0;
		cons.gridy = 2;
		JScrollPane spListEmp = new JScrollPane(lsEmployments);
		pMain.add(spListEmp, cons);
		updateData();

		
		// Register Button
		btRegister = new JButton();
		btRegister.setText("Register new Employment");
		cons.fill = GridBagConstraints.HORIZONTAL; 
		cons.gridx = 0;  
        cons.gridy = 4;
        cons.gridwidth = 2;
        btRegister.addActionListener(btManager);
        pMain.add(btRegister, cons);
        
        //delete button
        btDelete = new JButton();
        btDelete.setText("Delete Employment");
		cons.fill = GridBagConstraints.HORIZONTAL; 
		cons.gridx = 2;  
        cons.gridy = 4;
        cons.gridwidth = 2;
        btDelete.addActionListener(btManager);
        pMain.add(btDelete, cons);
		
        //Edit Button
        btEdit = new JButton();
        btEdit.setText("Edit Employment");
		cons.fill = GridBagConstraints.HORIZONTAL; 
		cons.gridx = 4;  
        cons.gridy = 4;
        cons.gridwidth = 2;
        btEdit.addActionListener(btManager);
        pMain.add(btEdit, cons);
        
        
        //Back to Main Menu Button
        btMainMenu = new JButton("Back to Main Menu");
        cons.fill = GridBagConstraints.HORIZONTAL; 
        cons.gridx = 6;
		cons.gridy = 4;
		btMainMenu.addActionListener(btManager);
		pMain.add(btMainMenu, cons);
		
		pSetup.add(pMain, "pMain");
		// Fim do Painel Principal
		//
		
		//
		*/
		container.add(cards);
		cardLayout = (CardLayout) cards.getLayout();
	}
	
	private class ButtonManager implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource().equals(ok)) {
				try {
					addHorary(hourBegin.getText(), hourFinish.getText());
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "The time you entered is not in the default hh:mm", "Error", 1);
					cardLayout.show(cards, "Add");
				}
			}
			
		}
		
	}
	
	private Horary addHorary(String hour1, String hour2) throws ParseException {
		return HoraryCtrl.getInstance().addHorary(HoraryCtrl.getInstance().strToDateHour(hour1), HoraryCtrl.getInstance().strToDateHour(hour2));
	}

	public void show(String string) {
		cardLayout.show(cards, string);		
	}
	/*
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
	*/
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
