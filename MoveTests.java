package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import connect.Game;

public class MoveTests {
	@Test
	public void move0() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] newBoard = game.makeMove((byte) 0, game.board(), true);
		byte[][] expectedBoard = { { 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 1, 0, 0, 0, 0, 0, 0 } };
		for (int i = 0; i < expectedBoard.length; i++) {
			assertArrayEquals(expectedBoard[i], newBoard[i], "Row " + i + " mismatch");
		}
	}

	@Test
	public void move1() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] newBoard = game.makeMove((byte) 1, game.board(), true);
		byte[][] expectedBoard = { { 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 1, 0, 0, 0, 0, 0 } };
		for (int i = 0; i < expectedBoard.length; i++) {
			assertArrayEquals(expectedBoard[i], newBoard[i], "Row " + i + " mismatch");
		}
	}

	@Test
	public void move2() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] newBoard = game.makeMove((byte) 2, game.board(), true);
		byte[][] expectedBoard = { { 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 1, 0, 0, 0, 0 } };
		for (int i = 0; i < expectedBoard.length; i++) {
			assertArrayEquals(expectedBoard[i], newBoard[i], "Row " + i + " mismatch");
		}
	}

	@Test
	public void move3() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] newBoard = game.makeMove((byte) 3, game.board(), true);
		byte[][] expectedBoard = { { 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 1, 0, 0, 0 } };
		for (int i = 0; i < expectedBoard.length; i++) {
			assertArrayEquals(expectedBoard[i], newBoard[i], "Row " + i + " mismatch");
		}
	}

	@Test
	public void move4() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] newBoard = game.makeMove((byte) 4, game.board(), true);
		byte[][] expectedBoard = { { 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 1, 0, 0 } };
		for (int i = 0; i < expectedBoard.length; i++) {
			assertArrayEquals(expectedBoard[i], newBoard[i], "Row " + i + " mismatch");
		}
	}

	@Test
	public void move5() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] newBoard = game.makeMove((byte) 5, game.board(), true);
		byte[][] expectedBoard = { { 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 1, 0 } };
		for (int i = 0; i < expectedBoard.length; i++) {
			assertArrayEquals(expectedBoard[i], newBoard[i], "Row " + i + " mismatch");
		}
	}

	@Test
	public void move6() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] newBoard = game.makeMove((byte) 6, game.board(), true);
		byte[][] expectedBoard = { { 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 1 } };
		for (int i = 0; i < expectedBoard.length; i++) {
			assertArrayEquals(expectedBoard[i], newBoard[i], "Row " + i + " mismatch");
		}
	}

	@Test
	public void changingTurn() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] newBoard = game.makeMove((byte) 0, game.board(), true);
		newBoard = game.makeMove((byte) 0, newBoard, false);
		byte[][] expectedBoard = { { 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 }, 
				{-1, 0, 0, 0, 0, 0, 0 }, 
				{ 1, 0, 0, 0, 0, 0, 0 } };
		for (int i = 0; i < expectedBoard.length; i++) {
			assertArrayEquals(expectedBoard[i], newBoard[i], "Row " + i + " mismatch");
		}
	}

	@Test
	public void cannotOverstackColumn() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] newBoard = game.makeMove((byte) 0, game.board(), true);
		newBoard = game.makeMove((byte) 0, newBoard, false);
		newBoard = game.makeMove((byte) 0, newBoard, true);
		newBoard = game.makeMove((byte) 0, newBoard, false);
		newBoard = game.makeMove((byte) 0, newBoard, true);
		newBoard = game.makeMove((byte) 0, newBoard, false);
		try {
			game.makeMove((byte) 0, newBoard, true);
			assert false;
		}catch(IllegalArgumentException e) {}
		byte[][] expectedBoard = { {-1, 0, 0, 0, 0, 0, 0 }, 
				{ 1, 0, 0, 0, 0, 0, 0 }, 
				{-1, 0, 0, 0, 0, 0, 0 },
				{ 1, 0, 0, 0, 0, 0, 0 }, 
				{-1, 0, 0, 0, 0, 0, 0 }, 
				{ 1, 0, 0, 0, 0, 0, 0 } };
		for (int i = 0; i < expectedBoard.length; i++) {
			assertArrayEquals(expectedBoard[i], newBoard[i], "Row " + i + " mismatch");
		}
	}
	
	@Test
	public void cannotTakeTwoTurns() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] newBoard = game.makeMove((byte) 0, game.board(), true);
		try {
			game.makeMove((byte) 0, newBoard, true);
			game.makeMove((byte) 0, newBoard, true);
			assert false;
		}catch(IllegalStateException e) {}
	}
}
