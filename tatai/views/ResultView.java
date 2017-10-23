package tatai.views;

import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import tatai.Difficulty;
import tatai.ResultController;
import tatai.math.Question;
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
	private TableView<Question> _resultsTable;

	@FXML
	private TableColumn<Question, String> _questionCol;

	@FXML
	private TableColumn<Question, String> _weHeardCol;

	@FXML
	private TableColumn<Question, String> _correctCol;

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

	@FXML
	private Label _theGame;
	
	@FXML
	private ImageView _greatJob;

	@FXML
	private Button _continue;


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
		
		if (_controller.canLevelUp()) {
			_controller.stopAudioClip();
			FadeTransition ft = new FadeTransition(Duration.millis(1200), _mainPane);
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
		else {
			finishGame();
		}


	}

	@FXML
	void retry(ActionEvent event) {
		_controller.retry();
	}

	@FXML
	void finish(ActionEvent event) {
		FadeTransition ft = new FadeTransition(Duration.millis(800), _mainPane);
		ft.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				_controller.backToMenu();
			}
		});
		ft.setFromValue(1);
		ft.setToValue(0);
		ft.play();
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

	/**
	 * Displays the final congrats
	 */
	public void finishGame() {

		//Getting rid of the pane and the result logo
		this._resultLogo.setVisible(false);
		this._resultPane.setVisible(false);

		this._congratsLogo.setVisible(true);
		this._continue.setVisible(true);
		this._theGame.setVisible(true);
		this._greatJob.setVisible(true);
	}
	
	
	public void setTableItems(ObservableList<Question> questions) {
		System.out.println("hi");
		_questionCol.setCellValueFactory(new PropertyValueFactory<Question,String>("questionString"));
		_weHeardCol.setCellValueFactory(new PropertyValueFactory<Question, String>("recognisedString"));
		_correctCol.setCellValueFactory(new PropertyValueFactory<Question, String>("answerString"));
//		System.out.println(new PropertyValueFactory<Question, String>("_answerString").call());
		_resultsTable.setItems(questions);
	}

}
