package eg.edu.guc.yugioh.cards.spells;
import eg.edu.guc.yugioh.cards.*;
import eg.edu.guc.yugioh.board.*;
import eg.edu.guc.yugioh.board.player.*;
import java.util.*;

public class CardDestruction extends SpellCard {

	public CardDestruction(String name, String description) {
		super(name, description);
		
	}
	
	
	public void action(MonsterCard monster) {
		
		
		Player active = getBoard().getActivePlayer();
		Player opponent = getBoard().getOpponentPlayer();
		int activeHand = active.getField().getHand().size();
		int opponentHand = opponent.getField().getHand().size();
		int activeDeckSize = active.getField().getDeck().getDeck().size();
		int opponentDeckSize = opponent.getField().getDeck().getDeck().size();
		
		if(activeDeckSize >= activeHand) {
			active.getField().removeAllToGraveyard();
			active.getField().addNCardsToHand(activeHand);
		}
		
		else { 
			
			getBoard().setWinner(opponent);
			return;
		
		}
		
		if(opponentDeckSize >= opponentHand) {
			
			opponent.getField().removeAllToGraveyard();
			opponent.getField().addNCardsToHand(opponentHand);
		
		}
		
		else { 
			
			getBoard().setWinner(active);
			return;
		}
		
	}

}
