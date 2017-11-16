package br.ufsc.ine5605.view;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;   
import java.util.Date;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.ufsc.ine5605.controller.FinancialSectorCtrl;
import br.ufsc.ine5605.model.Reasons;

/**
 * Classe responsavel por realizar o input e output de dados referentes as Setor Financeiro;
 * 
 * @author Sadi Junior Domingos Jacinto;
 */

public class FinancialSectorScreen extends JFrame {
	private FinancialSectorCtrl financialSectorCtrl;
	private JButton btFinancialSector;
	private JButton btDeniedAccess;
	private JButton btAllDeniedAccess;
	private JButton btDeniedAccessByNumber;
	private JButton btDeniedAccessByReason;
	private JButton mainMenu;
	private JButton enter1;
	private JButton enter2;
	private JButton enter3;
	private JButton return1;
	private JButton return2;
	private JButton return3;
	private JButton return4;
	private ButtonManager btManager;
	private ButtonReturn btReturn;
	private CardLayout cardLayout;
	private JPanel cards;
	private JPanel panel1;
	private JLabel text1;
	private JLabel text2;
	private JLabel text3;
	private JLabel text4;
	private JLabel text5;
	private JLabel text6;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel panel5;
	private JTextField number1;
	private JTextField number2;
	private JTextField hour;
	private JComboBox<String> reasons;
	
	public FinancialSectorScreen() {
		super("Menu Financial Sector");
		btManager = new ButtonManager();
		btReturn = new ButtonReturn();
		initComponents();
	}
	
	private void initComponents() {
		
		//Config container;
		Container container = getContentPane();
		GridBagConstraints cons = new GridBagConstraints();
		 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setResizable(true);
        
        // Instantiating JButtons;
        btFinancialSector = new JButton("Enter Financial Sector");
        btDeniedAccess = new JButton("Denied Access");
        btAllDeniedAccess = new JButton("All Denied Access");
        btDeniedAccessByNumber = new JButton("Denied Access By Number Registration");
        btDeniedAccessByReason = new JButton("Denied Access By Reason");
        mainMenu = new JButton("Main Menu");
        enter1 = new JButton("OK");
        enter2 = new JButton("OK");
        enter3 = new JButton("OK");
        return1 = new JButton("Return");
        return2 = new JButton("Return");
        return3 = new JButton("Return");
        return4 = new JButton("Return");
        
        //Add action listeners;
        btFinancialSector.addActionListener(btManager);
        btDeniedAccess.addActionListener(btManager);
        btAllDeniedAccess.addActionListener(btManager);
        btDeniedAccessByNumber.addActionListener(btManager);
        btDeniedAccessByReason.addActionListener(btManager);
        mainMenu.addActionListener(btManager);
        enter1.addActionListener(btManager);
        enter2.addActionListener(btManager);
        enter3.addActionListener(btManager);
        return1.addActionListener(btReturn);
        return2.addActionListener(btReturn);
        return3.addActionListener(btReturn);
        return4.addActionListener(btReturn);
        
        
        // Instantiating JLabel;
        text1 = new JLabel("Please enter the required information:");
        text2 = new JLabel("Number of Registration");
        text3 = new JLabel("Hour of Access");
        text4 = new JLabel("Reason of Denied Access");
        text5 = new JLabel("Number of Registration");
        text6 = new JLabel("Please enter the required information:");
        
        
        // Instantiating JTextField
        
        number1 = new JTextField(10);
        number2 = new JTextField(10);
        hour = new JTextField(10);
        
        // Instantiating JPanel with CardLayout;
        cards = new JPanel(new CardLayout());
        
        //Create the cards
        
        //Main panel
        panel1 = new JPanel(new GridBagLayout());
        cons.gridx = 0;  
        cons.gridy = 0;
        panel1.add(btFinancialSector, cons);
        cons.gridx = 0;  
        cons.gridy = 2;
        panel1.add(btDeniedAccess, cons);
        cons.gridx = 0;
        cons.gridy = 3;
        panel1.add(mainMenu, cons);
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
        panel2.add(number1, cons);
        cons.gridx = 0;
        cons.gridy = 3;
        panel2.add(text3, cons);
        cons.gridx = 0;
        cons.gridy = 4;
        panel2.add(hour, cons);
        cons.gridx = 0;
        cons.gridy = 5;
        panel2.add(enter1, cons);
        cons.gridx = 0;
        cons.gridy = 6;
        panel2.add(return1, cons);
        cards.add(panel2, "panel2");
        
        
        // Denied Access Panel
        panel3 = new JPanel(new GridBagLayout());
        cons.gridx = 0;
        cons.gridy = 1;
        panel3.add(btAllDeniedAccess, cons);
        cons.gridx = 0;
        cons.gridy = 2;
        panel3.add(btDeniedAccessByNumber, cons);
        cons.gridx = 0;
        cons.gridy = 3;
        panel3.add(btDeniedAccessByReason, cons);
        cons.gridx = 0;
        cons.gridy = 4;
        panel3.add(return2, cons);
        cards.add(panel3, "panel3");       
        
        // Denied Access By Number Registration;
        panel4 = new JPanel(new GridBagLayout());
        cons.gridx = 0;
        cons.gridy = 0;
        panel4.add(text6, cons);
        cons.gridx = 0;
        cons.gridy = 1;
        panel4.add(text5, cons);
        cons.gridx = 0;
        cons.gridy = 2;  
        panel4.add(number2, cons);
        cons.gridx = 0;
        cons.gridy = 3;
        panel4.add(enter2, cons);
        cons.gridx = 0;
        cons.gridy = 4;
        panel4.add(return3, cons);
        cards.add(panel4, "panel4");
        
        // Denied Access By Reason;
        panel5 = new JPanel(new GridBagLayout());
        cons.gridx = 0;
        cons.gridy = 0;
        panel5.add(text1, cons);
        cons.gridx = 0;
        cons.gridy = 2;
        panel5.add(text4, cons);
        String[] reason = new String[]{"The number registration does not exist", "You do not have access", "The access time is not allowed",
        		"Access blocked"};
        reasons = new JComboBox<>(reason);
        cons.gridx = 0;
        cons.gridy = 3;
        panel5.add(reasons, cons);
        cons.gridx = 0;
        cons.gridy = 4;
        panel5.add(enter3, cons);
        cons.gridx = 0;
        cons.gridy = 5;
        panel5.add(return4, cons);
        cards.add(panel5, "panel5");
        
        container.add(cards);
        cardLayout = (CardLayout) cards.getLayout();
    }

	private class ButtonManager implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource().equals(btFinancialSector)) {
				cardLayout.show(cards, "panel2");
			
			}else if(e.getSource().equals(btDeniedAccess)){
				cardLayout.show(cards, "panel3");
				
			}else if(e.getSource().equals(mainMenu)) {
				setVisible(false);
				FinancialSectorCtrl.getInstance().mainMenu();
				
			}else if(e.getSource().equals(btAllDeniedAccess)) {
				try{
					FinancialSectorCtrl.getInstance().showAllDeniedAccess();
				} catch(IndexOutOfBoundsException ex) {
					JOptionPane.showMessageDialog(null, "There are no records of denied accesses in the system", "Attention", 1);
				}
			}else if(e.getSource().equals(btDeniedAccessByNumber)) {
				cardLayout.show(cards, "panel4");
				
			}else if(e.getSource().equals(btDeniedAccessByReason)) {
				cardLayout.show(cards, "panel5");
				
			//
			}else if(e.getSource().equals(enter1)) {
				try{
					int number = FinancialSectorCtrl.getInstance().conversionStringToInt(number1.getText());
					Date hours = FinancialSectorCtrl.getInstance().strToDateHour(hour.getText());
					boolean b = FinancialSectorCtrl.getInstance().validAccess(number, hours);
					if(b) {
						JOptionPane.showMessageDialog(null, "Welcome to Financial Sector", "Welcome", 1);
					}else {
						JOptionPane.showMessageDialog(null, "You are not worthy of entrance, for: \n" + FinancialSectorCtrl.getInstance().getReasonBy(), "Error" ,1);
					}
				} catch(ParseException ex) {
					JOptionPane.showMessageDialog(null, "A conversion error has occurred, make sure you have entered the required data correctly", "Error" ,1);
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Please enter only valid numbers", "Error", 1);
				}
			} else if(e.getSource().equals(enter2)) {
				try {
					int number = FinancialSectorCtrl.getInstance().conversionStringToInt(number2.getText());
					FinancialSectorCtrl.getInstance().showDeniedAccessByNumRegistration(number);
				}catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Please enter only valid numbers", "Error", 1);
				}catch(IndexOutOfBoundsException ex) {
					JOptionPane.showMessageDialog(null, "There are no access records denied with \nthis registration number in the system", "Attention", 1);
				}
			} else if(e.getSource().equals(enter3)) {
				try {
					String s = reasons.getSelectedItem().toString();
				

					if(s.equals(Reasons.BLOCK)) {
						FinancialSectorCtrl.getInstance().showDeniedAccessByReason(Reasons.BLOCK);
					}else if(s.equals(Reasons.INCTIME)) {
						FinancialSectorCtrl.getInstance().showDeniedAccessByReason(Reasons.INCTIME);
					}else if(s.equals(Reasons.NOACCESS)) {
						FinancialSectorCtrl.getInstance().showDeniedAccessByReason(Reasons.NOACCESS);
					}else {
						FinancialSectorCtrl.getInstance().showDeniedAccessByReason(Reasons.NONUMREGS);
					}
				}catch(IndexOutOfBoundsException ex) {
					JOptionPane.showMessageDialog(null, "There are no access records denied with this denial motive", "Attention", 1);
				}
			}
			
			
		}

		
	}
	
	private class ButtonReturn implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource().equals(return1)) {
				cardLayout.show(cards, "panel1");
				
			}else if(e.getSource().equals(return2)) {
				cardLayout.show(cards, "panel1");
				
			}else if(e.getSource().equals(return3)) {
				cardLayout.show(cards, "panel3");
			
			}else if(e.getSource().equals(return4)) {
				cardLayout.show(cards, "panel3");
			}
		}
		
	}

}
/*
public class FinancialSectorScreen {
	private FinancialSectorCtrl financialSectorCtrl;
	private Scanner keyboard;

	/**
	 * Construtor padrao da classe FinancialSectorScrenn;
	 * 
	 * @param financialSectorCtrl - recebe uma insta�ncia do FinancialSectorCtrl, para que possa 
	 * transferir o input para que o programa os trate;
	 * 
	 * @author Sadi Junior Domingos Jacinto;
	 */
	/*public FinancialSectorScreen(FinancialSectorCtrl financialSectorCtrl) {
		this.financialSectorCtrl = financialSectorCtrl;
		keyboard = new Scanner(System.in);
	}
	
	/**
	 * Menu principal do Setor Financeiro;
	 * Redireciona o usuario para outros metodos baseado no input do mesmo;
	 * 
	 * @throws NumberFormatException sendo que tal excecao ocorre quando o usuario digita um 
	 * caracter alfabetico ou nao-imprima�vel num campo destinado a� numeros inteiros;
	 * 
	 * @author Sadi Junior Domingos Jacinto;
	 */
	/*public void menuFinancialSector() {//Metodo ja revisado;
		
		try {
		
			int option = 0;
			do {
			
				System.out.println("Please, select your option: ");
				System.out.println("1 - Enter Financial Sector");
				System.out.println("2 - Denied Accesses Report");
				System.out.println("0 - Exit");
				System.out.println("-------------------------------------------------------------------------");
				option = financialSectorCtrl.conversionStringToInt(keyboard.nextLine());
				
				switch(option) {
				
				case 1:
					enterFinancialSector();
					break;
					
				case 2:
					showDeniedAccess();
					break;
				
				case 0:
					System.out.println("Goodbye");
					break;
				
				default:
					System.out.println("The number you entered is not valid");	
				}
				
			}while(option != 0);
			financialSectorCtrl.mainMenu();
			
		}catch(NumberFormatException e) {
			System.out.println("Please enter only valid numbers");
			menuFinancialSector();
		}
	}
	
	/**
	 * Menu de entrada do Setor Financeiro;
	 * Durante a excecucao do metodo, sao requeridos dois dados:
	 * 1 - Numero de registro, sendo valido ou nao e 
	 * 2 - Hora de acesso;
	 * 
	 * @throws NumberFormatException sendo que tal excecao ocorre quando o usuario digita um 
	 * caracter alfabetico ou nao-imprima�vel num campo destinado a� numeros inteiros;
	 * 
	 * @throws ParseException ocorre caso o usuario digite um horario fora do padrao HH:mm 
	 * no momento onde e requerido ao mesmo a hora de acesso;
	 * 
	 * @throws NullPointerException caso esse erro ocorra, por favor, contate urgentemente o suporte;
	 * 
	 * @author Sadi Junior Domingos Jacinto;
	 */
	/*public void enterFinancialSector() {//Metodo revisado, OK;
		
		try {
			System.out.println("Welcome to the entrance of the Financial Sector, noble adventurer");
			System.out.println("To continue your quest for capital, please enter your registration number and," + 
								"\nif you are worthy, you may enter this sacred place");
			System.out.println("Number of registration: ");
			int num = financialSectorCtrl.conversionStringToInt(keyboard.nextLine());
			System.out.println("Time of access: ");
			Date hourAccess = financialSectorCtrl.strToDateHour(keyboard.nextLine());
			
			//aqui eu pego a hora atual do sistema, no caso o dia
			
			Date dateAccess = financialSectorCtrl.getCurrenteDate();
			
			boolean valid = financialSectorCtrl.validAccess(num, hourAccess, dateAccess);
			
			if(valid) {
				System.out.println("You are worthy of entering this sacred place");
			}else {
				System.out.println("You are not worthy of entrance, adventurer, for:");
				System.out.println(financialSectorCtrl.getReasonBy());
			}
		
		}catch(NumberFormatException e) {
			System.out.println("Please enter only valid numbers");
			enterFinancialSector();
		
		}catch(ParseException e) {
			System.out.println("A conversion error has occurred, make sure you have entered the required data correctly");
			enterFinancialSector();
		
		}catch(NullPointerException e) {
			System.out.println("If you are reading this message, \nit means that an unexpected error has occurred. \nPlease contact support for help");
			enterFinancialSector();
		}
		

	}
	
	/**
	 * Menu que exibe um relatorio de acessos negados, sendo que tais acessos podem ser filtrados de 
	 * acordo com a vontade do usuario;
	 * 
	 * @throws NumberFormatException ocorrendo quando o usuario digita um caracter 
	 * alfabetico ou nao-imprima�vel num campo destinado a� numeros inteiros;
	 * 
	 * @throws IndexOutOfBoundsException ocorre quando o usuario tenta listar os acessos negados
	 * quando nao existe nenhum acesso negado cadastrado no sistema;
	 * 
	 * @throws NullPointerException caso esse erro ocorra, por favor, contate urgentemente o suporte;
	 * 
	 * @author Sadi Junior Domingos Jacinto;
	 */
	/*public void showDeniedAccess() {//Metodo revisado, Ok;
		try {
			System.out.println("Please select your option: ");
			System.out.println("1 - Denied Accesses");
			System.out.println("2 - Denied Accesses by Registration number");
			System.out.println("3 - Denied Accesses by denial reason");
			System.out.println("0 - Exit");
			
			int option = financialSectorCtrl.conversionStringToInt(keyboard.nextLine());
			switch(option) {
			
			case 1:
				financialSectorCtrl.showAllDeniedAccess();
				break;
				
			case 2: 
				System.out.println("Please enter the registration number: ");
				int numRegistration = financialSectorCtrl.conversionStringToInt(keyboard.nextLine());
				financialSectorCtrl.showDeniedAccessByNumRegistration(numRegistration);
				break;
				
			case 3:
				System.out.println("Please select your reason of denial: ");
				System.out.println("1 - The number registration does not exist");
				System.out.println("2 - You do not have access");
				System.out.println("3 - Restricted time of access");
				System.out.println("4 - Access blocked");
				int reason = financialSectorCtrl.conversionStringToInt(keyboard.nextLine());
				
				switch(reason) {
				
				case 1:
					financialSectorCtrl.showDeniedAccessByReason(Reasons.NONUMREGS);
					break;
					
				case 2:
					financialSectorCtrl.showDeniedAccessByReason(Reasons.NOACCESS);
					break;
					
				case 3:
					financialSectorCtrl.showDeniedAccessByReason(Reasons.INCTIME);
					break;
					
				case 4:
					financialSectorCtrl.showDeniedAccessByReason(Reasons.BLOCK);
					break;
					
				default:
					System.out.println("The number you entered is not valid");
				}
				break;
				
			case 0:
				financialSectorCtrl.mainMenu();
				break;
				
			default:
				System.out.println("The number you entered is not valid");

			}
			
		} catch(NumberFormatException e) {
			System.out.println("Please enter only a valid number");
			showDeniedAccess();
		
		} catch(IndexOutOfBoundsException e) {
			System.out.println("There is no denied access to the system");
			showDeniedAccess();
		
		} catch(NullPointerException e) {
			System.out.println("There is no denied access to the system");
			showDeniedAccess();
		}
		

	}
}
*/