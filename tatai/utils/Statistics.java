package tatai.utils;

import tatai.Difficulty;

public class Statistics {
	/**
	 * Returns the average score gotten for a given level, or the total number of games played at a given level.
	 * By using "0" as the input for D, you will get the combined stats of all levels. 
	 * Returns -1 if inputs are invalid.
	 * @param D (difficulty level)
	 * @param C ('A' to get average, 'T' to get Total Games)
	 * @return
	 */
	public static  int average(Difficulty D, char C) {
		int totalScore = 0;
		int totalGames = 0;
		int L = -1;
		switch (D) {
		case ONE: L = 0;
		break;
		case TWO: L = 1;
		break;
		case THREE: L = 2;
		break;
		case FOUR: L = 3;
		break;
		case FIVE: L = 4;
		break;
		}
		
		String[] tmp = UserData.splitStatElement(L);
		for (int i = 1; i < tmp.length; i++) {
			totalGames = totalGames + Integer.valueOf(tmp[i]);
			totalScore = totalScore + (Integer.valueOf(tmp[i]) * (i-1));
		}
		
		int average = totalScore/totalGames;

		if (C == 'A') {
			return average;
		}
		
		else if (C == 'T') {
			return totalGames;
		}
		
		else  {
			return -1;
		}
	}


}
