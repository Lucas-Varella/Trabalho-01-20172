package br.ufsc.ine5605.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Date; 
import java.text.ParseException;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import br.ufsc.ine5605.controller.EmployeeCtrl;
import br.ufsc.ine5605.model.*;

/**
 * Tela responsÃ¡vel pela interaÃ§Ã£o do usuÃ¡rio com as funcionalidades relacionadas aos Employees;
 * @author Sadi JÃºnior Domingos Jacinto;
 */
public class EmployeeScreenI extends JFrame{
	
	private JLabel lbEmpScrn;
	private JButton btAdd;
	private JButton btEdit;
	private JButton btFSector;
	private ButtonManager btManager;
	private CardLayout cardLayout;
	private JList<String> lsEmployees;
	private JPanel screen;
	private JPanel pnAdd;
	private JButton btSave;
	private JPanel pnMain;
	private JComboBox<String> cbEmployments;
	private JButton btReturn;
	private JButton btMainMenu;
	private JTextField tfName;
	private JTextField tfBday;
	private JTextField tfPhone;
	private JTextField tfSalary;
	private JButton btError;
	private JButton btOk;
	private HashMap<String, Employee> hashEmployee;
	private HashMap<String, Employment> hashEmployment;
	
	/**
	 * Construtor padrÃ£o da classe
	 * @param employeeCtrl - Recebe uma instÃ¢ncia do EmployeeCtrl, o que permite a comunicaÃ§Ã£o com outras classes;
	 */
	public EmployeeScreenI() {
		super("Employee Management and Data");
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
		
		
		
		//Doin a lil' panel thingy
		screen = new JPanel(new CardLayout());
		
		//Main panel
		pnMain = new JPanel(new GridBagLayout());
		//Screen label
		lbEmpScrn = new JLabel("Please Select desired Employee to manage, or add a new one.");
		cons.gridx = 0;
		cons.gridy = 0;
		pnMain.add(lbEmpScrn, cons);
						
		
		
		//List of emps, now selectable(?)
		lsEmployees = new JList();
		lsEmployees.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lsEmployees.setLayoutOrientation(JList.VERTICAL);
		lsEmployees.setVisibleRowCount(10);
		cons.gridheight = 20;
		cons.gridwidth = 30;
		cons.gridx = 0;
		cons.gridy = 4;
		JScrollPane spListEmp = new JScrollPane(lsEmployees);
		pnMain.add(spListEmp, cons);
		//updateData();
		
		//addEmployee button
		cons.gridheight = 2;
		cons.gridwidth = 4;
		btAdd = new JButton("Add new Employee");
		cons.gridx = 0;
		cons.gridy = 50;
		btAdd.addActionListener(btManager);
		pnMain.add(btAdd, cons);
						
							
		//edit button
		btEdit = new JButton("Edit selected employee");
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
		
		
		
		screen.add(pnMain, "Employee Screen");
		
		//Add employee panel
		pnAdd = new JPanel(new GridBagLayout());
			//Labels
		
			//Text Fields, labels
		cons.gridheight = 2;
		cons.gridwidth = 4;
		cons.gridx = 0;
		cons.gridy = 0;
		pnAdd.add(new JLabel("Employee's Name:"), cons);
		cons.gridx = 4;
		cons.gridy = 0;
		tfName = new JTextField(10);
		pnAdd.add(tfName, cons);
		cons.gridx = 0;
		cons.gridy = 4;
		pnAdd.add(new JLabel("Employee's Birthday: "), cons);
		cons.gridx = 4;
		cons.gridy = 4;
		tfBday = new JTextField(10);
		pnAdd.add(tfBday, cons);
		cons.gridx = 0;
		cons.gridy = 8;
		pnAdd.add(new JLabel("Employee's Phone:"), cons);
		cons.gridx = 4;
		cons.gridy = 8;
		tfPhone = new JTextField(10);
		pnAdd.add(tfPhone, cons);
		cons.gridx = 0;
		cons.gridy = 12;
		pnAdd.add(new JLabel("Employee's Salary:"), cons);
		cons.gridx = 4;
		cons.gridy = 12;
		tfSalary = new JTextField(10);
		pnAdd.add(tfSalary, cons);
		
		//combo employments
		cons.gridx = 0;
		cons.gridy = 16;
		pnAdd.add(new JLabel("Employee's Employment: "), cons);
		cbEmployments = new JComboBox<String>();
		cons.gridx = 4;
		cons.gridy = 16;
		cons.insets = new Insets(0, 0, 0,0);
		cbEmployments.addActionListener(btManager);
		pnAdd.add(cbEmployments, cons);
		
			//Save button
		btSave = new JButton("Save");
		cons.gridx = 4;
		cons.gridy = 20;
		btSave.addActionListener(btManager);
		pnAdd.add(btSave, cons);
		
			//Return button
		btReturn = new JButton("Return");
		cons.gridx = 0;
		cons.gridy = 20;
		btReturn.addActionListener(btManager);
		pnAdd.add(btReturn, cons);
		
		//Save error panel
		JPanel pnError = new JPanel(new GridBagLayout());
		JLabel lbError = new JLabel("Please don't.");
		cons.gridx = 0;
		cons.gridy = 0;
		pnError.add(lbError, cons);
		btError = new JButton("OK... sorry");
		btError.addActionListener(btManager);
		cons.gridx = 0;
		cons.gridy = 4;
		pnError.add(btError, cons);
		screen.add(pnError, "error");
		
		
		screen.add(pnAdd, "Add Employee");
		container.add(screen);
		cardLayout = (CardLayout) screen.getLayout();
		
		
		
		
		
	}
	private class ButtonManager implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource().equals(btAdd)) {
				
				cardLayout.show(screen, "Add Employee");
				updateData();
			
			} else if(e.getSource().equals(btReturn) || e.getSource().equals(btError) || e.getSource().equals(btOk)) {
				updateData();
				cardLayout.show(screen, "Employee Screen");
			
			}else if(e.getSource().equals(btMainMenu)) {
				
				setVisible(false);
				EmployeeCtrl.getInstance().mainMenu();
				
			}else if(e.getSource().equals(btSave)) {
				updateData();
				saveConditions();
			}else if(e.getSource().equals(btError)) {
				cardLayout.show(screen, "Employee Screen");
			}
			/* else if(e.getSource().equals(but)) {s
				employeeCtrl.action();
			}
			*/
		}
		
		private void saveConditions() {
			try {
				String name = tfName.getText();
				Date bday = EmployeeCtrl.getInstance().strToDate(tfBday.getText());
				int phone = Integer.parseInt(tfPhone.getText());
				int salary = Integer.parseInt(tfSalary.getText());
				if(cbEmployments.getSelectedItem().equals("Please add Employments first.") || cbEmployments.getSelectedItem() == null ) {
					cardLayout.show(screen, "error");
				}else {
					System.out.println("here");
					Employment employment = hashEmployment.get(cbEmployments.getSelectedItem());
					System.out.println("does it do?");
					Employee employee = EmployeeCtrl.getInstance().addEmployee(name, bday, phone, salary);
					System.out.println("added");
					try {
						System.out.println("contract");
						EmployeeCtrl.getInstance().addContract(employment, employee);
					} catch(Exception e) {
						System.out.println(e.getMessage());
					}
					JPanel added = new JPanel(new GridBagLayout());
					GridBagConstraints cons = new GridBagConstraints();
					cons.gridx = 0;
					cons.gridy = 0;
					added.add(new JLabel("Employee's Name:     " + name), cons);
					cons.gridx = 0;
					cons.gridy = 4;
					added.add(new JLabel("Employee's Birthday:     " + bday), cons);
					cons.gridx = 0;
					cons.gridy = 8;
					added.add(new JLabel("Employee's Phone:     " + phone), cons);
					cons.gridx = 0;
					cons.gridy = 12;
					added.add(new JLabel("Employee's Salary:     " + salary), cons);
					cons.gridx = 0;
					cons.gridy = 16;
					System.out.println("before empployment");
					added.add(new JLabel("Employee's Employment:     " +  cbEmployments.getSelectedItem()), cons);
					btOk = new JButton("Return");
					cons.gridx = 0;
					cons.gridy = 20;
					btOk.addActionListener(btManager);
					added.add(btOk, cons);
					screen.add(added, "added");
					cardLayout.show(screen, "added");
				}
				
			} catch (ParseException e1) {
				
				cardLayout.show(screen, "error");
				
				
			} catch (ArrayIndexOutOfBoundsException e) {
				cardLayout.show(screen, "error");
			} catch (NullPointerException e) {
				cardLayout.show(screen, "error");
				
			}
			
		}

		
	}
	private void updateData() {
		//about list of emps
		
		DefaultListModel<String> lsModel = new DefaultListModel<String>();
		if(EmployeeCtrl.getInstance().getEmployees() != null) {

			for(Employee employee : EmployeeCtrl.getInstance().getEmployees()) {
				hashEmployee.put(employee.getName(), employee);
				lsModel.addElement(employee.getName());
			}
			this.repaint();
		}	
		lsEmployees.setModel(lsModel);
		
		//about combo box
		
		DefaultComboBoxModel<String> cbModel = new DefaultComboBoxModel<String>();
		try {
			if(EmployeeCtrl.getInstance().getEmployments() != null ) {
				for(Employment emp : EmployeeCtrl.getInstance().getEmployments()) {
					hashEmployment.put(emp.getName(), emp);
					cbModel.addElement(emp.getName());
				}
			}
			cbEmployments.setModel(cbModel);

			
		}catch(NullPointerException e) {
			this.repaint();
		}


		this.repaint();
		
	}
	/**
	 * 
	 * @return returns a String list, containing the names of all available employments
	 */
//	private void stringListCb() {
//		DefaultComboBoxModel<String> cbModel = new DefaultComboBoxModel<String>();
//		//alter employmentScreen so that every 'add' adds a string here.
//		try {
//			if(employeeCtrl.getEmployments() != null ) {
//				int i = 0;
//				for(Employment emp : employeeCtrl.getEmployments()) {
//					cbModel.addElement(emp.getName());
//					i++;
//				}
//			}
//		}catch(NullPointerException e) {
//			this.repaint();
//		}
//		cbEmployments.setModel(cbModel);
//		this.repaint();
//		
//
//	}
	
//	delEmployee();
//	listEmployees();
//employeeCtrl.mainMenu();
//public void addEmployee() {
//
//System.out.println("Please enter the following information");
//
//System.out.println("Name: ");
//System.out.println("Date of birth: ");
//System.out.println("Phone: ");
//System.out.println("Salary: ");
//System.out.println("Please, enter the number corresponding to the chosen employment: ");
//employeeCtrl.listEmployments();
//Employment gen = employeeCtrl.findEmploymentByIndex(option);
//Employee generic = employeeCtrl.addEmployee(name, birthDay, phone, salary);
//employeeCtrl.addContract(gen, generic);
//
//System.out.println("-------------------------------------------------------------------------");
//System.out.println("Congratulations, you have created a new employee with the following characteristics: ");
//}		
///**
//* Tela responsÃ¡vel pela interaÃ§Ã£o do usuÃ¡rio com os mÃ©todos de ediÃ§Ã£o dos atributos do FuncionÃ¡rio;
//*/
//public void editEmployee() {
//System.out.println("--------------------------------------------------------------------------------");
//System.out.println("Please enter the number corresponding to your choice: ");
//listEmployees();
//System.out.println("Please enter the number corresponding to the characteristic you want to change: ");
//
//System.out.println("1 - Name");
//System.out.println("2 - Birthday");
//System.out.println("3 - Phone");
//System.out.println("4 - Salary");
//System.out.println("5 - Employment");
//System.out.println("Or 0 to exit");
//	System.out.println("Enter a new name: ");
//	System.out.println("Enter a new birthday: ");
//	System.out.println("Enter a new phone");
//	System.out.println("Enter a new salary");
//	System.out.println("Enter the number corresponding to the new employment");
//
//}
//
///**
//* Tela responsÃ¡vel pela interaÃ§Ã£o do usuÃ¡rio com o mÃ©todo de remoÃ§Ã£o de Employees;
//*/
//public void delEmployee() {
//System.out.println("Select an employee to fire");
//employeeCtrl.delEmployee(option);
//
	/*
	public void addEmployee() {
		try {
			System.out.println("Please enter the following information");
			
			System.out.println("Name: ");
			Scanner keyboard = new Scanner(System.in);
			String name = keyboard .nextLine();
			
			System.out.println("Date of birth: ");
		
			Date birthDay = employeeCtrl.strToDate(keyboard.nextLine());
			
			
			System.out.println("Phone: ");
			int phone = employeeCtrl.conversionStringToInt(keyboard.nextLine()); 
			
			
			System.out.println("Salary: ");
			double salary = employeeCtrl.conversionStringToDouble(keyboard.nextLine());
			
			
			System.out.println("Please, enter the number corresponding to the chosen employment: ");
			
			employeeCtrl.listEmployments();
			
			int option = employeeCtrl.conversionStringToInt(keyboard.nextLine()) - 1;
			Employment gen = employeeCtrl.findEmploymentByIndex(option);
		
			Employee generic = employeeCtrl.addEmployee(name, birthDay, phone, salary);
			
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("Congratulations, you have created a new employee with the following characteristics: ");
			System.out.println("Number of registration - " + generic.getNumRegistration());
			System.out.println("Name - " + generic.getName());
			System.out.println("Birthday - " + generic.getDateBirth());
			System.out.println("Phone - " + generic.getPhone());
			System.out.println("Salary - " + generic.getSalary());
			System.out.println("Employment - " + generic.getEmployment().getEmployment().getName());
		
		
		} catch(ParseException e) {
			System.out.println("The date format entered by the user is not correct\n"+ 
							   "Please try again based on this format:\n" + "dd/MM/yyyy");
			System.out.println("-------------------------------------------------------------------------");
			addEmployee();
		}
		
	}	*/
	
	
}
