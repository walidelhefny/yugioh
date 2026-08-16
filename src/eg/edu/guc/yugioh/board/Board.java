package eg.edu.guc.yugioh.board;
import eg.edu.guc.yugioh.board.player.*;
import eg.edu.guc.yugioh.cards.*;

public class Board {
	
	private Player activePlayer;
	private Player opponentPlayer;
	private Player winner;
	private boolean end;
	
	public Board () {
		
		Card.setBoard(this);
		
	}
	
	
	public void whoStarts(Player p1, Player p2) {
		
		int n = (int)( Math.random()*2)+1;
		
		if(n == 1) {
			activePlayer = p1;
			opponentPlayer = p2;
		}
		else {
			activePlayer = p2;
			opponentPlayer = p1;
		}
	}
	
	public void startGame(Player p1, Player p2) {
		
		
		p1.getField().addNCardsToHand(5);
		p2.getField().addNCardsToHand(5);
		
		
		
		whoStarts(p1,p2);
		activePlayer.getField().setPhase(Phase.MAIN1);
		activePlayer.getField().addCardToHand();
		
		
	}

	public void nextPlayer() {
		
		Player temp = activePlayer;
		activePlayer = opponentPlayer;
		opponentPlayer = temp;
		
		activePlayer.getField().setPhase(Phase.MAIN1);
		activePlayer.getField().addCardToHand();
		
	}

	public Player getActivePlayer() {
		return activePlayer;
	}


	public void setActivePlayer(Player activePlayer) {
		this.activePlayer = activePlayer;
	}


	public Player getOpponentPlayer() {
		return opponentPlayer;
	}


	public void setOpponentPlayer(Player opponentPlayer) {
		this.opponentPlayer = opponentPlayer;
	}


	public Player getWinner() {
		return winner;
	}


	public void setWinner(Player winner) {
		this.winner = winner;
		end = true;
		
	}
	
	public boolean getEnd() {
		
		return end;
	}
	

}
