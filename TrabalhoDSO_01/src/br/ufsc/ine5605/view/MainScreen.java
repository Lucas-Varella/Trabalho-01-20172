package br.ufsc.ine5605.view;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import br.ufsc.ine5605.controller.MainScreenCtrl;

// Classe responsavel por realizar o input e output de dados referentes a Tela Principal


public class MainScreen extends JFrame{
	
	private MainScreenCtrl screenCtrl; 
	private JLabel lbGuide;
	private JButton btEmployee;
	private JButton btEmployment;
	private JButton btFSector;
	private ButtonManager btManager;
	
	
	
	public MainScreen(MainScreenCtrl screenCtrl) {
		super("Welcome!");
		this.screenCtrl = screenCtrl;
		btManager = new ButtonManager();
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
		setLocationRelativeTo(null);
		
		//Guide Label 
		lbGuide = new JLabel();
		lbGuide.setText("Select the Sector you want to go to:");
		container.add(lbGuide, cons);
		cons.gridx = 0;  
        cons.gridy = 1;
		
		
		// Employee button 
		btEmployee = new JButton();
		btEmployee.setText("Employee Sector");
		container.add(btEmployee, cons);
		btEmployee.addActionListener(btManager);
		cons.gridx = 0;  
        cons.gridy = 2;
        
        //Employment button
        btEmployment = new JButton();
		btEmployment.setText("Employment Sector");
		container.add(btEmployment, cons);
		btEmployment.addActionListener(btManager);
		cons.gridx = 0;  
        cons.gridy = 3;
      
        //Financial Sector button
        btFSector = new JButton();
        btFSector.setText("Financial Sector");
		container.add(btFSector, cons);
		btFSector.addActionListener(btManager);
		cons.gridx = 0;  
        cons.gridy = 4;
	}
	
	
	
	private class ButtonManager implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource().equals(btEmployee)) {
				
				screenCtrl.employeeMenu();
			
			} else if(e.getSource().equals(btEmployment)) {
				
				screenCtrl.employmentMenu();
			
			} else if(e.getSource().equals(btEmployment)) {
				screenCtrl.financialSectorMenu();
			}
		}
	}
}

