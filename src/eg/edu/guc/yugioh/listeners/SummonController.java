package eg.edu.guc.yugioh.listeners;
import eg.edu.guc.yugioh.board.*;

import java.util.*;

import eg.edu.guc.yugioh.board.player.*;
import eg.edu.guc.yugioh.cards.*;
import eg.edu.guc.yugioh.cards.spells.*;
import eg.edu.guc.yugioh.exceptions.*;

import javax.swing.*;

import eg.edu.guc.yugioh.gui.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.awt.event.ActionListener;

public class SummonController implements MouseListener {
	
	private BoardWindow board; 
	private StartWindow start;
	private EndWindow end;
	private Board area;
	private Player p1;
	private Player p2;
	private JLabel firstPlayer;
	private JLabel secondPlayer;
	private JButton currentPhase;
	private Boolean firstClick;
	private Boolean secondClick;
	private Boolean thirdClick;
	private int perform;
	private Actions actions;
	private MonsterCard summon;
	private MonsterCard attack;
	private SpellCard spellCard;
	private Boolean summonMonster;
	private Boolean setMonster;
	private Boolean attackMonster;
	private Boolean activateSpell;
	private Boolean spellMonster;
	private ArrayList<MonsterCard> sacrifices;
	private TimerSpell spellTimer;
	private MonsterCard monsterSpell;
	
	
	public SummonController(BoardWindow board, StartWindow start, EndWindow end) {
		
		this.board = board;
		this.start = start;
		this.end = end;
		sacrifices = new ArrayList<MonsterCard> ();
		addActionListenersToButtons();
	}
	
	public void addActionListenersToButtons() {
		
		start.getStartGame().addMouseListener(this);
		board.getEndPhase().addMouseListener(this);
		board.getEndTurn().addMouseListener(this);
		board.getRefreshButton().addMouseListener(this);
		end.getExit().addMouseListener(this);
		end.getRestart().addMouseListener(this);
		
		
	}
	
	public void refresh() throws MissingFieldException, EmptyFieldException, UnknownCardTypeException, UnknownSpellCardException, IOException {
		
		
		
		board.getCurrentPhase().removeAll();
		board.getActiveDeckSize().removeAll();
		board.getOpponentDeckSize().removeAll();
		board.getActiveHand().removeAll();
		board.getOpponentHand().removeAll();
		board.getActiveZone().removeAll();
		board.getOpponentZone().removeAll();
		board.getFieldPanel().removeAll();
		board.getInformation().removeAll();
		board.getInformation().removeAll();
		board.getInformation().removeAll();
		board.getInformation().removeAll();
		board.getInformation().removeAll();
		board.getInformation().removeAll();
		board.getInformation().removeAll();
		board.getInformation().removeAll();
		board.getActiveName().removeAll();
		board.getOpponentName().removeAll();
		firstClick = false;
		secondClick = false;
		thirdClick = false;
		sacrifices.removeAll(sacrifices);
		summon = null;
		attack = null;
		spellCard = null;
		attackMonster = false;
		summonMonster = false;
		spellMonster = false;
		setMonster = false;
		activateSpell = false;
		perform = -1;
		
		for(int w = 0; w < area.getActivePlayer().getField().getGraveyard().size(); w++) {
			
			if(area.getActivePlayer().getField().getGraveyard().get(w) instanceof MonsterCard) {
				MonsterCard monster = (MonsterCard) area.getActivePlayer().getField().getGraveyard().get(w);
				monster.setAttackCheck(false);
			}
			
		}
		
		for(int w = 0; w < area.getOpponentPlayer().getField().getGraveyard().size(); w++) {
			
			if(area.getOpponentPlayer().getField().getGraveyard().get(w) instanceof MonsterCard) {
				MonsterCard monster = (MonsterCard) area.getOpponentPlayer().getField().getGraveyard().get(w);
				monster.setAttackCheck(false);
			}
			
		}
		
		JTextArea activeName = new JTextArea(area.getActivePlayer().getName() + "'s Turn With " + area.getActivePlayer().getLifePoints() + " Life Points Remaining");
		activeName.setFont(new Font("Arial", 0, 17));
		activeName.setForeground(new Color(255, 255, 255));
		JTextArea opponentName = new JTextArea(area.getOpponentPlayer().getName() + " Is Waiting For " + area.getActivePlayer().getName() +" To Play His Turn With " + area.getOpponentPlayer().getLifePoints() + " Life Points Remaining");
		opponentName.setFont(new Font("Arial", 0, 17));
		opponentName.setForeground(new Color(255, 255, 255));
		activeName.setOpaque(false);
		opponentName.setOpaque(false);
		board.getActiveName().add(activeName,BorderLayout.CENTER);
		board.getOpponentName().add(opponentName,BorderLayout.CENTER);
		
		
		String activeDeckSize = String.valueOf(area.getActivePlayer().getField().getDeck().getDeck().size());
		String opponentDeckSize = String.valueOf(area.getOpponentPlayer().getField().getDeck().getDeck().size());
		JButton activeDeck = new JButton(activeDeckSize + " " + "Cards");
		JButton opponentDeck = new JButton(opponentDeckSize + " " + "Cards");
	
		board.getActiveDeckSize().add(activeDeck);
		board.getOpponentDeckSize().add(opponentDeck);
		
		currentPhase.setOpaque(false);
		currentPhase.setContentAreaFilled(false);
		currentPhase.setBorderPainted(false);
		
		board.getCurrentPhase().add(currentPhase);
		
		if(area.getActivePlayer().getLifePoints() < 0) {
			
			area.getActivePlayer().setLifePoints(0);
			
		}
		
		if(area.getOpponentPlayer().getLifePoints() < 0) {
			
			area.getOpponentPlayer().setLifePoints(0);
			
		}

		JLabel firstPoints = new JLabel (String.valueOf(p1.getLifePoints()));
		JLabel secondPoints = new JLabel (String.valueOf(p2.getLifePoints()));
		firstPoints.setFont(new Font("Arial", 0, 20));
		firstPoints.setForeground(new Color(168, 15, 15));
		secondPoints.setFont(new Font("Arial", 0, 20));
		secondPoints.setForeground(new Color(168, 15, 15));
		
		board.getInformation().add(board.getPlayerA());
		board.getInformation().add(firstPlayer);
		board.getInformation().add(board.getLifePointsA());
		board.getInformation().add(firstPoints);
		board.getInformation().add(board.getPlayerB());
		board.getInformation().add(secondPlayer);
		board.getInformation().add(board.getLifePointsB());
		board.getInformation().add(secondPoints);
		
		
		Player active = area.getActivePlayer();
		Player opponent = area.getOpponentPlayer();
		
		int activeHandSize = active.getField().getHand().size();
		int opponentHandSize = opponent.getField().getHand().size();
		
		board.getActiveHand().setLayout(new FlowLayout());
		board.getOpponentHand().setLayout(new FlowLayout());
		
		for(int i = 0; i < activeHandSize; i++) {
			
			String name = active.getField().getHand().get(i).getName();
			Image img = new ImageIcon(name + ".png").getImage();
			Image newImg = img.getScaledInstance( 85, 85,  java.awt.Image.SCALE_SMOOTH );
			ImageIcon finalImg = new ImageIcon(newImg);
			
			if(active.getField().getHand().get(i) instanceof MonsterCard) {
				
			MonsterHand monster = new MonsterHand(active.getField().getHand().get(i),finalImg);
			monster.setOpaque(false);
			monster.setContentAreaFilled(false);
			monster.setBorderPainted(false);
			monster.addMouseListener(this);
			monster.setPreferredSize(new Dimension(85,85));
			board.getActiveHand().add(monster); 
			
			
			} else {
				
				SpellHand spell = new SpellHand(active.getField().getHand().get(i),finalImg);
				spell.setOpaque(false);
				spell.setContentAreaFilled(false);
				spell.setBorderPainted(false);
				spell.addMouseListener(this);
				spell.setPreferredSize(new Dimension(85,85));
				board.getActiveHand().add(spell); 
			
			}
		}
		
		board.getActiveHand().validate();
		board.getActiveHand().revalidate();
		board.getActiveHand().repaint();
		board.getActiveScroller().getViewport().add(board.getActiveHand(),ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		board.getActiveScroller().setOpaque(false);
		board.getActiveScroller().getViewport().setOpaque(false);
		
		
		for(int j = 0; j < opponentHandSize; j++) {
			
			Image img = new ImageIcon("Card Back.png").getImage();
			Image newImg = img.getScaledInstance( 85, 85,  java.awt.Image.SCALE_SMOOTH );
			ImageIcon finalImg = new ImageIcon(newImg);
			
			if(opponent.getField().getHand().get(j) instanceof MonsterCard) {
				
				
				
				MonsterHand monster = new MonsterHand(opponent.getField().getHand().get(j),finalImg);
				monster.setOpaque(false);
				monster.setContentAreaFilled(false);
				monster.setBorderPainted(false);
				monster.addMouseListener(this);
				monster.setPreferredSize(new Dimension(85,85));
				board.getOpponentHand().add(monster); 
				
				
			} else {
				
				SpellHand spell = new SpellHand(opponent.getField().getHand().get(j),finalImg);
				spell.setOpaque(false);
				spell.setContentAreaFilled(false);
				spell.setBorderPainted(false);
				spell.addMouseListener(this);
				spell.setPreferredSize(new Dimension(85,85));
				board.getOpponentHand().add(spell); 
				
				
			}
		}
		
		board.getOpponentHand().validate();
		board.getOpponentHand().revalidate();
		board.getOpponentHand().repaint();
		board.getOpponentScroller().getViewport().add(board.getOpponentHand(),ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		board.getOpponentScroller().setOpaque(false);
		board.getOpponentScroller().getViewport().setOpaque(false);
		
		ArrayList<MonsterCard> activeMonsters = active.getField().getMonstersArea();
		ArrayList<SpellCard> activeSpells = active.getField().getSpellArea();
		ArrayList<MonsterCard> opponentMonsters = opponent.getField().getMonstersArea();
		ArrayList<SpellCard> opponentSpells = opponent.getField().getSpellArea();
		
		
		for(int a = 0; a < activeMonsters.size(); a++) {
			
			String name = active.getField().getMonstersArea().get(a).getName();
			int x = 0;
			Image img = new ImageIcon(name + ".png").getImage();
			Image newImg = img.getScaledInstance( 83, 80,  java.awt.Image.SCALE_SMOOTH );
			ImageIcon finalImg = new ImageIcon(newImg);
			
			MonsterArea monster;
			if(activeMonsters.get(a).getMode() == Mode.ATTACK)	 {
			 monster = new MonsterArea(activeMonsters.get(a), finalImg);
			
			} else {
				
				monster = new MonsterArea(activeMonsters.get(a), new ImageIcon("Flip.png"));
			}
			
			monster.setOpaque(false);
			monster.setContentAreaFilled(false);
			monster.setBorderPainted(false);
			monster.addMouseListener(this);
			monster.setPreferredSize(new Dimension(40,181));
			board.getActiveZone().add(monster); 
			
			
			
		}
			
		
		for(int c = 0; c < 5 - activeMonsters.size(); c++) {
			
			JButton monster = new JButton();
			monster.setOpaque(false);
			monster.setContentAreaFilled(false);
			monster.setBorderPainted(false);
			board.getActiveZone().add(monster);
		}
		
		
		
		ArrayList<Card> activeGraveyard = active.getField().getGraveyard();
		int activeGraveyardSize = activeGraveyard.size();
		
		
		if(activeGraveyardSize != 0) {
			
			String name = activeGraveyard.get(activeGraveyardSize - 1).getName();
			Image img = new ImageIcon(name + ".png").getImage();
			Image newImg = img.getScaledInstance( 83, 80,  java.awt.Image.SCALE_SMOOTH );
			ImageIcon finalImg = new ImageIcon(newImg);
		
		Card activeGraveyardCard = (Card) activeGraveyard.get(activeGraveyardSize-1);
		
		if(activeGraveyardCard instanceof MonsterCard) {
			
		MonsterArea monsterG = new MonsterArea(activeGraveyardCard,finalImg);
		monsterG.setPreferredSize(new Dimension(85,85));
		monsterG.setOpaque(false);
		monsterG.setContentAreaFilled(false);
		monsterG.setBorderPainted(false);
		board.getActiveZone().add(monsterG);
		monsterG.addMouseListener(this);
		
		}
		
		else {
			
			SpellArea spellG = new SpellArea(activeGraveyardCard, finalImg);
			spellG.setPreferredSize(new Dimension(85,85));
			spellG.setOpaque(false);
			spellG.setContentAreaFilled(false);
			spellG.setBorderPainted(false);
			board.getActiveZone().add(spellG);
			spellG.addMouseListener(this);
		}
		
		}
		
		else  
		{	board.getActiveZone().add(new JButton(new ImageIcon("Graveyard.png")));  }
			
		for(int b = 0; b < activeSpells.size(); b++) {
				
			String name = active.getField().getSpellArea().get(b).getName();
			int x = 0;
			Image img = new ImageIcon(name + ".png").getImage();
			Image newImg = img.getScaledInstance( 83, 80,  java.awt.Image.SCALE_SMOOTH );
			ImageIcon finalImg = new ImageIcon(newImg);
				
					
				SpellArea spell;
				
				if(activeSpells.get(b).isHidden()) {
					
				img = new ImageIcon("Card Back.png").getImage();
				newImg = img.getScaledInstance(83, 80, java.awt.Image.SCALE_SMOOTH);
				finalImg = new ImageIcon(newImg);
				
				spell = new SpellArea(activeSpells.get(b), finalImg);
				
				} else {
					
					spell = new SpellArea(activeSpells.get(b), finalImg);
				}
				spell.setOpaque(false);
				spell.setContentAreaFilled(false);
				spell.setBorderPainted(false);
				spell.addMouseListener(this);
				spell.setPreferredSize(new Dimension(40,181));
				board.getActiveZone().add(spell); 
				
			
			}
		
		for(int d = 0; d < 5 - activeSpells.size(); d++) {
			
			JButton spell = new JButton();
			spell.setOpaque(false);
			spell.setContentAreaFilled(false);
			spell.setBorderPainted(false);
			board.getActiveZone().add(spell);
		}
		
		JButton deck1 = new JButton(new ImageIcon("Main_Deck.png"));
		board.getActiveZone().add(deck1);
		deck1.setOpaque(false);
		deck1.setContentAreaFilled(false);
		deck1.setBorderPainted(false);
		


		for(int g = 0; g < opponentSpells.size(); g++) {

			String name = opponent.getField().getSpellArea().get(g).getName();
			int x = 0;
			Image img = new ImageIcon(name + ".png").getImage();
			Image newImg = img.getScaledInstance( 83, 80,  java.awt.Image.SCALE_SMOOTH );
			ImageIcon finalImg = new ImageIcon(newImg);

			SpellArea spell;
			
			if(opponentSpells.get(g).isHidden()) {
				
				img = new ImageIcon("Card Back.png").getImage();
				newImg = img.getScaledInstance(83, 80, java.awt.Image.SCALE_SMOOTH);
				finalImg = new ImageIcon(newImg);
				
			spell = new SpellArea(opponentSpells.get(g), finalImg);
			
			} else {
				
				spell = new SpellArea(opponentSpells.get(g),finalImg);
			}
			spell.setOpaque(false);
			spell.setContentAreaFilled(false);
			spell.setBorderPainted(false);
			spell.addMouseListener(this);
			spell.setPreferredSize(new Dimension(40,181));
			board.getOpponentZone().add(spell);


		}

		for(int h = 0; h < 5 - opponentSpells.size(); h++) {

			JButton spell = new JButton();
			spell.setOpaque(false);
			spell.setContentAreaFilled(false);
			spell.setBorderPainted(false);
			board.getOpponentZone().add(spell);
		}
		
		JButton deck2 = new JButton(new ImageIcon("Main_Deck.png"));
		board.getOpponentZone().add(deck2);
		deck2.setOpaque(false);
		deck2.setContentAreaFilled(false);
		deck2.setBorderPainted(false);
		

		for(int e = 0; e < opponentMonsters.size(); e++) {

			String name = opponent.getField().getMonstersArea().get(e).getName();
			int x = 0;
			Image img = new ImageIcon(name + ".png").getImage();
			Image newImg = img.getScaledInstance( 83, 80,  java.awt.Image.SCALE_SMOOTH );
			ImageIcon finalImg = new ImageIcon(newImg);


			MonsterArea monster;
			if(opponentMonsters.get(e).getMode() == Mode.ATTACK)	 {
			 monster = new MonsterArea(opponentMonsters.get(e), finalImg);
			
			} else {
				
				monster = new MonsterArea(opponentMonsters.get(e), new ImageIcon("Flip.png"));
			}
			
			monster.setOpaque(false);
			monster.setContentAreaFilled(false);
			monster.setBorderPainted(false);
			monster.addMouseListener(this);
			monster.setPreferredSize(new Dimension(40,181));
			board.getOpponentZone().add(monster); 
		

		}


		for(int f = 0; f < 5 - opponentMonsters.size(); f++) {

			JButton monster = new JButton();
			monster.setOpaque(false);
			monster.setContentAreaFilled(false);
			monster.setBorderPainted(false);
			board.getOpponentZone().add(monster);
		}
		
		
		ArrayList<Card> opponentGraveyard = opponent.getField().getGraveyard();
		int opponentGraveyardSize = opponentGraveyard.size();
		
		
		if(opponentGraveyardSize != 0) {
			
			String name = opponentGraveyard.get(opponentGraveyardSize - 1).getName();
			Image img = new ImageIcon(name + ".png").getImage();
			Image newImg = img.getScaledInstance( 83, 80,  java.awt.Image.SCALE_SMOOTH );
			ImageIcon finalImg = new ImageIcon(newImg);
		
		Card opponentGraveyardCard = (Card) opponentGraveyard.get(opponentGraveyardSize-1);
		
		if(opponentGraveyardCard instanceof MonsterCard) {
			
		MonsterArea monsterG = new MonsterArea(opponentGraveyardCard, finalImg);
		monsterG.setOpaque(false);
		monsterG.setContentAreaFilled(false);
		monsterG.setBorderPainted(false);
		monsterG.setPreferredSize(new Dimension(85,85));
		board.getOpponentZone().add(monsterG);
		monsterG.addMouseListener(this);
		
		}
		
		else 
			
			{
			SpellArea spellG = new SpellArea(opponentGraveyardCard, finalImg);
			spellG.setPreferredSize(new Dimension(85,85));
			spellG.setOpaque(false);
			spellG.setContentAreaFilled(false);
			spellG.setBorderPainted(false);
			board.getOpponentZone().add(spellG);
			spellG.addMouseListener(this);
			}
		
		} else  

			{ board.getOpponentZone().add(new JButton(new ImageIcon("Graveyard.png"))); } 
		
		board.getFieldPanel().add(board.getOpponentZone());
		board.getFieldPanel().add(board.getActiveZone());
		
		board.getActiveZone().revalidate();
		board.getActiveZone().repaint();
		board.getOpponentZone().revalidate();
		board.getOpponentZone().repaint();
		board.getActiveHand().revalidate();
		board.getActiveHand().repaint();
		board.getOpponentHand().revalidate();
		board.getOpponentHand().repaint();
		board.getFieldPanel().revalidate();
		board.getFieldPanel().repaint();
		board.getCurrentPhase().revalidate();
		board.getCurrentPhase().repaint();
		board.revalidate();
		board.repaint();
		
		if(area.getWinner() != null) {
			
			JOptionPane.showMessageDialog(null,"The Winner is" + " " + area.getWinner().getName());
			board.setVisible(false);
			end.setVisible(true);
			
			return;
		}
		
		
	}

	


	@Override
	public void mouseClicked(MouseEvent e) {
		
	
		try {
			
			if(e.getSource() == board.getRefreshButton()) {
				
				refresh();
				return;
			}
			
			if(e.getSource() == end.getRestart()) {
				
				
				end.setVisible(false);
				YuGiOh x = new YuGiOh();
				
			}
			
			if(e.getSource() == end.getExit()) {
				
				
				System.exit(0);
				
			}
			
			String textA = (start.getFieldA().getText());
			String textB = (start.getFieldB().getText());
			
			if(e.getSource() == start.getStartGame()) {
				
				if(textA.length() == 0 || textB.length() == 0) {
					
					JOptionPane.showMessageDialog(null,"Please Enter The Names of Both Players");
					return;
				}
				
				start.setVisible(false);
				//start.getClip().stop();
				start.getLoading().setVisible(true);
				TimerLoading timer = new TimerLoading(start,board);
				timer.getTimer().start();
				board = timer.getBoard();
			
				
				try {
					
					p1 = new Player(textA);
					p2 = new Player(textB);
				} catch (MissingFieldException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (EmptyFieldException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnknownCardTypeException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnknownSpellCardException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				area = new Board();
				area.startGame(p1,p2);
				
				
				firstPlayer = new JLabel (p1.getName());
				secondPlayer = new JLabel (p2.getName());
				
				
				firstPlayer.setFont(new Font("Arial", 0, 20));
				firstPlayer.setForeground(new Color(255, 204, 51));
				secondPlayer.setFont(new Font("Arial", 0, 20));
				secondPlayer.setForeground(new Color(255, 204, 51));
				
				currentPhase = new JButton(new ImageIcon ("MainPhase.png"));
				
				refresh();
				
				JOptionPane.showMessageDialog(null,"Active Player is:" + " " + area.getActivePlayer().getName());
				
				return;
				
			}
			
				
		
			if(e.getSource() == board.getEndPhase()) {
				
				Player active = area.getActivePlayer();
				
				if(active.getField().getPhase() == Phase.MAIN1) 
					currentPhase = new JButton(new ImageIcon ("BattlePhase.png"));
				 else { 	
				
				if(active.getField().getPhase() == Phase.BATTLE) 
					currentPhase = new JButton(new ImageIcon ("MainPhase2.png"));
				
				else {
				
				if(active.getField().getPhase() == Phase.MAIN2) {
					currentPhase = new JButton(new ImageIcon ("MainPhase.png"));
					
				}
				
			   }
				
			 }
				
				
				active.endPhase();
				
				refresh();
				return;
			
			
		}
			
			if(e.getSource() == board.getEndTurn()) {
				
				Player active = area.getActivePlayer();
				
				currentPhase = new JButton(new ImageIcon ("MainPhase.png"));
				
				active.endTurn();
				
				refresh();
				return;
			}
			
		if(firstClick == false) {
			
			
			if(e.getSource() instanceof SpellArea) {
				
				SpellArea spell1 = (SpellArea) e.getSource();
				SpellCard spell = (SpellCard) spell1.getSpell();
				
				if(area.getActivePlayer().getField().getSpellArea().contains(spell)) {
					
					actions = new Actions(new String []{"Activate Spell"});
					
					if(area.getActivePlayer().getField().getPhase() == Phase.BATTLE)
						throw new WrongPhaseException("This action can't be done at this phase");
					
					if(area.getActivePlayer().getField().getSpellArea().size() >= 5)
						throw new NoSpellSpaceException("No free space available at spellss area");
					
				perform = actions.getAction();
				
				if(perform == 0) {
					
					switch(spell.getName()) {
					
					case "Change Of Heart":
					case "Mage Power": { 
						area.getActivePlayer().getField().getSpellArea().remove(spell);
						spell.setHidden(false);
						spellTimer = new TimerSpell(this,spell,area);
						area.getActivePlayer().getField().getSpellArea().add(spell);
						refresh();
						spellCard = spell; spellMonster = true; firstClick = true; JOptionPane.showMessageDialog(null,"Please choose 1 Monster To Cast The Spell On");  break; }
					default: {  area.getActivePlayer().getField().getSpellArea().remove(spell); spell.setHidden(false); spellTimer = new TimerSpell(this,spell,area); area.getActivePlayer().getField().getSpellArea().add(spell); refresh(); spellTimer.getTimer().start();return; } 
						
					}
					
				}
				
			} else { JOptionPane.showMessageDialog(null,"Invalid Move! Can't Access This Area"); refresh(); return;}
				
			} 
			
			
			if(e.getSource() instanceof SpellHand) {
				
				
				
				SpellHand spell1 = (SpellHand) e.getSource();
				SpellCard spell = (SpellCard) spell1.getSpell();
				
				
				if(area.getActivePlayer().getField().getHand().contains(spell)) {
					
					actions = new Actions(new String []{"Activate Spell","Set Spell"});
					
				perform = actions.getAction();
				
				if(area.getActivePlayer().getField().getPhase() == Phase.BATTLE)
					throw new WrongPhaseException("This action can't be done at this phase");
				
				if(area.getActivePlayer().getField().getSpellArea().size() >= 5)
					throw new NoSpellSpaceException("No free space available at spellss area");
				
				if(perform == 1) {
					
					area.getActivePlayer().setSpell(spell);
					refresh();
					return;
					
				}
				
				if(perform == 0) {
					
					
					
					switch(spell.getName()) {
					
					case "Change Of Heart": { 
						if(area.getOpponentPlayer().getField().getMonstersArea().size() < 1) {
							
							 JOptionPane.showMessageDialog(null,"Invalid Move! Can't Activate This Spell. Insufficient Number of Monsters on Field"); refresh(); return; 
							 
						}
					}
					case "Mage Power": { 
						if(area.getOpponentPlayer().getField().getMonstersArea().size() < 1 && area.getActivePlayer().getField().getMonstersArea().size() < 1) {
							
							 JOptionPane.showMessageDialog(null,"Invalid Move! Can't Activate This Spell. Insufficient Number of Monsters on Field"); refresh(); return; 
							 
						}
						area.getActivePlayer().getField().getHand().remove(spell);
						refresh();
						spell.setHidden(false); spellTimer = new TimerSpell(this,spell,area); area.getActivePlayer().getField().getSpellArea().add(spell); refresh();
						spellCard = spell; spellMonster = true; firstClick = true; JOptionPane.showMessageDialog(null,"Please choose 1 monster to cast the spell on");  break; }
					default: { area.getActivePlayer().getField().getHand().remove(spell); refresh(); spell.setHidden(false); spellTimer = new TimerSpell(this,spell,area); area.getActivePlayer().getField().getSpellArea().add(spell); refresh(); spellTimer.getTimer().start();  
					 return; } 
						
					}
				}
			} else { JOptionPane.showMessageDialog(null,"Invalid Move! Can't Access Opponent Hand"); refresh(); return;}
				
			}
				
			if(e.getSource() instanceof MonsterHand) {
				
			MonsterHand monster1 = (MonsterHand) e.getSource();
			MonsterCard monster = (MonsterCard) monster1.getMonster();
			
			if(area.getActivePlayer().getField().getHand().contains(monster))  {
				
				actions = new Actions(new String []{"Summon Monster","Set Monster"});
				perform = actions.getAction();
				summon = monster;
				
			if(perform == 0) {
				
				summonMonster = true;
				
				if(summon.getLevel() <= 4) {
					area.getActivePlayer().summonMonster(summon);
					refresh();
					return;
				}
				
				if(summon.getLevel() == 5 || summon.getLevel() == 6) {
					
					if(area.getActivePlayer().getField().getMonstersArea().size() < 1) {
						
						 JOptionPane.showMessageDialog(null,"Invalid Move! Can't Summon This Monster. Insufficient Monsters On Your Monsters Area"); refresh(); return; 
						 
					}
					
					firstClick = true;
					JOptionPane.showMessageDialog(null,"Please choose 1 monster to sacrifice");
				}
				
				if(summon.getLevel() == 7 || summon.getLevel() == 8) {
					
					if(area.getActivePlayer().getField().getMonstersArea().size() <= 1) {
						
						 JOptionPane.showMessageDialog(null,"Invalid Move! Can't Summon This Monster. Insufficient Monsters On Your Monsters Area"); refresh(); return; 
						 
					}
					
					firstClick = true;
					JOptionPane.showMessageDialog(null,"Please choose 2 monsters to sacrifice");
					
				}
				
			}
				
				if(perform == 1) {
					
					summon = monster;
					setMonster = true;
					
					if(summon.getLevel() <= 4) {
						area.getActivePlayer().setMonster(summon);
						refresh();
						return;
					}
					
					if(summon.getLevel() == 5 || summon.getLevel() == 6) {
						
						if(area.getActivePlayer().getField().getMonstersArea().size() < 1) {
							
							 JOptionPane.showMessageDialog(null,"Invalid Move! Can't Summon This Monster. Insufficient Monsters On Your Monsters Area"); refresh(); return; 
							 
						}
						
						firstClick = true;
						JOptionPane.showMessageDialog(null,"Please choose 1 monster to sacrifice");
					}
					
					if(summon.getLevel() == 7 || summon.getLevel() == 8) {
						
						if(area.getActivePlayer().getField().getMonstersArea().size() <= 1) {
							
							 JOptionPane.showMessageDialog(null,"Invalid Move! Can't Summon This Monster. Insufficient Monsters On Your Monsters Area"); refresh(); return; 
							 
						}
						
						firstClick = true;
						JOptionPane.showMessageDialog(null,"Please choose 2 monsters to sacrifice");
						
					}
				
			}
			
		} else { JOptionPane.showMessageDialog(null,"Invalid Move! Can't Access Opponent Hand"); refresh(); return;}
			
		}
			
			
			if(e.getSource() instanceof MonsterArea) {
				
				MonsterArea monster1 = (MonsterArea) e.getSource();
				MonsterCard monster = (MonsterCard) monster1.getMonster();
				
				
				if(area.getActivePlayer().getField().getMonstersArea().contains(monster)) {
					
				actions = new Actions(new String []{"Attack Opponent","Switch Position"});
				perform = actions.getAction();	
				
				if(perform == 1) {
					
					if(area.getActivePlayer().switchMonsterMode(monster) == false)
						JOptionPane.showMessageDialog(null,"Invalid Move! Can't Switch The Monster Mode Twice");
					
					refresh();
					return;
				}
				
				if(perform == 0) {
					
					if(area.getOpponentPlayer().getField().getMonstersArea().size() == 0) {
						
						area.getActivePlayer().declareAttack(monster);
						refresh();
						return;
						
					}
					
					if(area.getOpponentPlayer().getField().getMonstersArea().size() > 0) {
						
						firstClick = true;
						attackMonster = true;
						attack = monster;
						JOptionPane.showMessageDialog(null,"Please choose 1 monster to attack");
						
					}
				}
				
			} else { JOptionPane.showMessageDialog(null,"Invalid Move! Can't Access This Area"); refresh(); return;}
				
		} 
			
		} else {
		
			if(secondClick == false) {
				
				if(e.getSource() instanceof MonsterHand || e.getSource() instanceof SpellArea || e.getSource() instanceof SpellHand) {
					
					if(attackMonster == true) {
					 JOptionPane.showMessageDialog(null,"Invalid Move! Can't Attack This Card. Please Try Attacking Again!"); refresh(); return;  }
					 
					 else
						 
						 if(spellMonster == true) {
							// if(attackMonster == true) 
							 area.getActivePlayer().getField().getHand().add(spellCard); area.getActivePlayer().getField().getSpellArea().remove(spellCard); refresh();
								 JOptionPane.showMessageDialog(null,"Invalid Move! Can't Choose This Card. Please Try Activating The Spell Again!"); refresh(); return;
							
							 
						 } else
							 
					 JOptionPane.showMessageDialog(null,"Invalid Move! Can't Sacrifice This Card. Please Try Summoning Again!"); refresh(); return;  
				}
				
			if(e.getSource() instanceof MonsterArea) {
			
			MonsterArea monster1 = (MonsterArea) e.getSource();
			MonsterCard monster = (MonsterCard) monster1.getMonster();
			sacrifices.add(monster);
			
			if(spellMonster == true) {
				
				
				if(spellCard.getName().equals("Change Of Heart")) {
					
					if(area.getOpponentPlayer().getField().getMonstersArea().contains(monster)) {
						
						monsterSpell = monster;
						spellTimer.getTimer().start();
						refresh();
						return;
						
					} else { area.getActivePlayer().getField().getHand().add(spellCard); area.getActivePlayer().getField().getSpellArea().remove(spellCard); refresh(); JOptionPane.showMessageDialog(null,"Invalid Move! Can't Choose This Card To Cast The Spell. Try Activating The Spell Again"); refresh(); return;}
				
				} else {
				
				monsterSpell = monster;
				spellTimer.getTimer().start();
				refresh();
				return;
				
				}
			}
			
			if(attackMonster == true) {
				
				
				if(area.getOpponentPlayer().getField().getMonstersArea().contains(monster)) {
				
				area.getActivePlayer().declareAttack(attack,monster);
				refresh();
				return;
				
				} else { JOptionPane.showMessageDialog(null,"Invalid Move! Can't Attack This Card"); refresh(); return;}
			}
			
			if(summonMonster == true) {
				
				
				if(area.getActivePlayer().getField().getMonstersArea().contains(monster)) {
				
				if(summon.getLevel() == 5 || summon.getLevel() == 6) {
					
					
					area.getActivePlayer().summonMonster(summon,sacrifices);
					refresh();
					return;
				}
				
				if(summon.getLevel() == 7 || summon.getLevel() == 8) {
				
				
					secondClick = true;
					JOptionPane.showMessageDialog(null,"Please choose the second monster to sacrifice");
					
				} 
				
			} else { JOptionPane.showMessageDialog(null,"Invalid Move! Can't Sacrifice This Card. Try Summoning The Monster Again!"); refresh(); return;}
			
			} if(setMonster == true ) {
				
				if(area.getActivePlayer().getField().getMonstersArea().contains(monster)) {
				
				if(summon.getLevel() == 5 || summon.getLevel() == 6) {
					
					area.getActivePlayer().setMonster(summon,sacrifices);
					refresh();
					return;
				}
				
				if(summon.getLevel() == 7 || summon.getLevel() == 8) {
				
					secondClick = true;
					JOptionPane.showMessageDialog(null,"Please choose the second monster to sacrifice");
					
				}
			} else { JOptionPane.showMessageDialog(null,"Invalid Move! Can't Sacrifice This Card. Try Setting The Monster Again!"); refresh(); return;}
			
			}
			
			}
			
				
			} else {
			
			if(thirdClick == false) {
				
				if(e.getSource() instanceof MonsterHand || e.getSource() instanceof SpellArea || e.getSource() instanceof SpellHand) {
					
					 JOptionPane.showMessageDialog(null,"Invalid Move! Can't Sacrifice This Card. Please Try Playing The Monster Again!"); refresh(); return; 
					 
				}
				
				
				if(e.getSource() instanceof MonsterArea) {
				
				MonsterArea monster1 = (MonsterArea) e.getSource();
				MonsterCard monster = (MonsterCard) monster1.getMonster();
				sacrifices.add(monster);
				
				if(area.getActivePlayer().getField().getMonstersArea().contains(monster)) {
				
				if(summonMonster == true) {
					
						area.getActivePlayer().summonMonster(summon,sacrifices);
						refresh();
						return;
					
				
				} if(setMonster == true ) {
						
						area.getActivePlayer().setMonster(summon,sacrifices);
						refresh();
						return;
				}
				
				} else { JOptionPane.showMessageDialog(null,"Invalid Move! Can't Sacrifice This Monster. Please Try Playing The Monster Again!"); refresh(); return;}
				
				
			}
				
			}
			
			}
			
		}
			
	} 
		
		catch(NoMonsterSpaceException ex1) {

			JOptionPane.showMessageDialog(null,ex1.getMessage());
		}
		
		catch(NoSpellSpaceException ex2) {
			
			JOptionPane.showMessageDialog(null,ex2.getMessage());
			
		}
		
		catch(WrongPhaseException ex3) {
			
			JOptionPane.showMessageDialog(null,ex3.getMessage());
		}
		
		catch(MultipleMonsterAdditionException ex4) {
			
			JOptionPane.showMessageDialog(null,ex4.getMessage());
		}
		
		catch(MonsterMultipleAttackException ex5) {
			
			JOptionPane.showMessageDialog(null,ex5.getMessage());
		}
		
		catch(DefenseMonsterAttackException ex6) {
			
			JOptionPane.showMessageDialog(null,ex6.getMessage());
			
		}
		 catch(Exception ex) {
		
			 ex.printStackTrace();
			 JOptionPane.showMessageDialog(null,"Something Went Wrong!");
		
	}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		try { 
			

		if(e.getSource() instanceof MonsterHand) {
			
			MonsterHand monster1 = (MonsterHand) e.getSource();
			MonsterCard monster = (MonsterCard) monster1.getMonster();
			String name = monster.getName();
			
			if(area.getOpponentPlayer().getField().getHand().contains(monster)) {
				
				return;
				
			}
			
			
			board.getDescription().removeAll();
			board.getDescription().add(new JButton(new ImageIcon (name+".png")));
			board.getDescription().revalidate();
			board.getDescription().repaint();
			board.getCardInfo().removeAll();
			String attack = "Attack Points: " + monster.getAttackPoints();
			String defense = "Defense Points: " + monster.getDefensePoints();
			String conc = attack + "\n" + defense;
			JTextArea power = new JTextArea(conc);
			power.setOpaque(false);
			power.setLineWrap(true);
			power.setWrapStyleWord(true);
			power.setFont(new Font("Arial", 0, 15)); 
			power.setForeground(new Color(255, 255, 255));
			board.getCardInfo().add(power,BorderLayout.NORTH);
			board.getCardInfo().revalidate();
			board.getCardInfo().repaint();
			return;
			
		}
		
		if(e.getSource() instanceof SpellHand) {
			
			SpellHand spell1 = (SpellHand) e.getSource();
			SpellCard spell = (SpellCard) spell1.getSpell();
			String name = spell.getName();
			
			if(area.getOpponentPlayer().getField().getHand().contains(spell)) {
				
				return;
				
			}
			
		
			board.getDescription().removeAll();
			board.getDescription().add(new JButton(new ImageIcon (name+".png")));
			board.getDescription().revalidate();
			board.getDescription().repaint();
			board.getCardInfo().removeAll();
			JTextArea description = new JTextArea(spell.getDescription());
			description.setFont(new Font("Arial", 0, 15)); 
			description.setForeground(new Color(255, 255, 255));
			description.setLineWrap(true);
	        description.setWrapStyleWord(true);
	        description.setOpaque(false);
	        description.setEditable(false);
			board.getCardInfo().add(description);
			board.getCardInfo().revalidate();
			board.getCardInfo().repaint();
			return;
			
		}
		
		if(e.getSource() instanceof MonsterArea) {
			
			MonsterArea monster1 = (MonsterArea) e.getSource();
			MonsterCard monster = (MonsterCard) monster1.getMonster();
			String name = monster.getName();
		
			if(area.getOpponentPlayer().getField().getMonstersArea().contains(monster)) {
				
				if(monster.getMode() == Mode.DEFENSE)
				return;
				
			}

			board.getDescription().removeAll();
			board.getDescription().add(new JButton(new ImageIcon (name+".png")));
			board.getDescription().revalidate();
			board.getDescription().repaint();
			board.getCardInfo().removeAll();
			String attack = "Attack Points: " + monster.getAttackPoints();
			String defense = "Defense Points: " + monster.getDefensePoints();
			String conc = attack + "\n" + defense;
			JTextArea power = new JTextArea(conc);
			power.setOpaque(false);
			power.setLineWrap(true);
			power.setWrapStyleWord(true);
			power.setFont(new Font("Arial", 0, 15)); 
			power.setForeground(new Color(255, 255, 255));
			board.getCardInfo().add(power,BorderLayout.NORTH);
			board.getCardInfo().revalidate();
			board.getCardInfo().repaint();
			return;
		}
		
		if(e.getSource() instanceof SpellArea) {
			
			SpellArea spell1 = (SpellArea) e.getSource();
			SpellCard spell = (SpellCard) spell1.getSpell();
			String name = spell.getName();
			
			if(area.getOpponentPlayer().getField().getSpellArea().contains(spell)) {
				
				return;
				
			}
			
			
			board.getDescription().removeAll();
			board.getDescription().add(new JButton(new ImageIcon (name+".png")));
			board.getDescription().revalidate();
			board.getDescription().repaint();
			board.getCardInfo().removeAll();
			JTextArea description = new JTextArea(spell.getDescription());
			description.setFont(new Font("Arial", 0, 15)); 
			description.setForeground(new Color(255, 255, 255));
			description.setLineWrap(true);
	        description.setWrapStyleWord(true);
	        description.setOpaque(false);
	        description.setEditable(false);
			board.getCardInfo().add(description);
			board.getCardInfo().revalidate();
			board.getCardInfo().repaint();
			return;
		}
		
		
		
	
		}  catch(Exception ex) {
			
			
		}
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		board.getDescription().removeAll();
		board.getCardInfo().removeAll();
		board.getDescription().add(new JButton(new ImageIcon ("Card Back.png")));
		board.getDescription().revalidate();
		board.getDescription().repaint();
		board.getCardInfo().revalidate();
		board.getCardInfo().repaint();
		return;
	}

	public MonsterCard getMonsterSpell() {
		return monsterSpell;
	}

	public void setMonsterSpell(MonsterCard monsterSpell) {
		this.monsterSpell = monsterSpell;
	}
	

	}
