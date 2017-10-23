package tatai.models;

import tatai.*;

public class Result {
	
	private ResultController _controller;
	private Difficulty _difficulty;
	private int _score;
	private int _totalQuestions;
	
	public Result(ResultController controller, int score, int totalQuestions, Difficulty difficulty) {
		_controller = controller;
		_score = score;
		_totalQuestions = totalQuestions;
		_difficulty = difficulty;
	}
	
	public int getScore() {
		return _score;
	}
	
	/**
	 * Returns the total number of questions
	 * @return
	 */
	public int getTotalQuestions() {
		return _totalQuestions;
	}
	
	public Difficulty getDifficulty() {
		return _difficulty;
	}
}
