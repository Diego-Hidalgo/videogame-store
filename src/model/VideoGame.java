package model;

public class VideoGame {

	private int code;
	private int quantity;
	private String shelf;
	private double price;
	
	public VideoGame(int code, int quantity, String shelf, double price) {
		this.code = code;
		this.quantity = quantity;
		this.shelf = shelf;
		this.price = price;
	}//End Constructor

	public int getCode() {
		return code;
	}//End getCode

	public void setCode(int code) {
		this.code = code;
	}//End setCode

	public int getQuantity() {
		return quantity;
	}//End getQuantity

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}//End setQuantity

	public String getShelf() {
		return shelf;
	}//End getShelf

	public void setShelf(String shelf) {
		this.shelf = shelf;
	}//End setShelf

	public double getPrice() {
		return price;
	}//End getPrice

	public void setPrice(double price) {
		this.price = price;
	}//End setPrice

	@Override
	public String toString() {
		String info = "";
		info = "\nEstantería: " + shelf +
				"\nCódigo: " + code +
				"\nCantidad: " + quantity +
				"\nPrecio: $" + price;
		return info;
	}//End toString
	
}//End VideoGame class
