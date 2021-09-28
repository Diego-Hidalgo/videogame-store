package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import dataStructures.linkedlist.LinkedList;
import dataStructures.queue.QueueException;

public class StoreTest {
	private Store store;

	public void setupScenary1() throws QueueException {
		store = new Store();

		store.registerClient("1234");

		store.registerShelf("A");

		store.registerVideoGame("A", 612, "GTA", 3, 17000);
	}

	public void setupScenary2() throws QueueException {
		store = new Store();

		store.registerClient("13579");
		store.registerClient("24680");
		store.registerClient("54321");

		store.registerShelf("A");
		store.registerShelf("B");
		store.registerShelf("C");
		store.registerShelf("D");

		store.registerVideoGame("A", 612, "GTA", 3, 17000);
		store.registerVideoGame("B", 331, "TS", 5, 15000);
		store.registerVideoGame("C", 767, "DB", 2, 10000);
		store.registerVideoGame("D", 287, "Cars", 6, 13000);
	}

	public void setupScenary3() throws QueueException {
		store = new Store();

		store.registerClient("13579");
		store.registerClient("24680");

		store.registerShelf("A");
		store.registerShelf("B");

		store.registerVideoGame("A", 612, "GTA", 3, 17000);
		store.registerVideoGame("B", 331, "TS", 5, 15000);

	}

	@Test
	public void testRegisterVideoGame() {
		try {
			setupScenary1();
			store.registerShelf("B");

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
			store.registerShelf("E");

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

			store.registerShelf("C");

			store.registerVideoGame("C", 308, "FIFA", 4, 15000);
			int amountGames = store.getRegisteredGamesAmount();

			assertEquals(3, amountGames);

		} catch (QueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testAddVideoGameClient() {
		try {
			setupScenary1();

			Client testClient = new Client("1234");
			store.registerClient("1234");

			store.registerShelf("A");

			VideoGame testGame = new VideoGame(612, "GTA", 3, "A", 17000);
			store.registerVideoGame("A", 612, "GTA", 3, 17000);

			testClient.addVideoGameToList(testGame);

			LinkedList<VideoGame> gamesList = testClient.getGames();
			assertEquals(1, gamesList.size());

		} catch (QueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testAddVideoGameClient2() {
		try {
			setupScenary2();

			Client testClient = new Client("13579");
			store.registerClient("13579");

			store.registerShelf("A");
			store.registerShelf("B");
			store.registerShelf("C");
			store.registerShelf("D");

			VideoGame testGame = new VideoGame(612, "GTA", 3, "A", 17000);
			store.registerVideoGame("A", 612, "GTA", 3, 17000);
			VideoGame testGame2 = new VideoGame(331, "TS", 5, "B", 15000);
			store.registerVideoGame("B", 331, "TS", 5, 15000);
			VideoGame testGame3 = new VideoGame(767, "DB", 2, "C", 10000);
			store.registerVideoGame("C", 767, "DB", 2, 10000);
			VideoGame testGame4 = new VideoGame(287, "Cars", 6, "D", 13000);
			store.registerVideoGame("D", 287, "Cars", 6, 13000);

			testClient.addVideoGameToList(testGame);
			testClient.addVideoGameToList(testGame2);
			testClient.addVideoGameToList(testGame3);
			testClient.addVideoGameToList(testGame4);

			LinkedList<VideoGame> gamesList = testClient.getGames();
			assertEquals(4, gamesList.size());

		} catch (QueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testAddVideoGameClient3() {
		try {
			setupScenary3();

			Client testClient = new Client("24680");
			store.registerClient("24680");

			store.registerShelf("A");
			store.registerShelf("B");

			VideoGame testGame = new VideoGame(612, "GTA", 3, "A", 17000);
			store.registerVideoGame("A", 612, "GTA", 3, 17000);
			VideoGame testGame2 = new VideoGame(331, "TS", 5, "B", 15000);
			store.registerVideoGame("B", 331, "TS", 5, 15000);

			testClient.addVideoGameToList(testGame);
			testClient.addVideoGameToList(testGame2);

			LinkedList<VideoGame> gamesList = testClient.getGames();
			assertEquals(2, gamesList.size());

		} catch (QueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void testSearchClient() {
		try {
			setupScenary1();

			Client testClient = new Client("56789");
			store.registerClient("56789");

			Client found = store.searchClient("56789");
			assertEquals(testClient.getId(), found.getId());

		} catch (QueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void testSearchClient2() {
		try {
			setupScenary2();

			Client testClient = new Client("45678");
			store.registerClient("45678");

			Client found = store.searchClient("45678");
			assertEquals(testClient.getId(), found.getId());

		} catch (QueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void testSearchClient3() {
		try {
			setupScenary3();

			Client testClient = new Client("10345");
			store.registerClient("10345");

			Client found = store.searchClient("10345");
			assertEquals(testClient.getId(), found.getId());

		} catch (QueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void testGetVideoGameInfo() {
		try {
			setupScenary1();

			store.registerVideoGame("A", 612, "GTA", 3, 17000);
			
			String info = "Estantería: " + "A" +
					"\nCódigo: " + 612 +
					"\nNombre: " + "GTA" +
					"\nPrecio: $" + 17000.0 +
					"\nCantidad disponible: " + 3;
			
			String data = store.getVideoGameInfo(612);
			assertEquals(info, data);

		} catch (QueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testGetVideoGameInfo2() {
		try {
			setupScenary2();

			store.registerVideoGame("C", 767, "DB", 2, 10000);
			
			String info = "Estantería: " + "C" +
					"\nCódigo: " + 767 +
					"\nNombre: " + "DB" +
					"\nPrecio: $" + 10000.0 +
					"\nCantidad disponible: " + 2;
			
			String data = store.getVideoGameInfo(767);
			assertEquals(info, data);

		} catch (QueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void testGetVideoGameInfo3() {
		try {
			setupScenary3();

			store.registerVideoGame("B", 331, "TS", 5, 15000);
			
			String info = "Estantería: " + "B" +
					"\nCódigo: " + 331 +
					"\nNombre: " + "TS" +
					"\nPrecio: $" + 15000.0 +
					"\nCantidad disponible: " + 5;
			
			String data = store.getVideoGameInfo(331);
			assertEquals(info, data);

		} catch (QueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}