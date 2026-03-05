package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import connect.Game;

public class ValidBoardTests {
	@Test public void invalidBoard_tooManyOfOnePiece() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] testBoard =   {{0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 1, 1, 0, 0},
								{0, 0, 0, 1, 1, 0, 0},
								{0, 0, 0, 1, 1, 0, 0}};
		try {
			game.validBoard(testBoard);
			assert false;
		}catch(IllegalStateException e) {}
	}
	
	@Test public void invalidBoard_hangingPiece() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] testBoard =   {{0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0,-1, 1, 0, 0},
								{0, 0, 0,-1, 1, 0, 0},
								{0, 0, 0, 0, 1, 0, 0}};
		try {
			game.validBoard(testBoard);
			assert false;
		}catch(IllegalStateException e) {}
	}
	
	@Test public void invalidBoard_invalidPiece() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] testBoard =   {{0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0,-3, 1, 0, 0},
								{0, 0, 0,-1, 1, 0, 0},
								{0, 0, 0,-1, 1, 0, 0}};
		try {
			game.validBoard(testBoard);
			assert false;
		}catch(IllegalStateException e) {}
	}
	
	@Test public void invalidBoard_invalidPiece_2() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] testBoard =   {{0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0,-1, 5, 0, 0},
								{0, 0, 0,-1, 1, 0, 0},
								{0, 0, 0,-1, 1, 0, 0}};
		try {
			game.validBoard(testBoard);
			assert false;
		}catch(IllegalStateException e) {}
	}
	
	@Test public void validBoard_1() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] testBoard =   {{0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0,-1, 1, 0, 0},
								{0, 0, 0,-1, 1, 0, 0},
								{0, 0, 0,-1, 1, 0, 0}};
		game.validBoard(testBoard);
	}
	
	@Test public void validBoard_2() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] testBoard =   {{0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 1, 0, 0},
								{0, 0, 0,-1, 1, 0, 0},
								{0, 0, 0,-1, 1, 0, 0}};
		game.validBoard(testBoard);
	}
	
	@Test public void validBoard_3() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] testBoard =   {{0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0,-1, 0, 0, 0},
								{0, 0, 0,-1, 1, 0, 0},
								{0, 0, 0,-1, 1, 0, 0}};
		game.validBoard(testBoard);
	}
}
