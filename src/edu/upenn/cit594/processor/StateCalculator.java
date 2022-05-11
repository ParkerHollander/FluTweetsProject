package edu.upenn.cit594.processor;

import java.util.List;

import edu.upenn.cit594.datamanagement.State;
import edu.upenn.cit594.ui.ScreenWriter;

public class StateCalculator {
	
	/**
	 * Calculates what state is represented by the latitude and longitude
	 * @param latitude to compare to the latitudes in the list of states
	 * @param longitude to compare to the longitudes in the list of states
	 * @param stateList representing the states and their locations
	 * @return the name of the state represented by the passed in latitude and longitude
	 */
	public static String calculateState(double latitude, double longitude, List<State> stateList) {
		
		String stateName = null;
		double distance = -50;
		
		for(State state: stateList) {//loop through all states in the list
			
			double newDistance = calculateDistance(latitude, longitude, state.getLatitude(), state.getLongitude());//distance of the current state we are looking at
			
			
			if(distance == -50) {//if this is the first state
				stateName = state.getName();
				distance = newDistance;
				continue;
			}
	
			if(newDistance < distance) {//if the newDistance is closer than the current
				stateName = state.getName();
				distance = newDistance;
			}
			
			
			
			
		}
		
		if(stateName == null) {
			ScreenWriter.DisplayErrorAndQuit("Error calculation state");
		}
		
		
		
		
		return stateName;
	}
	
	/**
	 * Calculates distance based on the formula given in project requirements
	 * @param latitude passed in to calculateState method
	 * @param longitude passed in to calculateState method
	 * @param stateLatitude of the state we are comparing passed in value to
	 * @param stateLongitude of the state we are comparing passed in value to
	 * @return
	 */
	private static double calculateDistance(double latitude, double longitude, double stateLatitude, double stateLongitude) {
		
		
		double distance = Math.sqrt((Math.pow(stateLatitude - latitude, 2))+ (Math.pow(stateLongitude - longitude, 2)));
		
		return distance;
	}
	
	
}
