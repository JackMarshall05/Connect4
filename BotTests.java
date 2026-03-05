package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import connect.Game;

public class BotTests {
	@Test public void findConnect4() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] newBoard = 
			  { { 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 },
				{ 1,-1, 0, 0, 0, 0, 0 }, 
				{ 1,-1, 0, 0, 0, 0, 0 }, 
				{ 1,-1, 0, 0, 0, 0, 0 } };
		
		assertEquals(1, game.bot.takeTurn(newBoard));
	}
	
	@Test public void blockConnect4() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] newBoard = 
			  { { 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 1, 0, 0, 0 }, 
				{ 0, 0,-1, 1, 1, 0, 0 }, 
				{ 0, 0,-1, 1,-1,-1, 0 } };
		
		assertEquals(3, game.bot.takeTurn(newBoard));
	}
	
	@Test public void blockConnect4_2() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] newBoard = 
			  { { 0, 0, 0, 1, 0, 0, 0 }, 
				{ 0,-1, 0,-1, 1, 0, 0 }, 
				{ 0,-1, 1,-1,-1, 0, 0 },
				{ 0, 1,-1,-1, 1, 0, 0 }, 
				{ 0,-1, 1, 1, 1, 0, 0 }, 
				{ 1, 1,-1, 1,-1,-1, 0 } };
		
		assertEquals(5, game.bot.takeTurn(newBoard));
	}
	
	@Test public void finishBoard() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] twoLeft = 
			  { {-1, 1,-1, 1,-1, 0, 0 }, 
				{-1, 1,-1, 1,-1, 1,-1 }, 
				{-1, 1,-1, 1,-1, 1,-1 },
				{ 1,-1, 1,-1, 1,-1, 1 }, 
				{ 1,-1, 1,-1, 1,-1, 1 }, 
				{ 1,-1, 1,-1, 1,-1, 1 } };
		assertEquals(5, game.bot.takeTurn(twoLeft));
		byte[][] oneLeft = 
			  { {-1, 1,-1, 1,-1, 1, 0 }, 
				{-1, 1,-1, 1,-1, 1,-1 }, 
				{-1, 1,-1, 1,-1, 1,-1 },
				{ 1,-1, 1,-1, 1,-1, 1 }, 
				{ 1,-1, 1,-1, 1,-1, 1 }, 
				{ 1,-1, 1,-1, 1,-1, 1 } };
		assertEquals(6, game.bot.takeTurn(oneLeft));
		byte[][] noneLeft = 
			  { {-1, 1,-1, 1,-1, 1, 1 }, 
				{-1, 1,-1, 1,-1, 1,-1 }, 
				{-1, 1,-1, 1,-1, 1,-1 },
				{ 1,-1, 1,-1, 1,-1, 1 }, 
				{ 1,-1, 1,-1, 1,-1, 1 }, 
				{ 1,-1, 1,-1, 1,-1, 1 } };
		assertEquals(-1, game.bot.takeTurn(noneLeft));
	}
	
	@Test public void findConnect4_3() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] twoLeft = 
			  { { 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 1, 0, 0, 0, 0 }, 
				{ 0, 0,-1,-1, 0, 0, 0 },
				{ 0, 0, 1,-1, 0, 0, 0 }, 
				{ 0, 0, 1,-1, 0, 0, 0 }, 
				{ 0, 1, 1, 1,-1, 0, 0 } };
		assertEquals(3, game.bot.takeTurn(twoLeft));
	}
	
	@Test public void findConnect4_2() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] twoLeft = 
			  { { 0, 0, 0,-1, 0, 0, 0 }, 
				{ 0, 0,-1, 1,-1, 0, 0 }, 
				{ 0, 1, 1,-1,-1, 0, 0 },
				{ 0,-1,-1, 1, 1, 1, 0 }, 
				{ 0, 1, 1,-1,-1,-1, 0 }, 
				{ 0, 1,-1,-1, 1, 1, 1 } };
		assertEquals(6, game.bot.takeTurn(twoLeft));
	}
	
	@Test public void blockConnect4_3() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] newBoard = 
			  { { 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{-1,-1, 0, 1, 0, 0, 0 },
				{ 1, 1, 0,-1,-1, 0, 0 }, 
				{-1, 1, 0, 1,-1, 0, 0 }, 
				{ 1, 1, 1,-1, 1,-1, 0 } };
		
		assertNotEquals(2, game.bot.takeTurn(newBoard));
	}
	
	@Test public void blockConnect4_4() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] newBoard = 
			  { { 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0,-1, 0, 0 }, 
				{ 0, 0, 0, 1, 1, 0, 0 } };
		
		int col = game.bot.takeTurn(newBoard);
		switch(col) {
		case 0, 1, 3, 4, 6:
			assert false: col;
		}
	}
}
