package tatai;

import java.io.IOException;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.*;
import tatai.models.*;
import tatai.views.*;

public class StartController {

	
	private Controller _gameC;
	
	public final Stage MAIN_STAGE;
	public final Scene START_SCENE;
	public final Scene MENU_SCENE;
	
	@FXML 
	private StartView _view;
	
	private Start _model;
	
	/**
	 * Constructor takes in a stage and a difficulty
	 * @param hard
	 * @param mainStage
	 * @throws IOException 
	 */
	public StartController(Boolean hard, Stage mainStage, Difficulty difficulty) throws IOException {
		
		MAIN_STAGE = mainStage;
		MENU_SCENE = mainStage.getScene();
		
		
		
		//Setting upu the START_SCENE field and the _view field
		FXMLLoader loader = new FXMLLoader(getClass().getResource("views/game.fxml"));
		Pane pane = (Pane) loader.load();
		_view = loader.getController();
		_view.setController(this);
		START_SCENE = new Scene(pane);
		show();
		
		
		//Setting up the menu
		if (hard == null) {
			_model = new Start(this, difficulty);
			_view.setLevel(difficulty);
		}
		else if (difficulty == null) {
			_model = new Start(this, hard);
			_view.setLevel(hard);
		}
		_view.setModel(_model);
		
	}
	
	/**
	 * Goes back to the menu
	 */
	public void backToMenu() {
		MAIN_STAGE.setScene(MENU_SCENE);
	}
	
	
	public void startGame() {
		try {
		
		if (!_model.gameExists()) {
			_model.createGame();
	    	if (_model.isPractice()) {
	    		boolean hard = _model.getHard();
	    		_gameC = new PracticeController(hard, this);
	    	}
	    	else {
	    		Difficulty difficulty = _model.getDifficulty();
	    		_gameC = new PlayController(difficulty, this);
	    	}
	    	
	    	try {
				_gameC.show();
			} 
	    	catch (IOException e) {
				
			}
	    	
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Cannot start new Game");
    		alert.setHeaderText(null);
    		alert.setContentText("You cannot start a new game if one exists");
    		Optional<ButtonType> result = alert.showAndWait();
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createGame() {
		_model.createGame();
	}
	
	public void deleteGame() {
		_model.deleteGame();
	}
	
	public void addToList(String str) {
		_view.addToList(_model.addToList(str));
	}
	
	public void show(boolean hard) {
		show();
		_view.setLevel(hard);
	}
	
	public void show(Difficulty difficulty) {
		show();
		_view.setLevel(difficulty);
	}
	
	private void show() {
		MAIN_STAGE.setScene(START_SCENE);
		
	}
	
	

}
