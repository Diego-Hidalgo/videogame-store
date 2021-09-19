package model;

public class Videogame {

	private int code;
	private int quantity;
	private String shelf;
	private double price;
	
	public Videogame(int code, int quantity, String shelf, double price) {
		this.code = code;
		this.quantity = quantity;
		this.shelf = shelf;
		this.price = price;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getShelf() {
		return shelf;
	}

	public void setShelf(String shelf) {
		this.shelf = shelf;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
