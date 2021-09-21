package model;

import dataStructures.linkedlist.LinkedList;

public class Client {

	private String id;
	private int time;
	private double total;
	private LinkedList<VideoGame> games;

	public Client(String id) {
		this.id = id;
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

}//End Client class
