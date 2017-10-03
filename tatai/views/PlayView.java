package tatai.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import tatai.*;
import tatai.models.Play;


public class PlayView {

    @FXML
    private Label _score;

    @FXML
    private Button _record;

    @FXML
    private ImageView _image;

    @FXML
    private Label _tempLabel;

    @FXML
    private Label _message;

    @FXML
    private Label _moveOn;

    @FXML
    private Button _retry;

    @FXML
    private Button _nextLevel;

    private PlayController _controller;
    private Play _model;
    
    @FXML
    void nextLevel(ActionEvent event) {

    	_controller.nextLevel();
    	restart();

    }

    @FXML
    void record(ActionEvent event) {

    	_controller.record();
    	_record.setDisable(true);
    }

    @FXML
    void retry(ActionEvent event) {

    	restart();
    }
    
    
    
    /**
     * Restarts the game
     */
    public void restart() {
    	
    	_record.setVisible(true);
    	_retry.setVisible(false);
    	_nextLevel.setVisible(false);
    	_message.setVisible(false);
    	_moveOn.setVisible(false);
    	setImage(_model.getNumber());
    	_score.setText("1/10");
//    	updateMenuLabel();
    }
    
    
    
    public void terminate(int score) {
    	
    	
    	_tempLabel.setText("" + score + "/10");
    	_record.setVisible(false);
    	_retry.setVisible(true);
    	_nextLevel.setVisible(true);
    	_message.setVisible(true);
    	_moveOn.setVisible(true);
    	

    	_controller.canLevelUp();
    	
    	if (_model.getScore() >= 8 && _controller.canLevelUp()) {
     		_nextLevel.setDisable(false);
     	}
     	else {
     		_nextLevel.setDisable(true);
     	}
    	

    	
    	if (_model.getScore() >= 8 && _controller.canLevelUp()) {
    		_nextLevel.setDisable(false);
    	}
    	else {
    		_nextLevel.setDisable(true);
    	}
    	
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
    	_tempLabel.setText("" + i);
    }

    
    public void enableRecord() {
    	_record.setDisable(false);
    }
    
    public void setLabel(String label) {
    	_score.setText(label);
    }
    
    public void setController(PlayController controller) {
    	_controller = controller;
    }
}
