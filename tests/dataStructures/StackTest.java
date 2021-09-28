package dataStructures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import dataStructures.stack.Stack;
import dataStructures.stack.StackException;
import model.VideoGame;

public class StackTest {

	public Stack<VideoGame> setupScenary1(){
		Stack<VideoGame> stack = new Stack<VideoGame>();
		return stack;
	}
	
	@Test
	public void testPush() {
		Stack<VideoGame> stack = setupScenary1();
		
		VideoGame game = new VideoGame(331, "TS", 5, "A", 15000);
	
		stack.push(game);
		
		assertFalse(stack.isEmpty());
		assertTrue(stack.isInStack(game));
	}
	
	@Test
	public void testPush2() throws StackException {
		Stack<VideoGame> stack = setupScenary1();
		
		VideoGame game = new VideoGame(331, "TS", 5, "A", 15000);
		VideoGame game2 = new VideoGame(121, "ZL3", 5, "B", 12000);
		VideoGame game3 = new VideoGame(211, "SD2", 5, "C", 15000);

		stack.push(game);
		stack.push(game2);
		stack.push(game3);
		
		assertFalse(stack.isEmpty());
		
		assertTrue(stack.isInStack(game));
		assertTrue(stack.isInStack(game2));
		assertTrue(stack.isInStack(game3));
		
		assertEquals(game3,stack.peek());
		assertEquals(game,stack.bottom());
		
		assertEquals(3, stack.size());
	}
	
	@Test
	public void testPush3() throws StackException {
		Stack<VideoGame> stack = setupScenary1();
		
		VideoGame game = new VideoGame(331, "TS", 5, "A", 15000);
		VideoGame game2 = new VideoGame(121, "ZL3", 5, "B", 12000);
		VideoGame game3 = new VideoGame(211, "SD2", 5, "C", 15000);
		VideoGame game4 = new VideoGame(678, "MN", 5, "D", 15000);
		VideoGame game5 = new VideoGame(123, "AGK", 5, "E", 15000);
		VideoGame game6 = new VideoGame(451, "TWD", 5, "F", 15000);
		VideoGame game7 = new VideoGame(301, "GR", 5, "G", 15000);

		stack.push(game);
		stack.push(game2);
		stack.push(game3);
		stack.push(game4);
		stack.push(game5);
		stack.push(game6);
		stack.push(game7);
		
		assertFalse(stack.isEmpty());
		
		assertTrue(stack.isInStack(game));
		assertTrue(stack.isInStack(game2));
		assertTrue(stack.isInStack(game3));
		assertTrue(stack.isInStack(game4));
		assertTrue(stack.isInStack(game5));
		assertTrue(stack.isInStack(game6));
		assertTrue(stack.isInStack(game7));
		
		assertEquals(game7,stack.peek());
		assertEquals(game,stack.bottom());
		
		assertEquals(7, stack.size());
	}
	
	@Test
	public void testPop() throws StackException {
		Stack<VideoGame> stack = setupScenary1();
		
		VideoGame game = new VideoGame(331, "TS", 5, "A", 15000);
	
		stack.push(game);
		assertEquals(game, stack.pop());
		
		assertTrue(stack.isEmpty());
		assertFalse(stack.isInStack(game));
	}
	
	@Test
	public void testPop2() throws StackException {
		Stack<VideoGame> stack = setupScenary1();
		
		VideoGame game = new VideoGame(331, "TS", 5, "A", 15000);
		VideoGame game2 = new VideoGame(121, "ZL3", 5, "B", 12000);
		VideoGame game3 = new VideoGame(211, "SD2", 5, "C", 15000);

		stack.push(game);
		stack.push(game2);
		stack.push(game3);
		
		assertEquals(game3, stack.pop());
		
		assertFalse(stack.isEmpty());
		
		assertTrue(stack.isInStack(game));
		assertTrue(stack.isInStack(game2));
		assertFalse(stack.isInStack(game3));
		
		assertEquals(game2,stack.peek());
		assertEquals(game,stack.bottom());
		
		assertEquals(2, stack.size());
	}
	
	@Test
	public void testPop3() throws StackException {
		Stack<VideoGame> stack = setupScenary1();
		
		VideoGame game = new VideoGame(331, "TS", 5, "A", 15000);
		VideoGame game2 = new VideoGame(121, "ZL3", 5, "B", 12000);
		VideoGame game3 = new VideoGame(211, "SD2", 5, "C", 15000);
		VideoGame game4 = new VideoGame(678, "MN", 5, "D", 15000);
		VideoGame game5 = new VideoGame(123, "AGK", 5, "E", 15000);
		VideoGame game6 = new VideoGame(451, "TWD", 5, "F", 15000);
		VideoGame game7 = new VideoGame(301, "GR", 5, "G", 15000);

		stack.push(game);
		stack.push(game2);
		stack.push(game3);
		stack.push(game4);
		stack.push(game5);
		stack.push(game6);
		stack.push(game7);
		
		
		assertEquals(game7, stack.pop());
		assertEquals(game6, stack.pop());
		assertEquals(game5, stack.pop());
		assertEquals(game4, stack.pop());
		
		assertFalse(stack.isEmpty());
		
		assertTrue(stack.isInStack(game));
		assertTrue(stack.isInStack(game2));
		assertTrue(stack.isInStack(game3));
		assertFalse(stack.isInStack(game4));
		assertFalse(stack.isInStack(game5));
		assertFalse(stack.isInStack(game6));
		assertFalse(stack.isInStack(game7));
		
		assertEquals(game3,stack.peek());
		assertEquals(game,stack.bottom());
		
		assertEquals(3, stack.size());
	}
}
