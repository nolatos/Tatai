package tatai.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import tatai.*;

public class Play extends Game {

	public final int TOTAL_QUESTIONS;
	
	private  Difficulty _hard;
	private PlayController _controller; 
	private boolean _retried = false;
	
	public Play (Difficulty hard, PlayController controller) {
		_hard = hard;
		_controller = controller;
		
		switch (_hard) {
		case ONE:
			TOTAL_QUESTIONS = 10;
			break;
		case TWO:
			TOTAL_QUESTIONS = 15;
			break;
		case THREE:
			TOTAL_QUESTIONS = 20;
			break;
		case FOUR:
			TOTAL_QUESTIONS = 30;
			break;			
		case FIVE:
			TOTAL_QUESTIONS = 50;
			break;
		default:
			TOTAL_QUESTIONS = 10;
		}
		
	}
	
	public String levelUp() {
		switch (_hard) {
		case ONE:
			_hard = Difficulty.TWO;
			break;
		case TWO:
			_hard = Difficulty.THREE;
			break;
		case THREE:
			_hard = Difficulty.FOUR;
			break;
		case FOUR:
			_hard = Difficulty.FIVE;
			break;			
		}
		
		return String.valueOf(_hard);
		
	}
	
	/**
	 * Increases the score, if there is an increase. 
	 * @return whether or not there was an increase
	 */ 
	public boolean updateScore() {
		if (checkCorrect(getCurrentNumber(), getRecognised())) {
    		increaseScore();
    		return true;
    	}
    	else {
    		return false;
    	}
	}
	
	/**
	 * Returns a random number
	 */
	public int getNumber() {
		double d = Math.random();
		int i;
		if (_hard == Difficulty.ONE) {
			d = d * 10;
			i = (int) d;
			if (i >= 10) {
				i = 9;
			}
		}
		else {
			
			d = d * 90 + 10;
			i = (int) d;
			if (i > 99) {
				i = 99;
			}
			else if (i < 10) {
				i = 10;
			}
		}
		
		if (i == 0) {
			i = 1;
		}
		setNumber(i);
		return i;
	}
	
	
	public String getDisplayEquation() {
		switch (_hard) {
		//Between 1 and 10
		case ONE:
			double d = Math.random();
			d = d * 10;
			int i = (int) d;
			if (i >= 10) {
				i = 9;
			}
			else if (i == 0) {
				i = 1;
			}
			setNumber(i);
			return "" + i;
		//Between 10 and 99
		case TWO:
			d = Math.random();
			d = d * 90 + 10;
			i = (int) d;
			if (i > 99) {
				i = 99;
			}
			else if (i < 10) {
				i = 10;
			}
			setNumber(i);
			return "" + i;
		case THREE:
			d = Math.random();
			d = 100 * d;
			i = (int) d;
			if (i == 0) {
				i = 1;
			}
			else if (i < 99) {
				i = 99;
			}
			
			
		}
		return null;
	}
	
	
	public Difficulty getDifficulty() {
		return _hard;
	}
	
	
	public boolean retried() {
		return _retried;
	}
	
	public void setRetry(boolean retry) {
		_retried = retry;
	}
	
	
	public boolean canLevelUp() {
		return !(_hard == Difficulty.FIVE);
	}

	
	
	
}
