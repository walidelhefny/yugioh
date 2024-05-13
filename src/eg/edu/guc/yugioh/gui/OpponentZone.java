package eg.edu.guc.yugioh.gui;
import javax.swing.*;

import java.awt.*;

public class OpponentZone extends JPanel {

	private JButton opponentDeck;
	private JButton opponentGraveyard;
	private Image img;
	private Image scaledImage;
	
	public OpponentZone() {
		
		super();
		img = (new ImageIcon("OpponentField.png")).getImage();
		setOpaque(false);
		GridLayout layout = new GridLayout(2,6);
		setLayout(layout);
	
		setSize(510,170);
		this.setPreferredSize(new Dimension(510,170));
		setOpaque(false);
		scaledImage = img.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
		
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	    g.drawImage(scaledImage, 0, 0, null);
	    this.revalidate();
	    this.repaint();
	  }


	public JButton getOpponentDeck() {
		return opponentDeck;
	}

	public void setOpponentDeck(JButton opponentDeck) {
		this.opponentDeck = opponentDeck;
	}

	public JButton getOpponentGraveyard() {
		return opponentGraveyard;
	}

	public void setOpponentGraveyard(JButton opponentGraveyard) {
		this.opponentGraveyard = opponentGraveyard;
	}
	
	

}
