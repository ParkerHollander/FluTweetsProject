package edu.upenn.cit594.datamanagement;

/**
 * Represents a state
 * @author Parker
 *
 */
public class State {
	
	private double latitude;
	private double longitude;
	private String name;
	
	/**
	 * State constructor taking 3 values
	 * @param name of the state
	 * @param latitude location for the state
	 * @param longitude location for the state
	 */
	public State(String name, double latitude, double longitude) {
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getLatitude() {
		return this.latitude;
	}
	
	public double getLongitude() {
		return this.longitude;
	}
}
