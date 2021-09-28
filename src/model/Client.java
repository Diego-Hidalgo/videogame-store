package model;

import dataStructures.linkedlist.LinkedList;
import dataStructures.stack.Stack;

public class Client implements Comparable<Client> {

	private String id;
	private int time;
	private double total;
	private LinkedList<VideoGame> games;
	private Stack<VideoGame> cart;
	private Stack<VideoGame> bag;

	public Client(String id) {
		this.id = id;
		time = 0;
		total = 0.0;
		games = new LinkedList<VideoGame>();
		cart = new Stack<VideoGame>();
		bag = new Stack<VideoGame>();
	}//End Client

	public String getId() {
		return id;
	}//End getId

	public void setId(String id) {
		this.id = id;
	}//End setId

	public int getTime() {
		return time;
	}//End getTime

	public void setTime(int time) {
		this.time = time;
	}//End setTime

	public double getTotal() {
		return total;
	}//End getTotal

	public void setTotal(double total) {
		this.total = total;
	}//End setTotal

	public LinkedList<VideoGame> getGames() {
		return games;
	}//End getGames;

	public void setGames(LinkedList<VideoGame> games) {
		this.games = games;
	}//End setGames

	public Stack<VideoGame> getCart() {
		return cart;
	}//End getCart

	public void setCart(Stack<VideoGame> cart) {
		this.cart = cart;
	}//End setCart

	public Stack<VideoGame> getBag() {
		return bag;
	}//End getBag

	public void setBag(Stack<VideoGame> bag) {
		this.bag = bag;
	}//End setBag

	public void addVideoGameToList(VideoGame toAdd) {
		games.add(toAdd);
	}//End addVideoGameToList

	public boolean searchGameInList(VideoGame game) {
		return games.isInList(game);
	}//End searchGameInList

	@Override
	public String toString() {
		String info = "";
		info = "\nId: " + id +
				"\nTiempo: " + time + " minutos" +
				"\n--- JUEGOS ---" + bag.toString() + "\n--------------" +
				"\nTotal: $" + total;
		return info;
	}//End toString

	@Override
	public int compareTo(Client o) {
		return Integer.compare(time, o.getTime());
	}//End compareTo
	
}//End Client class
