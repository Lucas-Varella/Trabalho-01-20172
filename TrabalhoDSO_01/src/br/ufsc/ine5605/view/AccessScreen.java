package br.ufsc.ine5605.view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException; 
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import br.ufsc.ine5605.controller.AccessCtrl;
import br.ufsc.ine5605.model.Access;

/**
 * Classe responsável por exibir ao usuário um relatório de acessos negados;
 * @author Sadi Júnior Domingos Jacinto;
 *
 */
public class AccessScreen extends JFrame {
	private AccessCtrl accessCtrl;
	private JPanel panel;
	private JLabel text1;
	private JLabel text2;
	private JLabel text3;
	private JLabel text4;
	private JLabel numberRegistration;
	private JLabel date;
	private JLabel hour;
	private JLabel reason;
	
	public AccessScreen(AccessCtrl accessCtrl) {
		this.accessCtrl = accessCtrl;
		initComponents();
	}
	
	private void initComponents() {
		// Config container;
		Container container = getContentPane();
		GridBagConstraints cons = new GridBagConstraints();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setResizable(true);
        
        // Instantiating JLabels;
        text1 = new JLabel("Number of Registration: ");
        text2 = new JLabel("Date of Access: ");
		text3 = new JLabel("Hour of Access: ");
		text4 = new JLabel("Reason for denial of access: ");
		numberRegistration = new JLabel();
		date = new JLabel();
		hour = new JLabel();
		reason = new JLabel();
		
		
        
        //Create the cards
        
        //All Denied Access
        panel = new JPanel(new GridBagLayout());
        cons.gridx = 0;  
        cons.gridy = 0;
        panel.add(text1, cons);
        cons.gridx = 0;  
        cons.gridy = 1;
        panel.add(text2, cons);
        cons.gridx = 0;  
        cons.gridy = 2;
        panel.add(text3, cons);
        cons.gridx = 0;  
        cons.gridy = 3;
        panel.add(text4, cons);
        
		
	}
	
	public void show(String panel, ArrayList<MessageAccess> message) {
		
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
	public void listDeniedAccessByNumRegistration(Access a) {
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
	public void listDeniedAccessByReason(Access a) {
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
