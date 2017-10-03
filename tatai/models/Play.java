package tatai.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import tatai.*;

public class Play extends Game {

	private  Difficulty _hard;
	private PlayController _controller; //What is now practice will later turn into play
//	private Scene _game;
	
	public Play (Difficulty hard, PlayController controller) {
		_hard = hard;
		_controller = controller;
//		//Initialising _practice
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("play.fxml"));		
//		Pane pane = (Pane)loader.load();		
//		_practice = loader.getController();
//		_practice.setGame(this);
//		_practice.setImage(getNumber());
//		loader = new FXMLLoader(getClass().getResource("record.fxml"));
		
//		_practice.setLoader(loader); //Sets the _recordController class
		
		//Initialising the field _game so it can be passed to other stages
//		_game = new Scene(pane);
		
		
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
			
			d = d * 90 + 10;
			i = (int) d;
			if (i > 99) {
				i = 99;
			}
			else if (i < 10) {
				i = 10;
			}
		}
		else {
			d = d * 10;
			i = (int) d;
			if (i >= 10) {
				i = 9;
			}
		}
		
		if (i == 0) {
			i = 1;
		}
		setNumber(i);
		return i;
	}
	
	
	public Difficulty getDifficulty() {
		return _hard;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean canLevelUp() {
		return !(_hard == Difficulty.FIVE);
	}

	
	
}
