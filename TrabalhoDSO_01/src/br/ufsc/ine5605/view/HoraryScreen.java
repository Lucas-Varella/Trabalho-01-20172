package br.ufsc.ine5605.view;


import java.text.ParseException;
import java.util.ConcurrentModificationException;
import java.util.Date;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufsc.ine5605.controller.EmployeeCtrl;
import br.ufsc.ine5605.controller.EmploymentCtrl;
import br.ufsc.ine5605.controller.HoraryCtrl;
import br.ufsc.ine5605.model.Employee;
import br.ufsc.ine5605.model.Horary;


public class HoraryScreen extends JFrame {
	private JButton btConfirm;
	private JButton btAdd;
	private JButton btEdit;
	private JButton btDel;
	private JButton btReturn;
	private JButton btCancel;
	private JButton btCancel1;
	private JButton btOk;
	private JPanel screen;
	private CardLayout cardLayout;
	private JPanel pnMain;
	private JPanel pnAdd;
	private JPanel pnEdit;
	private JTextField tfHour1;
	private JTextField tfHour2;
	private JTextField tfName;
	private JList<String> lsTimes;
	private ButtonManager btManager;
	
	
	public HoraryScreen() {
		super("Timetable screen");
		btManager = new ButtonManager();
		initComponents();
	}
	
	private void initComponents() {
		
		// Config container;
		Container container = getContentPane();
		GridBagConstraints cons = new GridBagConstraints();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(550, 300);
		setLocationRelativeTo(null);
		setResizable(true);
		
		screen = new JPanel(new CardLayout());
		cardLayout = new CardLayout();
		
		// Instantiating JButtons;
		btConfirm = new JButton("Confirm");
		btAdd = new JButton("Add new time");
		btEdit = new JButton("Edit time");
		btDel = new JButton("Delete time");
		btReturn = new JButton("Return");
		btCancel = new JButton("Cancel");
		btCancel1 = new JButton("Cancel");
		
		// Add action listeners;
		btConfirm.addActionListener(btManager);
		btAdd.addActionListener(btManager);
		btEdit.addActionListener(btManager);
		btDel.addActionListener(btManager);
		btReturn.addActionListener(btManager);
		btCancel.addActionListener(btManager);
		btCancel1.addActionListener(btManager);
		
		// Instantiating JLabels;
		JLabel lbName = new JLabel("Enter this time's Identifier:");
		JLabel lbHour1 = new JLabel("Enter the start time");
		JLabel lbHour2 = new JLabel("Enter the ending time");
		JLabel lbHoraryScrn = new JLabel("Select desired time to manage, or add a new one");
		
		
		// Instantiating JTextFields;
		tfHour1 = new JTextField(10);
		tfHour2 = new JTextField(10);
		tfName = new JTextField(10);
		
		pnMain = new JPanel(new GridBagLayout());
		cons.gridx = 0;
		cons.gridy = 0;
		pnMain.add(lbHoraryScrn, cons);
		lsTimes = new JList<String>();
		lsTimes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lsTimes.setLayoutOrientation(JList.VERTICAL);
		lsTimes.setVisibleRowCount(10);
		cons.gridheight = 40;
		cons.gridwidth = 60;
		cons.gridx = 0;
		cons.gridy = 4;
		JScrollPane spListEmp = new JScrollPane(lsTimes);
		pnMain.add(spListEmp, cons);
		cons.gridheight = 2;
		cons.gridwidth = 4;
		cons.gridx = 0;
		cons.gridy = 50;
		pnMain.add(btAdd, cons);
		cons.gridx = 4;
		cons.gridy = 50;
		pnMain.add(btEdit, cons);
		cons.gridx = 8;
		cons.gridy = 50;
		pnMain.add(btDel, cons);
		cons.gridx = 12;
		cons.gridy = 50;
		pnMain.add(btReturn, cons);
		screen.add(pnMain, "Main");
		
		pnAdd = new JPanel(new GridBagLayout());
		cons.gridx = 0;
		cons.gridy = 0;
		pnAdd.add(lbName, cons);
		cons.gridx = 4;
		cons.gridy = 0;
		pnAdd.add(tfName, cons);
		cons.gridx = 0;
		cons.gridy = 4;
		pnAdd.add(lbHour1, cons);
		cons.gridx = 4;
		cons.gridy = 4;
		pnAdd.add(tfHour1, cons);
		cons.gridx = 0;
		cons.gridy = 8;
		pnAdd.add(lbHour2, cons);
		cons.gridx = 4;
		cons.gridy = 8;
		pnAdd.add(tfHour2, cons);
		cons.gridx = 0;
		cons.gridy = 12;
		pnAdd.add(btConfirm, cons);
		cons.gridx = 4;
		cons.gridy = 12;
		pnAdd.add(btCancel, cons);
		screen.add(pnAdd, "Add");
		
		pnEdit = new JPanel(new GridBagLayout());
		

		container.add(screen);
		cardLayout = (CardLayout) screen.getLayout();
		
	}
	
	private class ButtonManager implements ActionListener {


		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource().equals(btAdd)) {
				
				show("Add");
				updateData();
				
			}else if(e.getSource().equals(btConfirm)) {
				try {
					String name = tfName.getText();
					String hour1 = tfHour1.getText();
					String hour2 = tfHour2.getText();
					addHorary(name, hour1, hour2);
					updateData();
					JPanel added = new JPanel(new GridBagLayout());
					GridBagConstraints cons = new GridBagConstraints();
					cons.gridx = 0;
					cons.gridy = 4;
					added.add(new JLabel("Time added Successfully:"));
					cons.gridx = 0;
					cons.gridy = 4;
					added.add(new JLabel("Time's Identifier:     " + name), cons);
					cons.gridx = 0;
					cons.gridy = 8;
					added.add(new JLabel("Time's start Hour:     " + hour1), cons);
					cons.gridx = 0;
					cons.gridy = 12;
					added.add(new JLabel("Time's ending Hour:     " + hour2), cons);
				    btOk = new JButton("Return");
					cons.gridx = 0;
					cons.gridy = 16;
					btOk.addActionListener(btManager);
					added.add(btOk, cons);
					screen.add(added, "added");
					cardLayout.show(screen, "added");
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "The time you entered is not in the default hh:mm", "Error", 1);
					cardLayout.show(screen, "Add");
					updateData();
				}
			} else if(e.getSource().equals(btReturn)) {// || e.getSource().equals(btEdOk) || e.getSource().equals(btError) || e.getSource().equals(btOk) || e.getSource().equals(btCancel)) {
				setVisible(false);
				EmploymentCtrl.getInstance().menu();
			
			} else if(e.getSource().equals(btCancel) || e.getSource().equals(btOk)) {
				show("Main");
			} else if(e.getSource().equals(btDel)) {
//				Horary horary = HoraryCtrl.getInstance().getTime(lsTimes.getSelectedIndex());
				HoraryCtrl.getInstance().delHoraryInt(lsTimes.getSelectedIndex());
				/*
				String name = lsTimes.getSelectedValue().substring(lsTimes.getSelectedValue().indexOf(">") + 1, lsTimes.getSelectedValue().indexOf("<"));
//				System.out.println(name);
				try {
					HoraryCtrl.getInstance().delHorary(name);
				}catch(ConcurrentModificationException e2){
					updateData();
					JOptionPane.showMessageDialog(null, "Time range deleted Successfully!");

				}*/
				updateData();
				JOptionPane.showMessageDialog(null, "Time range deleted Successfully!");
			
			}
			
		}
		
	}
	public void updateData() {
		//about list of emps
		
		DefaultListModel<String> lsModel = new DefaultListModel<String>();
		if(HoraryCtrl.getInstance().getTimes() != null) {

			for(Horary horary : HoraryCtrl.getInstance().getTimes()) {
				
				lsModel.addElement("Name:  >" + horary.getName() + "< --  Hours(Beginning/End):  " +  horary.getHourBegin() + "  /-/  " + horary.getHourFinish());
			}
			this.repaint();
		}	
		lsTimes.setModel(lsModel);
		
	}
	private void addHorary(String name, String hour1, String hour2) throws ParseException {
		HoraryCtrl.getInstance().addHorary(name, HoraryCtrl.getInstance().strToDateHour(hour1), HoraryCtrl.getInstance().strToDateHour(hour2));
	}

	public void show(String string) {
		cardLayout.show(screen, string);		
	}/*
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
