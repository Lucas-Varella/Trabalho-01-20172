package br.ufsc.ine5605.view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException; 
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import br.ufsc.ine5605.controller.AccessCtrl;
import br.ufsc.ine5605.controller.FinancialSectorCtrl;
import br.ufsc.ine5605.model.Access;
import br.ufsc.ine5605.model.MessageAccess;

/**
 * Classe responsável por exibir ao usuário um relatório de acessos negados;
 * @author Sadi Júnior Domingos Jacinto;
 *
 */
public class AccessScreen extends JFrame {
	private JTable jtTable;
	private JScrollPane spScroll;
	private JPanel panel;
	
	public AccessScreen() {
		initComponents();
	}
	
	private void initComponents() {
		// Config container;
		Container container = getContentPane();
		GridBagConstraints cons = new GridBagConstraints();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        		
		// Instantiating JTable;
		jtTable = new JTable();
		
		jtTable.setPreferredScrollableViewportSize(new Dimension(750, 150));
		jtTable.setFillsViewportHeight(true);

		// Instantiating JScrollPane;
		spScroll = new JScrollPane(jtTable); 
		
		
		container.add(spScroll);

		// Another config container;
		setSize(1000, 250);
        setLocationRelativeTo(null);
        setResizable(true);
		
	}
	
	private void updateData(ArrayList<Access> m) {
		try{
			
			DefaultTableModel model = (DefaultTableModel) jtTable.getModel();
			model.setRowCount(0);
			model.addColumn("Number of Registration");
			model.addColumn("Date of Access");
			model.addColumn("Hour of Access");
			model.addColumn("Reason for Denied Acess");
			if(m != null) {
				for(Access a : m) {
					model.addRow(new Object[]{a.getNumRegistration(), AccessCtrl.getInstance().dateToStringDate(a.getDate()), 
							AccessCtrl.getInstance().dateToStringHour(a.getHour()), a.getReason()});
				}

				jtTable.setModel(model);
				repaint();
				model.removeTableModelListener(jtTable);
				
			}else {
				JOptionPane.showMessageDialog(null, "There are no records of denied accesses", "Error" ,1);
			}
			
	
		}catch(ParseException e) {
			
		}
		
	}
	
	public void show(ArrayList<Access> m) {
		updateData(m);
		this.setVisible(true);
	}
	
	public void noReason() {
		
	}

	public void noNumRegistration() {
		// TODO Auto-generated method stub
		
	}
}
	

	
	/*
	/**
	 * Contrutor padrão da classe;
	 * @param accessCtrl - Recebe uma instância do AccessCtrl, o que permite a comunicação com outras classes;
	 */
	/*public AccessScreen(AccessCtrl accessCtrl) {
		this.accessCtrl = accessCtrl;
	}
	/**
	 * Lista todos os acessos negados; 
	 * @param deniedAccess - Recebe um ArrayList de Access;
	 */
	/*public void listAllDeniedAccess(ArrayList<Access> deniedAccess) {
		try {
			int i = 1;
			for(Access a : deniedAccess) {
				System.out.println("--------------- "+ i + "º ---------------");
				System.out.println("Number of Registration - " + a.getNumRegistration());
				
				System.out.println("Date of Access - " + accessCtrl.dateToStringDate(a.getDate()));
				System.out.println("Hour of Access - " + accessCtrl.dateToStringHour(a.getHour()));
				System.out.println("Reason for denial of access - " + a.getReason());
				i++;
			}
		} catch(ParseException e) {
			System.out.println("An internal error occurred. Please contact support");
		}
	}
	
	/**
	 * Lista os acessos negados previamente filtrados por um número de matrícula;
	 * @param a - Recebe a instância de Access que deverá ser exibida ao usuário;
	 */
	/*public void listDeniedAccessByNumRegistration(Access a) {
		try {
			System.out.println("______________________________________________________");
			System.out.println("Number of Registration - " + a.getNumRegistration());
			System.out.println("Date of Access - " + accessCtrl.dateToStringDate(a.getDate()));
			System.out.println("Hour of Access - " + accessCtrl.dateToStringHour(a.getHour()));
			System.out.println("Reason for denial of access - " + a.getReason());
		} catch(ParseException e) {
			System.out.println("An internal error occurred. Please contact support");
		}
		
	}
	
	/**
	 * Lista os acessos negados previamente filtrados por um motivo de negação de acesso;
	 * @param a - Recebe a instância de Access que deverá ser exibida ao usuário;
	 */
	/*public void listDeniedAccessByReason(Access a) {
		try {
			System.out.println("______________________________________________________");
			System.out.println("Number of Registration - " + a.getNumRegistration());
			System.out.println("Date of Access - " + accessCtrl.dateToStringDate(a.getDate()));
			System.out.println("Hour of Access - " + accessCtrl.dateToStringHour(a.getHour()));
			System.out.println("Reason for denial of access - " + a.getReason());
		} catch(ParseException e) {
			System.out.println("An internal error occurred. Please contact support");
		}
		
	}
	
}
*/
