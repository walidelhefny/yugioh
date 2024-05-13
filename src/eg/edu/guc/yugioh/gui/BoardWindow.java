package eg.edu.guc.yugioh.gui;
import java.awt.*;

import javax.swing.*;

public class BoardWindow extends JFrame {
	
	private JPanel fieldPanel = new JPanel();
	private JPanel opponentHand = new JPanel();
	private JPanel activeHand = new JPanel();
	private JPanel description = new JPanel();
	private JPanel information = new JPanel();
	private JPanel cardInfo = new JPanel();
	private JButton endPhase;
	private JButton endTurn;
	private JLabel playerA;
	private JLabel playerB;
	private JLabel lifePointsA;
	private JLabel lifePointsB;
	private JPanel currentPhase;
	private OpponentZone opponentZone = new OpponentZone();
	private ActiveZone activeZone = new ActiveZone();
	private JPanel activeDeckSize;
	private JPanel opponentDeckSize;
	private JPanel refresh;
	private JButton refreshButton;
	private JScrollPane activeScroller = new JScrollPane();
	private JScrollPane opponentScroller = new JScrollPane();
	private JPanel activeName;
	private JPanel opponentName;
	
	public BoardWindow () {
		
		super();
		
		setTitle("Yu-Gi-Oh! Duel Monsters");
		setContentPane(new JLabel(new ImageIcon("Pyramids.jpg")));
		setBounds(120,0,1100,700);
		setLayout(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
		// Field Panel
		
		GridLayout layout = new GridLayout(2,6);
		fieldPanel.setOpaque(false);
		fieldPanel.setLayout(layout);
		fieldPanel.add(opponentZone);
		fieldPanel.add(activeZone);
		fieldPanel.setSize(1020,630);
		fieldPanel.setPreferredSize(new Dimension(1020,630));
		add(fieldPanel);
		fieldPanel.setBounds(320,160,510,340);
		
		// Opponent Hand
		
		opponentHand.setOpaque(false);
		opponentScroller.setOpaque(false);
		opponentScroller.setBorder(BorderFactory.createEmptyBorder());
		add(opponentScroller);
		opponentScroller.setBounds(270,40,600,105);
		
		// Active Hand
		
		activeHand.setOpaque(false);
		activeScroller.setOpaque(false);
		activeScroller.setBorder(BorderFactory.createEmptyBorder());
		add(activeScroller);
		activeScroller.setBounds(270,527,600,110);
		
		// Card Description
		
		description.setOpaque(false);
		description.setLayout(new BorderLayout());
		description.add(new JButton (new ImageIcon("Card Back.png")));
		add(description);
		description.setBounds(5,10,248,350);
		cardInfo.setOpaque(false);
		cardInfo.setLayout(new BorderLayout());
		add(cardInfo);
		cardInfo.setBounds(5,370,248,200);
		
		// Information Panel
		
		information.setOpaque(false);
		GridLayout infoLayout = new GridLayout (4,2);
		information.setLayout(infoLayout);
		playerA = new JLabel ("First Player:");
		playerA.setForeground(new Color(168, 15, 15));
		playerA.setFont(new Font("Arial", 0, 20)); 
		lifePointsA = new JLabel ("Life Points:");
		lifePointsA.setForeground(new Color(255, 204, 51));
		lifePointsA.setFont(new Font("Arial", 0, 20)); 
		playerB = new JLabel ("Second Player:");
		playerB.setFont(new Font("Arial", 0, 20)); 
		playerB.setForeground(new Color(168, 15, 15));
		lifePointsB = new JLabel ("Life Points:");
		lifePointsB.setFont(new Font("Arial", 0, 20)); 
		lifePointsB.setForeground(new Color(255, 204, 51));
		infoLayout.setHgap(0);
		infoLayout.setVgap(0);
		add(information);
		information.setBounds(10,500,300,150);
		
		
		// Ending Phase and Turn 
		
		JPanel phase = new JPanel();
		phase.setOpaque(false);
		phase.setLayout(new GridLayout(2,0));
		endPhase = new JButton(new ImageIcon("EndPhase"));
		endPhase.setOpaque(false);
		endPhase.setContentAreaFilled(false);
		endPhase.setBorderPainted(false);
		phase.add(endPhase);
		endTurn = new JButton (new ImageIcon("EndTurn.png"));
		endTurn.setOpaque(false);
		endTurn.setContentAreaFilled(false);
		endTurn.setBorderPainted(false);
		phase.add(endTurn);
		add(phase);
		phase.setBounds(980,234,100,200);
		
		// Current Phase
		
		currentPhase = new JPanel();
		currentPhase.setOpaque(false);
		currentPhase.setLayout(new GridLayout(1,0));
		add(currentPhase);
		currentPhase.setBounds(980,65,100,200);
		
		// Refresh GUI
		
		refresh = new JPanel();
		refresh.setOpaque(false);
		refresh.setLayout(new GridLayout(1,0));
		refreshButton = new JButton(new ImageIcon("refresh.png"));
		refreshButton.setOpaque(false);
		refreshButton.setContentAreaFilled(false);
		refreshButton.setBorderPainted(false);
		refresh.add(refreshButton);
		add(refresh);
		refresh.setBounds(980,410,100,200);
		
		// Deck Size
		
		activeDeckSize = new JPanel();
		opponentDeckSize = new JPanel();
		activeDeckSize.setOpaque(false);
		opponentDeckSize.setOpaque(false);
		add(activeDeckSize);
		add(opponentDeckSize);
		activeDeckSize.setBounds(840,430,100,100);
		opponentDeckSize.setBounds(840,190,100,100);
		
		// Player Names Beside Fields!
		
		activeName = new JPanel();
		opponentName = new  JPanel();
		activeName.setLayout(new BorderLayout());
		opponentName.setLayout(new BorderLayout());
		activeName.setOpaque(false);
		opponentName.setOpaque(false);
		activeName.add(new JButton("walid"));
		opponentName.add(new JButton("tarek"));
		add(activeName);
		add(opponentName);
		activeName.setBounds(320,640,900,200);
		opponentName.setBounds(320, 10, 900, 200);
		
		
		setResizable(false);
		setVisible(false);
		
	}

	public JPanel getFieldPanel() {
		return fieldPanel;
	}

	public void setFieldPanel(JPanel fieldPanel) {
		this.fieldPanel = fieldPanel;
	}

	public JPanel getOpponentHand() {
		return opponentHand;
	}

	public void setOpponentHand(JPanel opponentHand) {
		this.opponentHand = opponentHand;
	}

	public JPanel getActiveHand() {
		return activeHand;
	}

	public void setActiveHand(JPanel activeHand) {
		this.activeHand = activeHand;
	}

	public JPanel getDescription() {
		return description;
	}

	public void setDescription(JPanel description) {
		this.description = description;
	}

	public JPanel getInformation() {
		return information;
	}

	public void setInformation(JPanel information) {
		this.information = information;
	}

	public JButton getEndPhase() {
		return endPhase;
	}

	public void setEndPhase(JButton endPhase) {
		this.endPhase = endPhase;
	}

	public JButton getEndTurn() {
		return endTurn;
	}

	public void setEndTurn(JButton endTurn) {
		this.endTurn = endTurn;
	}


	public JLabel getPlayerA() {
		return playerA;
	}

	public void setPlayerA(JLabel playerA) {
		this.playerA = playerA;
	}

	public JLabel getPlayerB() {
		return playerB;
	}

	public void setPlayerB(JLabel playerB) {
		this.playerB = playerB;
	}

	public JLabel getLifePointsA() {
		return lifePointsA;
	}

	public void setLifePointsA(JLabel lifePointsA) {
		this.lifePointsA = lifePointsA;
	}

	public JLabel getLifePointsB() {
		return lifePointsB;
	}

	public void setLifePointsB(JLabel lifePointsB) {
		this.lifePointsB = lifePointsB;
	}
	
	
	public JPanel getCurrentPhase() {
		return currentPhase;
	}

	public void setCurrentPhase(JPanel currentPhase) {
		this.currentPhase = currentPhase;
	}
	

	public OpponentZone getOpponentZone() {
		return opponentZone;
	}

	public void setOpponentZone(OpponentZone opponentZone) {
		this.opponentZone = opponentZone;
	}

	public ActiveZone getActiveZone() {
		return activeZone;
	}

	public void setActiveZone(ActiveZone activeZone) {
		this.activeZone = activeZone;
	}


	public JPanel getActiveDeckSize() {
		return activeDeckSize;
	}

	public void setActiveDeckSize(JPanel activeDeckSize) {
		this.activeDeckSize = activeDeckSize;
	}

	public JPanel getOpponentDeckSize() {
		return opponentDeckSize;
	}

	public void setOpponentDeckSize(JPanel opponentDeckSize) {
		this.opponentDeckSize = opponentDeckSize;
	}

	public JPanel getRefresh() {
		return refresh;
	}

	public void setRefresh(JPanel refresh) {
		this.refresh = refresh;
	}

	public JButton getRefreshButton() {
		return refreshButton;
	}

	public void setRefreshButton(JButton refreshButton) {
		this.refreshButton = refreshButton;
	}

	public JScrollPane getActiveScroller() {
		return activeScroller;
	}

	public void setActiveScroller(JScrollPane activeScroller) {
		this.activeScroller = activeScroller;
	}

	public JScrollPane getOpponentScroller() {
		return opponentScroller;
	}

	public void setOpponentScroller(JScrollPane opponentScroller) {
		this.opponentScroller = opponentScroller;
	}

	public JPanel getCardInfo() {
		return cardInfo;
	}

	public void setCardInfo(JPanel cardInfo) {
		this.cardInfo = cardInfo;
	}

	public JPanel getActiveName() {
		return activeName;
	}

	public void setActiveName(JPanel activeName) {
		this.activeName = activeName;
	}

	public JPanel getOpponentName() {
		return opponentName;
	}

	public void setOpponentName(JPanel opponentName) {
		this.opponentName = opponentName;
	}
	
	
	
}
