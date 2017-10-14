package tatai.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tatai.Difficulty;
import tatai.StartController;

public class Start {

	private Difficulty _difficulty;
	private boolean _gameExists = false; //States whehter or not a game already exists
	private StartController _controller;
	private ObservableList<String> _history = FXCollections.observableArrayList();
	
	/**
	 * Constructor for practice mode
	 * @param controller
	 * @param hard
	 */
	public Start(StartController controller) {
		_controller = controller;
		
	}
	
	/**
	 * Constructor for play mode
	 * @param controller
	 * @param difficulty
	 */
	public Start(StartController controller, Difficulty difficulty) {
		_difficulty = difficulty;
		_controller = controller;
		
				
	}
	
	
	
	
	
	public ObservableList<String> addToList(String str) {
		_history.add(str);
		return _history;
	}
	
	public boolean gameExists() {
		return _gameExists;
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
	
	
}
