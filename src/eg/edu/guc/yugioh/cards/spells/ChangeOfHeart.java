package eg.edu.guc.yugioh.cards.spells;
import eg.edu.guc.yugioh.board.player.*;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class ChangeOfHeart extends SpellCard {

	public ChangeOfHeart(String name, String description) {
		super(name, description);
		
	}
		
		public void action(MonsterCard monster) {
			
			Player p1 = getBoard().getActivePlayer();
			Player p2 = getBoard().getOpponentPlayer();
			
			p2.getField().getMonstersArea().remove(monster);
			p1.getField().getMonstersArea().add(monster);
			
		}

}
