package eg.edu.guc.yugioh.gui;
import java.awt.*;

import javax.swing.*;

public class EndWindow extends JFrame {
	
	private JButton restart;
	private JButton exit;
	
	public EndWindow() {
		
		super();
        setTitle("Yu-Gi-Oh! Duel Monsters | End Game!");
	    setContentPane(new JLabel(new ImageIcon("1.gif")));
	    setLayout(null);
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    setBounds(350,150,500,400);
	    
	    restart = new JButton ("Restart Game");
	    exit = new JButton ("Exit");
	    
	    add(restart);
	    add(exit);
	    
	    restart.setBounds(40,330,200,35);
	    exit.setBounds(280,330,200,35);
	    
	    setResizable(false);
		setVisible(false);
		
	}
	
	
	public JButton getRestart() {
		return restart;
	}


	public void setRestart(JButton restart) {
		this.restart = restart;
	}


	public JButton getExit() {
		return exit;
	}


	public void setExit(JButton exit) {
		this.exit = exit;
	}


}
