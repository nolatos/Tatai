package tatai;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import tatai.math.Question;
import tatai.models.Result;
import tatai.views.*;

public class ResultController implements Controller {

	private PlayController _playC;
	private StartController _startC;

	private Scene _resultScene;
	private ResultView _view;
	private Result _model;

	/**
	 * Constructor takes in two different controllers
	 * @param playC to get the results from
	 * @param startC to switch scenes to
	 */
	public ResultController(PlayController playC, StartController startC, 
			int score, int total, Difficulty difficulty) {
		_playC = playC;
		_startC = startC;

		//Setting up views and model
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("views/results.fxml"));
			Pane pane = (Pane) loader.load();
			_view = loader.getController();
			_resultScene = new Scene(pane);
			_model = new Result(this, score, total, difficulty);
			_view.set(this, _model);
			_view.setLevelLabel(difficulty);

		}
		catch (Exception e) {
			e.printStackTrace();
		}

		_view.setScoreLabel();


	}

	public void setTableItems(ObservableList<Question> questions) {
		_view.setTableItems(questions);
	}

	public void show() {
		//Setting up the showing Pane
		WelcomeController.setShowingController(this);
		_startC.getWelcomeController().startAudioClip();

		_startC.MAIN_STAGE.setScene(_resultScene);
	}

	public Pane getShowingPane() {
		return _view.getShowingPane();
	}

	public void changeColour(MouseEvent event) {
		_startC.changeColour(event);
	}

	public void changeColourBack(MouseEvent event) {
		_startC.changeColourBack(event);
	}

	/**
	 * Advances to the next Level
	 */
	public void nextLevel() {

		_playC.nextLevel();
	}

	/**
	 * Goes back to the menu
	 */
	public void backToMenu() {
		_startC.backToMenu();
	}

	/**
	 * 
	 */
	public void retry() {
		stopAudioClip();
		_playC.restart();
		try {
			_playC.show();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean canLevelUp() {
		return _playC.canLevelUp();
	}

	public void stopAudioClip() {
		_startC.getWelcomeController().stopAudioClip();
	}

	/**
	 * Undisables the relevant button
	 */
	public void unlockLevel() {
		Difficulty difficulty = _model.getDifficulty().levelUp();
		if (difficulty != null) {
			_startC.getWelcomeController().unlockLevel(difficulty);
		}
	}
}
