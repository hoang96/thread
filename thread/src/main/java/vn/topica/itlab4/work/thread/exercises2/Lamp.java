package vn.topica.itlab4.work.thread.exercises2;

/**
 * Create class Lamp
 * @author NoName
 *
 */
public class Lamp {

	public enum STATUS { //enum STATUS 
		on, off, repair
	};

	private STATUS status; // status
	private int index;
	private static int IdTemp = 1;

	public Lamp() {	//constructor
		this.index = IdTemp++;
	}

	public Lamp(STATUS status) { // constructor with param status
		this.status = status; // assign status
		this.index = IdTemp++;
	}
//getter and setter for STATUS  and index
	public STATUS getStatus() {   
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
