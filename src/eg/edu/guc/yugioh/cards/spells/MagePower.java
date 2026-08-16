package eg.edu.guc.yugioh.cards.spells;
import eg.edu.guc.yugioh.board.player.*;
import java.util.*;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class MagePower extends SpellCard {

	public MagePower(String name, String description) {
		super(name, description);
		 
	}
	
	public void action(MonsterCard monster) {
		
		Player p1 = getBoard().getActivePlayer();
		int spells = p1.getField().getSpellArea().size();
		spells = spells * 500;
		
		monster.setAttackPoints(monster.getAttackPoints()+spells);
		monster.setDefensePoints(monster.getDefensePoints()+spells);

		
	}
	

}
