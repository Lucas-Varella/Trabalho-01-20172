package br.ufsc.ine5605.view;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainScreenI extends JFrame{
	private JLabel lbGuide;
	private JButton btEmployee;
	private JButton btEmployment;
	private JButton btFSector;
	private JButton btExit;
	
	
	public MainScreenI() {
		super("Welcome!");
		config();
	}
	
	private void config() {
		Container container = getContentPane();
		GridBagConstraints cons = new GridBagConstraints();
		container.setLayout(new GridBagLayout());
		
		
		cons.fill = GridBagConstraints.BOTH; 
		cons.gridx = 0;  
        cons.gridy = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 150);
		
		
		
		btEmployee = new JButton();
		btEmployee.setText("Employee Sector");
		container.add(btEmployee, cons);
		cons.gridx = 0;  
        cons.gridy = 1;
        
        btEmployment = new JButton();
		btEmployment.setText("Employment Sector");
		container.add(btEmployment, cons);
		cons.gridx = 0;  
        cons.gridy = 2;
	}
}
