package eg.edu.guc.yugioh.gui;
import javax.swing.*;

import java.awt.*;

public class ActiveZone extends JPanel {
	
	private JButton activeGraveyard;
	private JButton activeDeck;
	private Image img;
	private Image scaledImage;
	
	public ActiveZone () {
		
		super();
		img = (new ImageIcon("ActiveField.png")).getImage();
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

	
	public JButton getActiveGraveyard() {
		return activeGraveyard;
	}

	public void setActiveGraveyard(JButton activeGraveyard) {
		this.activeGraveyard = activeGraveyard;
	}

	public JButton getActiveDeck() {
		return activeDeck;
	}

	public void setActiveDeck(JButton activeDeck) {
		this.activeDeck = activeDeck;
	}
	
}
