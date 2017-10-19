package tatai.views;

import java.io.IOException;
import java.util.Optional;

import javax.swing.JOptionPane;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import tatai.*;

public class WelcomeController {

	@FXML
	private EnterController _enterC;

	@FXML
	private Button _practice;

	@FXML
	private Button _play; 

	private Stage _mainStage;

	@FXML
	private Button _logOut;

	@FXML
	private ImageView _levels;
	
	@FXML
	private Label _mathLabel;
	
	@FXML
	private Label _pronunciationLabel;


	@FXML
	private Button _back;


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
	private Pane _mainPane;


	private Scene _menuScene;

	private boolean _started = false;
	private Scene _startScene;

	@FXML
	private Button _stats;

	@FXML
	/**
	 * Takes the user back to the enter Screen
	 * @param event
	 */
	void logOut(ActionEvent event) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirm Logout");
		alert.setHeaderText("Are you sure you want to log out?");
		alert.setContentText("Any unsaved progress may be lost");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			_enterC.show();
		} 

	}

	@FXML
	/**
	 * Plays the fade away animation then sets up the Practice Screen
	 * @param event
	 * @throws IOException
	 */
	void practicePressed(ActionEvent event) throws IOException {
		WelcomeController w = this;
		this.playFadeTransition(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					//Loading the practice
					FXMLLoader loader = new FXMLLoader(getClass().getResource("practice.fxml"));
					Pane pane = (Pane) loader.load();
					Scene scene = new Scene(pane);
					PracticeController controller = new PracticeController(w);
					controller.show();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@FXML
	void statsPressed(ActionEvent event) {

	}


	public void setEnterController(EnterController e) {
		_enterC = e;
	}

	@FXML
	/**
	 * Shows the "play" level buttons
	 * @param event
	 */
	void playPressed(ActionEvent event) {

		//Level buttons visible
		//		_pick.setVisible(true);
		_one.setVisible(true);
		_two.setVisible(true);
		_three.setVisible(true);
		_four.setVisible(true);
		_five.setVisible(true);
		_back.setVisible(true);
		_levels.setVisible(true);
		_mathLabel.setVisible(true);
		_pronunciationLabel.setVisible(true);
		
		//Menu buttons invisible
		_practice.setVisible(false);
		_play.setVisible(false);
		_stats.setVisible(false);
	}


	@FXML
	/**
	 * Takes you back to the menu
	 * @param e
	 */
	void backToMenu(ActionEvent e) {

		_back.setVisible(false);
		_one.setVisible(false);
		_two.setVisible(false);
		_three.setVisible(false);
		_four.setVisible(false);
		_five.setVisible(false);
		_levels.setVisible(false);
		_mathLabel.setVisible(false);
		_pronunciationLabel.setVisible(false);

		//Menu buttons visible
		_practice.setVisible(true);
		_play.setVisible(true);
		_stats.setVisible(true);

	}

	@FXML
	/**
	 * A level is selected
	 * @param e
	 * @throws Exception
	 */
	void levelPressed(ActionEvent e) throws Exception {
		WelcomeController c = this;

		//So that the thing only happens after the animation finishes playing
		this.playFadeTransition(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event ) {
				try {
					//Change the scenes
					if (_started == false) {

						_started = true;

						if (e.getSource().equals(_five)) {
							_startC = new StartController(c, Difficulty.FIVE);
							_startC.show();
						}
						else if (e.getSource().equals(_four)) {
							_startC = new StartController(c, Difficulty.FOUR);
							_startC.show();
						}
						else if (e.getSource().equals(_three)) {
							_startC = new StartController(c, Difficulty.THREE);
							_startC.show();
						}
						else if (e.getSource().equals(_two)) {
							_startC = new StartController(c, Difficulty.TWO);
							_startC.show();
						}
						else if (e.getSource().equals(_one)) {
							_startC = new StartController(c, Difficulty.ONE);
							_startC.show();
						}

					}
					else {
						_mainStage.setScene(_startScene);
						if (e.getSource().equals(_five)) {
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
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});


	}

	/**
	 * Shows the menu scene
	 * @throws Exception
	 */
	public void show() throws Exception {
		_mainStage.setScene(_menuScene);

		_mainPane.setOpacity(1);
	}


	/**
	 * Sets the field _menuScene
	 * @param scene
	 */
	public void setScene(Scene scene) {
		_menuScene = scene; 
	}

	/**
	 * Sets the main Stage where everything will be hosted on
	 * @param stage
	 */
	public void setStage(Stage stage) {
		_mainStage = stage;
	}


	@FXML
	public void changeColour(MouseEvent event) {

		Object obj = event.getSource();
		if (obj instanceof Button) {
			Button button = (Button) obj;
			((Button) obj).setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 10, 10); -fx-background-color: white;");
			button.setTextFill(Color.ORANGE);

		}
	}

	@FXML
	public void changeColourBack(MouseEvent event) {

		Object obj = event.getSource();
		if (obj instanceof Button) {
			Button button = (Button) obj;
			button.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 10, 10); -fx-background-color: orange;");
			button.setTextFill(Color.WHITE);
		}
	}

	public Stage getMainStage() {
		return _mainStage;
	}

	/**
	 * Plays the fade away animation. 
	 * @param handler determines what happens after animation finishes
	 */
	private void playFadeTransition(EventHandler<ActionEvent> handler) {
		FadeTransition ft = new FadeTransition(Duration.millis(600), _mainPane);

		ft.setFromValue(1.0);
		ft.setToValue(0.0);


		ft.play();
		ft.setOnFinished(handler);
	}


}
