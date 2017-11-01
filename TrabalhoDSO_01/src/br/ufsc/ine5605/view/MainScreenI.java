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
		container.setLayout(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
	}
}
