package trabalhodso_01;

public class MainScreenCtrl {
	private MainController mainCtrl;
	private MainScreen mainScreen;
	
	public MainScreenCtrl(MainController mainCtrl) {
		this.mainCtrl = mainCtrl;
		mainScreen = new MainScreen(this);
	}
	
	public void menu() throws Exception {
		mainScreen.showMenu();
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

}
