package eg.edu.guc.yugioh.cards.spells;
import eg.edu.guc.yugioh.board.player.*;
//import eg.edu.guc.yugioh.cards.*;
import java.util.*;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class GracefulDice extends SpellCard {

	public GracefulDice(String name, String description) {
		super(name, description);
		
	}
	
	public void action(MonsterCard monster) {
		
		int number = (int) (Math.random()*9)+1;
		Player p1 = getBoard().getActivePlayer();
		int increase = number * 100;
		ArrayList<MonsterCard> m = p1.getField().getMonstersArea();
		
		for(int i = 0; i < m.size(); i++) {
		
			MonsterCard m1 = m.get(i);
			m1.setAttackPoints(m1.getAttackPoints()+increase);
			m1.setDefensePoints(m1.getDefensePoints()+increase);
			
		}
		
		
		
		
	}

}
