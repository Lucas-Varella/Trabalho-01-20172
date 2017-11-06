package br.ufsc.ine5605.model;

import java.io.*;
import java.util.*;

public class EmployeeMapping {
	
	private HashMap<Integer, Employee> cacheEmployees = new HashMap<>();
	private final String filename = "employees.dat";
	
	public Employee get(int numReg) {
		return cacheEmployees.get(numReg);
	}
	public void put(Employee emp) {
		cacheEmployees.put(emp.getNumRegistration(), emp);
	}
	
	public void persist() {
		try {			
			FileOutputStream fout = new FileOutputStream(filename);
			
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(cacheEmployees);
			
			oos.flush();
			fout.flush();
			
			oos.close();
			fout.close();
			oos = null;
			fout = null;
			
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		
	}
	
}
