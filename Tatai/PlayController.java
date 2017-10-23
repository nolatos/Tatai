package tatai;



import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Optional;

import javafx.concurrent.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import tatai.models.*;
import tatai.utils.SpeechRecognition;
import tatai.utils.UserData;
import tatai.views.*;

public class PlayController implements Controller {

	//Private fields
	private Play _model;
	private Background _background = new Background();
	private StartController _startC;
	private Scene _playScene;




	@FXML
	private PlayView _view;

	/**
	 * Constructor that takes in a difficulty parameter
	 * @param difficulty the difficulty of the game
	 */
	public PlayController(Difficulty difficulty, StartController startC) {
		_startC = startC;

		//Initialising the model and viewer
		_model = new Play(difficulty, this);

		FXMLLoader loader = new FXMLLoader(getClass().getResource("views/play.fxml"));	
		try {

			//Setting up the play scene			
			Pane pane = (Pane) loader.load();
			_playScene = new Scene(pane);

			_view = loader.getController();
			_view.setController(this);
			_view.setModel(_model);

		} 
		catch (IOException e) {
			e.printStackTrace();
		}		



	}

	/**
	 * Returns the pane that is currently showing
	 */
	public Pane getShowingPane() {
		return _view.getMainPane();
	}

	public void backToStart() {
		_startC.show();

	}


	/**
	 * Restarts a new game
	 */
	public void restart() {
		_view.restart();
		_model.reset();
	}

	public StartController getStartController() {
		return _startC;
	}

	/**
	 * Increases to the next level
	 */
	public void nextLevel() {
		Difficulty difficulty = Difficulty.valueOf(_model.levelUp());
		_view.restart();
		_startC.show(difficulty);	

	}


	/**
	 * Sets the image and the _number field
	 * CHANGE LATER
	 * @param i the image we are setting to
	 */
	public void setImage(int i) {
		_view.setImage(i);
	}

	public void setImage(String equation) {
		_view.setImage(equation);
	}

	public void setImage(URL url) {
		_view.setImage(url);
	}


	/**
	 * Shows the game screen
	 */
	public void show() throws IOException {
		WelcomeController.setShowingController(this);
		_startC.MAIN_STAGE.setScene(_playScene);
		_model.setQuestion();

	}




	/**
	 * Starts the recording in the background
	 */
	public void record() {

		
		_background.restart();
		_model.getCurrentQuestion().setRecognisedString(_model.getRecognised());
	}

	/**
	 * Advances to next number
	 */
	public void advance() {
		_model.advance();

		int progress = _model.getProgress();
		if (progress > _model.TOTAL_QUESTIONS) {
			terminate();
		}
		else {
			_view.progress();
			_model.setQuestion();
		}  	
	}





	public boolean canLevelUp() {
		return _model.canLevelUp();
	}

	/**
	 * Ends the game
	 */
	private void terminate() {
		
		//Terminating the view
		_view.terminate(_model.getScore());

		//Adding the stats
		_startC.addToList("Score: " + _model.getScore() + "/" + _model.TOTAL_QUESTIONS + 
				", Diffculty: " + String.valueOf(_model.getDifficulty()));
		ResultController resultC = new ResultController(this, _startC, _model.getScore(),
				_model.TOTAL_QUESTIONS, _model.getDifficulty());
		resultC.setTableItems(_model.getQuestions());
		UserData.updateHistory(_model.getScore(), _model.getDifficulty());
		UserData.updateStats(_model.getScore(), _model.getDifficulty());
		resultC.show();
	}




	public String getRecognised() {
		return _model.getRecognised();
	}

	

	public int getProgress() {
		return _model.getProgress();
	}

	/**
	 * Returns the number currently showing as a string
	 * @return 
	 */
	public String getNumber() {
		return SpeechRecognition.translation(_model.getCurrentNumber());
	}

	public void changeColour(MouseEvent event) {
		_startC.changeColour(event);
	}

	public void changeColourBack(MouseEvent event) {
		_startC.changeColourBack(event);
	}


	class Background extends Service<Void> {




		public Task<Void> createTask() {

			Task<Void> t =  new Task<Void>() {
				@Override
				public Void call() {
					try {


						//Showing the "recording" dialog		      
						_model.setRecognised(SpeechRecognition.runVoiceRecognition());

						_view.recordingEnded();



					}
					catch (Exception e) {
						e.printStackTrace();
					}

					return null;
				}
			};

			reset();
			return t;


		}


	}






}


