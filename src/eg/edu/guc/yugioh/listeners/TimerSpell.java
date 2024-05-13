package eg.edu.guc.yugioh.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import eg.edu.guc.yugioh.board.player.*;
import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.gui.BoardWindow;

public class TimerSpell implements ActionListener {

	private Timer timer;
	private SummonController controller;
	private Board area;
	private SpellCard spell;
	
	public TimerSpell(SummonController controller, SpellCard spell, Board area) {
		
		this.controller = controller;
		this.spell = spell;
		this.area = area;
		timer = new Timer(400, this);
	}
	public void actionPerformed(ActionEvent e) {
		
		try {
		area.getActivePlayer().getField().getSpellArea().remove(spell);
		area.getActivePlayer().activateSpell(spell,controller.getMonsterSpell());
	
		controller.refresh();
		timer.stop();
		
		} catch(Exception ex) {
		
			
			
		}
		
		
	}
	public Timer getTimer() {
		return timer;
	}
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	public SummonController getController() {
		return controller;
	}
	public void setController(SummonController controller) {
		this.controller = controller;
	}
	public Board getArea() {
		return area;
	}
	public void setArea(Board area) {
		this.area = area;
	}
	public SpellCard getSpell() {
		return spell;
	}
	public void setSpell(SpellCard spell) {
		this.spell = spell;
	}
	
	

}
