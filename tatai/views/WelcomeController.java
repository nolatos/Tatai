package tatai.views;

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
import tatai.*;

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
	private StartController _startC;

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
	
	@FXML 
	private Button _back2;

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
	/**
	 * Shows the "play" level buttons
	 * @param event
	 */
	void playPressed(ActionEvent event) {

		//Level buttons visible
		_pick.setVisible(true);
		_one.setVisible(true);
		_two.setVisible(true);
		_three.setVisible(true);
		_four.setVisible(true);
		_five.setVisible(true);
		_back2.setVisible(true);
		
		//Menu buttons invisible
		_practice.setVisible(false);
		_play.setVisible(false);
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
		_one.setVisible(false);
		_two.setVisible(false);
		_three.setVisible(false);
		_four.setVisible(false);
		_five.setVisible(false);
		_back2.setVisible(false);

		//Menu buttons visible
		_practice.setVisible(true);
		_play.setVisible(true);
	}

	@FXML
	/**
	 * A level is selected
	 * @param e
	 * @throws Exception
	 */
	void levelPressed(ActionEvent e) throws Exception {

		if (_started == false) {

			_started = true;
			

			
			if (e.getSource().equals(_hard)) {
				_startC = new StartController(true, _mainStage, null);
			}
			else if (e.getSource().equals(_easy)){
				_startC = new StartController(false, _mainStage, null);
			}  
			else if (e.getSource().equals(_five)) {
				_startC = new StartController(null, _mainStage, Difficulty.FIVE);
			}
			else if (e.getSource().equals(_four)) {
				_startC = new StartController(null, _mainStage, Difficulty.FOUR);
			}
			else if (e.getSource().equals(_three)) {
				_startC = new StartController(null, _mainStage, Difficulty.THREE);
			}
			else if (e.getSource().equals(_two)) {
				_startC = new StartController(null, _mainStage, Difficulty.TWO);
			}
			else if (e.getSource().equals(_one)) {
				_startC = new StartController(null, _mainStage, Difficulty.ONE);
			}
			
		}
		else {
			_mainStage.setScene(_startScene);
			if (e.getSource().equals(_hard)) {
				_startC.show(true);;
			}
			else if (e.getSource().equals(_easy)){
				_startC.show(false);;
			}  
			else if (e.getSource().equals(_five)) {
				_startC.show(Difficulty.FIVE);
			}
			else if (e.getSource().equals(_four)) {
				_startC.show(Difficulty.FOUR);
			}
			else if (e.getSource().equals(_three)) {
				_startC.show(Difficulty.THREE);
			}
			else if (e.getSource().equals(_two)) {
				_startC.show(Difficulty.TWO);
			}
			else if (e.getSource().equals(_one)) {
				_startC.show(Difficulty.ONE);
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
