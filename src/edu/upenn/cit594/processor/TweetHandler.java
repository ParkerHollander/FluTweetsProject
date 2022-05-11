package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.*;

import edu.upenn.cit594.datamanagement.Tweet;
import edu.upenn.cit594.ui.ScreenWriter;

/**
 * used as parent class for handling tweets from multiple file types
 * @author Parker
 *
 */
public class TweetHandler{
	
	//below strings are used to compare to the filename to get the type
	String jsonExtension = ".json";
	String txtExtension = ".txt";
	
	private List<Tweet> tweets = new ArrayList<Tweet>();
	
	String fileName;
	
	/**
	 * Constructor taking a fileName as parameter
	 * @param fileName refers to the name of the file where the tweets are stored
	 */
	public TweetHandler(String fileName) {
		this.fileName = fileName;
		
		setTweetsFromFile();
	}
	
	/**
	 * Checks the fileName to get the file type extension and then calls the appropriate method to interpret the file
	 */
	public void setTweetsFromFile(){
		if(FileType.checkIfJson(fileName)){
			setTweetsFromJsonFile();
		}
		else if(FileType.checkIfTxt(fileName)) {
			setTweetsFromTxtFile();
		}
		else {
			ScreenWriter.DisplayErrorAndQuit("Invalid file type for Tweet File!");
		}
	}
	
	/**
	 * Returns the list of tweets
	 * @return
	 */
	public List<Tweet> getTweets(){
		return this.tweets;
	}
	
	/**
	 * Calls the JsonHandler subclass of FileHandler to interpret the Json file of tweets
	 */
	public void setTweetsFromJsonFile(){
		
		FileHandler fileHandler = new JsonHandler(fileName);
		
		
		List<Object> objArray = fileHandler.getFileAsList();//get a list of objects from the Json file
		
		
		
		
		//go through all objects in the array
		for(Object obj : objArray) {
			
			
			
			JSONObject jo = (JSONObject) obj;//convert the objects to JsonObjects
			String text = (String)jo.get("text");
			
			JSONArray array = (JSONArray) jo.get("location");//array is an array of two doubles containing the latitude and longitude information
			
			Double latitude = (Double) array.get(0);
			Double longitude = (Double) array.get(1);
			
			
			
			Tweet tweet = new Tweet(text, latitude, longitude);//create the tweet object
			
			tweets.add(tweet);//add the tweet to the list

		}
		
	}
	
	/**
	 * Calls the TxtHandler subclass of FileHandler to interpret the txt file of tweets
	 */
	public void setTweetsFromTxtFile() {
		
		
		FileHandler fileHandler = new TxtHandler(fileName);
		
		
		List<Object> objArray = fileHandler.getFileAsList();//get a list of objects from the txt file
		
		//go through all objects in the array
		for(Object obj : objArray) {
			
			String line = (String) obj;//convert the object to a string
			
			String[] lineArray = line.split("\t");//split the line by tab characters
			
			String text = lineArray[3];//text for the tweet is the third entry in the array
			String location = lineArray[0];//location is at the first entry in the array
			
			Tweet tweet = new Tweet(text, location);//create the tweet object
			
			tweets.add(tweet);//add the tweet to the list
			
			
		}
		
	}
	
	/**
	 * Returns whether or not a specific tweet contains a key word meeting the given condition of the following character (if one exists) not being another letter
	 * @param tweet to search for the given text
	 * @param text to search for in the given tweet
	 * @return boolean indicating whether or not the tweet contains the text
	 */
	private boolean tweetTextContains(Tweet tweet, String text) {
		
		//if the tweet contains the given text, regardless of capitalization
		if(tweet.getText().toLowerCase().contains(text.toLowerCase())) {
			
			
			try {
				int nextChar = tweet.getText().toLowerCase().indexOf(text.toLowerCase()) + text.length();//store what the next character index would be
				if(!Character.isLetter(tweet.getText().charAt(nextChar))) {//if this character is not a letter
					return true;
				}
			} catch (IndexOutOfBoundsException e) {//if the next character index is out of bounds
				// TODO: handle exception
				if(tweet.getText().toLowerCase().indexOf(text.toLowerCase()) + text.length() == tweet.getText().length()) {//if this is the last word in the tweet
					return true;
				}
				else {
					return false;
				}
				
			}
		}
		
		
		return false;
	}
	
	/**
	 * Returns a list of tweets containing the specified text
	 * @param text to search for
	 * @return list of the tweets containing the specified text
	 */
	public List<Tweet> tweetsContaining(String text) {
		List<Tweet> tweetsContainingText = new ArrayList<Tweet>();
		for(Tweet tweet: tweets) {
			if(tweetTextContains(tweet, text)) {
				tweetsContainingText.add(tweet);
			}
		}
		
		
		return tweetsContainingText;
	}
	
	
	
	
}
