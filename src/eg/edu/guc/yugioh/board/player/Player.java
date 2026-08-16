package eg.edu.guc.yugioh.board.player;
import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.cards.*;

import java.io.IOException;
import java.util.ArrayList;

import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.DefenseMonsterAttackException;
import eg.edu.guc.yugioh.exceptions.EmptyFieldException;
import eg.edu.guc.yugioh.exceptions.MissingFieldException;
import eg.edu.guc.yugioh.exceptions.MonsterMultipleAttackException;
import eg.edu.guc.yugioh.exceptions.MultipleMonsterAdditionException;
import eg.edu.guc.yugioh.exceptions.NoMonsterSpaceException;
import eg.edu.guc.yugioh.exceptions.NoSpellSpaceException;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.exceptions.UnknownCardTypeException;
import eg.edu.guc.yugioh.exceptions.UnknownSpellCardException;
import eg.edu.guc.yugioh.exceptions.WrongPhaseException;

public class Player implements Duelist {
	
	private String name;
	private int lifePoints;
	private Field field;
	private boolean summonCheck;
	
	public Player (String name) throws IOException, MissingFieldException, EmptyFieldException, UnknownCardTypeException, UnknownSpellCardException{
		
		this.name = name;
		lifePoints = 8000;
		field = new Field();
		summonCheck = false;
		
	}

	public String getName() {
		return name;
	}


	public int getLifePoints() {
		return lifePoints;
	}

	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}

	public Field getField() {
		return field;
	}
	
	public boolean summonMonster(MonsterCard monster) {
	
		Player active = Card.getBoard().getActivePlayer();
		boolean endCheck = Card.getBoard().getEnd();
		
		if(this.equals(active)) {
			
		if(this.getField().getPhase() == Phase.BATTLE)
			throw new WrongPhaseException("This action can't be done at this phase");
		
		if(summonCheck)
			throw new MultipleMonsterAdditionException("You can't add more than 1 monster per turn");
		
		if(this.getField().getMonstersArea().size() >= 5)
			throw new NoMonsterSpaceException("No free space available at monsters area");
		
		
		
		if(!endCheck && this.getField().getMonstersArea().size() < 5 && monster.getLevel() <= 4 && !summonCheck && this.getField().getPhase() != Phase.BATTLE && monster.getLocation() == Location.HAND) {
		
		this.getField().addMonsterToField(monster, Mode.ATTACK ,false);
		summonCheck = true;
		return true;
		
		
		}
		return false;
		}
		
		return false;
		
		
		
	}
	
	public boolean summonMonster(MonsterCard monster, ArrayList<MonsterCard> sacrifices) {
		
		Player active = Card.getBoard().getActivePlayer();
		boolean endCheck = Card.getBoard().getEnd();
		boolean flag1 = active.getField().helperSummon(monster, sacrifices);
		boolean flag2 = false;
		
		if(this.equals(active)) {
			
			if(this.getField().getPhase() == Phase.BATTLE)
				throw new WrongPhaseException("This action can't be done at this phase");
			
			if(summonCheck)
				throw new MultipleMonsterAdditionException("You can't add more than 1 monster per turn");
			
			if(this.getField().getMonstersArea().size() >= 5)
				throw new NoMonsterSpaceException("No free space available at monsters area");
			
			if(!endCheck && !summonCheck && this.getField().getPhase() != Phase.BATTLE && monster.getLocation() == Location.HAND ) {
				
				this.getField().addMonsterToField(monster, Mode.ATTACK, sacrifices);
				summonCheck = true;
				flag2 = true;
				
			}
			
		}
		
		
		return (flag1 && flag2);
		
		
	}
	
	public boolean setMonster(MonsterCard monster) {
		
		Player active = Card.getBoard().getActivePlayer();
		boolean endCheck = Card.getBoard().getEnd();
		
		if(this.equals(active)) {
		
			if(this.getField().getPhase() == Phase.BATTLE)
				throw new WrongPhaseException("This action can't be done at this phase");
			
			if(summonCheck)
				throw new MultipleMonsterAdditionException("You can't add more than 1 monster per turn");
			
			if(this.getField().getMonstersArea().size() >= 5)
				throw new NoMonsterSpaceException("No free space available at monsters area");
		
			if(!endCheck && !summonCheck && monster.getLocation() == Location.HAND && this.getField().getPhase() != Phase.BATTLE ) {
		
				if(this.getField().getMonstersArea().size() < 5) {
			
					this.getField().addMonsterToField(monster, Mode.DEFENSE, true);
					summonCheck = true;
					return true;
		
				}
		
				return false;
		
			}
		
			return false;
		
	}
		return false;
		
	}
	
	public boolean setMonster(MonsterCard monster, ArrayList<MonsterCard> sacrifices) {
		
		Player active = Card.getBoard().getActivePlayer();
		boolean endCheck = Card.getBoard().getEnd();
		boolean flag1 = active.getField().helperSummon(monster,sacrifices);
		boolean flag2 = false;
		
		if(this.equals(active)) {

			if(this.getField().getPhase() == Phase.BATTLE)
				throw new WrongPhaseException("This action can't be done at this phase");
			
			if(summonCheck)
				throw new MultipleMonsterAdditionException("You can't add more than 1 monster per turn");
			
			if(this.getField().getMonstersArea().size() >= 5)
				throw new NoMonsterSpaceException("No free space available at monsters area");
			
			if(!endCheck && !summonCheck && this.getField().getPhase() != Phase.BATTLE && monster.getLocation() == Location.HAND ) {
				
				this.getField().addMonsterToField(monster, Mode.DEFENSE, sacrifices);
				summonCheck = true;
				flag2 = true;
				
			}
			
		}
		
		
		return (flag1 && flag2);
		
	}
		
		
	public boolean setSpell(SpellCard spell) {
		
		Player active = Card.getBoard().getActivePlayer();
		boolean endCheck = Card.getBoard().getEnd();
		
		if(this.equals(active)) {
			
			if(this.getField().getPhase() == Phase.BATTLE)
				throw new WrongPhaseException("This action can't be done at this phase");
		
			if(this.getField().getSpellArea().size() >= 5)
				throw new NoSpellSpaceException("No free space available at spells area");
		
			if(!endCheck && spell.getLocation() == Location.HAND && active.getField().getPhase() != Phase.BATTLE ) {
		
				if(this.getField().getSpellArea().size() < 5 ) {
			
					this.getField().addSpellToField(spell,null,true);
					return true;
				}
		
				return false;
		
			}
		
			return false;
		
		}
		
		return false;
		
	}
	
	public boolean activateSpell(SpellCard spell, MonsterCard monster) {
		
		Player active = Card.getBoard().getActivePlayer();
		boolean endCheck = Card.getBoard().getEnd();
		
		if(this.equals(active)) {
			
		if(this.getField().getPhase() == Phase.BATTLE)
			throw new WrongPhaseException("This action can't be done at this phase");
		
		if(!endCheck && active.getField().getPhase() != Phase.BATTLE ) {
		
		if(spell.getLocation() == Location.HAND) {
			
			if(this.getField().getSpellArea().size() >= 5)
				throw new NoSpellSpaceException("No free space available at spellss area");
			
			if(this.getField().getSpellArea().size() !=5 ) {
				
				this.getField().addSpellToField(spell,monster,false);
				return true;
			}
		}
		
		else {
			
			if(this.getField().getSpellArea().contains(spell)) {
				
			this.getField().activateSetSpell(spell,monster);
			return true;
			}
			
			}
		
			return false;
		
			}
		
			return false;
		
			}
		
		return false;
	}
	
	public boolean declareAttack(MonsterCard activeMonster, MonsterCard opponentMonster) {
		
		boolean flag = false;
		boolean check = activeMonster.getAttackCheck();
		Player active = Card.getBoard().getActivePlayer();
		boolean endCheck = Card.getBoard().getEnd();
		
		if(this.equals(active)) {
			
			if(this.getField().getPhase() != Phase.BATTLE)
				throw new WrongPhaseException("This action can't be done at this phase");
			
			if(activeMonster.getMode() != Mode.ATTACK)
				throw new DefenseMonsterAttackException("You can't attack with a monster in defense mode");
			
			if(check)
				throw new MonsterMultipleAttackException("You can't attack with this monster more than once");
			
			if(!endCheck && activeMonster.getMode() == Mode.ATTACK && activeMonster.getLocation() == Location.FIELD
					&& opponentMonster.getLocation() == Location.FIELD && !check 
					&& this.getField().getPhase() == Phase.BATTLE) {
				
				activeMonster.action(opponentMonster);
				flag = true;
				activeMonster.setAttackCheck(true);
		
		}
		
	}
		
		return flag;
		
	}
	
	public boolean declareAttack(MonsterCard activeMonster) {
		
		boolean flag = false;
		boolean check = activeMonster.getAttackCheck();
		Player active = Card.getBoard().getActivePlayer();
		Player opponent = Card.getBoard().getOpponentPlayer();
		boolean empty = opponent.getField().getMonstersArea().isEmpty();
		boolean endCheck = Card.getBoard().getEnd();
		
		if(this.equals(active)) {
			
			if(this.getField().getPhase() != Phase.BATTLE)
				throw new WrongPhaseException("This action can't be done at this phase");
			
			if(activeMonster.getMode() != Mode.ATTACK)
				throw new DefenseMonsterAttackException("You can't attack with a monster in defense mode");
			
			if(check)
				throw new MonsterMultipleAttackException("You can't attack with this monster more than once");
			
			if(!endCheck && activeMonster.getMode() == Mode.ATTACK && activeMonster.getLocation() == Location.FIELD && empty && !check
					&& this.getField().getPhase() == Phase.BATTLE) {
				
				activeMonster.action();
				flag = true;
				activeMonster.setAttackCheck(true);
				
				
			}
			
		}
		
		return flag;
		
	}
	
	public void addCardToHand() {
		
		Player active = Card.getBoard().getActivePlayer();
		boolean endCheck = Card.getBoard().getEnd();
		
		if(this.equals(active)) {
			
			if(this.getField().getPhase() == Phase.BATTLE)
				throw new WrongPhaseException("This action can't be done at this phase");
			
				if(!endCheck && active.getField().getPhase() != Phase.BATTLE)
					this.getField().addCardToHand();
				
		}
		

		
	}
	
	public void addNCardsToHand(int n) {
		
		Player active = Card.getBoard().getActivePlayer();
		boolean endCheck = Card.getBoard().getEnd();
		
		if(this.equals(active)) {
			
			if(this.getField().getPhase() == Phase.BATTLE)
				throw new WrongPhaseException("This action can't be done at this phase");
			
				if(!endCheck && active.getField().getPhase() != Phase.BATTLE) 
					this.getField().addNCardsToHand(n);
				
		}
		
		
	}
	
	public void endPhase() {
		
		Player active = Card.getBoard().getActivePlayer();
		boolean endCheck = Card.getBoard().getEnd();
		
		if(this.equals(active) && !endCheck) {
		
		if(this.getField().getPhase() == Phase.MAIN1) 
			this.getField().setPhase(Phase.BATTLE);
		
		else {
			
			if(this.getField().getPhase() == Phase.BATTLE) 
				this.getField().setPhase(Phase.MAIN2); 
			
			else {
				
				if(this.getField().getPhase() == Phase.MAIN2) 
					this.endTurn();
				
			}
			
		}  
	
		}
		
		}
	
	
	public boolean endTurn() {
		
		Player active = Card.getBoard().getActivePlayer();
		boolean endCheck = Card.getBoard().getEnd();
		
		if(this.equals(active) && !endCheck) {
			
			int size = Card.getBoard().getActivePlayer().getField().getMonstersArea().size();
			
			for(int i = 0; i < size; i++) {
				
				Card.getBoard().getActivePlayer().getField().getMonstersArea().get(i).setAttackCheck(false);
				Card.getBoard().getActivePlayer().getField().getMonstersArea().get(i).setSwitchCheck(false);
			}
			
			this.summonCheck = false;
			Card.getBoard().nextPlayer();
			
			return true;
			
		}
		
		return false;
			
	}
	
	public boolean switchMonsterMode(MonsterCard monster) {
		
		boolean check = monster.getSwitchCheck();
		Player active = Card.getBoard().getActivePlayer();
		boolean endCheck = Card.getBoard().getEnd();
		
		if(this.equals(active)) {
			
			if(this.getField().getPhase() == Phase.BATTLE)
				throw new WrongPhaseException("This action can't be done at this phase");
			
				if(!endCheck && monster.getLocation() == Location.FIELD && active.getField().getPhase() != Phase.BATTLE && !check) {
				
					if(monster.getMode() == Mode.ATTACK)
						monster.setMode(Mode.DEFENSE);
					else monster.setMode(Mode.ATTACK);
			
					monster.setSwitchCheck(true);
			
					return true;
			
		}
		
		return false;
		
		}
		
		return false;
		
	}
	
	}
	