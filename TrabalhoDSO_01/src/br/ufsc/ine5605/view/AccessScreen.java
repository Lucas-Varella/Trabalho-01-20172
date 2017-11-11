package br.ufsc.ine5605.view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException; 
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import br.ufsc.ine5605.controller.AccessCtrl;
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
        setSize(1000, 250);
        setLocationRelativeTo(null);
        setResizable(true);
		
		// Inicializa os cabeçalhos das colunas.
		String[] Headings = { "Number of Registration", "Date of Access", "Hour of Access", "Reason for Denied Acess" };
		// Inicializa data.
		String[][] data = {};
		
		// Cria a tabela.
		jtTable = new JTable(data, Headings);
		// Adiciona a tabela a um painel de rolagem.
		spScroll = new JScrollPane(jtTable); 
		// Adiciona o painel de rolagem ao painel de conteúdo.
		container.add(spScroll);
		
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
