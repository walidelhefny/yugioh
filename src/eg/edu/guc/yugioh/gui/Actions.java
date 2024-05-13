package eg.edu.guc.yugioh.gui;
import javax.swing.*;

public class Actions {
	
	private String[] choices;
	private int action;
	
	public Actions(String[] choices) {
	
		
		this.choices = choices;
		action = JOptionPane.showOptionDialog(
				null,
                "Choose An Action:",
                "Yu-Gi-Oh! Card Actions",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                choices,
                choices[0]);
		
	}
	
	
	
	public String[] getChoices() {
		return choices;
	}



	public void setChoices(String[] choices) {
		this.choices = choices;
	}



	public int getAction() {
		return action;
	}



	public void setAction(int action) {
		this.action = action;
	}


}
