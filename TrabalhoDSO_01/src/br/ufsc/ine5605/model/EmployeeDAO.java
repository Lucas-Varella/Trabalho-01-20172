package br.ufsc.ine5605.model;

import java.io.*;
import java.util.*;

public class EmployeeDAO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<Integer, Employee> cacheEmployees = new HashMap<>();
	private final String filename = "employees.dat";
	
	public EmployeeDAO() {
		load();
	}
	
	
	public Employee get(int numReg) {
		return cacheEmployees.get(numReg);
	}
	public void put(Employee emp) {
		cacheEmployees.put(emp.getNumRegistration(), emp);
		persist();
	}
	
	//returns a collection of employees.  
	public Collection<Employee> getList() {
		return cacheEmployees.values();
	}
	
	private void persist() {
		try {			
			FileOutputStream fot = new FileOutputStream(filename);
			
			ObjectOutputStream oos = new ObjectOutputStream(fot);
			oos.writeObject(cacheEmployees);
			
			oos.flush();
			fot.flush();
			
			oos.close();
			fot.close();
			oos = null;
			fot = null;
			
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		
	}
	private void load() {
		try {		
			FileInputStream fiut = new FileInputStream(filename);
		
			ObjectInputStream ois = new ObjectInputStream(fiut);
			
			this.cacheEmployees = (HashMap<Integer, Employee>) ois.readObject();
			
		} catch (FileNotFoundException e) {
			persist();
		} catch (IOException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
	}
	public void remove(int index) {
		cacheEmployees.remove((Integer) index);
		persist();
	}
	
}
