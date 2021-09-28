package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import dataStructures.queue.QueueException;

public class StoreTest {
	private Store store;

	public void setScenary1() throws QueueException {
		store = new Store();
		
		Client testClient = new Client("1234");
		store.registerClient("1234");
		
		Shelf testShelf = new Shelf("A4");
		store.registerShelf("A4");
		
		VideoGame testGame = new VideoGame(612, 3, "A4", 17000);
		store.registerVideoGame("A4", 612, 3, 17000);
	}

	public void setScenary2() throws QueueException {
		Client testClient = new Client("13579");
		store.registerClient("13579");
		Client testClient2 = new Client("24680");
		store.registerClient("24680");
		Client testClient3 = new Client("54321");
		store.registerClient("54321");
		
		
		Shelf testShelf = new Shelf("A4");
		store.registerShelf("A4");
		Shelf testShelf2 = new Shelf("B2");
		store.registerShelf("B2");
		Shelf testShelf3 = new Shelf("C1");
		store.registerShelf("C1");
		Shelf testShelf4 = new Shelf("D3");
		store.registerShelf("D3");
		
		VideoGame testGame = new VideoGame(612, 3, "A4", 17000);
		store.registerVideoGame("A4", 612, 3, 17000);
		VideoGame testGame2 = new VideoGame(331, 5, "B2", 15000);
		store.registerVideoGame("B2", 331, 5, 15000);
		VideoGame testGame3 = new VideoGame(767, 2, "C1", 10000);
		store.registerVideoGame("C1", 767, 2, 10000);
		VideoGame testGame4 = new VideoGame(287, 6, "D3", 13000);
		store.registerVideoGame("D3", 287, 6, 13000);
	}
	
	@Test
	public void test() {}
}
