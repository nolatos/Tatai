package tatai;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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
			_model = new Result(this, score, total);
			_view.set(this, _model);
			_view.setLevelLabel(difficulty);

		}
		catch (Exception e) {
			e.printStackTrace();
		}

		_view.setScoreLabel();


	}

	public void show() throws IOException {
		//Setting up the showing Pane
		WelcomeController.setShowingController(this);

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
		_playC.restart();
		try {
			_playC.show();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
