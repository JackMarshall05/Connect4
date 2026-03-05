package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import connect.Game;

public class ConnectTests {
	@Test
	public void connect4Vertical_1() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] newBoard = { { 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 1, 0, 0, 0, 0, 0, 0 },
				{ 1,-1, 0, 0, 0, 0, 0 }, 
				{ 1,-1, 0, 0, 0, 0, 0 }, 
				{ 1,-1, 0, 0, 0, 0, 0 } };
		assertEquals(true, game.hasWon(newBoard, (byte)1, game.connectNum()));
		assertEquals(false, game.hasWon(newBoard, (byte)-1, game.connectNum()));
	}
	
	@Test
	public void connect4Vertical_2() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] newBoard = { { 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 1, 0, 0, 0 }, 
				{ 0, 0, 0, 1, 0, 0, 0 },
				{ 1,-1, 0, 1,-1, 0, 0 }, 
				{ 1,-1,-1, 1,-1, 0, 0 }, 
				{ 1,-1,-1,-1, 1, 0, 0 } };
		assertEquals(true, game.hasWon(newBoard, (byte)1, game.connectNum()));
		assertEquals(false, game.hasWon(newBoard, (byte)-1, game.connectNum()));
	}
	
	@Test
	public void connect4Horizontal_1() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] newBoard = { { 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 },
				{ 0,-1, 0, 0, 0, 0, 0 }, 
				{ 0,-1,-1, 0, 0, 0, 0 }, 
				{ 0, 1, 1, 1, 1, 0, 0 } };
		assertEquals(true, game.hasWon(newBoard, (byte)1, game.connectNum()));
		assertEquals(false, game.hasWon(newBoard, (byte)-1, game.connectNum()));
	}
	
	@Test
	public void connect4Horizontal_2() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] newBoard = { { 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 1, 0, 0, 0 },
				{ 1,-1, 0, 1,-1, 0, 0 }, 
				{ 1,-1,-1, 1,-1, 1, 0 }, 
				{ 1,-1,-1,-1,-1, 1, 0 } };
		assertEquals(true, game.hasWon(newBoard, (byte)-1, game.connectNum()));
		assertEquals(false, game.hasWon(newBoard, (byte)1, game.connectNum()));
	}
	
	@Test
	public void connect4NW_1() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] newBoard = { { 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{-1, 0, 0, 0, 0, 0, 0 },
				{ 1,-1, 0, 0, 0, 0, 0 }, 
				{ 1,-1,-1, 0, 0, 0, 0 }, 
				{ 1, 1, 1,-1, 0, 0, 0 } };
		assertEquals(true, game.hasWon(newBoard, (byte)-1, game.connectNum()));
		assertEquals(false, game.hasWon(newBoard, (byte)1, game.connectNum()));
	}
	
	@Test
	public void connect4NW_2() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] newBoard = { { 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{-1, 0, 0, 1, 0, 0, 0 },
				{ 1,-1, 0, 1,-1, 0, 0 }, 
				{ 1,-1,-1, 1,-1, 1, 0 }, 
				{ 1, 1,-1,-1,-1, 1, 0 } };
		assertEquals(true, game.hasWon(newBoard, (byte)-1, game.connectNum()));
		assertEquals(false, game.hasWon(newBoard, (byte)1, game.connectNum()));
	}
	
	@Test
	public void connect4NE_1() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] newBoard = { { 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0,-1, 0, 0 },
				{ 1, 0, 1,-1, 1, 0, 0 }, 
				{ 1,-1,-1, 1,-1, 0, 0 }, 
				{ 1,-1, 1,-1, 1, 0, 0 } };
		assertEquals(true, game.hasWon(newBoard, (byte)-1, game.connectNum()));
		assertEquals(false, game.hasWon(newBoard, (byte)1, game.connectNum()));
	}
	
	@Test
	public void connect4NE_2() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] newBoard = { { 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{-1, 0, 0, 1, 1, 0, 0 },
				{ 1,-1, 0, 1,-1, 0, 0 }, 
				{ 1,-1, 1, 1,-1, 1, 0 }, 
				{-1, 1,-1,-1,-1, 1, 0 } };
		assertEquals(true, game.hasWon(newBoard, (byte)1, game.connectNum()));
		assertEquals(false, game.hasWon(newBoard, (byte)-1, game.connectNum()));
	}
	
	@Test
	public void noConnect4() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] newBoard = { { 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{-1, 0, 0, 1, 1, 0, 0 },
				{ 1,-1, 0,-1,-1, 0, 0 }, 
				{ 1,-1, 1, 1,-1, 1, 0 }, 
				{-1, 1,-1,-1,-1, 1, 0 } };
		assertEquals(false, game.hasWon(newBoard, (byte)1, game.connectNum()));
		assertEquals(false, game.hasWon(newBoard, (byte)-1, game.connectNum()));
	}
}
