package eg.edu.guc.yugioh.cards.spells;
import java.util.ArrayList;

import eg.edu.guc.yugioh.board.player.*;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class Raigeki extends SpellCard {

	public Raigeki(String name, String description) {
		super(name, description);
		
	}
	
	public void action(MonsterCard monster) {
		
		Player p2 = getBoard().getOpponentPlayer();
		ArrayList<MonsterCard> m2 = p2.getField().getMonstersArea();
		
		p2.getField().removeMonsterToGraveyard(m2);
		
		
	}

}
