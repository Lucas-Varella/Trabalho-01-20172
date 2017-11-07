package br.ufsc.ine5605.controller;
 
import java.util.ArrayList;

import br.ufsc.ine5605.model.Employment;
import br.ufsc.ine5605.model.EmploymentRestrictAccess;
import br.ufsc.ine5605.model.Privileges;
import br.ufsc.ine5605.model.Contract;
import br.ufsc.ine5605.model.Horary;
import br.ufsc.ine5605.view.EmploymentScreen;
import br.ufsc.ine5605.view.EmploymentScreenI;
import br.ufsc.ine5605.model.Screen2;

/**
 * Classe responsável pela comunicação entre as classes Employment, EmploymentRestrictAccess e EmploymentScreen entre si e
 * com outras classes do sistema;
 * @author Sadi Júnior Domingos Jacinto;
 *
 */
public class EmploymentCtrl implements Screen2{
	private MainController mainCtrl;
	private EmploymentScreenI employmentScreen;
	private Employment employment;
	private EmploymentRestrictAccess employmentRestrictAccess;
	private ArrayList<Employment> employments;
	private static int code = 1000;
	
	/**
	 * Construtor padrão da classe;
	 * @param mainCtrl - recebe uma instância do MainController, o que permite a comunicação com o mesmo;
	 */
	public EmploymentCtrl(MainController mainCtrl) {
		this.mainCtrl = mainCtrl;
		this.employmentScreen = new EmploymentScreenI(this);
		this.employments = new ArrayList<Employment>();
		}

	public void mainMenu() {
		mainCtrl.showMainScreen();
	}
	
	public void menu() {
		employmentScreen.setVisible(true);
	}
	
	/**
	 * Cria um novo Employment e o adiciona a um ArrayList<Employment>; 
	 * @param name - String contendo o nome do Employment;
	 * @param option - Privilégio que o Employment terá;
	 */
	
	public void addEmployment(String name, Privileges option) {
		Employment employment = new Employment(getCode(), name, option, this);
		employments.add(employment);
		setCode(getCode() + 1 );
	}
	
	/**
	 * Cria um novo EmploymentRestrictAccess e o adciona ao ArrayList<Employment>;
	 * @param name - String contendo o nome do cargo;
	 * @param option - Privilégio que o Employment terá, por default será Privileges.Restrict;
	 * @return EmploymentRestrictAccess - o novo EmploymentRestrictAccess criado; 
	 */
	public EmploymentRestrictAccess addEmploymentRestrictAccess(String name, Privileges option) {
		EmploymentRestrictAccess em = new EmploymentRestrictAccess(getCode(), name, option, this);
		employments.add(em);
		setCode(getCode() + 1 );
		return em;
	}
	
	/**
	 * Deleta do sistema um Employment;
	 * @param employment - a instância do Employment que será deletada;
	 */
	public void delEmployment(Employment employment) {
		for(Contract c : employment.getEmployees()) {
			mainCtrl.delEmployee(c.getEmployee());
		}
		employments.remove(employment);
	}
	
	public ArrayList<Employment> getEmployments() {
		return this.employments;
	}
	
	public void listAllEmployments() {
		
	}
	
	public Horary addHorary() {
		return mainCtrl.addHorary();
	}
	
	/**
	 * Busca um Employment no ArrayList<Employment> baseado no index;
	 * @param num - int contendo o index do objeto no ArrayList;
	 * @return Employment - Retorna a instância encontrada no index do ArrayList
	 * @throws NullPointerException Ocorre quando o usuário tenta acessar um indíce inexistente;
	 */
	public Employment getEmployment(int num) throws NullPointerException {
		try {
			return employments.get(num);
		} catch(NullPointerException e) {
			throw e;
		}		
	}

	public static int getCode() {
		return code;
	}

	public static void setCode(int code) {
		EmploymentCtrl.code = code;
	}

	public void setHorary(EmploymentRestrictAccess e, Horary horary) {
		e.addHorary(horary);
	}

	public ArrayList<Horary> getHorarys(EmploymentRestrictAccess e)  {
		return e.getHorarys();
	}

	public Horary editHorary(Horary horary) {
		return mainCtrl.editHorary(horary);
		
	}
	/**
	 * Cria e adiciona um novo Employment. A única diferença com o outro método é a adição de um novo parâmetro de entrada;
	 * @param code2 - int do código do Employment;
	 * @param name - String do nome do Employment;
	 * @param privilege - Privilégio do Employment;
	 * @return Employment - A instância criada;
	 */
	public Employment addEmployment(int code2, String name, Privileges privilege) {
		Employment e = new Employment(code2, name, privilege, this);
		employments.add(e);
		return e;
	}
	
	/**
	 * Cria e adiciona um novo EmploymentRestrictAccess. A única diferença com o outro método é a adição de um novo parâmetro de entrada;
	 * @param code2 - int do código do EmploymentRestrictAccess;
	 * @param name - String do nome do EmploymentRestrictAccess;
	 * @param privilege - Privilégio do EmploymentRestrictAccess;
	 * @return EmploymentRestrictAccess - A instância criada;
	 */
	public EmploymentRestrictAccess addEmploymentRestrictAccess(int code2,
			String name, Privileges privilege) {
		EmploymentRestrictAccess e = new EmploymentRestrictAccess(code, name, privilege, this);
		employments.add(e);
		return e;
	}
	/**
	 * Busca um Employment que possui um Employee associado a ele com determinado número de registro;
	 * @deprecated - Método criado, mas não usado
	 * @param numRegistration - int do número de registro do funcionário;
	 * @return Employment - Retorna o Employment encontrado, retorna null se não encontrar;
	 */
	public Employment getEmploymentByNumRegistration(int numRegistration) {
		Employment e = null;
		for(Employment en : employments) {
			for(Contract c : en.getEmployees()) {
				if(c.getEmployee().getNumRegistration() == numRegistration) {
					e = en;
					return e;
				}
			}
				
		}
		return null;
	}
	
	public int conversionStringToInt(String data) throws NumberFormatException {
		try {
			int num = Integer.parseInt(data);	
			return num;
		} catch(NumberFormatException e ) {
			throw new NumberFormatException();
		}
	}

	public Privileges stringToPrivilege(String txt) {
		
		Privileges pConvertido = null;
		
		if (txt.equals("Full")) {
			pConvertido = Privileges.Full;
		} else if (txt.equals("Restricted")) {
			pConvertido = Privileges.Restricted;
		} else if (txt.equals("No")) {
			pConvertido = Privileges.No;
		}
		
		return pConvertido;
	
	}
	
//	public double conversionStringToDouble(String data)
//			throws NumberFormatException {
//		try {
//			double num = Double.parseDouble(data);	
//			return num;
//		} catch(NumberFormatException e ) {
//			throw new NumberFormatException();
//		}
//	}

}
