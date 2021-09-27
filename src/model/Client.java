package model;

import dataStructures.linkedlist.LinkedList;
import dataStructures.stack.Stack;

public class Client {

	private String id;
	private int time;
	private double total;
	private LinkedList<VideoGame> games;
	private Stack<VideoGame> shoppingCart;

	public Client(String id) {
		this.id = id;
		time = 0;
		total = 0.0;
		games = new LinkedList<VideoGame>();
		shoppingCart = new Stack<VideoGame>();
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

	public Stack<VideoGame> getShoppingCart() {
		return shoppingCart;
	}//End getShoppingCart

	public void setShoppingCart(Stack<VideoGame> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}//End setShoppingCart

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
				"\nTiempo: " + time + " s" +
				"\n--- JUEGOS ---" + games.toString() +
				"\nTotal: $" + total;
		return info;
	}//End toString

}//End Client class
