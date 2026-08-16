package eg.edu.guc.yugioh.cards.spells;
import eg.edu.guc.yugioh.board.player.*;
import java.util.*;
import eg.edu.guc.yugioh.cards.*;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class MonsterReborn extends SpellCard {

	public MonsterReborn(String name, String description) {
		super(name, description);
		
	}
	
	public void action(MonsterCard monster) {
		
		Player p1 = getBoard().getActivePlayer();
		Player p2 = getBoard().getOpponentPlayer();
		ArrayList<Card> g1 = p1.getField().getGraveyard();
		ArrayList<Card> g2 = p2.getField().getGraveyard();
		MonsterCard max1 = null;
		MonsterCard max2 = null;
		Boolean player = false;
		
		
		for(int i = 0; i < g1.size(); i++) {
			
			if(g1.get(i) instanceof MonsterCard) {
				
				MonsterCard temp1 = (MonsterCard) g1.get(i);
				
				if(max1 == null)
					max1 = (MonsterCard) g1.get(i);
				
				if(temp1.getAttackPoints() > max1.getAttackPoints())
					max1 = temp1;
			}
		}
		
		for(int j = 0; j < g2.size(); j++) {
			
			if(g2.get(j) instanceof MonsterCard) {
				
				MonsterCard temp2 = (MonsterCard) g2.get(j);
				
				if(max2 == null)
					max2 = (MonsterCard) g2.get(j);
				
				if(temp2.getAttackPoints() > max2.getAttackPoints())
					max2 = temp2;
				
			}
		}
		
		if(max1 != null && max2 != null) {
		
		if(max1.getAttackPoints() < max2.getAttackPoints()) {
				
			max1 = max2;
			player = true;
			
			}
		
		}
			if(max1 == null && max2 != null)  {
				
				max1 = max2;
				player = true;
			}
			
			if(max1 == null && max2 == null) {
				return;
		}
			
			if(player) {
				g2.remove(max1);
			} else {
				g1.remove(max1);
				
			}
		
		
		p1.getField().addMonsterToField(max1, Mode.ATTACK, false);
		
		
	}

}
