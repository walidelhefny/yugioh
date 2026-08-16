package eg.edu.guc.yugioh.board.player;
import java.io.IOException;

import eg.edu.guc.yugioh.cards.*;

import java.util.*;

import eg.edu.guc.yugioh.cards.spells.*;
import eg.edu.guc.yugioh.exceptions.EmptyFieldException;
import eg.edu.guc.yugioh.exceptions.MissingFieldException;
import eg.edu.guc.yugioh.exceptions.NoMonsterSpaceException;
import eg.edu.guc.yugioh.exceptions.NoSpellSpaceException;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.exceptions.UnknownCardTypeException;
import eg.edu.guc.yugioh.exceptions.UnknownSpellCardException;
import eg.edu.guc.yugioh.board.*;

public class Field {
	
	private Phase phase;
	private ArrayList<MonsterCard> monstersArea;
	private ArrayList<SpellCard> spellArea;
	private Deck deck;
	private ArrayList<Card> hand;
	private ArrayList<Card> graveyard;
	
	public Field() throws IOException, MissingFieldException, EmptyFieldException, UnknownCardTypeException, UnknownSpellCardException {
		
		phase = Phase.MAIN1;
		monstersArea = new ArrayList<MonsterCard>();
		spellArea = new ArrayList<SpellCard>();
		hand = new ArrayList<Card>();
		graveyard = new ArrayList<Card>();
		
		deck = new Deck();
	}
	
	
	public void addMonsterToField(MonsterCard monster, Mode m, boolean isHidden) {
		
		if(monstersArea.size() < 5) {
		
		monster.setMode(m);
		monster.setHidden(isHidden);
		monstersArea.add(monster);
		monster.setLocation(Location.FIELD);
		hand.remove(monster);
		
		} else {
			
			throw new NoMonsterSpaceException("No free space available at monsters area");
		}
	
		
	}


	
	public void addMonsterToField(MonsterCard monster, Mode mode, ArrayList<MonsterCard> sacrifices) {
		
		int level = monster.getLevel();
		int size = sacrifices.size();
			
		if(size > 0) {
			
			if( ( (level == 5 || level == 6) && size == 1) || ( (level == 7 || level == 8) && size == 2) )  {
				
				
				for(int i = sacrifices.size()-1; i >=0; i--) {
				
				MonsterCard temp = sacrifices.get(i);
				monstersArea.remove(temp);
				temp.setLocation(Location.GRAVEYARD);
				graveyard.add(temp);
				
			
			}
			
			if(mode == Mode.ATTACK)
			addMonsterToField(monster,mode,false);
			else addMonsterToField(monster,mode,true);
			
			
			}
			
			sacrifices.clear();
			
		} else {
			
			if(level <= 4) {
				
				if(mode == Mode.ATTACK)
					addMonsterToField(monster,mode,false);
				else addMonsterToField(monster,mode,true);
			
			}
			
		}
		
		}
	
	
	public boolean helperSummon(MonsterCard monster, ArrayList<MonsterCard> sacrifices) {
		
		int level = monster.getLevel();
		int size = sacrifices.size();
		
		if(size > 0) {
		
		switch(level) {
		
		case 5: if(size == 1) return true; break;
		case 6: if(size == 1) return true; break;
		case 7: if(size == 2) return true; break;
		case 8:	if(size == 2) return true; break;
		default: return false;
		
		}
		
		}
		
		return false;
		
	}
	
	public void addSpellToField(SpellCard action, MonsterCard monster, boolean hidden) {
		
		
		if(spellArea.size() < 5) {
			
			if(action.getLocation() == Location.HAND) {
	
			
			action.setHidden(hidden);
			spellArea.add(action);
			action.setLocation(Location.FIELD);
			hand.remove(action);
			
			if(!hidden) 
				activateSetSpell(action,monster);
			
			}
			
		} else {
			
			throw new NoSpellSpaceException("No free space available at spells area");
		}
		
	}
	
	public void activateSetSpell(SpellCard action, MonsterCard monster) {
		
		if(spellArea.contains(action) && action.getLocation() == Location.FIELD) {
		action.action(monster);
		removeSpellToGraveyard(action);
		
		}
		
	}
	
	public void removeSpellToGraveyard(SpellCard spell) {
		
		if(spell.getLocation() == Location.FIELD)  {
		
		spellArea.remove(spell);
		graveyard.add(spell);
		spell.setLocation(Location.GRAVEYARD);
		
		}
	}
	
	public void removeSpellToGraveyard(ArrayList<SpellCard> spells) {
		
		for(int i = spells.size()-1; i >=0; i--) {
			
			removeSpellToGraveyard(spells.get(i));
			
		}
		
	}
	
	public void removeMonsterToGraveyard(MonsterCard monster) {
		
		if(monster.getLocation() == Location.FIELD) {
		
			monstersArea.remove(monster);
			graveyard.add(monster);
			monster.setLocation(Location.GRAVEYARD);
	
		}
	
	
		
	}
	
	public void removeMonsterToGraveyard(ArrayList<MonsterCard> monsters) {
		
		
		for(int i = monsters.size()-1; i >=0; i--) {
			
			removeMonsterToGraveyard(monsters.get(i));
		}
		
		
	}
	
	public void addCardToHand() {
		
		Player opponent = Card.getBoard().getOpponentPlayer();
		
		if(deck.getDeck().size() == 0 ) {
			
			Card.getBoard().setWinner(opponent);
			
		} else {
	
		Card temp = deck.drawOneCard();
		temp.setLocation(Location.HAND);
		
		hand.add(temp);
		
		}
		
	}
	
	public void addNCardsToHand(int n) {
		
		Player opponent = Card.getBoard().getOpponentPlayer();
		
		if(deck.getDeck().size() == 0 || deck.getDeck().size() < n ) {
			
			Card.getBoard().setWinner(opponent);
			
		} else {
		
		
		for(int i = 0; i < n; i++) {
			
			addCardToHand();
		}
	}
		
	}


	public Phase getPhase() {
		return phase;
	}


	public void setPhase(Phase phase) {
		this.phase = phase;
	}


	public ArrayList<MonsterCard> getMonstersArea() {
		return monstersArea;
	}




	public ArrayList<SpellCard> getSpellArea() {
		return spellArea;
	}



	public Deck getDeck() {
		return deck;
	}


	public ArrayList<Card> getHand() {
		return hand;
	}



	public ArrayList<Card> getGraveyard() {
		return graveyard;
	}
	
	public void removeAllToGraveyard() {
			
		while(this.hand.size() != 0) {
			
			graveyard.add(hand.get(0));
			hand.remove(0).setLocation(Location.GRAVEYARD);
		
		}
		
	}
	
}
