package tatai.models;

import javafx.application.Application;
import javafx.stage.*;
import tatai.*;
import tatai.utils.SpeechRecognition;

public abstract class Game {
	
	private int _score = 0;
	private Stage _stage;
	private int _progress = 1;
	private int _number; //The number that is being displayed
	private String _recognised;
	
	
	
	public void setStage(Stage stage) {
		_stage = stage;
	}
	
	
	
	
	public void increaseScore() {
		_score++;
	}
	
	/**
	 * Checks if it was correct
	 * @param i the number we are checking
	 * @param str what the user said
	 * @return whether or not the translation of the number i is the same as what the user said
	 */
	public boolean checkCorrect(int i, String str) {
		return str.compareTo(SpeechRecognition.translation(i)) == 0;		
		
	}
	
	public int getScore() {
		return _score;
	}
	
	public Stage getStage() {
		return _stage;
	}
	
	public int getProgress() {
		return _progress;
	}

	
	public abstract int getNumber();
	
	
	
	public void reset() {
		_score = 0;
		_progress = 1;
	} 
	
	/**
	 * Advances the progress
	 */
	public void advance() {
		_progress++;
	}
	
	/**
	 * Sets the correct answer
	 * @param i the correct answer
	 */
	public void setNumber(int i) {
		_number = i;
	}
	
	public int getCurrentNumber() {
		return _number;
	}
	
	
	public void setRecognised(String recognised) {
		_recognised = recognised;
	}
	
	public String getRecognised() {
		return _recognised;
	}
}