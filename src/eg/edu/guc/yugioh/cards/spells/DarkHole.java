package eg.edu.guc.yugioh.cards.spells;
import eg.edu.guc.yugioh.board.player.*;
import java.util.*;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class DarkHole extends Raigeki {

	public DarkHole(String name, String description) {
		super(name, description);
		
	}
	
	public void action(MonsterCard monster) {
		
		super.action(monster);
		
		Player p1 = getBoard().getActivePlayer();
		
		ArrayList<MonsterCard> m1 = p1.getField().getMonstersArea();
		
		p1.getField().removeMonsterToGraveyard(m1);
		
		
	}

}
