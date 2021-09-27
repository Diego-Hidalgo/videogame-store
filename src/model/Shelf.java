package model;

import dataStructures.hashtable.HashTable;
import dataStructures.hashtable.HashTableException;

public class Shelf {
	
	private String identifier;
	private HashTable<Integer, VideoGame> videoGames;

	public Shelf(String identifier) {
		this.identifier = identifier;
		videoGames = new HashTable<>();
	}//End Constructor

	public String getIdentifier() {
		return identifier;
	}//End getIdentifier

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}//End setIdentifier

	public HashTable<Integer, VideoGame> getVideoGames() {
		return videoGames;
	}//End getVideoGames

	public void setVideoGames(HashTable<Integer, VideoGame> videoGames) {
		this.videoGames = videoGames;
	}//End setVideoGames

	public void addGame(VideoGame toAdd) throws HashTableException {
		videoGames.insert(toAdd.getCode(), toAdd);
	}//End addGame

	public VideoGame searchVideoGame(int code) {
		return videoGames.search(code);
	}//End searchVideoGame

	public boolean removeVideoGame(int code) {
		return videoGames.remove(code);
	}//End removeVideoGames

	public Object[] getAllGamesCodes() {
		return videoGames.getAllKeys();
	}//End getAllGamesCodes

	@Override
	public String toString() {
		return identifier;
	}//End toString

}//End shelf class
