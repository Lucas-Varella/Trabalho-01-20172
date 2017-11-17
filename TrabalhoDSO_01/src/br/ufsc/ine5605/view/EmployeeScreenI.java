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
import br.ufsc.ine5605.controller.EmploymentCtrl;
import br.ufsc.ine5605.controller.HoraryCtrl;
import br.ufsc.ine5605.model.*;

/**
 * Tela responsÃ¡vel pela interaÃ§Ã£o do usuÃ¡rio com as funcionalidades relacionadas aos Employees;
 * @author Sadi JÃºnior Domingos Jacinto;
 */
public class EmployeeScreenI extends JFrame{
	
	private JLabel lbEmpScrn;
	private JButton btAdd;
	private EmploymentDAO employmentDAO;
	private JButton btEdit;
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
	private JButton btErrorNew;
	private JButton btOk;
	private JPanel pnEdit;
	private JTextField tfNewName;
	private JTextField tfNewBday;
	private JTextField tfNewPhone;
	private JTextField tfNewSalary;
	private JComboBox<String> cbNewEmployments;
	private JButton btCancel;
	private JButton btConEdit;
	private JButton btDel;
	private JLabel lbError = new JLabel();
	private JButton btErrorEdit;
	private boolean edit;
	private Employee employee;
	
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
		cons.gridwidth = 30;
		cons.gridx = 0;
		cons.gridy = 0;
		pnMain.add(lbEmpScrn, cons);
						
		
		
		//List of emps, now selectable(?)
		lsEmployees = new JList<String>();
		lsEmployees.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lsEmployees.setLayoutOrientation(JList.VERTICAL);
		lsEmployees.setVisibleRowCount(10);
		cons.gridheight = 20;
		cons.gridwidth = 30;
		
		cons.gridx = 0;
		cons.gridy = 2;
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
		
		//Delete button
		btDel = new JButton("Delete Selected Employee");
		cons.gridx = 8;
		cons.gridy = 50;
		btDel.addActionListener(btManager);
		pnMain.add(btDel, cons);
		
		//Main Menu Button
		btMainMenu = new JButton("Main Menu");
		cons.gridx = 12;
		cons.gridy = 50;
		btMainMenu.addActionListener(btManager);
		pnMain.add(btMainMenu, cons);
		
		
		
		screen.add(pnMain, "Employee Screen");
		
		//Add employee panel
		pnAdd = new JPanel(new GridBagLayout());
		
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
		
		
		//Edit panel
		pnEdit = new JPanel(new GridBagLayout());
		//Text fields
		cons.gridheight = 2;
		cons.gridwidth = 4;
		cons.gridx = 0;
		cons.gridy = 0;
		pnEdit.add(new JLabel("New Name:"), cons);
		cons.gridx = 4;
		cons.gridy = 0;
		tfNewName = new JTextField(10);
		pnEdit.add(tfNewName, cons);
		cons.gridx = 0;
		cons.gridy = 4;
		pnEdit.add(new JLabel("New Birthday: "), cons);
		cons.gridx = 4;
		cons.gridy = 4;
		tfNewBday = new JTextField(10);
		pnEdit.add(tfNewBday, cons);
		cons.gridx = 0;
		cons.gridy = 8;
		pnEdit.add(new JLabel("Employee's Phone:"), cons);
		cons.gridx = 4;
		cons.gridy = 8;
		tfNewPhone = new JTextField(10);
		pnEdit.add(tfNewPhone, cons);
		cons.gridx = 0;
		cons.gridy = 12;
		pnEdit.add(new JLabel("Employee's Salary:"), cons);
		cons.gridx = 4;
		cons.gridy = 12;
		tfNewSalary = new JTextField(10);
		pnEdit.add(tfNewSalary, cons);
		cons.gridx = 0;
		cons.gridy = 16;
		pnEdit.add(new JLabel("New Employment: "), cons);
		//cb new
		cbNewEmployments = new JComboBox<String>();
		cons.gridx = 4;
		cons.gridy = 16;
		cons.insets = new Insets(0, 0, 0,0);
		cbNewEmployments.addActionListener(btManager);
		pnEdit.add(cbNewEmployments, cons);
		//cancel button
		btCancel = new JButton("Cancel");
		cons.gridx = 0;
		cons.gridy = 20;
		btCancel.addActionListener(btManager);
		pnEdit.add(btCancel, cons);
		//Confirm Edit button
		btConEdit = new JButton("Confirm Editing");
		cons.gridx = 4;
		cons.gridy = 20;
		btConEdit.addActionListener(btManager);
		pnEdit.add(btConEdit, cons);
		
		
		screen.add(pnAdd, "Add Employee");
		screen.add(pnEdit, "Edit Employee");
		container.add(screen);
		cardLayout = (CardLayout) screen.getLayout();
		
		
		
		
		
		
		
	}
	private class ButtonManager implements ActionListener {

		private AbstractButton btEdOk;

		public void actionPerformed(ActionEvent e) {
			
			if( e.getSource().equals(btErrorNew) || e.getSource().equals(btAdd)) {
				updateData();
				cardLayout.show(screen, "Add Employee");
				
			
			} else if(e.getSource().equals(btReturn) || e.getSource().equals(btEdOk) || e.getSource().equals(btOk) || e.getSource().equals(btCancel)) {
				updateData();
				cardLayout.show(screen, "Employee Screen");
			
			}else if(e.getSource().equals(btMainMenu)) {
				updateData();
				setVisible(false);
				EmployeeCtrl.getInstance().mainMenu();
				updateData();
				
			}else if(e.getSource().equals(btSave)) {
				updateData();
				saveConditions();
			}else if (e.getSource().equals(btEdit) || e.getSource().equals(btErrorEdit)) {
				updateData();
				employee = EmployeeCtrl.getInstance().getEmployee(lsEmployees.getSelectedIndex());

				if(lsEmployees.getSelectedIndex() < -1) {
					JOptionPane.showMessageDialog(null, "Please Select or Create an Employee!");

				}else {
					cardLayout.show(screen, "Edit Employee");

				}
			}else if (e.getSource().equals(btConEdit)) {
				updateData();
				editConditions();
			}
			 else if(e.getSource().equals(btDel)) {
				EmployeeCtrl.getInstance().delEmployee(EmployeeCtrl.getInstance().getEmployee(lsEmployees.getSelectedIndex()));
				updateData();
				JOptionPane.showMessageDialog(null, "Employee deleted Successfully!");
			
			}
			
		}
		
		private void editConditions() {
			edit = true;
			try {
				String name = tfNewName.getText();
				Date bday = EmployeeCtrl.getInstance().strToDate(tfNewBday.getText());
				int phone = Integer.parseInt(tfNewPhone.getText());
				int salary = Integer.parseInt(tfNewSalary.getText());
				employee = EmployeeCtrl.getInstance().getEmployee(lsEmployees.getSelectedIndex());
				Employment employment = EmploymentCtrl.getInstance().getEmployment(cbNewEmployments.getSelectedIndex());
				employee.setName(name);
				employee.setDateBirth(bday);
				employee.setPhone(phone);
				employee.setSalary(salary);
				employee.setEmployment(employment);
				listing("","","","", "");
				
			} catch (ArrayIndexOutOfBoundsException e) {
				lbError.setText("Please add an Employment First");
				error();
			} catch (NullPointerException e) {
				listing("","","","", "");
				
			} catch (NumberFormatException e) {
				lbError.setText("Please Type Required Information, knowing that Phone number and Salary are numbers.");
				error();
			} catch (ParseException e) {
				lbError.setText("Please Type the Date in a correct DD/MM/YYY Format.");
				error();
			}
		}

		private void listing(String name, String bday, String phone, String salary, String employment){
			// TODO Auto-generated method stub
			if(edit) {
				name = tfNewName.getText();
				bday = tfNewBday.getText();
				phone = tfNewPhone.getText();
				salary = tfNewSalary.getText();
				employment = (String) cbNewEmployments.getSelectedItem();
			}
			JPanel edited = new JPanel(new GridBagLayout());
			GridBagConstraints cons = new GridBagConstraints();
			cons.gridx = 0;
			cons.gridy = 0;
			edited.add(new JLabel("Employee's Name:     " + name), cons);
			cons.gridx = 0;
			cons.gridy = 4;
			edited.add(new JLabel("Employee's Birthday:     " + bday), cons);
			cons.gridx = 0;
			cons.gridy = 8;
			edited.add(new JLabel("Employee's Phone:     " + phone), cons);
			cons.gridx = 0;
			cons.gridy = 12;
			edited.add(new JLabel("Employee's Salary:     " + salary), cons);
			cons.gridx = 0;
			cons.gridy = 16;
			edited.add(new JLabel("Employee's Employment:     " +  employment), cons);
			btEdOk = new JButton("Return");
			cons.gridx = 0;
			cons.gridy = 20;
			btEdOk.addActionListener(btManager);
			edited.add(btEdOk, cons);
			screen.add(edited, "added");
			cardLayout.show(screen, "added");
		}

		private void saveConditions() {
			edit = false;
			try {
				String name = tfName.getText();
				Date bday = EmployeeCtrl.getInstance().strToDate(tfBday.getText());
				int phone = Integer.parseInt(tfPhone.getText());
				int salary = Integer.parseInt(tfSalary.getText());
				if(cbEmployments.getSelectedItem() == null || EmploymentCtrl.getInstance().getEmployment(cbEmployments.getSelectedIndex()) == null ) {
					cardLayout.show(screen, "error");
				}else {
					Employment employment = EmploymentCtrl.getInstance().getEmployment(cbEmployments.getSelectedIndex());
					Employee employee = EmployeeCtrl.getInstance().addEmployee(name, bday, phone, salary);
					try {
						//System.out.println("contract");
						EmployeeCtrl.getInstance().addContract(employment, employee);
					} catch(Exception e) {
						System.out.println(e.getMessage());
					}
					listing(name, tfBday.getText(), tfPhone.getText(), tfSalary.getText(), (String) cbEmployments.getSelectedItem());
				}
				
			
			} catch (ArrayIndexOutOfBoundsException e) {
				lbError.setText("Please add an Employment First");
				error();
			} catch (NullPointerException e) {
				lbError.setText("Please add an Employment First");
				error();
			} catch (NumberFormatException e) {
				lbError.setText("Please Type Required Information, knowing that Phone number and Salary are numbers.");
				error();
			} catch (ParseException e) {
				lbError.setText("Please Type the Date in a correct DD/MM/YYY Format.");
				error();
			}
		}

		
	}
	public void error() {
		//Save error panel
		JPanel pnError = new JPanel(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
		pnError.add(lbError, cons);
		if(edit) {
			btErrorEdit = new JButton("Try Again");
			btErrorEdit.addActionListener(btManager);
			cons.gridx = 0;
			cons.gridy = 4;
			pnError.add(btErrorEdit, cons);
		}else {
			btErrorNew = new JButton("Try Again");
			btErrorNew.addActionListener(btManager);
			cons.gridx = 0;
			cons.gridy = 4;
			pnError.add(btErrorNew, cons);
		}
		
		
		screen.add(pnError, "error");
		cardLayout.show(screen, "error");
		
	}
	
	public void updateData() {
		//about list of emps
		
		DefaultListModel<String> lsModel = new DefaultListModel<String>();
		if(EmployeeCtrl.getInstance().getEmployees() != null) {

			for(Employee employee : EmployeeCtrl.getInstance().getEmployees()) {
				//hashEmployee.put(employee.getName(), employee);
				lsModel.addElement("Name:  " + employee.getName() + "  |  Birthday:  " + employee.getDateBirth() + "  |  Phone:  " + employee.getPhone() + "  |  Salary:  " + employee.getSalary() +  "  |  Code:  " +  employee.getNumRegistration());
			}
			this.repaint();
		}	
		lsEmployees.setModel(lsModel);
		
		//about combo box
		
		DefaultComboBoxModel<String> cbModel = new DefaultComboBoxModel<String>();
		try {
			if(EmployeeCtrl.getInstance().getEmployments() != null ) {
				for(Employment emp : EmployeeCtrl.getInstance().getEmployments()) {
					//hashEmployment.put(emp.getName(), emp);
					cbModel.addElement(emp.getName());
				}
			}
			cbEmployments.setModel(cbModel);
			cbNewEmployments.setModel(cbModel);
			
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
