package eg.edu.guc.yugioh.gui;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import eg.edu.guc.yugioh.listeners.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class StartWindow extends JFrame {

	private JLabel PlayerA;
	private JLabel PlayerB;
	private JPanel playerFieldA;
	private JPanel playerFieldB;
	private JButton startGame;
	private Clip clip;
	private JTextField fieldA ;
	private JTextField fieldB;
	private JFrame loading;
	
public StartWindow() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
	
		// Start Game!
	
	    super();
        setTitle("Welcome to Yu-Gi-Oh! Duel Monsters");
	    setContentPane(new JLabel(new ImageIcon("Intro.jpg")));
	    setLayout(null);
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    setBounds(300,150,600,500);
	    
	    PlayerA = new JLabel("First Player");
	    PlayerA.setFont(new Font("Arial", 0, 27)); 
	    PlayerA.setForeground(new Color(168, 15, 15));
	    add(PlayerA);
	    PlayerA.setBounds(60, -60, 200, 300);
	    
	    playerFieldA = new JPanel(new BorderLayout());
	    fieldA = new JTextField();
	    playerFieldA.add(fieldA);
	    add(playerFieldA);
	    playerFieldA.setBounds(30,110,200,35);
	    
	    PlayerB = new JLabel("Second Player");
	    PlayerB.setFont(new Font("Arial", 0, 27)); 
	    PlayerB.setForeground(new Color(168, 15, 15));
	    add(PlayerB);
	    PlayerB.setBounds(40, 178, 200, 300);
	    
	    playerFieldB = new JPanel(new BorderLayout());
	    fieldB = new JTextField();
	    playerFieldB.add(fieldB);
	    add(playerFieldB);
	    playerFieldB.setBounds(30,350,200,35);
	    
	    startGame = new JButton("Start Game!");
	    startGame.setFont(new Font("Arial",0,15));
	    add(startGame);
	    startGame.setBounds(375,400,200,35);
	   /* File soundFile = new File("Theme.wav"); 
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile); 
        clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);*/
        loading = new Loading();
        loading.setVisible(false);
        setResizable(false);
	    setVisible(true); 
	    this.validate();
	    
	} 


	public JButton getStartGame() {
		return startGame;
	}



	public void setStartGame(JButton startGame) {
		this.startGame = startGame;
	}


	public Clip getClip() {
		return clip;
	}
	public void setClip(Clip clip) {
		this.clip = clip;
	}

	public JFrame getLoading() {
		return loading;
	}


	public void setLoading(JFrame loading) {
		this.loading = loading;
	}
	
	
	public JTextField getFieldA() {
		return fieldA;
	}


	public void setFieldA(JTextField fieldA) {
		this.fieldA = fieldA;
	}


	public JTextField getFieldB() {
		return fieldB;
	}


	public void setFieldB(JTextField fieldB) {
		this.fieldB = fieldB;
	}


}
     