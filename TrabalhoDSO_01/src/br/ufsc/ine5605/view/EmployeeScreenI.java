package br.ufsc.ine5605.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date; 
import java.text.ParseException;
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
	
	private EmployeeCtrl employeeCtrl;
	private JLabel lbEmpScrn;
	private JButton btAdd;
	private JButton btEdit;
	private JButton btFSector;
	private ButtonManager btManager;
//	private JTable tbEmployees;
	private JList lsEmployees;
	
	/**
	 * Construtor padrÃ£o da classe
	 * @param employeeCtrl - Recebe uma instÃ¢ncia do EmployeeCtrl, o que permite a comunicaÃ§Ã£o com outras classes;
	 */
	public EmployeeScreen(EmployeeCtrl employeeCtrl) {
		super("Employee Management and Data");
		this.employeeCtrl = employeeCtrl;
		btManager = new ButtonManager();
		config();
		
	}
	public void config() {
		Container container = getContentPane();
		GridBagConstraints cons = new GridBagConstraints();
		container.setLayout(new GridBagLayout());
		
		cons.fill = GridBagConstraints.BOTH;

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 300);
		setLocationRelativeTo(null);
		
		//Screen label
		lbEmpScrn = new JLabel("Please Select desired Employee to manage, or add a new one.");
		cons.gridx = 0;
		cons.gridy = 0;
		container.add(lbEmpScrn, cons);
		
		//List of emps, now selectable(?)
		
		lsEmployees = new JList();
		lsEmployees.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lsEmployees.setLayoutOrientation(JList.VERTICAL);
		lsEmployees.setVisibleRowCount(-1);
		cons.gridheight = 4;
		cons.gridwidth = 2;
		cons.gridx = 0;
		cons.gridy = 1;
		JScrollPane spListEmp = new JScrollPane(lsEmployees);
		container.add(lsEmployees, cons);
		
		
//		
//		//Employees table
//		tbEmployees = new JTable();
//		tbEmployees.setPreferredScrollableViewportSize(new Dimension(300, 90));
//		tbEmployees.setFillsViewportHeight(true);
//		cons.gridwidth = 2;
//		cons.gridheight = 4;
//		cons.gridx = 0;
//		cons.gridy = 1;
//		JScrollPane spScrollEmp = new JScrollPane(tbEmployees);
//		container.add(spScrollEmp, cons);
//		
		//addEmployee button
		btAdd = new JButton("Add new Employee");
		cons.gridx = 0;
		cons.gridy = 8;
		container.add(btAdd, cons);
		btAdd.addActionListener(btManager);
		
		//edit button
		btEdit = new JButton("Edit existing employee");
		cons.gridx = 1;
		cons.gridy = 1;
		container.add(btAdd, cons);
		btEdit.addActionListener(btManager);
		
		
		
		
		
	}
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
		
	}	
	private void updateData() {
		DefaultListModel model = new DefaultListModel();
		addEmployee();
		for(Employee employee : employeeCtrl.getEmployees()) {
			model.addElement(employee.getName());
		}
		lsEmployees.setModel(model);
		this.repaint();
	}
//					delEmployee();
//					listEmployees();
//			employeeCtrl.mainMenu();
//	public void addEmployee() {
//
//			System.out.println("Please enter the following information");
//			
//			System.out.println("Name: ");
//			System.out.println("Date of birth: ");
//			System.out.println("Phone: ");
//			System.out.println("Salary: ");
//			System.out.println("Please, enter the number corresponding to the chosen employment: ");
//			employeeCtrl.listEmployments();
//			Employment gen = employeeCtrl.findEmploymentByIndex(option);
//			Employee generic = employeeCtrl.addEmployee(name, birthDay, phone, salary);
//			employeeCtrl.addContract(gen, generic);
//			
//			System.out.println("-------------------------------------------------------------------------");
//			System.out.println("Congratulations, you have created a new employee with the following characteristics: ");
//	}		
//	/**
//	 * Tela responsÃ¡vel pela interaÃ§Ã£o do usuÃ¡rio com os mÃ©todos de ediÃ§Ã£o dos atributos do FuncionÃ¡rio;
//	 */
//	public void editEmployee() {
//				System.out.println("--------------------------------------------------------------------------------");
//				System.out.println("Please enter the number corresponding to your choice: ");
//				listEmployees();
//				System.out.println("Please enter the number corresponding to the characteristic you want to change: ");
//		
//				System.out.println("1 - Name");
//				System.out.println("2 - Birthday");
//				System.out.println("3 - Phone");
//				System.out.println("4 - Salary");
//				System.out.println("5 - Employment");
//				System.out.println("Or 0 to exit");
//					System.out.println("Enter a new name: ");
//					System.out.println("Enter a new birthday: ");
//					System.out.println("Enter a new phone");
//					System.out.println("Enter a new salary");
//					System.out.println("Enter the number corresponding to the new employment");
//		
//	}
//	
//	/**
//	 * Tela responsÃ¡vel pela interaÃ§Ã£o do usuÃ¡rio com o mÃ©todo de remoÃ§Ã£o de Employees;
//	 */
//	public void delEmployee() {
//			System.out.println("Select an employee to fire");
//			employeeCtrl.delEmployee(option);
//			
	private class ButtonManager implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource().equals(btAdd)) {
				
				addEmployee();
				updateData();
			
			}/* else if(e.getSource().equals(but)) {
				
				employeeCtrl.action();
			
			} else if(e.getSource().equals(but)) {
				employeeCtrl.action();
			}
			*/
		}
	}
	
	
	
}
