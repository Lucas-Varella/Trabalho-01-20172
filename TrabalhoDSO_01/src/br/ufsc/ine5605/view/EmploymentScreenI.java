package br.ufsc.ine5605.view;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.ufsc.ine5605.controller.EmploymentCtrl;
import br.ufsc.ine5605.model.Privileges;

public class EmploymentScreenI extends JFrame {
	
	private EmploymentCtrl ctrl;
	private JLabel lbGuide;
	private JButton btRegister;
	private ButtonManager btManager;
	private JTable tbEmployments;
	private JScrollPane spLista;
	private JTextField tfNome;
	private JPanel pSetup;
	private JPanel pMain;
	private JPanel pRegister;
	private CardLayout cardLayout;
	private JComboBox cbPrivileges;
	private JButton btOk;
	
	public EmploymentScreenI(EmploymentCtrl ctrl) {
		super("Employment Sector");
		this.ctrl = ctrl;
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
		lbGuide.setText("What do you want to do?");
		cons.fill = GridBagConstraints.CENTER; 
		cons.gridx = 2;  
		cons.gridy = 0;
		pMain.add(lbGuide, cons);
		
		
		// JTable de Employments
		tbEmployments = new JTable();
		tbEmployments.setPreferredScrollableViewportSize(new Dimension(500, 70));
		tbEmployments.setFillsViewportHeight(true);
		cons.fill = GridBagConstraints.CENTER; 
		cons.gridx = 0;  
		cons.gridy = 2;
		cons.gridwidth = 3;
		cons.gridheight = 2;
		spLista = new JScrollPane(tbEmployments);
		pMain.add(spLista, cons);
		
		// Register Button
		btRegister = new JButton();
		btRegister.setText("Register new Employment");
		cons.fill = GridBagConstraints.BOTH; 
		cons.gridx = 1;  
        cons.gridy = 5;
        btRegister.addActionListener(btManager);
        pMain.add(btRegister, cons);
		
		
		pSetup.add(pMain, "pMain");
		// Fim do Painel Principal
		//
		
		//
		// Começo Painel de registro
		pRegister = new JPanel(new GridBagLayout());
		
		//Text field para o nome do cargo
		tfNome = new JTextField();
		tfNome.setText("  Nome do Cargo  ");
		cons.fill = GridBagConstraints.BOTH; 
		cons.gridx = 0;  
		cons.gridy = 1;
		pRegister.add(tfNome, cons);
		
		// Combo Box de Privilegios
		String[] privileges = { "Full", "Restricted", "No"};
		cbPrivileges = new JComboBox(privileges);
		cons.fill = GridBagConstraints.BOTH; 
		cons.gridx = 3;  
		cons.gridy = 1;
		cbPrivileges.addActionListener(btManager);
		pRegister.add(cbPrivileges, cons);
		
		// Botao confirma
		btOk = new JButton();
		btOk.setText("OK");
		cons.fill = GridBagConstraints.BOTH; 
		cons.gridx = 2;  
        cons.gridy = 4;
        cons.gridwidth = 4;
        btRegister.addActionListener(btManager);
        pRegister.add(btOk, cons);
		
		pSetup.add(pRegister, "pRegister");
		// Fim do Painer de registro
		
		
		container.add(pSetup, cons);
		cardLayout = (CardLayout) pSetup.getLayout();
		
		
		
	}
	
//	private void updateData() {
//		
//		DefaultTableModel modelTbEmployments = new DefaultTableModel();
//		modelTbEmployments.addColumn("Position");
//		modelTbEmployments.addColumn("Employee");
//	
//		tbEmployments.setModel(modelTbEmployments);
//		this.repaint();
//	
//	}
	
	private class ButtonManager implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			
			
			if(e.getSource().equals(btRegister)) {
				cardLayout.show(pSetup, "pRegister");
			
			} else if (e.getSource().equals(btOk)) {
				ctrl.addEmployment(tfNome.getText(), ctrl.stringToPrivilege(cbPrivileges.getSelectedItem().toString()));
				cardLayout.show(pSetup, "pMain");
			}
		}	
	}

}
