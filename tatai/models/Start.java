package tatai.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tatai.Difficulty;
import tatai.StartController;
import tatai.utils.UserData;

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
		
		for (String s : UserData.getHistory()) {
			_history.add(s);
		}
				
	}
	
	public void setDifficulty(Difficulty difficulty) {
		_difficulty = difficulty;
	}
	
	
	/**
	 * Adds the string to the list which is displayed in the
	 * "start" screen
	 * @param str the string to be added
	 * @return the observableList, which make up the items
	 */
	public ObservableList<String> addToList(String str) {
		if (str != null) {
			_history.add(str);
		}
		
		return _history;
	}
	
	
	
	
	public Difficulty getDifficulty() {
		return _difficulty;
	}
	
	
}
