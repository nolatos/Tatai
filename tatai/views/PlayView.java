package tatai.views;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.Timer;

import javafx.animation.FadeTransition;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import tatai.*;
import tatai.models.Play;
import tatai.utils.SpeechRecognition;


public class PlayView {


	@FXML
	private Pane _mainPane;

	@FXML
	private ProgressBar _progress;

	@FXML
	private Label _recordingLabel;

	@FXML
	private Button _record;

	@FXML
	private ImageView _image;

	@FXML
	private Label _numberLabel;

	@FXML
	private Button _retry;

	@FXML
	private Button _menu;

	@FXML
	private Button _next;
	//
	//	@FXML
	//	private Button _nextLevel;
	//
	//	@FXML
	//	private Button _retryLevel;

	@FXML
	private Button _check;

	@FXML
	private Button _skip;

	@FXML
	private Label _correct;

	@FXML
	private Label _tryAgain;

	@FXML
	private Label _sorry;

	@FXML
	private Label _tens;

	@FXML
	private Label _maa;

	@FXML
	private Label _ones;

	@FXML
	private Label _answerWas;

	@FXML
	private Label _kaPai;

	private PlayController _controller;
	private Play _model;

	@FXML
	private Button _playBack;

	
	
	@FXML
	/**
	 * Advances to next number. Turns off/on relevant components
	 * @param event
	 */
	void next(ActionEvent event) {
		_model.setRetry(false);


		_record.setVisible(true);
		_skip.setVisible(true);
		_numberLabel.setVisible(true);

		_sorry.setVisible(false);
		_correct.setVisible(false);
		_kaPai.setVisible(false);
		_tryAgain.setVisible(false);
		_next.setVisible(false);
		_controller.advance();
		_tens.setVisible(false);
		_maa.setVisible(false);
		_ones.setVisible(false);
		_answerWas.setVisible(false);

	}

	@FXML
	void record(ActionEvent event) {
		_controller.record();
		_record.setDisable(true);
		_recordingLabel.setVisible(true);
		_skip.setDisable(true);
		
		_playBack.setDisable(true);
		_check.setDisable(true);
	}

	@FXML 
	/**
	 * Will act as if you got it wrong
	 * @param event
	 */
	void skip(ActionEvent event) {
		_model.setRetry(true);

		_record.setVisible(false);
		_check.setVisible(false);
		_skip.setVisible(false);
		_playBack.setVisible(false);
		this.displayIncorrectScreen();
	}

	@FXML
	/**
	 * Retries the number. Turns
	 * @param event
	 */
	void retry(ActionEvent event) {


		_model.setRetry(true);

		_record.setVisible(true);
		_skip.setVisible(true);

		if (_model.getDifficulty() == Difficulty.FIVE) {
			_numberLabel.setVisible(false);
			_image.setVisible(true);
		}
		else {
			_numberLabel.setVisible(true);
			_image.setVisible(false);

		}

		_correct.setVisible(false);
		_kaPai.setVisible(false);
		_next.setVisible(false);

		_sorry.setVisible(false);
		_tryAgain.setVisible(false);
		_retry.setVisible(false);
		_next.setVisible(false);

	}

	@FXML
	/**
	 * Checks the answer. Also turns relevant buttons on/off
	 * @param event
	 */
	void checkAns(ActionEvent event) {
		if (_model.updateScore()) {
			displayCorrectScreen();
		}
		else {
			displayIncorrectScreen();
		}

		_record.setVisible(false);
		_check.setVisible(false);
		_skip.setVisible(false);
		_playBack.setVisible(false);



	}

	@FXML
	/**
	 * Goes back to the start screen
	 * @param event
	 */
	void back(ActionEvent event) {
		if (_model.getProgress() <= _model.TOTAL_QUESTIONS) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirm quit");
			alert.setHeaderText("Are you sure you want to go back?");
			alert.setContentText("Any unsaved progress will be lost");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				_controller.backToStart();

			}			
		}
		else {
			_controller.backToStart();
		}

	}

	/**
	 * Restarts the game
	 */
	public void restart() {

		_record.setVisible(true);
		_retry.setVisible(false);
		//		_nextLevel.setVisible(false);
		_model.setQuestion();
		_progress.setProgress((double) 1 / _model.TOTAL_QUESTIONS);
		//		_retryLevel.setVisible(false);
		_skip.setVisible(true);
	}



	public void terminate(int score) {


		_numberLabel.setText("" + score + "/" + _model.TOTAL_QUESTIONS);
		_record.setVisible(false);
		//		_retryLevel.setVisible(true);
		//		_nextLevel.setVisible(true);
		_skip.setVisible(false);
		_check.setVisible(false);

		//		if (_model.getScore() >= 0.8 * _model.TOTAL_QUESTIONS && _controller.canLevelUp()) {
		//			_nextLevel.setDisable(false);
		//		}
		//		else {
		//			_nextLevel.setDisable(true);
		//		}


	}

	public void setModel(Play play) {
		_model = play;
	}

	/**
	 * Sets the image and the _number field
	 * CHANGE LATER
	 * @param i the image we are setting to
	 */
	public void setImage(int i) {
		setImage("" + i);
	}

	/**
	 * Sets the image
	 * @param str the equation we are displaying
	 */
	public void setImage(String str) {
		_numberLabel.setText(str);
		_image.setVisible(false);
		_numberLabel.setVisible(true);
	}

	public void setImage(URL url) {
		_image.setImage(new Image(url.toString()));
		_image.setVisible(true);
		_numberLabel.setVisible(false);
	}



	/**
	 * Increases progress bar
	 */
	public void progress() {
		_progress.setProgress((double)_model.getProgress() / _model.TOTAL_QUESTIONS);
	}

	public void setController(PlayController controller) {
		_controller = controller;
	}

	@FXML
	void changeColour(MouseEvent event) {
		_controller.changeColour(event);
	}

	@FXML
	void changeColourBack(MouseEvent event) {
		_controller.changeColourBack(event);
	}



	@FXML
	/**
	 * Plays back the sound on a new thread
	 * @param event
	 */
	void playBack(ActionEvent event) {
		_playBack.setDisable(true);
		_record.setDisable(true);

		Task<Void> task = new Task<Void>() {
			@Override
			public Void call() {
				SpeechRecognition.playback();
				_playBack.setDisable(false);
				_record.setDisable(false);
				return null;
			}
		};
		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start(); 

	}

	/**
	 * Enables the correct button and turns off the "recording" label.
	 * Also sets the skip button to invisible
	 * and sets the playback and check buttons to visible
	 * @param correct
	 */
	public void recordingEnded() {
		_record.setDisable(false);
		_skip.setDisable(false);
		_skip.setVisible(false);
		_recordingLabel.setVisible(false);  

		_playBack.setVisible(true);
		_playBack.setDisable(false);
		_check.setDisable(false);
		_check.setVisible(true);
	}


	/**
	 * Displays the "correct"
	 */
	private void displayCorrectScreen() {
		_numberLabel.setVisible(false);
		_image.setVisible(false);
		_correct.setVisible(true);
		_kaPai.setVisible(true);
		_next.setVisible(true);
	}



	private void displayIncorrectScreen() {
		//Getting rid of the number Label
		_numberLabel.setVisible(false);
		_image.setVisible(false);
		if (_model.retried()) {
			_sorry.setVisible(true);
			_next.setVisible(true);

			//Setting up the answer was part
			if (_model.getCurrentNumber() <= 10) {
				_ones.setText(SpeechRecognition.translation(_model.getCurrentNumber()));
			}
			else if (_model.getCurrentNumber() % 10 == 0){

				_ones.setText(SpeechRecognition.translation(_model.getCurrentNumber()));
			}
			else {
				int tens = _model.getCurrentNumber() / 10 * 10;
				_tens.setText(SpeechRecognition.translation(tens));
				_tens.setVisible(true);
				_maa.setVisible(true);
				_ones.setText(SpeechRecognition.translation(_model.getCurrentNumber() % 10));
			}
			_ones.setVisible(true);
			_answerWas.setVisible(true);
		}
		else {
			_retry.setVisible(true);
			_tryAgain.setVisible(true);
		}



	}



	public Pane getMainPane() {
		return _mainPane;
	}

}
