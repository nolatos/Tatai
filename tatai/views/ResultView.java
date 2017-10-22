package tatai.views;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import tatai.Difficulty;
import tatai.ResultController;
import tatai.models.Result;

public class ResultView {

	@FXML
	private Pane _mainPane;

	@FXML
	private Label _numberLabel;

	@FXML
	private ImageView _resultLogo;

	@FXML
	private AnchorPane _resultPane;

	@FXML
	private TableView<?> _resultsTable;

	@FXML
	private TableColumn<?, ?> _questionCol;

	@FXML
	private TableColumn<?, ?> _weHeardCol;

	@FXML
	private TableColumn<?, ?> _correctCol;

	@FXML
	private Button _retry;

	@FXML
	private Button _next;

	@FXML
	private Label _levelLabel;

	@FXML
	private ImageView _congratsLogo;

	@FXML
	private Button _seeResults;

	@FXML
	private Label _finishingLabel;

	@FXML
	private Button _menu;

	private ResultController _controller;
	private Result _model;

	@FXML
	void backToMenu(ActionEvent event) {
		_controller.backToMenu();
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
	void next(ActionEvent event) {

		FadeTransition ft = new FadeTransition(Duration.millis(800), _mainPane);
		ft.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				_controller.nextLevel();
			}
		});
		ft.setFromValue(1);
		ft.setToValue(0);
		ft.play();

	}

	@FXML
	void retry(ActionEvent event) {
		_controller.retry();
	}

	@FXML
	/**'
	 * Displays the results page
	 * @param event
	 */
	void seeResults(ActionEvent event) {

		//Making relevant things invisible
		_numberLabel.setVisible(false);
		_congratsLogo.setVisible(false);
		_seeResults.setVisible(false);
		_finishingLabel.setVisible(false);
		
		_resultLogo.setVisible(true);
		_resultPane.setVisible(true);
		
	}


	public void set(ResultController controller, Result model) {
		_model = model;
		_controller = controller;
	}

	public Pane getShowingPane() {
		return _mainPane;
	}

	/**
	 * 
	 */
	public void setScoreLabel() {
		String str = "" + _model.getScore() + "/" + _model.getTotalQuestions();
		_numberLabel.setText(str);
		
		if (_model.getScore() >= 0.8 * _model.getTotalQuestions()) {
			_next.setDisable(false);
		}
		else {
			_next.setDisable(true);
		}
	}
	
	public void setLevelLabel(Difficulty difficulty) {
		_levelLabel.setText("" + difficulty);
	}
	
}
