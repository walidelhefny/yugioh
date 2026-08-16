package eg.edu.guc.yugioh.exceptions;
import java.io.*;

public class UnexpectedFormatException extends Exception {

	private String sourceFile;
	private int sourceLine;
	
	public UnexpectedFormatException(String sourceFile, int sourceLine) {
		
		super("Exception Message!");
		this.sourceFile = sourceFile;
		this.sourceLine = sourceLine;
		
	}

	public String getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}

	public int getSourceLine() {
		return sourceLine;
	}

	public void setSourceLine(int sourceLine) {
		this.sourceLine = sourceLine;
	}
	
	
}
