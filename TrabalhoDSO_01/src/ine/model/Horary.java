package ine.model;

import java.util.ArrayList;

import ine.controller.*;

public class Horary {
//	private HoraryCtrl horaryCtrl;
	private ArrayList<String> hourBegin;
	private ArrayList<String> hourFinish;
	/*
	 * Mudar os tipos do ArrayList para Date;
	 * Formatar as datas(como não sei fazer isso, vou deixar para alguém que saiba);
	 */
	public Horary(ArrayList<String> hourBegin, ArrayList<String> hourFinish) {
		addHourBegin(hourBegin);
		addHourFinish(hourFinish);
	}
	
	public void addHourBegin(ArrayList<String> hourBegin) {
		for(String hour : hourBegin) {
			this.hourBegin.add(hour);
		}
	}
	
	public void addHourFinish(ArrayList<String> hourFinish) {
		for(String hour : hourFinish) {
			this.hourFinish.add(hour);
		}
	}
	
	public void listHorary() {
		int i = 1;
		for(String hour : hourBegin) {
			System.out.println(i + "ª Hour(s) Begin: " + hour);
			i++;
		}
		i = 1;
		for(String hour : hourFinish) {
			System.out.println(i + "ª Hour(s) Finish: " + hour);
			i++;
		}
	}
	
	public void listHorary(int option) {
		int i = 1;
		if(option ==1) {
			for(String hour : hourBegin) {
				System.out.println(i+ "ª Hour(s) Begin: " + hour);
				i++;
			}
		}else {
			for(String hour : hourFinish) {
				System.out.println(i + "ª Hour(s) Finish: " + hour);
				i++;
			}
		}
	}
	
	public void editHorary(int array, int index, String newHorary) {
		if(array == 1) {
			hourBegin.set(index, newHorary);
		}else {
			hourFinish.set(index, newHorary);
		}
	}
	
	public void delHorary(int array, int index) {
		if(array == 1) {
			hourBegin.set(index, null);
		}else {
			hourFinish.set(index, null);
		}
	}
	
}
