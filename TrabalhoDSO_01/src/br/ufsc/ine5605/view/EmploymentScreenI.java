package br.ufsc.ine5605.view;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import br.ufsc.ine5605.controller.EmploymentCtrl;
import br.ufsc.ine5605.model.Employee;
import br.ufsc.ine5605.model.Employment;
import br.ufsc.ine5605.model.Privileges;

public class EmploymentScreenI extends JFrame {
	
	private JLabel lbGuide;
	private JButton btRegister;
	private ButtonManager btManager;
	private JList lsEmployments;
	private JScrollPane spLista;
	private JTextField tfNome;
	private JPanel pSetup;
	private JPanel pMain;
	private JPanel pRegister;
	private JPanel pDelete;
	private CardLayout cardLayout;
	private JComboBox cbPrivileges;
	private JButton btOk;
	private JButton btCancel;
	private JButton btDelete;
	private JButton btMainMenu;
	private JTable tbEmploymentsToDelete;
	private JScrollPane spListaDel;
	private JButton btDelCancel;
	private HashMap<String, Employment> hashEmployment;
	
	public EmploymentScreenI() {
		super("Employment Sector");
		btManager = new ButtonManager();
		mainConfig();
	}
	
	private void mainConfig() {
		Container container = getContentPane();
		GridBagConstraints cons = new GridBagConstraints();
		container.setLayout(new GridBagLayout());
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(600, 600);
		setLocationRelativeTo(null);
		
		
		// Panel geral
		pSetup = new JPanel(new CardLayout());
		
		//
		// começo painel Principal
		pMain = new JPanel(new GridBagLayout());
		
		//Guide Label 
		lbGuide = new JLabel();
		lbGuide.setText("Select an Employmet to edit it");
		cons.fill = GridBagConstraints.BOTH; 
		cons.gridx = 2;  
		cons.gridy = 0;
		pMain.add(lbGuide, cons);
		
		
		// JList de Employments
		lsEmployments = new JList();
		lsEmployments.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lsEmployments.setLayoutOrientation(JList.VERTICAL);
		lsEmployments.setVisibleRowCount(10);
		cons.gridheight = 2;
		cons.gridwidth = 6;
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
		
        //Back to Main Menu Button
        btMainMenu = new JButton("Back to Main Menu");
        cons.fill = GridBagConstraints.HORIZONTAL; 
        cons.gridx = 4;
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
		
		//Inicio Painel Deletar
		pDelete = new JPanel(new GridBagLayout());
		
		//Lista de Empregos
		tbEmploymentsToDelete = new JTable();
		tbEmploymentsToDelete.setPreferredScrollableViewportSize(new Dimension(500, 150));
		tbEmploymentsToDelete.setFillsViewportHeight(true);
		cons.fill = GridBagConstraints.HORIZONTAL; 
		cons.gridx = 0;  
		cons.gridy = 2;
		cons.gridwidth = 6;
		cons.gridheight = 2;
		spListaDel = new JScrollPane(tbEmploymentsToDelete);
		pDelete.add(spListaDel, cons);
		
		//Botao Cancel de Delete
		btDelCancel = new JButton();
		btDelCancel.setText("Cancel");
		cons.fill = GridBagConstraints.BOTH; 
		cons.gridx = 2;  
        cons.gridy = 4;
        cons.gridwidth = 4;
        btDelCancel.addActionListener(btManager);
        pDelete.add(btDelCancel, cons);
		
		
		pSetup.add(pDelete, "pDelete");
		//Fim Panel Delete
		
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
					lsModel.addElement(e.getName());
				}
				this.repaint();
			}	
		} catch(NullPointerException e) {
			repaint();
		}
		repaint();
		lsEmployments.setModel(lsModel);
	}	
	
	public JList<String> latrocinio() {
		return this.lsEmployments;
	}
	private class ButtonManager implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource().equals(btRegister)) {
				cardLayout.show(pSetup, "pRegister");
				repaint();
			
			} else if (e.getSource().equals(btOk)) {
				
				EmploymentCtrl.getInstance().addEmployment(tfNome.getText(), EmploymentCtrl.getInstance().stringToPrivilege(cbPrivileges.getSelectedItem().toString()));
				JOptionPane.showMessageDialog(null, "Employment '" + tfNome.getText() + "' Created Successfully!");
				cardLayout.show(pSetup, "pMain");
				repaint();
			
			} else if (e.getSource().equals(btCancel)  || e.getSource().equals(btDelCancel)) {
				cardLayout.show(pSetup, "pMain");
				repaint();
			
			} else if(e.getSource().equals(btMainMenu)) {
				setVisible(false);
				EmploymentCtrl.getInstance().mainMenu();
			
			} else if (e.getSource().equals(btDelete)) {
				cardLayout.show(pSetup, "pDelete");
				repaint();
			
			} 
		}	
	}

}
