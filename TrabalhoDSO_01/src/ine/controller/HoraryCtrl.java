package ine.controller;

import ine.model.Horary;
import ine.view.HoraryScreen;

import java.util.ArrayList;

public class HoraryCtrl {
	private MainController mainCtrl;
	private HoraryScreen horaryScreen;
	private Horary horary;
	
	public HoraryCtrl(MainController mainController) {
		this.mainCtrl = mainCtrl;
		this.horaryScreen = new HoraryScreen(this);
	}
	
	public void menuAdd() {
		horaryScreen.menuAdd();
	}
	public void menuEdit() {
		horaryScreen.editHorary();
	}
	public void mainMenu() {
		mainCtrl.showMainScreen();
	}
	
	public Horary addHorary(ArrayList<String> hourBegin, ArrayList<String> hourFinish) {
		Horary horary = new Horary(hourBegin, hourFinish);
		return horary;
	}
	
	public void listHorary() {
		horary.listHorary();
	}
	
	public void listHorary(int option) {
		horary.listHorary(option);
	}
	
	public void editHorary(int array, int index, String newHorary) {
		horary.editHorary(array, index, newHorary);
	}
	
	public void delPartialHorary(int array, int index) {
		horary.delHorary(array, index);		
	}
	
	public void delTotalHorary(Horary horary) {
		horary = null;
	}
	
	public void setHorary(Horary horary) {
		mainCtrl.setHorary(horary);
	}

}
