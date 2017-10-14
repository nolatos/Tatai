package tatai.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tatai.Difficulty;
import tatai.StartController;

public class Start {

	private Difficulty _difficulty;
	private StartController _controller;
	private ObservableList<String> _history = FXCollections.observableArrayList();
	
	
	
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
	
	
	
	public Difficulty getDifficulty() {
		return _difficulty;
	}
	
	
}
