package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import connect.Game;

public class InitTests {
	@Test public void emptyBoard() {
		Game game = new Game(true, (byte)4, (byte)6, (byte)7);
		byte[][] expectedBoard =   {{0, 0, 0, 0, 0, 0, 0},
									{0, 0, 0, 0, 0, 0, 0},
									{0, 0, 0, 0, 0, 0, 0},
									{0, 0, 0, 0, 0, 0, 0},
									{0, 0, 0, 0, 0, 0, 0},
									{0, 0, 0, 0, 0, 0, 0}};
		for (int i = 0; i < expectedBoard.length; i++) {
		    assertArrayEquals(expectedBoard[i], game.board()[i], "Row " + i + " mismatch");
		}
	}
}
