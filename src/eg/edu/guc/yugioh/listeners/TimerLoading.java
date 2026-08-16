package eg.edu.guc.yugioh.listeners;
import javax.swing.*;
import eg.edu.guc.yugioh.gui.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TimerLoading implements ActionListener {
	
	private StartWindow start;
	private BoardWindow board;
	private Timer timer;
	
	public TimerLoading(StartWindow start, BoardWindow board) {
		
		this.start = start;
		this.board = board;
		timer = new Timer(1880, this);
        
	}
	
	public void actionPerformed(ActionEvent e) {
		
		start.getLoading().setVisible(false);
		board.setVisible(true);
		timer.stop();
		
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

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
	

}
