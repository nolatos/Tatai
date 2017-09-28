package tatai;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class RecordController {

    @FXML
    private Label _recording;

    @FXML
    private Label _correct;

    @FXML
    private Label _incorrect;

    @FXML
    private Label _youSaid;

    @FXML
    private Button _retry;

    @FXML
    private Button _next;
    
    @FXML
    private Label _whatYouSaid;
    
    @FXML
    private PlayController _playC;
    
    @FXML
    private GameController _gameC;
    
    @FXML
    private Button _seeCorrect;

    @FXML
    private Label _whoops;

    @FXML
    void retry(ActionEvent event) {
    	advance();
    	
    }
    
    @FXML
    void next(ActionEvent event) {
    	advance();
    	_playC.advance();
    }
    
    @FXML
    void seeCorrect(ActionEvent event) {
    	
    }
    
    //Shows that either retry or next was pressed
    private void advance() {
    	_recording.setVisible(true);
    	_correct.setVisible(false);
		_next.setVisible(false);
    	_playC.closeRecorderStage();
    	_playC.enableRecord();
    	_incorrect.setVisible(false);
		_youSaid.setVisible(false);
		_retry.setVisible(false);
		_whatYouSaid.setVisible(false);
    }

    /**
     * Allows interaction with the PlayController
     * @param play
     */
    public void setPlay(PlayController play) {
    	_playC = play;
    }
    
    public void recordingEnded(boolean correct) {
    	_recording.setVisible(false);
    	//If it is correct, we display the "correct" screen
    	if (correct) {
    		_correct.setVisible(true);
    		_next.setVisible(true);
    	}
    	else {
    		_incorrect.setVisible(true);
    		_youSaid.setVisible(true);
    		_retry.setVisible(true);
    		Platform.runLater(new Runnable() {
    			@Override
    			public void run() {
    				_whatYouSaid.setText(_playC.getRecognised());
    			}
    		});
    		
    		_whatYouSaid.setVisible(true);
    	}
    }
    
    
    
    public void setGameController(GameController g) {
    	_gameC = g;
    }

}

