package edu.upenn.cit594.logging;

import java.io.FileWriter;
import java.io.IOException;

import edu.upenn.cit594.ui.ScreenWriter;

public class Logger {
	
	private FileWriter out = null;
	
	private Logger() {};
	
	//singleton instance
	private static Logger instance = new Logger();
	
	public static Logger getInstance() { 
		return instance;
	}
	
	public void log(String message) {
		try {
			out.write(message);
		} catch (IOException e) {
			//don't do anything with the exception
		}
	}
	
	public void setFile(String fileName) {
		if(this.out != null) {
			try {
				this.out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				String error = "Could not close file!";
				ScreenWriter.DisplayErrorAndQuit(error);
			}
		}
		
		try {
			this.out = new FileWriter(fileName, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			String error = "Unable to open file!";
			ScreenWriter.DisplayErrorAndQuit(error);
		}
		
		
		
	}
}
