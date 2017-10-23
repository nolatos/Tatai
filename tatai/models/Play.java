package tatai.models;

import java.net.MalformedURLException;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private ObservableList<Question> _questions = FXCollections.observableArrayList();
	private Question _currentQuestion;
	
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
			TOTAL_QUESTIONS = 15;
			break;
		case FOUR:
			TOTAL_QUESTIONS = 15;
			break;			
		case FIVE:
			TOTAL_QUESTIONS = 5;
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
			_currentQuestion = new Question(i, Difficulty.ONE);
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
			_currentQuestion = new Question(i, Difficulty.TWO);
			break;
		case THREE:
			_generator = new ArithmeticGenerator();
			_currentQuestion = _generator.generateQuestion();
			
			_controller.setImage(_currentQuestion.getQuestion());
			setNumber(_currentQuestion.getAnswer());
			break;
		case FOUR:
			_generator = new AlgebraGenerator();
			_currentQuestion = _generator.generateQuestion();
			_controller.setImage(_currentQuestion.getQuestion());
			setNumber(_currentQuestion.getAnswer());
			break;
		case FIVE:
			_generator = new AdvancedGenerator();
			_currentQuestion = _generator.generateQuestion();
			try {
				URL url = new URL(_currentQuestion.getQuestion());
				_controller.setImage(url);
				setNumber(_currentQuestion.getAnswer());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		}
		
		//Adding the question to the list
		_questions.add(_currentQuestion);
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

	public Question getCurrentQuestion() {
		return _currentQuestion;
	}
	
	public ObservableList<Question> getQuestions() {
		return _questions;
	}
	
}
