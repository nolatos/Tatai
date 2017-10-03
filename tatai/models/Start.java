package tatai.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tatai.Difficulty;
import tatai.StartController;

public class Start {

	private Difficulty _difficulty;
	private boolean _practice;
	private Boolean _hard;
	private boolean _gameExists = false; //States whehter or not a game already exists
	private StartController _controller;
	private ObservableList<String> _history = FXCollections.observableArrayList();
	
	/**
	 * Constructor for practice mode
	 * @param controller
	 * @param hard
	 */
	public Start(StartController controller, boolean hard) {
		_hard = hard;
		_controller = controller;
		_practice = true;
	}
	
	/**
	 * Constructor for play mode
	 * @param controller
	 * @param difficulty
	 */
	public Start(StartController controller, Difficulty difficulty) {
		_difficulty = difficulty;
		_controller = controller;
		_practice = false;
				
	}
	
	/**
	 * Switches to practice mode. If already in practice mode, it does nothing
	 * other than change the difficulty
	 * @param hard
	 */
	public void switchToPractice(boolean hard) {
		_hard = hard;
		if (_practice) {
			
		}
		else {
			_practice = true;
			_difficulty = null;
			
		}
		
	}
	
	/**
	 * Switches to play mode. If already in play mode, it changes
	 * the difficulty
	 * @param difficulty
	 */
	public void switchToPlay(Difficulty difficulty) {
		_difficulty = difficulty;
		if (_practice) {
			_practice = false;
			_hard = null;
		}
	}
	
	public ObservableList<String> addToList(String str) {
		_history.add(str);
		return _history;
	}
	
	public boolean gameExists() {
		return _gameExists;
	}
	
	public boolean isPractice() {
		return _practice;
	}
	
	public void createGame() {
		_gameExists = true;
	}
	
	public void deleteGame() {
		_gameExists = false;
	}
	
	public Difficulty getDifficulty() {
		return _difficulty;
	}
	
	public boolean getHard() {
		return _hard;
	}
}
