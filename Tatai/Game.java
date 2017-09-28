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
	 * TO BE CHANGED
	 * @param i
	 * @param str
	 * @return
	 */
	public boolean checkCorrect(int i, String str) {
		if (i <= 10) {
			return str.compareTo(_bh.translation(i)) == 0;
		}
		else {
			String[] toTest = str.split("\n");
			String[] correct = _bh.translation(i).split(" ");
			if (toTest.length != correct.length) {
				return false;
			}
			else {
				for (int j = 0; j < toTest.length; j++) {
					if (correct[j].compareTo(toTest[j]) != 0) {
						return false;
					}
				}
				return true;
			}
		}
		
		
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