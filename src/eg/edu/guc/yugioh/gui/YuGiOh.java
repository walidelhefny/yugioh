package eg.edu.guc.yugioh.gui;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import eg.edu.guc.yugioh.listeners.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class YuGiOh extends JFrame {

	StartWindow start;
	BoardWindow board;
	EndWindow end;
public YuGiOh() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
	
	start = new StartWindow();
	board = new BoardWindow();
	end = new EndWindow();
	new SummonController(board,start,end);
	
		
}



public StartWindow getStart() {
	return start;
}



public void setStart(StartWindow start) {
	this.start = start;
}



public BoardWindow getBoard() {
	return board;
}



public void setBoard(BoardWindow board) {
	this.board = board;
}



public static void main (String [] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
	
	YuGiOh x = new YuGiOh();
	
}

}