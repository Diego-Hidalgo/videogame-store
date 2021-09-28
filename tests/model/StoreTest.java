package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import dataStructures.queue.QueueException;

public class StoreTest {
	private Store store;

	public void setupScenary1() throws QueueException {
		store = new Store();

		Client testClient = new Client("1234");
		store.registerClient("1234");

		Shelf testShelf = new Shelf("A");
		store.registerShelf("A");

		VideoGame testGame = new VideoGame(612, "GTA", 3, "A", 17000);
		store.registerVideoGame("A", 612, null, 3, 17000);
	}

	public void setupScenary2() throws QueueException {
		store = new Store();
		
		Client testClient = new Client("13579");
		store.registerClient("13579");
		Client testClient2 = new Client("24680");
		store.registerClient("24680");
		Client testClient3 = new Client("54321");
		store.registerClient("54321");


		Shelf testShelf = new Shelf("A");
		store.registerShelf("A");
		Shelf testShelf2 = new Shelf("B");
		store.registerShelf("B");
		Shelf testShelf3 = new Shelf("C");
		store.registerShelf("C");
		Shelf testShelf4 = new Shelf("D");
		store.registerShelf("D");


		VideoGame testGame = new VideoGame(612, "GTA", 3, "A", 17000);
		store.registerVideoGame("A", 612, "GTA", 3, 17000);
		VideoGame testGame2 = new VideoGame(331, "TS", 5, "B", 15000);
		store.registerVideoGame("B", 331, "TS", 5, 15000);
		VideoGame testGame3 = new VideoGame(767, "DB", 2, "C", 10000);
		store.registerVideoGame("C", 767, "DB", 2, 10000);
		VideoGame testGame4 = new VideoGame(287, "Cars", 6, "D", 13000);
		store.registerVideoGame("D", 287, "Cars", 6, 13000);
	}

	public void setupScenary3() throws QueueException {
		store = new Store();
		
		Client testClient = new Client("13579");
		store.registerClient("13579");
		Client testClient2 = new Client("24680");
		store.registerClient("24680");

		Shelf testShelf = new Shelf("A");
		store.registerShelf("A");
		Shelf testShelf2 = new Shelf("B");
		store.registerShelf("B");

		VideoGame testGame = new VideoGame(612, "GTA", 3, "A", 17000);
		store.registerVideoGame("A", 612, "GTA", 3, 17000);
		VideoGame testGame2 = new VideoGame(331, "TS", 5, "B", 15000);
		store.registerVideoGame("B", 331, "TS", 5, 15000);

	}

	@Test
	public void testRegisterVideoGame() {
		try {
			setupScenary1();
			Shelf testShelf = new Shelf("B");
			store.registerShelf("B");

			VideoGame game = new VideoGame(308, "FIFA", 4, "B", 15000);
			store.registerVideoGame("B", 308, "FIFA", 4, 15000);
			int amountGames = store.getRegisteredGamesAmount();

			assertEquals(2, amountGames);

		} catch (QueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void testRegisterVideoGame2() {
		try {
			setupScenary2();
			Shelf testShelf = new Shelf("E");
			store.registerShelf("E");

			VideoGame game = new VideoGame(308, "FIFA", 4, "E", 15000);
			store.registerVideoGame("E", 308, "FIFA", 4, 15000);
			int amountGames = store.getRegisteredGamesAmount();

			assertEquals(5, amountGames);

		} catch (QueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	@Test
	public void testRegisterVideoGame3() {
		try {
			setupScenary3();
			Shelf testShelf = new Shelf("C");
			store.registerShelf("C");

			VideoGame game = new VideoGame(308, "FIFA", 4, "C", 15000);
			store.registerVideoGame("C", 308, "FIFA", 4, 15000);
			int amountGames = store.getRegisteredGamesAmount();

			assertEquals(3, amountGames);

		} catch (QueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
