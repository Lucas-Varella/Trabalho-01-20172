package br.ufsc.ine5605.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import br.ufsc.ine5605.controller.EmploymentCtrl;

public class EmploymentScreenI extends JFrame {
	
	private EmploymentCtrl ctrl;
	private JLabel lbGuide;
	private JButton btRegister;
	private ButtonManager btManager;
	private JTable tbEmployments;
	private JScrollPane spLista;
	
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
		cons.fill = GridBagConstraints.BOTH; 
		
		//Guide Label 
		lbGuide = new JLabel();
		lbGuide.setText("What do you want to do?");
		container.add(lbGuide, cons);
		cons.fill = GridBagConstraints.HORIZONTAL; 
		cons.gridx = 1;  
		cons.gridy = 2;		
		
		
		// JTable de Employments
		tbEmployments = new JTable();
		tbEmployments.setPreferredScrollableViewportSize(new Dimension(500, 70));
		tbEmployments.setFillsViewportHeight(true);
		cons.fill = GridBagConstraints.REMAINDER; 
		cons.gridx = 0;  
		cons.gridy = 1;
		cons.gridwidth = 3;
		cons.gridheight = 2;
		spLista = new JScrollPane(tbEmployments);
		container.add(spLista, cons);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 600);
		setLocationRelativeTo(null);
		
	}
	
	
	
	//puta vida bixo
	
	
	
	private class ButtonManager implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
		}
		
	}
}
