package tatai;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tatai.models.Stats;
import tatai.utils.UserData;
import tatai.views.*;

public class StatsController implements Controller {

	private WelcomeController _welcomeC;
	private StatsView _view;
	private Stats _model;
	private Scene _statsScene;

	/**
	 * Constructor takes in a WelcomeController object to interact with it
	 * @param controller
	 */
	public StatsController(WelcomeController controller) {
		_welcomeC = controller;

		try {
			//Setting up the welcome scene
			FXMLLoader loader = new FXMLLoader(getClass().getResource("views/stats.fxml"));
			Pane pane = (Pane) loader.load();
			_statsScene = new Scene(pane);

			//Setting up the model and view
			_view = loader.getController();
			_model = new Stats(this);
			_view.set(this, _model);

			//Setting up the correct stats
			setStatsLabels();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the model to the next level then updates stats;
	 */
	public void next() {
		_model.next();
		setStatsLabels();
	}

	/**
	 * Gets the model to the previous level then updates stats
	 */
	public void previous() {
		_model.previous();
		setStatsLabels();
	}

	public void show() {
		_welcomeC.getMainStage().setScene(_statsScene);
		WelcomeController.setShowingController(this);
	}

	public Pane getShowingPane() {
		return _view.getShowingPane();
	}

	public void backToMenu() {
		_welcomeC.show();

	}

	public void changeColour(MouseEvent event) {
		_welcomeC.changeColour(event);
	}

	public void changeColourBack(MouseEvent event) {
		_welcomeC.changeColourBack(event);
	}

	private void setStatsLabels() {
		_view.setLabels(_model.getDifficulty(), _model.getTotalGames(), 
				_model.getAverage(), _model.getHighScore());
	}
	
	/**
	 * Clears all statistics
	 */
	public void clearStats() {
		UserData.clearStats();
		_welcomeC.disableLevels();
	}
	
	public Stage getMainStage() {
		return _welcomeC.getMainStage();
	}
}
