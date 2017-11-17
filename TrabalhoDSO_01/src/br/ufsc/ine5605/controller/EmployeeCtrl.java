package br.ufsc.ine5605.controller;

import java.sql.Date;  
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.ufsc.ine5605.model.Employee;
import br.ufsc.ine5605.model.EmployeeDAO;
import br.ufsc.ine5605.model.Employment;
import br.ufsc.ine5605.model.Contract;
import br.ufsc.ine5605.model.Horary;
import br.ufsc.ine5605.model.EmploymentRestrictAccess;
import br.ufsc.ine5605.model.Privileges;
import br.ufsc.ine5605.view.EmployeeScreen;
import br.ufsc.ine5605.view.EmployeeScreenI;
import br.ufsc.ine5605.model.Screen2;
import br.ufsc.ine5605.model.Screen;
import br.ufsc.ine5605.model.ConversionDates;

/**
 * Classe responsável pela comunicação entre as classes Employee e EmployeeScreen entre si e com outras classes;
 * @author Sadi Júnior Domingos Jacinto
 *
 */
public class EmployeeCtrl implements Screen2, ConversionDates, Screen {
	private static final EmployeeCtrl instance = new EmployeeCtrl();
	private Employee employee;
	private EmployeeScreenI employeeScreen;
	private EmployeeDAO empDAO = new EmployeeDAO();
	private static int code = 17200000;
	
	/**
	 * Construtor padrão da classe;
	 * @param mainCtrl - Recebe uma instância do MainController, o que permite a comunicação com as outras classes, através do MainController;
	 */
	public EmployeeCtrl() {
		employeeScreen = new EmployeeScreenI();
	}
	
	public static EmployeeCtrl getInstance() {
		return instance;
	}

	public void menu() {
		employeeScreen.setVisible(true);
		employeeScreen.updateData();
	}
	
	/**
	 * Cria uma nova instância de Employee e a adicona ao ArrayList<Employee>;
	 * @param name - String contendo o nome do novo Employee;
	 * @param dateBirth - Date contendo o nascimento do Employee no formato dd/MM/yyyy;
	 * @param phone - int contendo o número de telefone do Employee;
	 * @param salary - double contendo o salário do Employee;
	 * @return Employee - Retorna a instância do objeto criado;
	 */
	public ArrayList<Employment> getEmployments() {
		return MainController.getInstance().getEmployments();
	}

	
	public Employee addEmployee(String name, Date dateBirth, int phone, double salary) {
		Employee generic = new Employee(getCode(), name, dateBirth, phone, salary);
		empDAO.put(generic);
		setCode(getCode() + 1);
		return generic;
	}
	
	/**
	 * Cria uma nova instância de Contract;
	 * @param employment - Recebe uma instância da classe Employment;
	 * @param employee - Recebe uma instância da classe Employee;
	 * @throws Exception Ocorre quando há uma tentativa de duplicação de contrato;
	 */
	public void addContract(Employment employment, Employee employee) throws Exception {
		try {
			new Contract(employment, employee);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * Remove um Employee do ArrayList baseado no index do mesmo dentro do ArrayList;
	 * @param index - int do index do objeto a ser removido;
	 */
	public void delEmployee(int index) {
		
		empDAO.remove((Integer)index);
	}
	
	/**
	 * Remove um Employee do ArrayList;
	 * @param e - Instância do objeto a ser removido;
	 */
	public void delEmployee(Employee e) {
		empDAO.remove((Integer)e.getNumRegistration());
		
	}
	
	public ArrayList<Employee> getEmployees() {
		return new ArrayList<Employee>(empDAO.getList());
	}
	
	/**
	 * Busca um Employee no ArrayList baseado no index do mesmo;
	 * @param index - int do index do objeto no ArrayList;
	 * @return Employee - Retorna o objeto encontrado. Retorna null se o index usado no parâmetro de entrada
	 * for incorreto(tentar acessar um indíce inexistente);
	 */
	public Employee getEmployee(int index) {
		if(getEmployees().size() >= index && index > -1) {
			return getEmployees().get(index);
		}
		return null;
	}
	
	public Employee findEmployeeByNumReg(int num) {
		for(Employee e : getEmployees()) {
			if(e.getNumRegistration() == num) {
				return e;
			}
		}
		return getEmployees().get(0);
	}
	
	
	public void listEmployments() {
		MainController.getInstance().listEmployments();
	}
	
	public Employment findEmploymentByIndex(int index) throws NullPointerException {
		try {
			return MainController.getInstance().findEmploymentByIndex(index);
		} catch(NullPointerException e) {
			throw e;
		}
	}
	
	public static int getCode() {
		return code;
	}

	public static void setCode(int code) {
		EmployeeCtrl.code = code;
	}

	public void mainMenu() {
		MainController.getInstance().showMainScreen();
		
	}
	
	/**
	 * Verifica se o número de registro existe
	 * @param numRegistration - int contendo o número de registro que deseja ser verificado;
	 * @return boolean - Retorna true se existe algum Employee com o mesmo número de registro, false se não existe;
	 */
	public boolean validNumRegistration(int numRegistration) {
		for(Employee e : getEmployees()) {
			if(e.getNumRegistration() == numRegistration) {
				return true;
			}
		}
		return false;
	}
	
	public void setName(String name) {
		employee.setName(name);
	}
	
	public void setDateBirth(Date dateBirth) {
		employee.setDateBirth(dateBirth);
	}
	
	public void setPhone(int phone) {
		employee.setPhone(phone);
	}
	
	public void setSalary(double salary) {
		employee.setSalary(salary);
	}
	
	public void setEmployment(Employment employment) {
		employee.setEmployment(employment);
	}
	
	/**
	 * @deprecated - Método criado, mas nunca utilizado. Não possuí utilidade algum, deletar depois;
	 * @param numRegistration
	 * @return
	 */
	public Privileges getPrivilegeByNumRegistration(int numRegistration) {
		for(Employee e : getEmployees()) {
			if(e.getNumRegistration() == numRegistration) {
				return e.getEmployment().getEmployment().getPrivilege();
			}
		}
		return null;
	}
	
	/**
	 * @deprecated - Método criado, mas nunca utilizado. Não possuí utilidade algum, deletar depois;
	 * @param numRegistration
	 * @return
	 */
	public ArrayList<Horary> getHoraryAccess(int numRegistration) {
		for(Employee e: getEmployees()) {
			if(e.getNumRegistration() == numRegistration) {
				EmploymentRestrictAccess er= (EmploymentRestrictAccess) e.getEmployment().getEmployment();
				return er.getHorarys();
			}
		}
		return null;
	}
	
	public int conversionStringToInt(String data) throws NumberFormatException {
		try {
			int num = Integer.parseInt(data);
			return num;
		} catch(NumberFormatException e) {
			throw new NumberFormatException();
		}
	}
	
	public double conversionStringToDouble(String data) throws NumberFormatException {
		try {
			double num = Double.parseDouble(data);	
			return num;
		} catch(NumberFormatException e ) {
			throw new NumberFormatException();
		}
	}

	public Date strToDate(String data) throws ParseException {
		if (data == null) {
            return null;
        }
        Date dataF = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            long time = dateFormat.parse(data).getTime();
            dataF = new Date(time);
        } catch (ParseException e) {
            throw new ParseException(data, 0);
        }
        return dataF;
	}


	public Date strToDateHour(String data) throws ParseException {
		if (data == null) {
            return null;
        }
        Date dataF = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("HH:mm");
            long time = dateFormat.parse(data).getTime();
            dataF = new Date(time);
        } catch (ParseException e) {
        	throw new ParseException(data, 0);
        }
        return dataF;
	}



}