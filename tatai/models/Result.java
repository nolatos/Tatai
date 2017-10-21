package tatai.models;

import tatai.ResultController;

public class Result {
	
	private ResultController _controller;

	private int _score;
	private int _totalQuestions;
	
	public Result(ResultController controller, int score, int totalQuestions) {
		_controller = controller;
		_score = score;
		_totalQuestions = totalQuestions;
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
}
