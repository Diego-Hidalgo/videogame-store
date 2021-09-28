package dataStructures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dataStructures.queue.Queue;
import dataStructures.queue.QueueException;
import model.Client;

public class QueueTest {
	public Queue<Client> setupScenary1(){
		Queue<Client> queue = new Queue<Client>();
		return queue;
	}
	
	@Test
	public void testEnqueue() {
		Queue<Client> queue = setupScenary1();
		
		Client client = new Client("12345");
		
		queue.enqueue(client);
		
		assertTrue(queue.isInQueue(client));
		assertFalse(queue.isEmpty());
	}
	
	@Test
	public void testEnqueue2() throws QueueException {
		Queue<Client> queue = setupScenary1();
		
		Client client = new Client("13579");
		Client client2 = new Client("24680");
		Client client3 = new Client("54321");
		
		queue.enqueue(client);
		queue.enqueue(client2);
		queue.enqueue(client3);
		
		assertTrue(queue.isInQueue(client));
		assertTrue(queue.isInQueue(client2));
		assertTrue(queue.isInQueue(client3));
		
		assertFalse(queue.isEmpty());
		
		assertEquals(client, queue.front());
		assertEquals(client3, queue.rear());
		
		assertEquals(3, queue.size());
	}
	
	@Test
	public void testEnqueue3() throws QueueException {
		Queue<Client> queue = setupScenary1();
		
		Client client = new Client("13579");
		Client client2 = new Client("24680");
		Client client3 = new Client("54321");
		Client client4 = new Client("56791");
		Client client5 = new Client("87634");
		Client client6 = new Client("23678");
		
		queue.enqueue(client);
		queue.enqueue(client2);
		queue.enqueue(client3);
		queue.enqueue(client4);
		queue.enqueue(client5);
		queue.enqueue(client6);
		
		assertTrue(queue.isInQueue(client));
		assertTrue(queue.isInQueue(client2));
		assertTrue(queue.isInQueue(client3));
		assertTrue(queue.isInQueue(client4));
		assertTrue(queue.isInQueue(client5));
		assertTrue(queue.isInQueue(client6));
		
		
		assertFalse(queue.isEmpty());
		
		assertEquals(client, queue.front());
		assertEquals(client6, queue.rear());
		
		assertEquals(6, queue.size());
	}
	
	
	@Test
	public void testDeuqueue() throws QueueException {
		Queue<Client> queue = setupScenary1();
		
		Client client = new Client("12345");
		
		queue.enqueue(client);
		assertEquals(client, queue.dequeue());
		
		assertFalse(queue.isInQueue(client));
		assertTrue(queue.isEmpty());
	}
	
	@Test
	public void testDeuqueue2() throws QueueException {
		Queue<Client> queue = setupScenary1();
		
		Client client = new Client("13579");
		Client client2 = new Client("24680");
		Client client3 = new Client("54321");
		
		queue.enqueue(client);
		queue.enqueue(client2);
		queue.enqueue(client3);
		
		assertEquals(client, queue.dequeue());
		
		assertFalse(queue.isInQueue(client));
		assertTrue(queue.isInQueue(client2));
		assertTrue(queue.isInQueue(client3));
		
		assertFalse(queue.isEmpty());
		
		assertEquals(client2, queue.front());
		assertEquals(client3, queue.rear());
		
		assertEquals(2, queue.size());
	}
	
	@Test
	public void testDeuqueue3() throws QueueException {
		Queue<Client> queue = setupScenary1();
		
		Client client = new Client("13579");
		Client client2 = new Client("24680");
		Client client3 = new Client("54321");
		Client client4 = new Client("56791");
		Client client5 = new Client("87634");
		Client client6 = new Client("23678");

		
		queue.enqueue(client);
		queue.enqueue(client2);
		queue.enqueue(client3);
		queue.enqueue(client4);
		queue.enqueue(client5);
		queue.enqueue(client6);
		
		assertEquals(client, queue.dequeue());
		assertEquals(client2, queue.dequeue());
		assertEquals(client3, queue.dequeue());
		
		assertFalse(queue.isInQueue(client));
		assertFalse(queue.isInQueue(client2));
		assertFalse(queue.isInQueue(client3));
		assertTrue(queue.isInQueue(client4));
		assertTrue(queue.isInQueue(client5));
		assertTrue(queue.isInQueue(client6));
		
		
		assertFalse(queue.isEmpty());
		
		assertEquals(client4, queue.front());
		assertEquals(client6, queue.rear());
		
		assertEquals(3, queue.size());
	}
	
}
