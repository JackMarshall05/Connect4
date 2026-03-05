package connect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel implements KeyListener {

	private final byte connectNum;
	protected byte[][] board;
	public final byte[][] connectHeuristicBoard = { { 3, 4, 5, 7, 5, 4, 3 }, { 4, 6, 8, 10, 8, 6, 4 },
			{ 5, 8, 11, 12, 11, 8, 5 }, { 5, 8, 11, 12, 11, 8, 5 }, { 4, 6, 8, 10, 8, 6, 4 }, { 3, 4, 5, 7, 5, 4, 3 } };
	public final boolean playerIsO = true;
	public final boolean playerStart;
	public Bot bot;
	private boolean gameOver = false;
	private Runnable onMove = null;

	public static int hasWonCount = 0;
	public static int evaluationCount = 0;

	public void setOnMove(Runnable r) {
		this.onMove = r;
	}

	public Game(boolean playerStart, byte connectNum, byte rows, byte cols) {
		this.playerStart = playerStart;
		this.connectNum = connectNum;
		board = new byte[rows][cols];
		bot = new Bot(!playerIsO, connectNum, this);
		initialiseBoard();
		setupGameFrame();
		if (!this.playerStart) { this.board = makeMove(bot.takeTurn(board), this.board, bot.isO); }
	}

	private void setupGameFrame() {
		JFrame frame = new JFrame("Connect 4");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setSize(700, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		addKeyListener(this);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!gameOver) {
					int panelWidth = getWidth();
					int cols = board[0].length;
					int slotSize = panelWidth / cols;
					int clickedCol = e.getX() / slotSize;

					try {
						makePlayerMove((byte) clickedCol);
						repaint();
					} catch (IllegalArgumentException ex) {
						System.out.println("Invalid move: " + ex.getMessage());
					}
				}
			}
		});
		setFocusable(true);
		requestFocusInWindow();
		setBackground(Color.GRAY);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		int cols = board[0].length;
		int rows = board.length;

		int panelWidth = getWidth();
		int panelHeight = getHeight();

		// Calculate the largest slot size that fits both dimensions
		int slotSize = Math.min(panelWidth / cols, panelHeight / rows);

		// Calculate offsets to center the board in the window
		int xOffset = (panelWidth - (slotSize * cols)) / 2;
		int yOffset = (panelHeight - (slotSize * rows)) / 2;

		// Draw blue board background
		g.setColor(new Color(50, 50, 155));
		g.fillRect(xOffset, yOffset, cols * slotSize, rows * slotSize);

		// Draw the game pieces
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				int x = xOffset + col * slotSize;
				int y = yOffset + row * slotSize;

				g.setColor(new Color(255, 255, 255)); // default empty
				if (board[row][col] == 1) g.setColor(new Color(0, 0, 255)); // O
				else if (board[row][col] == -1) g.setColor(new Color(255, 0, 0)); // X

				g.fillOval(x + 5, y + 5, slotSize - 10, slotSize - 10);
				g.setColor(new Color(0, 0, 0));
				g2.setStroke(new BasicStroke(5));
				g2.drawOval(x + 5, y + 5, slotSize - 10, slotSize - 10);
			}
		}
	}

	public void makePlayerMove(byte col) {
		if (gameOver) return;
		if (board[0][col] != 0) throw new IllegalArgumentException("Column full");

		this.board = makeMove(col, this.board, playerIsO);
		if (checkGameOver(this.board)) {
			gameOver = true;
			System.out.println("Player won!");
			return;
		}

		paintImmediately(0, 0, getWidth(), getHeight());

		this.board = makeMove(bot.takeTurn(board), deepCopy(this.board), bot.isO);
		if (checkGameOver(this.board)) { // I have to check again
			gameOver = true;
			System.out.println("Bot won!");
			return;
		}
		if (onMove != null) onMove.run();
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (gameOver) {
			System.out.println("Game is Over");
			return;
		}
		byte col = (byte) (e.getKeyChar() - '1');
		if (col >= 0 && col < board[0].length) {
			makePlayerMove(col);
		} else {
			System.out.println("That is not a recognised key. Key must be between 1 and " + board[0].length);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public void initialiseBoard() {
		for (int i = 0; i < board.length; i++) { for (int j = 0; j < board[i].length; j++) { board[i][j] = 0; } }
	}

	public byte[][] makeMove(byte col, byte[][] givenBoard, boolean currentPlayerO) {
		if (gameOver) return givenBoard;
		if (givenBoard[0][col] != 0) { throw new IllegalArgumentException("Column full"); }
		for (int row = givenBoard.length - 1; row >= 0; row--) {
			if (givenBoard[row][col] == 0) {
				byte pieceToAdd = currentPlayerO ? (byte) 1 : (byte) -1;
				givenBoard[row][col] = pieceToAdd;
				validBoard(givenBoard);
				return givenBoard;
			}
		}
		throw new IllegalArgumentException(
				"Column full. Though I did a precheck so in theory in shou;dn't reach this exception");
	}

	public boolean validBoard(byte[][] givenBoard) {
		if (tooManyOfOnePiece(givenBoard)) {
			throw new IllegalStateException("There is too many of one peice on the given Board");
		}
		if (hangingPieces(givenBoard)) {
			throw new IllegalStateException("There is a hanging piece without a piece below it");
		}
		if (invalidPieceOnBoard(givenBoard)) {
			throw new IllegalStateException(
					"there is an invalid piece on the board that does not belong to nay player (1 or -1) or is not the null piece (0)");
		}
		return true;
	}

	public boolean tooManyOfOnePiece(byte[][] givenBoard) {
		int counter = 0;
		for (int i = 0; i < givenBoard.length; i++) {
			for (int j = 0; j < givenBoard[i].length; j++) { counter += givenBoard[i][j]; }
		}
		return Math.abs(counter) > 2; // I've made this two and not one so that I can do a check to see if opponent
										// has won in the takeTurn() method in the Bot
	}

	public boolean hangingPieces(byte[][] givenBoard) {
		int rows = givenBoard.length;
		int cols = givenBoard[0].length;

		for (int i = 0; i < cols; i++) {
			boolean reachedStackTop = false;
			for (int j = 0; j < rows; j++) {
				if (givenBoard[j][i] != 0) { reachedStackTop = true; }
				if (reachedStackTop && givenBoard[j][i] == 0) { return true; }
			}
		}
		return false;
	}

	public boolean invalidPieceOnBoard(byte[][] givenBoard) {
		for (int i = 0; i < givenBoard.length; i++) {
			for (int j = 0; j < givenBoard[i].length; j++) { if (Math.abs(givenBoard[i][j]) > 1) { return true; } }
		}
		return false;
	}

	public boolean boardIsFull(byte[][] givenBoard) {
		for (byte i = 0; i < givenBoard[0].length; i++) { if (givenBoard[0][i] == 0) return false; }
		return true;
	}

	public boolean checkGameOver(byte[][] givenBoard) {
		return hasWon(givenBoard, (byte) 1, this.connectNum) || hasWon(givenBoard, (byte) -1, this.connectNum);
	}

	public boolean hasWon(byte[][] board, byte player, byte connectNum) {
		int rows = board.length;
		int cols = board[0].length;

		hasWonCount++;

		// Horizontal
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c <= cols - connectNum; c++) {
				boolean win = true;
				for (int i = 0; i < connectNum; i++) {
					if (board[r][c + i] != player) {
						win = false;
						break;
					}
				}
				if (win) return true;
			}
		}

		// Vertical
		for (int c = 0; c < cols; c++) {
			for (int r = 0; r <= rows - connectNum; r++) {
				boolean win = true;
				for (int i = 0; i < connectNum; i++) {
					if (board[r + i][c] != player) {
						win = false;
						break;
					}
				}
				if (win) return true;
			}
		}

		// Diagonal (down-right)
		for (int r = 0; r <= rows - connectNum; r++) {
			for (int c = 0; c <= cols - connectNum; c++) {
				boolean win = true;
				for (int i = 0; i < connectNum; i++) {
					if (board[r + i][c + i] != player) {
						win = false;
						break;
					}
				}
				if (win) return true;
			}
		}

		// Diagonal (up-right)
		for (int r = connectNum - 1; r < rows; r++) {
			for (int c = 0; c <= cols - connectNum; c++) {
				boolean win = true;
				for (int i = 0; i < connectNum; i++) {
					if (board[r - i][c + i] != player) {
						win = false;
						break;
					}
				}
				if (win) return true;
			}
		}

		return false;
	}

	public boolean hasWon(byte[][] board, byte player, byte connectNum, byte col) {
		int rows = board.length;

		// Find the row of the last placed piece in column `col`
		int row = -1;
		for (int r = 0; r < rows; r++) {
			if (board[r][col] != 0) {
				row = r;
				break;
			}
		}
		if (row == -1) throw new IllegalStateException("There is none of the players pieces in that row"); // No piece found in column — shouldn't happen.

		// Direction vectors: {rowDelta, colDelta}
		int[][] directions = { { 0, 1 }, // Horizontal
				{ 1, 0 }, // Vertical
				{ 1, 1 }, // Diagonal down-right
				{ -1, 1 } // Diagonal up-right
		};

		for (int[] dir : directions) {
			int count = 1; // Include the placed piece

			// Check in positive direction
			count += countConsecutive(board, player, row, col, dir[0], dir[1]);

			// Check in negative direction
			count += countConsecutive(board, player, row, col, -dir[0], -dir[1]);

			if (count >= connectNum) return true;
			//System.out.println(count - connectNum);
		}

		return false;
	}

	private int countConsecutive(byte[][] board, byte player, int row, int col, int dRow, int dCol) {
		int count = 0;
		int rows = board.length;
		int cols = board[0].length;

		int r = row + dRow;
		int c = col + dCol;

		while (r >= 0 && r < rows && c >= 0 && c < cols && board[r][c] == player) {
			count++;
			r += dRow;
			c += dCol;
		}
		return count;
	}

	public byte[][] board() {
		return deepCopy(this.board);
	}

	public byte connectNum() {
		return this.connectNum;
	}

	public byte[][] deepCopy(byte[][] original) {
		byte[][] copy = new byte[original.length][original[0].length];
		for (int i = 0; i < original.length; i++) {
			for (int j = 0; j < original[i].length; j++) { copy[i][j] = original[i][j]; }
		}
		return copy;
	}

	public void printBoard(byte[][] givenBoard) {
		System.out.println("Current Board:");
		for (int i = 0; i < givenBoard.length; i++) {
			for (int j = 0; j < givenBoard[i].length; j++) {
				if (givenBoard[i][j] < 3) {
					System.out.print("|" + (givenBoard[i][j] == 0 ? "_" : (givenBoard[i][j] == 1 ? "O" : "X")));
				} else {
					System.out.print("|" + givenBoard[i][j]);
				}
			}
			System.out.println("|");
		}
		for (int i = 0; i < givenBoard[0].length; i++) { System.out.print("|" + (i + 1)); }
		System.out.println("|");
	}
}
