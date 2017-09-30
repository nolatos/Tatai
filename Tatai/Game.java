package tatai;

import javafx.application.Application;
import javafx.stage.*;

public abstract class Game {
	
	private int _score = 0;
	private BashHandler _bh = new BashHandler();
	private Stage _stage;
	
	
	public void setStage(Stage stage) {
		_stage = stage;
	}
	
	
	
	
	public void updateScore() {
		_score++;
	}
	
	/**
	 * Checks if it was correct
	 * @param i the number we are checking
	 * @param str what the user said
	 * @return whether or not the translation of the number i is the same as what the user said
	 */
	public boolean checkCorrect(int i, String str) {
		return str.compareTo(_bh.translation(i)) == 0;		
		
	}
	
	public int getScore() {
		return _score;
	}
	
	public Stage getStage() {
		return _stage;
	}

	
	public abstract int getNumber();
	
	public abstract boolean canLevelUp();
	
	public void reset() {
		_score = 0;
		
	}
}