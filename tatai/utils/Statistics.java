package tatai.utils;

public class Statistics {
	
	/**
	 * Returns the average score gotten for a given level, or the total number of games played at a given level.
	 * By using "0" as the input for D, you will get the combined stats of all levels. 
	 * Returns -1 if inputs are invalid.
	 * @param D
	 * @return
	 */
	public static int average(int D, char C) {
		int totalScore = 0;
		int totalGames = 0;
		if (D == 0) {
			for (int i = 0; i < UserData.getStats().size(); i++) {
				String[] tmp = UserData.splitStatElement(i);
				for (int j = 1; j < tmp.length; j++) {
					totalGames = totalGames + Integer.valueOf(tmp[j]);
					totalScore = totalScore + (Integer.valueOf(tmp[j]) * (j-1));
				}
			}
			
		}
		else {
			String[] tmp = UserData.splitStatElement(D-1);
			for (int i = 1; i < tmp.length; i++) {
				totalGames = totalGames + Integer.valueOf(tmp[i]);
				totalScore = totalScore + (Integer.valueOf(tmp[i]) * (i-1));
			}
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
