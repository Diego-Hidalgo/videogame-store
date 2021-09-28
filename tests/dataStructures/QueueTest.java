package dataStructures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dataStructures.hashtable.HashTable;
import dataStructures.hashtable.HashTableException;
import dataStructures.queue.Queue;
import model.Client;
import model.VideoGame;

public class QueueTest {
	public Queue<Client> setupScenary1(){
		Queue<Client> queue = new Queue<>();
		return queue;
	}
	
	@Test
	public void testInsert() throws HashTableException {
		Queue<Client> queue = setupScenary1();
		
		Client client = new Client(null);
		
		//assertEquals();
	}
	
}
