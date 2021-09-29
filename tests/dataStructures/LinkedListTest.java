package dataStructures;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dataStructures.linkedlist.LinkedList;
import dataStructures.linkedlist.LinkedListException;
import model.Shelf;

public class LinkedListTest {

	public  LinkedList<Shelf> setupScenary1(){
		LinkedList<Shelf> shelves =  new LinkedList<>();
		return shelves;
	}

	@Test
	public void testAdd() {
		LinkedList<Shelf> list =  setupScenary1();

		Shelf shelf = new Shelf("A");

		list.add(shelf);

		assertTrue(list.isInList(shelf));
		assertFalse(list.isEmpty());

	}

	@Test
	public void testAdd2() throws LinkedListException {
		LinkedList<Shelf> list =  setupScenary1();

		Shelf shelf = new Shelf("A");
		Shelf shelf2 = new Shelf("B");
		Shelf shelf3 = new Shelf("C");

		list.add(shelf);
		list.add(shelf2);
		list.add(shelf3);

		assertTrue(list.isInList(shelf));
		assertTrue(list.isInList(shelf2));
		assertTrue(list.isInList(shelf3));
		
		assertFalse(list.isEmpty());
		
		assertEquals(shelf,list.getFirst());
		assertEquals(shelf3,list.getLast());
		assertEquals(3, list.size());

	}


	@Test
	public void testAdd3() throws LinkedListException {
		LinkedList<Shelf> list =  setupScenary1();

		Shelf shelf = new Shelf("A");
		Shelf shelf2 = new Shelf("B");
		Shelf shelf3 = new Shelf("C");
		Shelf shelf4 = new Shelf("D");
		Shelf shelf5 = new Shelf("E");
		Shelf shelf6 = new Shelf("F");

		list.add(shelf);
		list.add(shelf2);
		list.add(shelf3);
		list.add(shelf4);
		list.add(shelf5);
		list.add(shelf6);
		

		assertTrue(list.isInList(shelf));
		assertTrue(list.isInList(shelf2));
		assertTrue(list.isInList(shelf3));
		assertTrue(list.isInList(shelf4));
		assertTrue(list.isInList(shelf5));
		assertTrue(list.isInList(shelf6));
		
		assertFalse(list.isEmpty());
		
		assertEquals(shelf,list.getFirst());
		assertEquals(shelf6,list.getLast());
		assertEquals(6, list.size());
	}


	@Test
	public void testRemove2() throws LinkedListException {
		LinkedList<Shelf> list =  setupScenary1();

		Shelf shelf = new Shelf("A");
		Shelf shelf2 = new Shelf("B");
		Shelf shelf3 = new Shelf("C");

		list.add(shelf);
		list.add(shelf2);
		list.add(shelf3);
		
		list.remove(shelf);

		assertFalse(list.isInList(shelf));
		assertTrue(list.isInList(shelf2));
		assertTrue(list.isInList(shelf3));
		
		assertFalse(list.isEmpty());
		
		assertEquals(shelf2,list.getFirst());
		assertEquals(shelf3,list.getLast());
		assertEquals(2, list.size());

	}

	@Test
	public void testRemove3() throws LinkedListException {
		LinkedList<Shelf> list =  setupScenary1();

		Shelf shelf = new Shelf("A");
		Shelf shelf2 = new Shelf("B");
		Shelf shelf3 = new Shelf("C");
		Shelf shelf4 = new Shelf("D");
		Shelf shelf5 = new Shelf("E");
		Shelf shelf6 = new Shelf("F");

		list.add(shelf);
		list.add(shelf2);
		list.add(shelf3);
		list.add(shelf4);
		list.add(shelf5);
		list.add(shelf6);
		
		list.remove(shelf4);
		list.remove(shelf5);
		list.remove(shelf6);
		
		
		assertTrue(list.isInList(shelf));
		assertTrue(list.isInList(shelf2));
		assertTrue(list.isInList(shelf3));
		assertFalse(list.isInList(shelf4));
		assertFalse(list.isInList(shelf5));
		assertFalse(list.isInList(shelf6));
		
		assertFalse(list.isEmpty());
		
		assertEquals(shelf,list.getFirst());
		assertEquals(shelf3,list.getLast());
		assertEquals(3, list.size());

	}
}
