package eg.edu.guc.yugioh.cards.spells;

import java.util.ArrayList;

import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class HeavyStorm extends HarpieFeatherDuster {

	public HeavyStorm(String name, String description) {
		super(name, description);
		
	}
	
	public void action(MonsterCard monster) {
		
		super.action(monster);
		
		Player p1 = getBoard().getActivePlayer();
		ArrayList<SpellCard> spells = p1.getField().getSpellArea();
		
		p1.getField().removeSpellToGraveyard(spells);
		
		
	}
}
