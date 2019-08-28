package vn.topica.itlab4.work.thread;

import java.util.ArrayList;
/**
 * Create class Store
 * 
 * @author NoName
 *
 */

public class Store {
	private ArrayList<Lamp> listLamp; // list to store lamp
	
	public Store() { // contructors
		this.listLamp = new ArrayList<Lamp>();
	}
	
	public void addLamp(Lamp lamp) { // add a lamp to list
		if(lamp == null) // check lamp null
			throw new NullPointerException(); // throw Exception 
		this.listLamp.add(lamp); // add to list
	}
	
	public Lamp getLampAtIndex(int index) { // get lamp by index
		return this.listLamp.get(index); // return lamp
	}

	public Lamp removeLampAtIndex(int index) { // remove lamp at index
		return this.listLamp.remove(index); // remove from list
	}
	
	public int size() { // get size of listLamp
		return this.listLamp.size(); // return size
	}
	
}
