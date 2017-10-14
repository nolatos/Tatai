package tatai.models;


import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import tatai.*;

public class Practice extends Game {

	private PracticeController _controller; //What is now practice will later turn into play
	private Scene _game;
	private int _number;
	
	public Practice (PracticeController controller) {
		_controller = controller;
		 
	}
	
	public Scene getGame() {
		return _game;
	}
	
	
	/**
	 * Returns a random number
	 */
	public int getNumber() {
		return _number;
	}
	
	
	public void setNumber(int i) {
		_number = i;
	}

	
	
	
	
}
