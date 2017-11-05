package br.ufsc.ine5605.view;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;   
import java.util.Date;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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
	private ButtonManager btManager;
	private CardLayout cardLayout;
	private JPanel basePanel;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel panel5;
	
	/*
	 * JPanel jpProjeto = new JPanel();
   JPanel jpModulo = new JPanel();
   JPanel jpProgramador = new JPanel();
   JTabbedPane jtpTabs = new JTabbePane();
//adiciona os panels como tabs
   jtpTabs.addTab("Projetos", jpProjeto);
   jtpTabs.addTab("Modulos", jpModulo);
   jtpTabs.addTab("Programadores", jpProgramador);
//adiciona a tab no getContentPane
  getContentPane().add(jtpTabs, BorderLayout.CENTER);
//depois eh vc adicionar os componentes em cada JPanel separado
	 */
	
	public FinancialSectorScreen(FinancialSectorCtrl financialSectorCtrl) {
		super("Financial Sector");
		this.financialSectorCtrl = financialSectorCtrl;
		btManager = new ButtonManager();
		initComponents();	
	}
	
	public void initComponents() {
		//Config container
		Container container = getContentPane();
		container.setLayout(new BoxLayout (container, BoxLayout.Y_AXIS));
        
		//Config basePanel;
        basePanel = new JPanel();
        cardLayout = new CardLayout();
        basePanel.setLayout (cardLayout);
        
        //Add cards in basePanel
        panel1 = new JPanel();
        basePanel.add (panel1, "Financial Sector");
        
        panel2 = new JPanel();
        //panel2.add(arg0);
        Container container1 = getContentPane();
		GridBagConstraints cons = new GridBagConstraints();
		container.setLayout(new GridBagLayout());
		cons.fill = GridBagConstraints.BOTH;
		cons.gridx = 0;  
        cons.gridy = 0;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500, 500);
		setLocationRelativeTo(null);
		
		//All Denied Access Button 
		btAllDeniedAccess = new JButton("All denied access");
		cons.gridx = 5;  
		cons.gridy = 5;
		container.add(btAllDeniedAccess, cons);
		btAllDeniedAccess.addActionListener(btManager);
		
		//Denied Access By Number Button
		btDeniedAccessByNumber = new JButton("Denied Access By Number of Registration");
		cons.gridx = 5;  
		cons.gridy = 6;
		container.add(btDeniedAccessByNumber, cons);		
		btDeniedAccessByNumber.addActionListener(btManager);
		
		//Denied Access By Reason
		btDeniedAccessByReason = new JButton("Denied Access By Reason");
		cons.gridx = 5;  
		cons.gridy = 7;
		container.add(btDeniedAccessByReason, cons);		
		btDeniedAccessByReason.addActionListener(btManager);
		basePanel.add (panel2, "Denied Access");
		
		
        panel3 = new JPanel();
        basePanel.add(panel3, "All Denied Access");
        
        panel4 = new JPanel();
        basePanel.add(panel4, "Denied Access by Number");
        
        panel5 = new JPanel();
        basePanel.add(panel5, "Denied Access By Reason");
        
        //Config Buttons
        btFinancialSector = new JButton("Financial Sector");
        btDeniedAccess = new JButton("Denied Access");
        
        
        //Add Action
        btFinancialSector.addActionListener(btManager);
        btDeniedAccess.addActionListener(btManager);
        
        //JUntando tudo
        container.add(basePanel);
        container.add(btFinancialSector);
        container.add(btDeniedAccess);
        
        setSize(400, 150);
	}
	/*
	 * Ideia para seguir:
	 * 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class TesteCardLayout extends JFrame {
    private JPanel painelBase;
    private JPanel painel1;
    private JPanel painel2;
    private CardLayout cardLayout;
    private JButton botao1;
    private JButton botao2;
    private void initComponents() {
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout (contentPane, BoxLayout.Y_AXIS));
        // Vamos adicionar um JPanel que vai conter 2 cards, 
        // e 2 botões que escolherão cada um o JPanel adequado
        painelBase = new JPanel();
        cardLayout = new CardLayout();
        painelBase.setLayout (cardLayout);
        // Adicionando os cards
            painel1 = new JPanel();
            painel1.setBackground (Color.RED);
            painelBase.add (painel1, "Painel 1");
            painel2 = new JPanel();
            painel2.setBackground (Color.BLUE); 
            painelBase.add (painel2, "Painel 2");
        // Criando os botões
        botao1 = new JButton ("Escolha o painel 1");
        botao2 = new JButton ("Escolha o painel 2");
        botao1.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                cardLayout.show (painelBase, "Painel 1");
            }
        });
        botao2.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                cardLayout.show (painelBase, "Painel 2");
            }
        });
        // Juntando tudo
        contentPane.add (painelBase);
        contentPane.add (botao1);
        contentPane.add (botao2);
        // Só pra não ficar muito pequeno...
        setPreferredSize (new Dimension (200, 300));
    }
    public TesteCardLayout () {
        super ("Teste de Card Layout");
        initComponents();
    }    
    public static void main(String[] args) {
        TesteCardLayout teste = new TesteCardLayout();
        teste.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        teste.pack();
        teste.setVisible (true);
    }
}
	 * 
	 * 
	 */
	
	public void mainConfig() {
		
		//Config. Container
		Container container = getContentPane();
		GridBagConstraints cons = new GridBagConstraints();
		container.setLayout(new GridBagLayout());
		cons.fill = GridBagConstraints.BOTH;
		cons.gridx = 0;  
        cons.gridy = 0;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(400, 150);
		setLocationRelativeTo(null);
		
		//Financial Sector Button 
		btFinancialSector = new JButton("Financial Sector");
		cons.gridx = 5;  
		cons.gridy = 5;
		container.add(btFinancialSector, cons);
		btFinancialSector.addActionListener(btManager);
		
		//Denied Access Button
		btDeniedAccess = new JButton("Denied Access");
		cons.gridx = 6;  
		cons.gridy = 5;
		container.add(btDeniedAccess, cons);		
		btDeniedAccess.addActionListener(btManager);
	}
	
	public void deniedAccess() {
		Container container = getContentPane();
		GridBagConstraints cons = new GridBagConstraints();
		container.setLayout(new GridBagLayout());
		cons.fill = GridBagConstraints.BOTH;
		cons.gridx = 0;  
        cons.gridy = 0;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500, 500);
		setLocationRelativeTo(null);
		
		//All Denied Access Button 
		btAllDeniedAccess = new JButton("All denied access");
		cons.gridx = 5;  
		cons.gridy = 5;
		container.add(btAllDeniedAccess, cons);
		btAllDeniedAccess.addActionListener(btManager);
		
		//Denied Access By Number Button
		btDeniedAccessByNumber = new JButton("Denied Access By Number of Registration");
		cons.gridx = 5;  
		cons.gridy = 6;
		container.add(btDeniedAccessByNumber, cons);		
		btDeniedAccessByNumber.addActionListener(btManager);
		
		//Denied Access By Reason
		btDeniedAccessByReason = new JButton("Denied Access By Reason");
		cons.gridx = 5;  
		cons.gridy = 7;
		container.add(btDeniedAccessByReason, cons);		
		btDeniedAccessByReason.addActionListener(btManager);
	}
	
	public void allDeniedAccess() {
		
	}
	
	private class ButtonManager implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource().equals(btFinancialSector)) {
				
				
			}else if(e.getSource().equals(btDeniedAccess)){
				cardLayout.show(basePanel, "Denied Access");
				
			}else if(e.getSource().equals(btAllDeniedAccess)) {
				
				
			}else if(e.getSource().equals(btDeniedAccessByNumber)) {
				
				
			}else if(e.getSource().equals(btDeniedAccessByReason)) {
				
			
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