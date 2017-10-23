package tatai.models;

import tatai.Difficulty;
import tatai.StatsController;
import tatai.utils.Statistics;

public class Stats {
	
	private Difficulty _difficulty;
	private int _totalGames;
	private int _average;
	private int _highScore;
	private StatsController _controller;
	
	public Stats(StatsController controller) {
		_controller = controller;
		
		_difficulty = Difficulty.ONE;
		setStats();
	}
	
	/**
	 * Shows the next level of the stats
	 */
	public void next() {
		switch (_difficulty) {
		case ONE:
			_difficulty = Difficulty.TWO;
			break;
		case TWO:
			_difficulty = Difficulty.THREE;
			break;
		case THREE:
			_difficulty = Difficulty.FOUR;
			break;
		case FOUR:
			_difficulty = Difficulty.FIVE;
			break;
		case FIVE:
			_difficulty = Difficulty.ONE;
		}
		setStats();
	}
	
	/**
	 * Gets the previous level
	 */
	public void previous() {
		switch (_difficulty) {
		case ONE:
			_difficulty = Difficulty.FIVE;
			break;
		case TWO:
			_difficulty = Difficulty.ONE;
			break;
		case THREE:
			_difficulty = Difficulty.TWO;
			break;
		case FOUR:
			_difficulty = Difficulty.THREE;
			break;
		case FIVE:
			_difficulty = Difficulty.FOUR;
		}
		setStats();
	}
	
	/**
	 * Sets the stats fields
	 */
	private void setStats() {
		_totalGames = Statistics.average(_difficulty, false);
		_average = Statistics.average(_difficulty, true);
		_highScore = Statistics.highscore(_difficulty);
	}
	
	
	public int getTotalGames() {
		return _totalGames;
	}
	
	public Difficulty getDifficulty() {
		return _difficulty;
	}
	
	public int getAverage() {
		return _average;
	}
	
	public int getHighScore() {
		return _highScore;
	}
	
	public void update() {
		setStats();
		
	}
}
