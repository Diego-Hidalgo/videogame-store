package model;

public class Cashier {

	private Client current;
	private boolean inUse;

	public Cashier() {
		inUse = false;
		current = null;
	}//End Constructor

	public Client getCurrent() {
		return current;
	}//End getCurrent

	public void setCurrent(Client current) {
		this.current = current;
	}//End setCurrent

	public void removeCurrent() {
		current = null;
		inUse = false;
	}//End removeCurrent

	public boolean getInUse() {
		return inUse;
	}//End getInUse

	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}//End setInUse
	
}//End Cashier class
