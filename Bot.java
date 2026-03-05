package connect;

import java.util.HashMap;
import java.util.Map;

/**
 * A connect-4 AI that uses alpha–beta minimax, win-state caching and
 * static-evaluation caching. It relies on Game.hasWon(board, player, connectNum
 * , lastCol) – i.e. the column of the last move.
 */
public class Bot {

	/* ---------- configuration ---------- */

	private static final int SEARCH_DEPTH = 6; // play depth
	private static final int WIN_SCORE = Integer.MAX_VALUE - 1; //the score assigned to a winning position for the bot
	private static final int LOSS_SCORE = Integer.MIN_VALUE + 1; //the score assigned to a losing position for the bot

	public final boolean isO; // true → this bot plays piece value 1 (O)
								// false → this bot plays piece value -1 (X)
	private final byte connectNum; // 4 for classic connect-4
	private final Game game; // callbacks (makeMove, hasWon, heuristic board)

	/* ---------- transposition tables ---------- */

	/** Boards that are already terminal. value==true → win for bot. value == false → loss for bot */
	private final Map<Long, Boolean> winPositions = new HashMap<>();
	/** Static evaluations for non-terminal boards (independent of side to move) */
	private final Map<Long, Integer> seenPositions = new HashMap<>();

	/* ---------- convenience pieces ---------- */

	private final byte botPiece; // 1 if isO, -1 otherwise
	private final byte oppPiece; // -1 if isO, 1 otherwise

	/* ---------------------------------------------------------------------- */

	public Bot(boolean isO, byte connectNum, Game game) {
		this.isO = isO;
		this.connectNum = connectNum;
		this.game = game;

		this.botPiece = isO ? (byte) 1 : (byte) -1;
		this.oppPiece = (byte) -botPiece;
	}

	/* ====================================================================== */

	/** Returns the column the bot wants to play (-1 if board full). */
	public byte takeTurn(byte[][] board) {

		if (game.boardIsFull(board)) return -1;

		/* ---------- 1. try to win immediately ---------- */
		for (byte c = 0; c < board[0].length; c++) {
			if (board[0][c] != 0) continue;
			byte[][] next = game.makeMove(c, game.deepCopy(board), isO);
			if (game.hasWon(next, botPiece, connectNum, c)) return c;
		}

		/* ---------- 2. must we block opponent’s win? ---------- */
		for (byte c = 0; c < board[0].length; c++) {
			if (board[0][c] != 0) continue;
			byte[][] next = game.makeMove(c, game.deepCopy(board), !isO);
			if (game.hasWon(next, oppPiece, connectNum, c)) return c;
		}

		/* ---------- 3. otherwise search ---------- */
		int bestScore = Integer.MIN_VALUE;
		byte bestCol = -1;

		for (byte c = 0; c < board[0].length; c++) {
			if (board[0][c] != 0) continue;

			byte[][] next = game.makeMove(c, game.deepCopy(board), isO);
			int score = minimax(next, SEARCH_DEPTH, /* botTurn? */ false, Integer.MIN_VALUE, Integer.MAX_VALUE, c);

			if (score > bestScore) {
				bestScore = score;
				bestCol = c;
			}
		}
		return bestCol;
	}

	/* ====================================================================== */

	/**
	 * Alpha–beta minimax.
	 *
	 * @param botTurn true → bot to move, false → opponent to move
	 * @param lastCol column in which the *last* piece was dropped
	 */
	private int minimax(byte[][] board, int depth, boolean botTurn, int alpha, int beta, byte lastCol) {

		/* ---------- hash key (board only) ---------- */
		long key = hashBoard(board);

		/* ---------- terminal cache ---------- */
		Boolean cachedWin = winPositions.get(key);
		if (cachedWin != null) return cachedWin ? WIN_SCORE : LOSS_SCORE;

		/* ---------- did the last move end the game? ---------- */
		byte lastPlayerPiece = botTurn ? oppPiece : botPiece;
		if (game.hasWon(board, lastPlayerPiece, connectNum, lastCol)) {
			boolean winForBot = (lastPlayerPiece == botPiece);
			winPositions.put(key, winForBot);
			return winForBot ? WIN_SCORE : LOSS_SCORE;
		}

		/* ---------- depth 0 or draw ---------- */
		if (depth == 0 || game.boardIsFull(board)) {
			Integer positionEvaluation = seenPositions.get(key);
			if (positionEvaluation != null) return positionEvaluation;

			int eval = evaluateBoard(board);
			seenPositions.put(key, eval);
			return eval;
		}
		
		/* ---------- recurse ---------- */
		int best = botTurn ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		for (byte c = 0; c < board[0].length; c++) {
			if (board[0][c] != 0) continue;
			byte copy[][] = game.makeMove(c,  game.deepCopy(board),  botTurn == isO);
			if (botTurn) {
				best = Math.max(best, minimax(copy, depth - 1, false, alpha, beta, c));
				alpha = Math.max(alpha, best);
				if (beta <= alpha) break; // β-cut
			} else {
				best = Math.min(best, minimax(copy, depth - 1, /* botTurn? */ true, alpha, beta, c));
				beta = Math.min(beta, best);
				if (beta <= alpha) break; // α-cut
			}
		}
		
		return best;
	}

	/* ====================================================================== */

	/** Heuristic: centre & higher rows are worth more. */
	private int evaluateBoard(byte[][] board) {
		Game.evaluationCount++;

		int score = 0;
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[r].length; c++) {
				byte cell = board[r][c];
				if (cell == 0) continue;

				int w = game.connectHeuristicBoard[r][c];
				score += (cell == botPiece) ? w : -w;
			}
		}
		return score;
	}

	/* ====================================================================== */

	/** 2-bit Zobrist-style hash (fits 6×7 board into 84 bits ⇒ we keep 64 MSB). */

	public long hashBoard(byte[][] board) {
		long hash = 0L;
		int counter = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				counter++;
				if (counter > 64) throw new IllegalArgumentException("Board too large");
				int val = board[i][j] + 1;
				hash = (hash << 2) | val;
			}
		}
		return hash;
	}
}
