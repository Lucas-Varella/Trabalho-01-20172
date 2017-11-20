package br.ufsc.ine5605.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.ConcurrentModificationException;
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
			
			ois.close();
			fiut.close();
			ois = null;
			fiut = null;
 
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
 
	public Collection<Access> getList() {
		return cacheAccess.values();
	}

	public void put(Access emp) {
		emp.setKey(cacheAccess.size() + 1);
		cacheAccess.put(emp.getKey(), emp);
		persist();
	}
	public void clearData() {
		try {
			for(Access e : cacheAccess.values()) {
				cacheAccess.remove(e.getKey());
			}
		} catch (ConcurrentModificationException e) {
			clearData();
		}
		persist();
		load();
		
	}
	


}
