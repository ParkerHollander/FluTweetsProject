package edu.upenn.cit594.ui;


import java.util.Map;

import edu.upenn.cit594.util.Format;

public class ScreenWriter {
	
	
	public static void DisplayErrorAndQuit(String error) {
		System.out.println(error);
		System.exit(0);
	}
	
	public static void displayString(String string) {
		System.out.println(string);
	}
	
	public static void displayMap(Map<String, Integer> map) {
		for(String key : map.keySet()) {
			System.out.println(Format.formatStateCountForPrint(key, map.get(key)));
		}
	}
}
