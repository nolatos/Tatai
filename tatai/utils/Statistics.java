package tatai.utils;

import tatai.Difficulty;

public class Statistics {
	/**
	 * Returns the average score gotten for a given level, or the total number of games played at a given level.
	 * Returns -1 if inputs are invalid.
	 * @param D (difficulty level)
	 * @param C (true to get average, false to get Total Games)
	 * @return
	 */
	public static int average(Difficulty D, boolean C) {
		int totalScore = 0;
		int totalGames = 0;
		int average = 0;
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
		
		if (totalGames != 0) {
			average = Math.round(totalScore/totalGames);	
		}

		if (C) {
			return average;
		}
		
		else if (!C) {
			return totalGames;
		}
		
		else  {
			return -1;
		}
	}
	
	/**
	 * Returns the highest score attained at a Level D
	 * @param D (difficulty)
	 * @return int 
	 */
	public static int highscore(Difficulty D) {
		int highscore = 0;
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
		String tmp[] = UserData.splitStatElement(L);
		for (int i = 1; i < tmp.length; i++) {
			if (Integer.valueOf(tmp[i]) > 0) {
				highscore = i-1;
			}
		}
		return highscore;
	}


}
