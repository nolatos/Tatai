package tatai.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tatai.Difficulty;

public class UserData {

	private static List<String> _history = new ArrayList<String>();
	private static List<String> _stats = new ArrayList<String>();
	private static File _user;

	/**
	 * Takes a username and creates a history file for them if the user doesn't already have one. It writes
	 * data to the file in the format it will be saved to for convenience, then it initialises 
	 * their history and stats arraylists
	 * @param x
	 * @throws IOException 
	 */
	public static void login(String[] x){
//		String name = "";
//		for (int k = 0; k < x.length; k++) {
//			name = name + "_" + x[k];
//		}
//		_user = new File(Constants.getUserPath().concat("/" + name));
//		boolean exists = (_user.exists() && !_user.isDirectory());
//		if (!exists) {
//			try {
//				_user.createNewFile();
//				BufferedWriter B = new BufferedWriter(new FileWriter(_user));
//				for (int j = 1; j < 6; j++) {
//					B.write("Times Scored in Level " + j + " ,0,0,0,0,0,0,0,0,0,0,0,");
//					B.newLine();
//				}
//				for (int i = 0; i < 10; i++) {
//					B.write("No Data");
//					B.newLine();
//				}
//				B.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		try {
//			int counter = 1;
//			Scanner sc = new Scanner(new File(_user.toString()));
//			_history.clear();
//			_stats.clear();
//			while (sc.hasNextLine() && counter < 16) {
//				if (counter < 6) {
//					_stats.add(sc.nextLine());
//				}
//				if (counter > 5) {
//					_history.add(sc.nextLine());
//				}
//				counter++;
//			}
//			sc.close();
//		} catch (FileNotFoundException e) {
//			System.out.println("login method failed to create file");
//		}
	}

	/**
	 * Adds a result to the list History, and replaces the oldest entry.
	 * @param S
	 * @param D
	 */
	public static void updateHistory(int S, Difficulty D, int i) {
		String Q = "" + i;
		_history.add("Score: " + S + "/" + Q + ", Difficulty: " + D);
		if (_history.size() > 10) {
			_history.remove(0);
		}
	}

	/**
	 * Replaced an element of the Stats arraylist with an updated element
	 * @param S
	 * @param D
	 */
	public static void updateStats(int S, Difficulty N) {
		int D = -1;
		switch (N) {
		case ONE: D = 0;
		case TWO: D = 1;
		case THREE: D = 2;
		case FOUR: D = 3;
		case FIVE: D = 4;
			
		}
		String[] tmp = splitStatElement(D);
		int i = Integer.valueOf(tmp[S+1]);
		i++;
		String newElement = "" + i;
		tmp[S+1] = newElement;
		StringBuilder builder = new StringBuilder();
		for (String element : tmp) {
			builder.append(element);
			builder.append(",");
		}
		String updated = builder.toString();
		_stats.set(D, updated);
	}
	
	
	
	/**
	 * Splits the List containing the statistics for ease of editing without using too many elements
	 * @param i
	 * @return
	 */
	public static String[] splitStatElement(int i) {
		String tmp = getStats().get(i);
		String[] stringArray = tmp.split(",");
		return stringArray;
	}

	/**
	 * Writes History list elements to a file so that History will remember results from previous
	 * session as well as statistics, so that the user can compare their results even if starting a new session.
	 * @throws IOException
	 */
	public static void storeUserData() throws IOException {
		BufferedWriter Writer = new BufferedWriter(new FileWriter(_user));
		for (String element : _stats) {
					Writer.write(element);;
					Writer.newLine();
				}
		for (String element : _history) {
			Writer.write(element);
			Writer.newLine();
		}
		Writer.close();
	}

	public static List<String> getHistory() {
		return _history;
	}

	public  static List<String> getStats() {
		return _stats;
	}
}
