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
	private MainController mainCtrl;
	private MainScreen mainScreen;
	
	/**
	 * Construtor padrão da classe;
	 * Instância a MainScreen;
	 * @param mainCtrl - Recebe uma instância do MainController;
	 * 
	 */
	public MainScreenCtrl(MainController mainCtrl) {
		this.mainCtrl = mainCtrl;
		mainScreen = new MainScreen(this);
	}
	
	public void menu() {
		mainScreen .setVisible(true);
	}
	
	public void employeeMenu() {
		mainCtrl.showEmployeeMenu();
	}
	
	public void employmentMenu() {
		mainCtrl.showEmploymentMenu();
	}
	
	public void financialSectorMenu() {
		mainCtrl.showFinancialSectorMenu();
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
