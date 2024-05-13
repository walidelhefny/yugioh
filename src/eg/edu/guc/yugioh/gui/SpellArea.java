package eg.edu.guc.yugioh.gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import eg.edu.guc.yugioh.cards.Card;

public class SpellArea extends JButton {
	
	Card spell;
	public SpellArea (Card spell, ImageIcon x) {
		
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
