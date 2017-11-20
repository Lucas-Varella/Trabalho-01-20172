package br.ufsc.ine5605.view;


import java.text.ParseException; 
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Container;
import java.awt.Dimension;

import br.ufsc.ine5605.controller.AccessCtrl;
import br.ufsc.ine5605.model.Access;

/**
 * Classe responsável por exibir ao usuário um relatório de acessos negados;
 * @author Sadi Júnior Domingos Jacinto;
 *
 */
public class AccessScreen extends JFrame {
	private JTable jtTable;
	private JScrollPane spScroll;
	
	public AccessScreen() {
		initComponents();
	}
	
	private void initComponents() {
		// Config container;
		Container container = getContentPane();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 300);
		setLocationRelativeTo(null);
		setResizable(true);
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
}