package connect;

public class BotEvaluation extends Game{
	private int numGames = 0;
	public int botLosses = 0;
	
	public BotEvaluation() {
		super(true, (byte) 4, (byte) 6, (byte) 7);
		long start = System.currentTimeMillis();
		while(true) {
			while(!checkGameOver(this.board)) {
				this.board = makeMove(bot.takeTurn(deepCopy(this.board)), deepCopy(this.board), bot.isO);
				
				byte randomCol = (byte) (Math.random() * 7);
				while(this.board[0][randomCol] != 0){
					randomCol = (byte) (Math.random() * 7);
				}
				this.board = makeMove(randomCol, deepCopy(this.board), !bot.isO);
				
				repaint();
			}
			if(!hasWon(this.board, (byte)-1, (byte)4)) {
				botLosses++;
				System.out.println("The Bot has now lost " + botLosses);
			}
			numGames++;
			if(numGames%100 == 0) {
				System.out.println("Games played" + numGames);
				System.out.println("Time " + (System.currentTimeMillis() - start)/1000 + " seconds");
			}
			
			initialiseBoard();
		}
	}
}
