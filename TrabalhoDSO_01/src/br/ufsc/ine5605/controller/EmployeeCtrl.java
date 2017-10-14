package br.ufsc.ine5605.controller;

import java.sql.Date; 
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.ufsc.ine5605.model.Employee;
import br.ufsc.ine5605.model.Employment;
import br.ufsc.ine5605.model.Contract;
import br.ufsc.ine5605.model.Horary;
import br.ufsc.ine5605.model.EmploymentRestrictAccess;
import br.ufsc.ine5605.model.Privileges;
import br.ufsc.ine5605.view.EmployeeScreen;


/**
 * 
 * @author Sadi Júnior Domingos Jacinto
 *
 */
public class EmployeeCtrl {
	private MainController mainCtrl;
	private Employee employee;
	private EmployeeScreen employeeScreen;
	private ArrayList<Employee> employees;
	private static int code = 17200000;
	
	public EmployeeCtrl(MainController mainCtrl) {
		this.mainCtrl = mainCtrl;
		employeeScreen = new EmployeeScreen(this);
		employees = new ArrayList<Employee>();
	}
	
	public void menu() {
		employeeScreen.menu();
	}
	
	public Employee addEmployee(String name, Date dateBirth, int phone, double salary) {
		Employee generic = new Employee(this, getCode(), name, dateBirth, phone, salary);
		employees.add(generic);
		setCode(getCode() + 1);
		return generic;
	}
	
	
	public Contract addContract(Employment employment, Employee employee) throws Exception {
		try {
			return new Contract(employment, employee);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void delEmployee(int index) {
		employees.remove(index);
	}
	
	public void delEmployee(Employee e) {
		employees.remove(e);
		
	}
	
	public ArrayList<Employee> listEmployees() {
		return employees;
	}
	
	public Employee getEmployee(int index) {
		if(employees.size() >= index && index > -1) {
			return employees.get(index);
		}
		return null;
	}
	
	
	public void listEmployments() {
		mainCtrl.listEmployments();
	}
	
	public Employment findEmploymentByIndex(int index) throws NullPointerException {
		try {
			return mainCtrl.findEmploymentByIndex(index);
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
		mainCtrl.showMainScreen();
		
	}
	
	/**
	 * Verifica se o n�mero de registro existe
	 * @param numRegistration
	 * @return
	 */
	public boolean validNumRegistration(int numRegistration) {
		for(Employee e : employees) {
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

	public Privileges getPrivilegeByNumRegistration(int numRegistration) {
		for(Employee e : employees) {
			if(e.getNumRegistration() == numRegistration) {
				return e.getEmployment().getEmployment().getPrivilege();
			}
		}
		return null;
	}

	public ArrayList<Horary> getHoraryAccess(int numRegistration) {
		for(Employee e: employees) {
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
