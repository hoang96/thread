package vn.topica.itlab4.work.thread.exercises2;
/**
 * Create class Trash extends Store
 * @author NoName
 *
 */

public class Trash extends Store{

	public void addLamp(Lamp lamp) { // add a lamp to list
		if(lamp == null) // lamp can't be null
			throw new NullPointerException(); // throw exception
		if(lamp.getStatus() == Lamp.STATUS.on) // only off lamp
			throw new IllegalArgumentException("Only allow off lamp or repair lamp to trash"); // throw exception
		
		super.addLamp(lamp); // call super to add lamp
	}
}
