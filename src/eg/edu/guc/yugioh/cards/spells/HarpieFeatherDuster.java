package eg.edu.guc.yugioh.cards.spells;
import eg.edu.guc.yugioh.board.player.*;
import java.util.*;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class HarpieFeatherDuster extends SpellCard {

	public HarpieFeatherDuster(String name, String description) {
		super(name, description);
		
	}
	
	public void action(MonsterCard monster) {
		
		Player p2 = getBoard().getOpponentPlayer();
		ArrayList<SpellCard> spells = p2.getField().getSpellArea();
		
		p2.getField().removeSpellToGraveyard(spells);
		
		
	}

}
