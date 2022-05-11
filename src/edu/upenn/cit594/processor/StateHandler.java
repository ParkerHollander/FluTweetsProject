package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.List;

import edu.upenn.cit594.datamanagement.State;
import edu.upenn.cit594.ui.ScreenWriter;

public class StateHandler {
	
	
	private List<State> states = new ArrayList<State>();
	
	String fileName;
	/**
	 * Constructor taking one parameter
	 * @param fileName of the list of States
	 */
	public StateHandler(String fileName) {
		this.fileName = fileName;
		setStatesFromFile();
	}
	
	public List<State> getStates(){
		return this.states;
	}
	
	/**
	 * Set states from the state file
	 */
	public void setStatesFromFile(){
		
		if(FileType.checkIfCsv(fileName)) {
			setStatesFromCsvFile();
		}
		else {
			ScreenWriter.DisplayErrorAndQuit("Invalid file type for Tweet File!");
		}
	}
	
	/**
	 * Sets the states from a CSV file
	 */
	public void setStatesFromCsvFile() {
		FileHandler fileHandler = new CsvHandler(fileName);
		
		
		List<Object> objArray = fileHandler.getFileAsList();//list of objects from the CSV file
		
		for(Object obj : objArray) {//loop through objects in list
			
			String line = (String) obj;//convert the object to a string
			
			String[] lineArray = line.split(",");
			
			//First entry in array is the name of the state, the following two are latitude and longitude, respectively
			String name = lineArray[0];
			double latitude = Double.parseDouble(lineArray[1]);
			double longitude = Double.parseDouble(lineArray[2]);
			
			State state = new State(name, latitude, longitude);//create the state object
			
			states.add(state);//add the state to the list
		}
	}
}
