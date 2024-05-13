package eg.edu.guc.yugioh.exceptions;
import java.io.*;

public class MissingFieldException extends UnexpectedFormatException {
	
	
	public MissingFieldException(String sourceFile, int sourceLine) {
		
		super(sourceFile,sourceLine);
	}
	
	

}