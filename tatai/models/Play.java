package tatai.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import tatai.*;
import tatai.math.*;
import tatai.views.PlayView;

public class Play extends Game {

	public final int TOTAL_QUESTIONS;
	
	private  Difficulty _hard;
	private PlayController _controller; 
	private boolean _retried = false;
	private MathGenerator _generator;
	
	
	public Play (Difficulty hard, PlayController controller) {
		_hard = hard;
		_controller = controller;
		
		switch (_hard) {
		case ONE:
			TOTAL_QUESTIONS = 10;
			break;
		case TWO:
			TOTAL_QUESTIONS = 10;
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
		if (checkCorrect()) {
    		increaseScore();
    		return true; 
    	}
    	else {
    		return false;
    	}
	}

	
	
	public void setQuestion() {
		switch (_hard) {
		//Between 1 and 10
		case ONE:
//			System.out.println("hi");
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
			_controller.setImage(i);
			break;
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
			_controller.setImage(i);
			break;
		case THREE:
			_generator = new ArithmeticGenerator();
			_controller.setImage(_generator.generateQuestion());
			setNumber(_generator.getAnswer());
			break;
		case FOUR:
			_generator = new AlgebraGenerator();
			_controller.setImage(_generator.generateQuestion());
			setNumber(_generator.getAnswer());
			break;
		case FIVE:
			_generator = new AlgebraGenerator();
			_controller.setImage(_generator.generateQuestion());
			setNumber(_generator.getAnswer());
			break;
		}
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
