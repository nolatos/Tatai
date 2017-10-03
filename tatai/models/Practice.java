package tatai.models;


import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import tatai.*;

public class Practice extends Game {

	private boolean _hard;
	private PlayController _practice; //What is now practice will later turn into play
	private Scene _game;
	
	public Practice (boolean hard) throws Exception {
		_hard = hard;
		
		//Initialising _practice
		FXMLLoader loader = new FXMLLoader(getClass().getResource("play.fxml"));		
		Pane pane = (Pane)loader.load();		
//		_practice = loader.getController();
//		_practice.setGame(this);
//		_practice.setImage(getNumber());
//		loader = new FXMLLoader(getClass().getResource("record.fxml"));
//		
//		_practice.setLoader(loader); //Sets the _recordController class
//		
//		//Initialising the field _game so it can be passed to other stages
//		_game = new Scene(pane);
//		
		
	}
	
	public Scene getGame() {
		return _game;
	}
	
	
	/**
	 * Returns a random number
	 */
	public int getNumber() {
		double d = Math.random();
		int i;
		if (_hard) {
			
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
		
		return i;
	}
	
	
	public boolean canLevelUp() {
		return !_hard;
	}

	public PlayController getPlayController() {
		return _practice;
	}
	
	public String levelUp() {
		_hard = true;
		return "HARD";
	}
	
}
