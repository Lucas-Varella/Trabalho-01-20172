package trabalhodso_01;

public class MainController {
	private MainScreenCtrl mainScreenCtrl;
	private EmployeeCtrl employeeCtrl;
	private EmploymentCtrl employmentCtrl;
	private FinancialSectorCtrl financialSectorCtrl;
	private AccessCtrl accessCtrl;
	private HoraryCtrl horaryCtrl;
	
	public MainController() {
		mainScreenCtrl = new MainScreenCtrl(this);
		employeeCtrl = new EmployeeCtrl(this);
		employmentCtrl = new EmploymentCtrl(this);
		financialSectorCtrl = new FinancialSectorCtrl(this);
		accessCtrl = new AccessCtrl(this);
		horaryCtrl = new HoraryCtrl(this);
	}
	
	public void showMainScreen() throws Exception {
		mainScreenCtrl.menu();
	}
	
	public void showEmployeeMenu() {
		employeeCtrl.menu();
	}
	
	public void showEmploymentMenu() {
		employmentCtrl.menu();
	}
	
	public void showFinancialSectorMenu() {
		
	}

}
