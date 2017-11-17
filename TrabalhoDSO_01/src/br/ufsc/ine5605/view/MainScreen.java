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
	
	private JLabel lbGuide;
	private JButton btEmployee;
	private JButton btEmployment;
	private JButton btFSector;
	private ButtonManager btManager;
	
	
	
	public MainScreen(MainScreenCtrl screenCtrl) {
		super("Welcome!");
		btManager = new ButtonManager();
		config();
	}
	
	private void config() {
		Container container = getContentPane();
		GridBagConstraints cons = new GridBagConstraints();
		container.setLayout(new GridBagLayout());
		
		
		cons.fill = GridBagConstraints.BOTH; 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 300);
		setLocationRelativeTo(null);
		setResizable(true);
		//Guide Label 
		lbGuide = new JLabel();
		lbGuide.setText("Select the Sector you want to go to:");
		cons.gridx = 0;  
        cons.gridy = 1;
		container.add(lbGuide, cons);
		
		
		
		// Employee button 
		btEmployee = new JButton();
		btEmployee.setText("Employee Sector");
		cons.gridx = 0;  
        cons.gridy = 2;
		container.add(btEmployee, cons);
		btEmployee.addActionListener(btManager);
		
        
        //Employment button
        btEmployment = new JButton();
		btEmployment.setText("Employment Sector");
		cons.gridx = 0;  
        cons.gridy = 3;
		container.add(btEmployment, cons);
		btEmployment.addActionListener(btManager);
		
      
        //Financial Sector button
        btFSector = new JButton();
        btFSector.setText("Financial Sector");
        cons.gridx = 0;  
        cons.gridy = 4;
        container.add(btFSector, cons);
		btFSector.addActionListener(btManager);
		
	}
	
	
	
	private class ButtonManager implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource().equals(btEmployee)) {
				setVisible(false);
				MainScreenCtrl.getInstance().employeeMenu();
			
			} else if(e.getSource().equals(btEmployment)) {
				setVisible(false);
				MainScreenCtrl.getInstance().employmentMenu();
			
			} else if(e.getSource().equals(btFSector)) {
				setVisible(false);
				MainScreenCtrl.getInstance().financialSectorMenu();
			}
		}
	}
}

