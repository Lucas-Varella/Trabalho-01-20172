package br.ufsc.ine5605.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date; 
import java.text.ParseException;
import javax.swing.*;
import br.ufsc.ine5605.controller.EmployeeCtrl;
import br.ufsc.ine5605.controller.EmploymentCtrl;
import br.ufsc.ine5605.model.*;


public class EmployeeScreenI extends JFrame{
	
	private JLabel lbEmpScrn;
	private JButton btAdd;
	private EmployeeDAO employeeDAO = new EmployeeDAO();
	private JButton btEdit;
	private ButtonManager btManager;
	private CardLayout cardLayout;
	private JList<String> lsEmployees;
	private JPanel screen;
	private JPanel pnAdd;
	private JButton btSave;
	private JPanel pnMain;
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
	private JButton btCancel;
	private JButton btConEdit;
	private JButton btDel;
	private JButton btErrorEdit;
	private boolean edit;
	private JTextField tfEmployment;
	private JTextField tfNewEmployment;
	private JTextField tfEmployee;
	
	
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
						
		
		
		//List of emps
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
		btEdit = new JButton("Edit an employee");
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
		cons.gridy = 4;
		pnAdd.add(new JLabel("Employee's Name:"), cons);
		cons.gridx = 4;
		cons.gridy = 4;
		tfName = new JTextField(10);
		pnAdd.add(tfName, cons);
		cons.gridx = 0;
		cons.gridy = 8;
		pnAdd.add(new JLabel("Employee's Birthday: "), cons);
		cons.gridx = 4;
		cons.gridy = 8;
		tfBday = new JTextField(10);
		pnAdd.add(tfBday, cons);
		cons.gridx = 0;
		cons.gridy = 12;
		pnAdd.add(new JLabel("Employee's Phone:"), cons);
		cons.gridx = 4;
		cons.gridy = 12;
		tfPhone = new JTextField(10);
		pnAdd.add(tfPhone, cons);
		cons.gridx = 0;
		cons.gridy = 16;
		pnAdd.add(new JLabel("Employee's Salary:"), cons);
		cons.gridx = 4;
		cons.gridy = 16;
		tfSalary = new JTextField(10);
		pnAdd.add(tfSalary, cons);
		
		//Text Field employments
		cons.gridx = 0;
		cons.gridy = 20;
		pnAdd.add(new JLabel("Employment's Code: "), cons);

		cons.gridx = 4;
		cons.gridy = 20;
		tfEmployment = new JTextField(10);
		pnAdd.add(tfEmployment, cons);
		
			//Save button
		btSave = new JButton("Save");
		cons.gridx = 4;
		cons.gridy = 24;
		btSave.addActionListener(btManager);
		pnAdd.add(btSave, cons);
		
			//Return button
		btReturn = new JButton("Return");
		cons.gridx = 0;
		cons.gridy = 24;
		btReturn.addActionListener(btManager);
		pnAdd.add(btReturn, cons);
		
		
		//Edit panel
		pnEdit = new JPanel(new GridBagLayout());
		//Text fields
		cons.gridheight = 2;
		cons.gridwidth = 4;
		cons.gridx = 0;
		cons.gridy = 0;
		pnEdit.add(new JLabel("Employee's Code:"), cons);
		cons.gridx = 4;
		tfEmployee = new JTextField(10);
		pnEdit.add(tfEmployee, cons);
		cons.gridx = 0;
		cons.gridy = 4;
		pnEdit.add(new JLabel("New Name:"), cons);
		cons.gridx = 4;
		cons.gridy = 4;
		tfNewName = new JTextField(10);
		pnEdit.add(tfNewName, cons);
		cons.gridx = 0;
		cons.gridy = 8;
		pnEdit.add(new JLabel("New Birthday: "), cons);
		cons.gridx = 4;
		cons.gridy = 8;
		tfNewBday = new JTextField(10);
		pnEdit.add(tfNewBday, cons);
		cons.gridx = 0;
		cons.gridy = 12;
		pnEdit.add(new JLabel("Employee's Phone:"), cons);
		cons.gridx = 4;
		cons.gridy = 12;
		tfNewPhone = new JTextField(10);
		pnEdit.add(tfNewPhone, cons);
		cons.gridx = 0;
		cons.gridy = 16;
		pnEdit.add(new JLabel("Employee's Salary:"), cons);
		cons.gridx = 4;
		cons.gridy = 16;
		tfNewSalary = new JTextField(10);
		pnEdit.add(tfNewSalary, cons);
		cons.gridx = 0;
		cons.gridy = 20;
		pnEdit.add(new JLabel("New Employment's code: "), cons);
		//Tf New Employment
		cons.gridx = 4;
		cons.gridy = 20;
		tfNewEmployment = new JTextField(10);
		pnEdit.add(tfNewEmployment, cons);
		//cancel button
		btCancel = new JButton("Cancel");
		cons.gridx = 0;
		cons.gridy = 24;
		btCancel.addActionListener(btManager);
		pnEdit.add(btCancel, cons);
		//Confirm Edit button
		btConEdit = new JButton("Confirm Editing");
		cons.gridx = 4;
		cons.gridy = 24;
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
				try {
					employeeDAO.persist();
					employeeDAO.load();
				}catch(NullPointerException e1) {
					
				}
			
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

				cardLayout.show(screen, "Edit Employee");

				
			}else if (e.getSource().equals(btConEdit)) {
				updateData();
				edit = true;
				try {
					String name = tfNewName.getText();
					Date bday = EmployeeCtrl.getInstance().strToDate(tfNewBday.getText());
					int phone = Integer.parseInt(tfNewPhone.getText());
					int salary = Integer.parseInt(tfNewSalary.getText());
					Employee employee = EmployeeCtrl.getInstance().findEmployeeByNumReg(Integer.parseInt(tfEmployee.getText()));
					Employment employment = EmploymentCtrl.getInstance().findEmploymentByCode(Integer.parseInt(tfNewEmployment.getText()));
					if(!EmployeeCtrl.getInstance().validNumRegistration(Integer.parseInt(tfEmployee.getText()))) {
						throw new IncorrectRegistrationException();
					}else if(!EmploymentCtrl.getInstance().validCode(Integer.parseInt(tfNewEmployment.getText()))) {
						throw new IncorrectCodeException();

					}else {
						employee.setName(name);
						employee.setDateBirth(bday);
						employee.setPhone(phone);
						employee.setSalary(salary);
						employee.setEmployment(employment);
						listing("","","","", "");
						employeeDAO.override(employee);
						employeeDAO.load();
						updateData();
						employeeDAO.persist();
					}
					
					
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "\"Please Type Required Information, knowing that Phone number and Salary are numbers.\"");
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Please Type the Date in a correct DD/MM/YYY Format.");
				} catch (IncorrectRegistrationException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (IncorrectCodeException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			 else if(e.getSource().equals(btDel)) {
				try {
					EmployeeCtrl.getInstance().delEmployee(EmployeeCtrl.getInstance().getEmployee(lsEmployees.getSelectedIndex() - 1));
					updateData();
					JOptionPane.showMessageDialog(null, "Employee deleted Successfully!");
						
				} catch(ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, "Please select an Employee first!");
				}
			
			}
			
		}

		private void listing(String name, String bday, String phone, String salary, String employment){
			if(edit) {
				name = tfNewName.getText();
				bday = tfNewBday.getText();
				phone = tfNewPhone.getText();
				salary = tfNewSalary.getText();
				employment = tfNewEmployment.getText();
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
			edited.add(new JLabel("Employment's Code:     " +  employment), cons);
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
				Employment employment = EmploymentCtrl.getInstance().findEmploymentByCode(Integer.parseInt(tfEmployment.getText()));
				if(!EmploymentCtrl.getInstance().validCode(Integer.parseInt(tfEmployment.getText()))) {
					throw new IncorrectCodeException();
				}else {
					try {
					Employee employee = EmployeeCtrl.getInstance().addEmployee(name, bday, phone, salary);
					EmployeeCtrl.getInstance().addContract(employment, employee);
					employeeDAO.put(employee);
					employeeDAO.persist();
						
					}catch(Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					listing(name, tfBday.getText(), tfPhone.getText(), tfSalary.getText(), tfEmployment.getText());
				}	
				
				
			
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please Type Required Information, knowing that Phone number and Salary are numbers.");
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Please Type the Date in a correct DD/MM/YYY Format.");
			} catch (IncorrectCodeException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}

		
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
		this.repaint();
	}
	
}
