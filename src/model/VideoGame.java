package model;

public class VideoGame implements Comparable<VideoGame> {

	private String name;
	private int code;
	private int quantity;
	private String shelf;
	private double price;
	
	public VideoGame(int code, String name, int quantity, String shelf, double price) {
		this.code = code;
		this.name = name;
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

	public String getName() {
		return name;
	}//End getName

	public void setName(String name) {
		this.name = name;
	}//End setName

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

	public String getInfo() {
		String info = "";
		info += "Estantería: " + shelf +
				"\nCódigo: " + code +
				"\nNombre: " + name +
				"\nPrecio: $" + price +
				"\nCantidad disponible: " + quantity;
		return info;
	}//End getInfo

	@Override
	public String toString() {
		String info = shelf + code + ": " + name + " $" + price;
		return info;
	}//End toString

	@Override
	public int compareTo(VideoGame o) {
		String info = shelf + code;
		String aux = o.getShelf() + o.getCode();
		return info.compareTo(aux);
	}//End compareTo

}//End VideoGame class
