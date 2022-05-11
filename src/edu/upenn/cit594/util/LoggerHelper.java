package edu.upenn.cit594.util;

import java.util.List;
import java.util.Map;

import edu.upenn.cit594.logging.Logger;

public class LoggerHelper {
	
	public static Logger instance = Logger.getInstance();
	
	public static void log(Map<String, Integer> map) {
		for(String key : map.keySet()) {
			instance.log(Format.formatStateCountForFile(key, map.get(key)));
		}
	}
	
	public static void log(List<String[]> tweetList) {
		for(String[] arrayEntry: tweetList) {
			instance.log(Format.formatStateTweetForFile(arrayEntry[0], arrayEntry[1]));
		}
	}
}
