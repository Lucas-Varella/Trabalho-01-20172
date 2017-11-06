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
	private ButtonManager btManager;
	private CardLayout cardLayout;
	private JPanel cards;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
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
		btManager = new ButtonManager();
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
		
		// Instantiating JPanel with CardLayout;
        cards = new JPanel(new CardLayout());
        
        //Create the cards
        
        //All Denied Access
        panel1 = new JPanel(new GridBagLayout());
        cons.gridx = 0;  
        cons.gridy = 0;
        panel1.add(text1, cons);
        cons.gridx = 0;  
        cons.gridy = 1;
        panel1.add(text2, cons);
        cons.gridx = 0;  
        cons.gridy = 2;
        panel1.add(text3, cons);
        cons.gridx = 0;  
        cons.gridy = 3;
        panel1.add(text4, cons);
        
        cards.add(panel1, "panel1");
        
        //Financial Sector Panel
        panel2 = new JPanel(new GridBagLayout());
        cons.gridx = 0;
        cons.gridy = 0;
        panel2.add(text1, cons);
        cons.gridx = 0;
        cons.gridy = 1;
        panel2.add(text2, cons);
        cons.gridx = 0;
        cons.gridy = 2;
        panel2.add(text3, cons);
        cons.gridx = 0;
        cons.gridy = 3;
        panel2.add(text4, cons);
        
        
        cards.add(panel2, "panel2");
        
        
        // Denied Access Panel
        panel3 = new JPanel(new GridBagLayout());
        cons.gridx = 0;
        cons.gridy = 1;
        panel3.add(text1, cons);
        cons.gridx = 0;
        cons.gridy = 2;
        panel3.add(text2, cons);
        cons.gridx = 0;
        cons.gridy = 3;
        panel3.add(text3, cons);
        cons.gridx = 0;
        cons.gridy = 4;
        panel3.add(text4, cons);
        cards.add(panel3, "panel3");
        
        // Config CardLayout
        container.add(cards);
        cardLayout = (CardLayout) cards.getLayout();
		
		
		
		
		
		
	}
	
	public void show(String panel, ArrayList<MessageAccess> message) {
		cardLayout.show(cards, panel);
	}
	
	private class ButtonManager implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
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
