package edu.upenn.cit594.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.upenn.cit594.datamanagement.State;
import edu.upenn.cit594.datamanagement.Tweet;
import edu.upenn.cit594.processor.StateCalculator;

public class TweetStateUtility {
	
	/**
	 * Calls the StateCalculator class to get the state for the tweet
	 * @param tweet object we are calculating the state for
	 * @param states list of all of the states
	 * @return the name of the state the tweet is from
	 */
	private static String calculateStateForTweet(Tweet tweet, List<State> states){
		
		String state = StateCalculator.calculateState(tweet.getLatitude(), tweet.getLongitude(), states);
		
		return state;
	}
	
	/**
	 * Creates a map of states and their respective number of tweets
	 * @param tweets list of tweets to map to states
	 * @param states list of all the states
	 * @return a Map of the states and respective tweets
	 */
	public static Map<String, Integer> tweetCountByState(List<Tweet> tweets, List<State> states){
		Map<String, Integer> stateAndTweetMap = new TreeMap<String, Integer>();
		
		for(Tweet tweet: tweets) {
			String state = calculateStateForTweet(tweet, states);
			
			if(stateAndTweetMap.containsKey(state)) {
				int count = stateAndTweetMap.get(state) + 1;
				stateAndTweetMap.put(state, count);
			}
			else {
				stateAndTweetMap.put(state, 1);
			}
			
		}
		
		return stateAndTweetMap;
	}
	
	/**
	 * Creates a list that has the State where a tweet is from and the text of the tweet
	 * @param tweets to add to the list
	 * @param states list of all states
	 * @return a list with the state and text of each tweet
	 */
	public static List<String[]> stateAndTweetList(List<Tweet> tweets, List<State> states){
		List<String[]> stateAndTweetList = new ArrayList<String[]>();
		
		for(Tweet tweet: tweets) {
			
			String[] stateAndTweet = new String[2];
			
			String state = calculateStateForTweet(tweet, states);
			
			stateAndTweet[0] = state;
			stateAndTweet[1] = tweet.getText();
			
			stateAndTweetList.add(stateAndTweet);
			
		}
		
		return stateAndTweetList;
	}
	
	
	
	
	
}
