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

public class AccessDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private HashMap<Integer, Access> cacheAccess = new HashMap<Integer, Access>();
	private final String fileName = "access.dat";
 
	public AccessDAO() {
		load();
	}

	private void load() {
   
		try { 
			FileInputStream fiut = new FileInputStream(this.fileName);
   
			ObjectInputStream ois = new ObjectInputStream(fiut);
   
			this.cacheAccess = (HashMap<Integer, Access>) ois.readObject();
 
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
			oos.writeObject(cacheAccess);
   
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
		cacheAccess.remove((Integer) index);
		persist();
	}
 
	public Collection<Access> getList() {
		return cacheAccess.values();
	}

	public Access get(int code) {
		return cacheAccess.get(code);
	}
	public void put(Access emp) {
		cacheAccess.put(emp.getNumRegistration(), emp);
		persist();
	}
	


}
