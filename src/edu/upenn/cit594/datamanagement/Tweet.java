package edu.upenn.cit594.datamanagement;


public class Tweet {
	private String text;
	private double latitude;
	private double longitude;
	
	/**
	 * Constructor taking two parameters (Tweet text and location)
	 * @param text of the tweet
	 * @param location of the tweet
	 */
	public Tweet(String text, String location) {
		this.text = text;
		setLocationVariables(location);
	}
	
	/**
	 * Constructor taking 3 parameters
	 * @param text of the tweet
	 * @param latitude value of the tweet
	 * @param longitude value of the tweet
	 */
	public Tweet(String text, Double latitude, Double longitude) {
		this.text = text;
		this.latitude = latitude;
		this.longitude = longitude;
		
	}
	
	/**
	 * Gets the latitude and longitude from a String containing the two
	 * @param location as a string containing the latitude and longitude
	 */
	private void setLocationVariables(String location) {
		String[] coordinates = location.split(",");
		
		coordinates[0] = coordinates[0].substring(1);
		coordinates[1] = coordinates[1].substring(0, coordinates[1].length()-1);
		
		this.latitude = Double.parseDouble(coordinates[0]);
		this.longitude = Double.parseDouble(coordinates[1]);
		
	}
	
	public String getText() {
		return this.text;
	}
	
	public double getLatitude() {
		return this.latitude;
	}
	
	public double getLongitude() {
		return this.longitude;
	}
}
