package tatai;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class WelcomeController {

	@FXML
	private Pane _welcomePanel;

	@FXML
	private Button _practice;

	@FXML
	private Label _welcomeLabel;

	@FXML
	private Button _play; 

	private Stage _mainStage;

	@FXML
	private Button _easy;

	@FXML
	private Button _hard;

	@FXML
	private Button _back;

	@FXML
	private Label _pick;

	@FXML 
	private GameController _gameController;

	@FXML
	private Button _three;

	@FXML
	private Button _four;

	@FXML
	private Button _five;

	@FXML
	private Button _two;

	@FXML
	private Button _one;

	private Main _main;

	private boolean _started = false;
	private Scene _startScene;

	@FXML
	/**
	 * Event when practice is pressed. Opens up options easy and hard
	 * and closes the buttons practice and play
	 * @param event
	 */
	void practicePressed(ActionEvent event) {

		//Level buttons become visible
		_easy.setVisible(true);
		_hard.setVisible(true);
		_back.setVisible(true);
		_pick.setVisible(true);


		//Main Menu buttons become invisible
		_practice.setVisible(false);
		_play.setVisible(false);


	}

	@FXML
	void playPressed(ActionEvent event) {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Not available");
		alert.setHeaderText(null);
		alert.setContentText("Play is not available yet");
		alert.showAndWait();
	}


	@FXML
	/**
	 * Takes you back to the menu
	 * @param e
	 */
	void backToMenu(ActionEvent e) {

		//Making level buttons invisible
		_easy.setVisible(false);
		_hard.setVisible(false);
		_back.setVisible(false);
		_pick.setVisible(false);

		//Menu buttons visible
		_practice.setVisible(true);
		_play.setVisible(true);
	}

	@FXML
	/**
	 * A level is seleected
	 * @param e
	 * @throws Exception
	 */
	void levelPressed(ActionEvent e) throws Exception {

		if (_started == false) {

			_started = true;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
			Pane pane = (Pane) loader.load();


			_startScene = new Scene(pane);
			_mainStage.setScene(_startScene);
			_mainStage.setResizable(false);

			_mainStage.show();
			_gameController = loader.<GameController>getController();
			_gameController.setWelcomeController(this);
			_gameController.setStage(_mainStage);
			if (e.getSource().equals(_hard)) {
				_gameController.setHard(true);
			}
			else {
				_gameController.setHard(false);
			}  
		}
		else {
			_mainStage.setScene(_startScene);
			_gameController.setWelcomeController(this);
			_gameController.setStage(_mainStage);
			if (e.getSource().equals(_hard)) {
				_gameController.setHard(true);
			}
			else {
				_gameController.setHard(false);
			} 
		}
	}

	public void show() throws Exception {
		_main.showMenu(_mainStage);
	}

	public void setMain(Main main) {
		_main = main;
	}


	public void setStage(Stage stage) {
		_mainStage = stage;
	}
}
