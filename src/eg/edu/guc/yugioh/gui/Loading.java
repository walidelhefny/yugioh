package eg.edu.guc.yugioh.gui;
import java.awt.*;
import javax.swing.*;

public class Loading extends JFrame {
	
	public Loading () {
		
		super();
		setTitle("Loading Yu-Gi-Oh! Duel Monstes");
		setContentPane(new JLabel(new ImageIcon("2.gif")));
		setBounds(370,200,500,270);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);

	}

}
