package eg.edu.guc.yugioh.cards;
import eg.edu.guc.yugioh.board.player.*;

public class MonsterCard extends Card {
	
	private int level;
	private int defensePoints;
	private int attackPoints;
	private Mode mode;
	private boolean switchCheck;
	private boolean attackCheck;

	
	public MonsterCard(String name, String description, int level, int attack, int defense) {
		
		super(name,description);
		this.level = level;
		attackPoints = attack;
		defensePoints = defense;
		mode = Mode.DEFENSE;
		switchCheck = false;
		attackCheck = false;
		
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public int getDefensePoints() {
		return defensePoints;
	}


	public void setDefensePoints(int defensePoints) {
		this.defensePoints = defensePoints;
	}


	public int getAttackPoints() {
		return attackPoints;
	}


	public void setAttackPoints(int attackPoints) {
		this.attackPoints = attackPoints;
	}


	public Mode getMode() {
		return mode;
	}


	public void setMode(Mode mode) {
		this.mode = mode;
	}
	
	public void action(MonsterCard Monster) {
		
		Player active = Card.getBoard().getActivePlayer();
		Player opponent = Card.getBoard().getOpponentPlayer();
		int activeLP = active.getLifePoints();
		int opponentLP = opponent.getLifePoints();
		int activeAttack = this.getAttackPoints();
		int opponentAttack = Monster.getAttackPoints();
		int opponentDefense = Monster.getDefensePoints();
		int differenceAttack = activeAttack - opponentAttack;
		int differenceDefense = activeAttack - opponentDefense;
		
		
		if(Monster.getMode() == Mode.ATTACK) {
			
			if(differenceAttack > 0) {
				opponent.getField().removeMonsterToGraveyard(Monster);
				opponent.setLifePoints(opponentLP - differenceAttack);
				

			}
			
			if(differenceAttack == 0) {
				opponent.getField().removeMonsterToGraveyard(Monster);
				active.getField().removeMonsterToGraveyard(this);
			
			}
			
			if(differenceAttack < 0) {
				active.getField().removeMonsterToGraveyard(this);
				active.setLifePoints(activeLP + differenceAttack);
			}
			
		}
		
		if(Monster.getMode() == Mode.DEFENSE) {
			
			if(differenceDefense > 0) {
				opponent.getField().removeMonsterToGraveyard(Monster);
			}
			
			if(differenceDefense < 0) {
				active.setLifePoints(activeLP + differenceDefense);
			}
		}
		
		activeLP = active.getLifePoints();
		opponentLP = opponent.getLifePoints();
		
		if(activeLP <= 0)
			Card.getBoard().setWinner(opponent);
		
		if(opponentLP <= 0)
			Card.getBoard().setWinner(active);
	}
		
		
	
	public void action() {
		
		Player active = Card.getBoard().getActivePlayer();
		Player opponent = Card.getBoard().getOpponentPlayer();
		int activeAttack = this.getAttackPoints();
		int opponentLP = opponent.getLifePoints();
		
		opponent.setLifePoints(opponentLP - activeAttack);
		
		opponentLP = opponent.getLifePoints();
		if(opponentLP <= 0)
			Card.getBoard().setWinner(active);
			
	}
	
	public boolean getSwitchCheck() {
		
		return switchCheck;
		
	}
	
	public void setSwitchCheck(boolean switchCheck) {
		
		this.switchCheck = switchCheck;
		
	}
	
	public boolean getAttackCheck() {
		
		return attackCheck;
	}
	
	public void setAttackCheck(boolean attackCheck) {
		
		this.attackCheck = attackCheck;
		
	}
	

}
