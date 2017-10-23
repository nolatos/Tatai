package tatai.views;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import tatai.Difficulty;
import tatai.StatsController;
import tatai.models.Stats;

public class StatsView {
	
	@FXML
	private Pane _mainPane;

    @FXML
    private Button _previousLevel;

    @FXML
    private Button _nextLevel;

    @FXML
    private Label _levelLabel;

    @FXML
    private Label _totalGames;

    @FXML
    private Label _averageScore;

    @FXML
    private Button _back;
    
    @FXML
    private Label _highScore;

    @FXML
    private Button _clear;
    
    private StatsController _controller;
    private Stats _model;
    

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
    	_controller.next();
    }

    @FXML
    void previous(ActionEvent event) {
    	_controller.previous();
    }
    
    @FXML
    void clearStats() {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setHeaderText("Are you sure you want to clear all stats?");
    	alert.setContentText("THIS CANNOT BE UNDONE");
    	Optional<ButtonType> option = alert.showAndWait();
    	if (option.get() == ButtonType.OK) {
    		_controller.clearStats();
    		_model.update();
    		this.setLabels(_model.getDifficulty(), _model.getTotalGames(),
    				_model.getAverage(), _model.getHighScore());
    	}
    }
    
    /**
     * Sets the model and the controller
     * @param controller
     * @param model
     */
    public void set(StatsController controller, Stats model) {
    	_controller = controller;
    	_model = model;
    }
    
    /**
     * Displays the different stats
     * @param difficulty
     * @param totalGames
     * @param average
     */
    public void setLabels(Difficulty difficulty, int totalGames, int average, int highScore) {
    	_levelLabel.setText("" + difficulty);
    	_totalGames.setText("" + totalGames);
    	_averageScore.setText("" + average);
    	_highScore.setText("" + highScore);
    }
    
    /**
     * Returns the pane that will be faded out
     * @return
     */
    public Pane getShowingPane() {
    	return _mainPane;
    }
    
    

}
