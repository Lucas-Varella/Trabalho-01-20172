package br.ufsc.ine5605.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

public class EmploymentDAO implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<Integer, Employment> cacheEmployment = new HashMap<Integer, Employment>();
	private final String fileName = "employments.dat";
 
	public EmploymentDAO() {
		load();
	}
//nvm
	private void load() {
   
		try { 
			FileInputStream fiut = new FileInputStream(this.fileName);
   
			ObjectInputStream ois = new ObjectInputStream(fiut);
   
			this.cacheEmployment = (HashMap<Integer, Employment>) ois.readObject();
 
		} catch (FileNotFoundException e) {
			persist();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch(NullPointerException e) {
			persist();
		}
	} 
 
	private void persist() {
		try {   
			FileOutputStream fot = new FileOutputStream(fileName);
   
			ObjectOutputStream oos = new ObjectOutputStream(fot);
			oos.writeObject(cacheEmployment);
   
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
 
	public void remove(int index) {
		cacheEmployment.remove((Integer) index);
		persist();
	}
 
	public Collection<Employment> getList() {
		return cacheEmployment.values();
	}

	public Employment get(int code) {
		return cacheEmployment.get(code);
	}
	public void put(Employment emp) {
		cacheEmployment.put(emp.getCode(), emp);
		persist();
	}
	


}
