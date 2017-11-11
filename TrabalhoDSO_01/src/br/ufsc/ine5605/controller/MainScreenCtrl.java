package br.ufsc.ine5605.controller;



import br.ufsc.ine5605.view.MainScreen;
import br.ufsc.ine5605.model.Screen2;

/**
 * Classe responsável pela comunicação entre a classe MainScreen com as demais classes;
 * 
 * @author Sadi Júnior Domingos Jacinto;
 * @author marcos
 * @author lucas
 */
public class MainScreenCtrl implements Screen2 {
	private static final MainScreenCtrl instance = new MainScreenCtrl();
	private MainScreen mainScreen;
	
	/**
	 * Construtor padrão da classe;
	 * Instância a MainScreen;
	 * @param mainCtrl - Recebe uma instância do MainController;
	 * 
	 */
	public MainScreenCtrl() {
		mainScreen = new MainScreen(this);
	}
	
	public static MainScreenCtrl getInstance() {
		return instance;
	}

	public void menu() {
		mainScreen.setVisible(true); 
	}
	
	public void employeeMenu() {
		MainController.getInstance().showEmployeeMenu();
	}
	
	public void employmentMenu() {
		MainController.getInstance().showEmploymentMenu();
	}
	
	public void financialSectorMenu() {
		MainController.getInstance().showFinancialSectorMenu();
	}
	
	public int conversionStringToInt(String data) throws NumberFormatException {
		try {
			int num = Integer.parseInt(data);	
			return num;
		} catch(NumberFormatException e ) {
			throw new NumberFormatException();
		}
	}
}
