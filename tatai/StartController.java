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
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.*;
import tatai.models.*;
import tatai.views.*;

public class StartController implements Controller {


	private Controller _gameC;

	public final Stage MAIN_STAGE;
	public final Scene START_SCENE;
	public final Scene MENU_SCENE;

	private WelcomeController _welcomeC;

	@FXML 
	private StartView _view;

	private Start _model;

	/**
	 * Constructor takes in a stage and a difficulty
	 * @param hard
	 * @param mainStage
	 * @throws IOException 
	 */
	public StartController(WelcomeController welcomeC, Difficulty difficulty) throws IOException {

		_welcomeC = welcomeC;

		Stage mainStage = welcomeC.getMainStage();

		MAIN_STAGE = mainStage;
		MENU_SCENE = mainStage.getScene();



		//Setting upu the START_SCENE field and the _view field
		FXMLLoader loader = new FXMLLoader(getClass().getResource("views/start.fxml"));
		Pane pane = (Pane) loader.load();
		_view = loader.getController(); 
		_view.setController(this);
		START_SCENE = new Scene(pane);
		show();


		//Setting up the menu

		_model = new Start(this, difficulty);
		_view.setLevel(difficulty);
		_view.setModel(_model);
		_view.addToList(_model.addToList(null));

	}
	
	public Pane getShowingPane() {
		return _view.getMainPane();
	}

	/**
	 * Goes back to the menu
	 */
	public void backToMenu() {
		MAIN_STAGE.setScene(MENU_SCENE);
		try {
			_welcomeC.show();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public void startGame() {
		try {



			Difficulty difficulty = _model.getDifficulty();
			_gameC = new PlayController(difficulty, this);

			try {
				_gameC.show();
			} 
			catch (IOException e) {

			}


		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void addToList(String str) {
		_view.addToList(_model.addToList(str));
	}



	public void show(Difficulty difficulty) {
		
		show();
		_view.setLevel(difficulty);
		_model.setDifficulty(difficulty);
	}
	
	
	/**
	 * Shows the scene
	 */
	public void show() {
		WelcomeController.setShowingController(this);
		MAIN_STAGE.setScene(START_SCENE);

	}

	/**
	 * Changes the colour of the button
	 * @param event the source of which is the button
	 */
	public void changeColour(MouseEvent event) {
		_welcomeC.changeColour(event);
	}

	/**
	 * Changes the colour of the button back
	 * @param event
	 */
	public void changeColourBack(MouseEvent event) {
		_welcomeC.changeColourBack(event);
	}


}
