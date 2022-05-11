package edu.upenn.cit594;

import java.util.List;
import java.util.Map;

import edu.upenn.cit594.datamanagement.*;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.*;
import edu.upenn.cit594.ui.ScreenWriter;
import edu.upenn.cit594.util.LoggerHelper;
import edu.upenn.cit594.util.TweetStateUtility;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//get the logger instance
		Logger instance = Logger.getInstance();
		
		//there should be 3 arguments for this project
		if(args.length != 3) {
			ScreenWriter.DisplayErrorAndQuit("Wrong number of arguments!");
		}
		
		
		
		String tweetFile = args[0];//file name for tweets
		TweetHandler tweetHandler = new TweetHandler(tweetFile);
		List<Tweet> fluTweets = tweetHandler.tweetsContaining("flu");//tweets containing flu information
		
		
		String stateFile = args[1];//file name for states
		StateHandler stateHandler = new StateHandler(stateFile);
		
		
		String loggerFile = args[2];//file name for the logger
		instance.setFile(loggerFile);//set the file name for the logger instance

		
		Map<String, Integer> fluTweetsByState = TweetStateUtility.tweetCountByState(fluTweets, stateHandler.getStates());//get a map of the number of tweets by state
		
		ScreenWriter.displayMap(fluTweetsByState);//display the flu tweets by state to the console
		LoggerHelper.log(TweetStateUtility.stateAndTweetList(fluTweets, stateHandler.getStates()));//log the states and tweets
		
		
		
		
		
		
		
		
		
		
		
	}

}
