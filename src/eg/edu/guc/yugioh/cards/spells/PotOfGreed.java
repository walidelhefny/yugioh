package eg.edu.guc.yugioh.cards.spells;
import eg.edu.guc.yugioh.board.player.*;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class PotOfGreed extends SpellCard {

	public PotOfGreed(String name, String description) {
		super(name, description);
		
	}

	public void action(MonsterCard monster) {
		
		Player p1 = getBoard().getActivePlayer();
		
		p1.getField().addNCardsToHand(2);
		
		
	}
}
