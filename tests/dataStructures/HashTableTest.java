package dataStructures;

import dataStructures.hashtable.HashTable;
import dataStructures.hashtable.HashTableException;
import model.VideoGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class HashTableTest {


	public HashTable<Integer, VideoGame> setupScenary1(){
		HashTable<Integer, VideoGame> htable = new HashTable<>(1);
		return htable;
	}

	public HashTable<Integer, VideoGame> setupScenary2(){
		HashTable<Integer, VideoGame> htable = new HashTable<>(7);
		return htable;
	}
	public HashTable<Integer, VideoGame> setupScenary3(){
		HashTable<Integer, VideoGame> htable = new HashTable<>(4);
		return htable;
	}
	
	@Test
	public void testInsert() throws HashTableException {
		HashTable<Integer, VideoGame> htable = setupScenary1();
		
		VideoGame testGame = new VideoGame(612, "GTA", 3, "A", 17000);
		
		htable.insert(testGame.getCode(), testGame);
		
		assertEquals(testGame, htable.search(testGame.getCode()));
		
		assertEquals(1,htable.size());
	}
	
	@Test
	public void testInsert2() throws HashTableException {
		HashTable<Integer, VideoGame> htable = setupScenary2();
		
		VideoGame testGame = new VideoGame(331, "TS", 5, "A", 15000);
		VideoGame testGame2 = new VideoGame(121, "ZL3", 5, "B", 12000);
		VideoGame testGame3 = new VideoGame(211, "SD2", 5, "C", 15000);
		VideoGame testGame4 = new VideoGame(678, "MN", 5, "D", 15000);
		VideoGame testGame5 = new VideoGame(123, "AGK", 5, "E", 15000);
		VideoGame testGame6 = new VideoGame(451, "TWD", 5, "F", 15000);
		VideoGame testGame7 = new VideoGame(301, "GR", 5, "G", 15000);
		
		htable.insert(testGame.getCode(), testGame);
		htable.insert(testGame2.getCode(), testGame2);
		htable.insert(testGame3.getCode(), testGame3);
		htable.insert(testGame4.getCode(), testGame4);
		htable.insert(testGame5.getCode(), testGame5);
		htable.insert(testGame6.getCode(), testGame6);
		htable.insert(testGame7.getCode(), testGame7);
		
		assertEquals(testGame, htable.search(testGame.getCode()));
		assertEquals(testGame2, htable.search(testGame2.getCode()));
		assertEquals(testGame3, htable.search(testGame3.getCode()));
		assertEquals(testGame4, htable.search(testGame4.getCode()));
		assertEquals(testGame5, htable.search(testGame5.getCode()));
		assertEquals(testGame6, htable.search(testGame6.getCode()));
		assertEquals(testGame7, htable.search(testGame7.getCode()));
		
		assertEquals(7,htable.size());
	}
	
	@Test
	public void testInsert3() throws HashTableException {
		HashTable<Integer, VideoGame> htable = setupScenary3();
		
		VideoGame testGame = new VideoGame(331, "TS", 5, "A", 15000);
		VideoGame testGame2 = new VideoGame(121, "ZL3", 5, "B", 12000);
		VideoGame testGame3 = new VideoGame(211, "SD2", 5, "C", 15000);
		VideoGame testGame4 = new VideoGame(301, "GR", 5, "G", 15000);

		
		htable.insert(testGame.getCode(), testGame);
		htable.insert(testGame2.getCode(), testGame2);
		htable.insert(testGame3.getCode(), testGame3);
		htable.insert(testGame4.getCode(), testGame4);
		
		assertEquals(testGame, htable.search(testGame.getCode()));
		assertEquals(testGame2, htable.search(testGame2.getCode()));
		assertEquals(testGame3, htable.search(testGame3.getCode()));
		assertEquals(testGame4, htable.search(testGame4.getCode()));
		
		assertEquals(4,htable.size());
	}
	
}
