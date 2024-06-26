package eg.edu.guc.yugioh.board.player;
import eg.edu.guc.yugioh.cards.*;
import eg.edu.guc.yugioh.cards.spells.*;
import java.util.*;

public interface Duelist {

	public boolean summonMonster(MonsterCard monster);
	public boolean summonMonster(MonsterCard monster, ArrayList<MonsterCard> sacrifices);
	public boolean setMonster(MonsterCard monster);
	public boolean setMonster(MonsterCard monster, ArrayList<MonsterCard> sacrifices);
	public boolean setSpell(SpellCard spell);
	public boolean activateSpell(SpellCard spell, MonsterCard monster);
	public boolean declareAttack(MonsterCard activeMonster, MonsterCard opponentMonster);
	public boolean declareAttack(MonsterCard activeMonster);
	public void addCardToHand();
	public void addNCardsToHand(int n);
	public void endPhase();
	public boolean endTurn();
	public boolean switchMonsterMode(MonsterCard monster);
	
	
	
}
