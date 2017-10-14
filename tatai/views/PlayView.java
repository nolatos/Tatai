package tatai.views;

import java.util.Optional;
import java.util.Timer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import tatai.*;
import tatai.models.Play;


public class PlayView {
	

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

    @FXML
    private Button _nextLevel;
    
    @FXML
    private Button _retryLevel;

    private PlayController _controller;
    private Play _model;
    
    @FXML
    private Button _playBack;
    
    @FXML
    void retryLevel(ActionEvent event) {
    	restart();
    }
    
    @FXML
    void nextLevel(ActionEvent event) {

    	_controller.nextLevel();
    	restart();

    }
    
    @FXML
    void next(ActionEvent event) {
    	_controller.advance();
    }

    @FXML
    void record(ActionEvent event) {

    	_controller.record();
    	_record.setDisable(true);
    }

    @FXML
    void retry(ActionEvent event) {
    	
    	
    }
    
    @FXML
    void checkAns(ActionEvent event) {
    	
    }
    
    @FXML
    /**
     * Goes back to the start screen
     * @param event
     */
    void back(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirm quit");
    	alert.setHeaderText("Are you sure you want to go back?");
    	alert.setContentText("Any unsaved progress will be lost");
    	Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
	    	_controller.backToStart();
		}
		
    }
    
    /**
     * Restarts the game
     */
    public void restart() {
    	
    	_record.setVisible(true);
    	_retry.setVisible(false);
    	_nextLevel.setVisible(false);
    	setImage(_model.getNumber());
    	_progress.setProgress((double) 1 / _model.TOTAL_QUESTIONS);
    	_retryLevel.setVisible(false);
//    	updateMenuLabel();
    }
    
    
    
    public void terminate(int score) {
    	
    	
    	_numberLabel.setText("" + score + "/" + _model.TOTAL_QUESTIONS);
    	_record.setVisible(false);
    	_retryLevel.setVisible(true);
    	_nextLevel.setVisible(true);

//    	_controller.canLevelUp();
    	
//    	if (_model.getScore() >= 8 && _controller.canLevelUp()) {
//     		_nextLevel.setDisable(false);
//     	}
//     	else {
//     		_nextLevel.setDisable(true);
//     	}
    	

//    	
//    	if (_model.getScore() >= 8 && _controller.canLevelUp()) {
//    		_nextLevel.setDisable(false);
//    	}
//    	else {
//    		_nextLevel.setDisable(true);
//    	}
    	
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
    	_numberLabel.setText("" + i);
    }

    /**
     * Enables the record button
     * And turns off the recording label
     */
    public void enableRecord() {
    	_record.setDisable(false);
    	_recordingLabel.setVisible(false);
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
    void playBack(ActionEvent event) {

    }
}
