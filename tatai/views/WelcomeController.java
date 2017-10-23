package tatai.views;

import java.io.IOException;
import java.util.Optional;

import javax.swing.JOptionPane;

import javafx.animation.*;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import tatai.*;
import tatai.utils.UserData;

public class WelcomeController implements Controller {

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
	private Button _instructions;

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

	@FXML
	private Label _welcomeLabel;

	private Thread _audioClipThread;
	private MediaPlayer _clip;

	private Scene _menuScene;

	private boolean _started = false;
	private Scene _startScene;

	private static Controller _showingController;

	private boolean _audioPlaying = false;;

	@FXML
	private Button _stats; 

	@FXML
	private Button _about;

	
	@FXML
	void aboutPressed(ActionEvent event) {
		AboutController aboutC = new AboutController(this);
		aboutC.show();
	}


	@FXML
	/**
	 * Shows the instructions page
	 * @param event
	 */
	void instructionsPressed(ActionEvent event) {
		InstructionController controller = new InstructionController(this);
		controller.show();
	}

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
			stopAudioClip();
			_enterC.show();
			try {
				UserData.storeUserData();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 

	}

	@FXML
	/**
	 * Plays the fade away animation then sets up the Practice Screen
	 * @param event
	 * @throws IOException
	 */
	void practicePressed(ActionEvent event) throws IOException {
		//Stopping bgm
		stopAudioClip();



		WelcomeController w = this;
		this.playFadeTransition(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					//Loading the practice
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

		StatsController statsC = new StatsController(this);
		try {
			statsC.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Starts the Welcome scene
	 */
	public void start() {

		_showingController = this;


		enableButtons();
		//Fading in the welcome label
		FadeTransition ft = new FadeTransition(Duration.millis(1000), _welcomeLabel);
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.setOnFinished(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				//Start audio clip
				startAudioClip();

				//Fade out the label
				FadeTransition ft = new FadeTransition(Duration.millis(1600), _welcomeLabel);
				ft.setFromValue(1);
				ft.setToValue(0);
				ft.setOnFinished(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						//Setting up the menu
						_welcomeLabel.setVisible(false);

						//Fade in the main pane
						FadeTransition ft = new FadeTransition(Duration.millis(100), _mainPane);
						ft.setFromValue(0);
						ft.setToValue(1);
						ft.setOnFinished(new EventHandler<ActionEvent>() {
							@Override 
							public void handle(ActionEvent event) {
								_logOut.setVisible(true);
							}
						});
						ft.play();
					}
				});
				ft.play();
			}
		});
		ft.play();
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
		_instructions.setVisible(false);
		_about.setVisible(false);
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
		_instructions.setVisible(true);
		_about.setVisible(true);
	}

	@FXML
	/**
	 * A level is selected
	 * @param e
	 * @throws Exception
	 */
	void levelPressed(ActionEvent e) throws Exception {
		stopAudioClip();

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
	 * Stops the audio clip
	 */
	public void stopAudioClip() {
		//		if (_audioPlaying) {
		//			_audioPlaying = false;
		//Ensuring the clip stops playing
		EventHandler<ActionEvent> onFinished = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				_clip.stop();
			}
		};
		//Fading it out
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.8), onFinished,
				new KeyValue(_clip.volumeProperty(), 0)));
		timeline.play();
		//		}

	}

	/**
	 * Starts the bgm
	 */
	public void startAudioClip() {
		//		if (!_audioPlaying) {
		//			_audioPlaying = true;
		//Starting the audio clip
		Task<Void> task = new Task<Void>() {
			@Override
			public Void call() {

				_clip.setCycleCount(MediaPlayer.INDEFINITE);
				_clip.setVolume(0.2);
				_clip.play();
				return null;
			}
		};
		_audioClipThread = new Thread(task);
		_audioClipThread.setDaemon(true);
		_audioClipThread.start();
		//		}
	}

	/**
	 * Shows the menu scene
	 * and starts the bgm
	 * @throws Exception
	 */
	public void show() {
		startAudioClip();
		_mainStage.setScene(_menuScene);
		setShowingController(this);
		_mainPane.setOpacity(1);
	}



	/**
	 * Sets the field _menuScene
	 * @param scene
	 */
	void setScene(Scene scene) {
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
		playFadeTransition(handler, 1200);
	}

	private void playFadeTransition(EventHandler<ActionEvent> handler, int milliSeconds) {
		playFadeTransition(handler, milliSeconds, _mainPane);
	}

	private void playFadeTransition(EventHandler<ActionEvent> handler, int milliSeconds, Node node) {
		FadeTransition ft = new FadeTransition(Duration.millis(milliSeconds), node);

		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.setOnFinished(handler);

		ft.play();

	}

	/**
	 * Closes the application
	 */
	public void close() {
		this.stopAudioClip();
		try {
			UserData.storeUserData();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.playFadeTransition(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.exit(0);
			}
		}, 1300, _showingController.getShowingPane());

	}


	public void initialize() {
		_clip = new MediaPlayer(new Media(getClass().getResource("pokarekare.mp3").toString()));

	}


	public Pane getShowingPane() {
		return _mainPane;
	}


	public static void setShowingController(Controller controller) {
		_showingController = controller;
	}


	private void enableButtons() {
		Difficulty difficulty = UserData.highestDifficultyUnlocked();
		switch (difficulty) {
		case FIVE:
			_five.setDisable(false);
		case FOUR:
			_four.setDisable(false);
		case THREE:
			_three.setDisable(false);
		case TWO:
			_two.setDisable(false);
		default:
			break;
		}
	}

	/**
	 * Unlocks the level
	 * @param difficulty
	 */
	public void unlockLevel(Difficulty difficulty) {
		switch (difficulty) {
		case ONE:
			break;
		case TWO:
			_two.setDisable(false);
			break;
		case THREE:
			_three.setDisable(false);
			break;
		case FOUR:
			_four.setDisable(false);
			break;
		case FIVE:
			_five.setDisable(false);
			break;
		}
	}

}
