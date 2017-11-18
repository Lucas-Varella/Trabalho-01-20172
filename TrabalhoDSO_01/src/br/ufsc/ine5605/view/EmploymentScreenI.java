package br.ufsc.ine5605.view;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import br.ufsc.ine5605.controller.EmployeeCtrl;
import br.ufsc.ine5605.controller.EmploymentCtrl;
import br.ufsc.ine5605.controller.HoraryCtrl;
import br.ufsc.ine5605.model.Contract;
import br.ufsc.ine5605.model.Employment;
import br.ufsc.ine5605.model.Privileges;


public class EmploymentScreenI extends JFrame {
	
	private JLabel lbGuide;
	private JButton btRegister;
	private ButtonManager btManager;
	private JList lsEmployments;
	private JScrollPane spLista;
	private JTextField tfNome;
	private JTextField tfEdNome;
	private JPanel pSetup;
	private JPanel pMain;
	private JPanel pRegister;
	private JPanel pEdit;
	private CardLayout cardLayout;
	private JComboBox cbPrivileges;
	private JComboBox cbEdPrivileges;
	private JButton btOk;
	private JButton btEdOk;
	private JButton btCancel;
	private JButton btEdCancel;
	private JButton btDelete;
	private JButton btMainMenu;
	private JButton btEdit;
	
	
	public EmploymentScreenI() {
		super("Employment Sector");
		btManager = new ButtonManager();
		mainConfig();
	}
	
	private void mainConfig() {
		Container container = getContentPane();
		GridBagConstraints cons = new GridBagConstraints();
		container.setLayout(new GridBagLayout());
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(850, 300);
		setLocationRelativeTo(null);
		setResizable(true);
		
		
		// Panel geral
		pSetup = new JPanel(new CardLayout());
		
		//
		// começo painel Principal
		pMain = new JPanel(new GridBagLayout());
		
		//Guide Label 
		lbGuide = new JLabel();
		lbGuide.setText("Select an Employmet on the list, and click the button");
		cons.fill = GridBagConstraints.BOTH; 
		cons.gridx = 0;  
		cons.gridy = 0;
		pMain.add(lbGuide, cons);
		
		//yeah
		// JList de Employments
		lsEmployments = new JList<>();
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
		// Começo Painel de registro
		pRegister = new JPanel(new GridBagLayout());
		
		//Text field para o nome do cargo
		JLabel lbNomeCargo = new JLabel("Employment's name:     ");
		cons.gridy = 4;
		cons.gridx = 0;
		pRegister.add(lbNomeCargo, cons);
		tfNome = new JTextField(10);
		cons.fill = GridBagConstraints.BOTH; 
		cons.gridx = 4;  
		cons.gridy = 4;
		pRegister.add(tfNome, cons);
		
		// Combo Box de Privilegios
		JLabel lbPrivileges = new JLabel("Employment's privilege:     ");
		cons.gridx = 0;  
		cons.gridy = 8;
		pRegister.add(lbPrivileges, cons);
		String[] privileges = { "Full", "Restricted", "No"};
		cbPrivileges = new JComboBox(privileges);
		cons.fill = GridBagConstraints.BOTH; 
		cons.gridx = 4;  
		cons.gridy = 8;
		cbPrivileges.addActionListener(btManager);
		pRegister.add(cbPrivileges, cons);
		
		// Botao confirma
		btOk = new JButton();
		btOk.setText("OK");
		cons.fill = GridBagConstraints.BOTH; 
		cons.gridx = 4;  
        cons.gridy = 12;
        cons.gridwidth = 4;
        btOk.addActionListener(btManager);
        pRegister.add(btOk, cons);
        
        // Botão Cancela
        btCancel = new JButton();
        btCancel.setText("Cancel");
		cons.fill = GridBagConstraints.BOTH; 
		cons.gridx = 0;  
        cons.gridy = 12;
        cons.gridwidth = 4;
        btCancel.addActionListener(btManager);
        pRegister.add(btCancel, cons);
        
		pSetup.add(pRegister, "pRegister");
		// Fim do Painel de registro
		
		//Inicio Painel Editar
		pEdit = new JPanel(new GridBagLayout());
		
		//Texte field Nome do Painel Editar
		JLabel lbEdNomeCargo = new JLabel("Employment's New Name:     ");
		cons.gridy = 4;
		cons.gridx = 0;
		pEdit.add(lbEdNomeCargo, cons);
		tfEdNome = new JTextField(10);
		cons.fill = GridBagConstraints.BOTH; 
		cons.gridx = 4;  
		cons.gridy = 4;
		pEdit.add(tfEdNome, cons);
		
		//Combo Box Privilegios do Painel Editar
		JLabel lbEdPrivileges = new JLabel("Employment's New privilege:     ");
		cons.gridx = 0;  
		cons.gridy = 8;
		pEdit.add(lbEdPrivileges, cons);
		cbEdPrivileges = new JComboBox(privileges);
		cons.fill = GridBagConstraints.BOTH; 
		cons.gridx = 4;  
		cons.gridy = 8;
		cbEdPrivileges.addActionListener(btManager);
		pEdit.add(cbEdPrivileges, cons);
		
		//Botao ok do Painel Editar
		btEdOk = new JButton();
		btEdOk.setText("OK");
		cons.fill = GridBagConstraints.BOTH; 
		cons.gridx = 4;  
        cons.gridy = 12;
        cons.gridwidth = 4;
        btEdOk.addActionListener(btManager);
        pEdit.add(btEdOk, cons);
        
        // Botão Cancela do painel editar
        btEdCancel = new JButton();
        btEdCancel.setText("Cancel");
		cons.fill = GridBagConstraints.BOTH; 
		cons.gridx = 0;  
        cons.gridy = 12;
        cons.gridwidth = 4;
        btEdCancel.addActionListener(btManager);
        pEdit.add(btEdCancel, cons);
		
		pSetup.add(pEdit, "pEdit");
		//Fim Panel Editar
		
		
		
		
		container.add(pSetup, cons);
		cardLayout = (CardLayout) pSetup.getLayout();
		
		
		
	}
	
	public void updateData() {
		//about list of emps
		DefaultListModel<String> lsModel = new DefaultListModel<String>();
		try {
			
			if(EmploymentCtrl.getInstance().getEmployments() != null) {
	
				for(Employment e : EmploymentCtrl.getInstance().getEmployments()) {
					//hashEmployment.put(e.getName(), e);
					lsModel.addElement("Name :  " + e.getName() + "  Code:  " + e.getCode());
				}
				this.repaint();
			}	
		} catch(NullPointerException e) {
			repaint();
		}
		repaint();
		lsEmployments.setModel(lsModel);
	}	
	
	private class ButtonManager implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource().equals(btRegister)) {
				cardLayout.show(pSetup, "pRegister");
				repaint();
			
			} else if (e.getSource().equals(btOk)) {
				
				if (cbPrivileges.getSelectedItem().toString().equals("Restricted")) {
					
					EmploymentCtrl ctrl = EmploymentCtrl.getInstance();
					ctrl.setEmpDeEnvio(ctrl.addEmploymentRestrictAccess(tfNome.getText(), Privileges.Restricted));
					
					HoraryCtrl.getInstance().menuAdd();
					HoraryCtrl.getInstance().updateData();
					cardLayout.show(pSetup, "pMain");
					
					updateData();
					
				} else {
					EmploymentCtrl.getInstance().addEmployment(tfNome.getText(), EmploymentCtrl.getInstance().stringToPrivilege(cbPrivileges.getSelectedItem().toString()));
					JOptionPane.showMessageDialog(null, "Employment '" + tfNome.getText() + "' Created Successfully!");
					cardLayout.show(pSetup, "pMain");
					repaint();				
				}	
					
			} else if (e.getSource().equals(btCancel)  || e.getSource().equals(btEdCancel)) {
				cardLayout.show(pSetup, "pMain");
				repaint();
			
			} else if(e.getSource().equals(btMainMenu)) {
				setVisible(false);
				EmploymentCtrl.getInstance().mainMenu();
			
			} else if (e.getSource().equals(btDelete)) {
				
				Employment employment = EmploymentCtrl.getInstance().getEmployment(lsEmployments.getSelectedIndex());
				
				if (employment.getEmployees().size() == 0)  {
					
					EmploymentCtrl.getInstance().delEmployment(employment);
					updateData();
					
					JOptionPane.showMessageDialog(null, "Employment Deleted Successfully!");
				} else {
					
					int option = JOptionPane.showConfirmDialog(null, "Employment has Employees Linked to it \n Do you want to delete all of them?");
					
					if(option == JOptionPane.YES_OPTION) {
						for(Contract c: employment.getEmployees()) {
							EmployeeCtrl.getInstance().delEmployee(c.getEmployee());
						}
						
						EmploymentCtrl.getInstance().delEmployment(employment);
						JOptionPane.showMessageDialog(null, "Employment and Employments Deleted Successfully!");
						updateData();
					}
				}
			
			} else if (e.getSource().equals(btEdit)) {
				cardLayout.show(pSetup, "pEdit");
				tfEdNome.setText(lsEmployments.getSelectedValue().toString());
			
			}  else if (e.getSource().equals(btEdOk)) {
				
				if (cbPrivileges.getSelectedItem().toString().equals("Restricted")) { 
					
					
				} else {
				
					Employment	employment = EmploymentCtrl.getInstance().getEmployment(lsEmployments.getSelectedIndex());
					employment.setName(tfEdNome.getText());
					employment.setPrivilege(EmploymentCtrl.getInstance().stringToPrivilege(cbEdPrivileges.getSelectedItem().toString()));
					cardLayout.show(pSetup, "pMain");
				}
				
				updateData();
			}
		}	
	}

}
