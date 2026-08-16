package eg.edu.guc.yugioh.gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import eg.edu.guc.yugioh.cards.Card;

public class MonsterHand extends JButton {
	
	Card monster;
	
	public MonsterHand(Card monster, ImageIcon x) {
			
			super(x);
			this.monster = monster;
		
	}

	public Card getMonster() {
		return monster;
	}

	public void setMonster(Card monster) {
		this.monster = monster;
	}
	
	

}
