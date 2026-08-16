package eg.edu.guc.yugioh.gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import eg.edu.guc.yugioh.cards.Card;

public class SpellHand extends JButton {

	Card spell;
	
	public SpellHand(Card spell, ImageIcon x) {
		
		super(x);
		this.spell = spell;
	}

	public Card getSpell() {
		return spell;
	}

	public void setSpell(Card spell) {
		this.spell = spell;
	}
	
	

}
