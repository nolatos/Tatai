package tatai;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import tatai.views.*;

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
    private Label _answerWas;
    
    @FXML
//    private RecordView _view;
    
    private boolean _retried = false; //Whether or not the person has already retried the number

    @FXML
    void retry(ActionEvent event) {
    	advance();
    	
    }
    
    @FXML
    void next(ActionEvent event) {
    	advance();
    	_playC.advance();
    	_retried = false;
    	
    	
    }
    
    @FXML
    void seeCorrect(ActionEvent event) {
    	_seeCorrect.setVisible(false);
    	_next.setVisible(true);
    	_retried = false;
    	_youSaid.setVisible(false);
    	_answerWas.setVisible(true);
    	_whatYouSaid.setText( _playC.getNumber());
    	
    	
    }
    


    /**
     * Reverts the window back to its original state
     */
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
		_answerWas.setVisible(false);
		_whoops.setVisible(false);
    }

    /**
     * Allows interaction with the PlayController
     * @param play
     */
    public void setPlay(PlayController play) {
    	_playC = play;
    }
    
    /**
     * Shows that the recording has ended and decides what to do from there
     * @param correct
     */
    public void recordingEnded(boolean correct) {
    	_recording.setVisible(false);
    	//If it is correct, we display the "correct" screen
    	if (correct) {
    		_correct.setVisible(true);
    		_next.setVisible(true);
    	}
    	else {
    		
    		
    		_youSaid.setVisible(true);
    		
    		Platform.runLater(new Runnable() {
    			@Override
    			public void run() {
    				_whatYouSaid.setText(_playC.getRecognised());
    			}
    		});
    		
    		_whatYouSaid.setVisible(true);
    		
    		if (_retried == false) {
    			_incorrect.setVisible(true);
    			_retried = true;
    			_retry.setVisible(true);
    		}
    		else {
    			_whoops.setVisible(true);
    			_seeCorrect.setVisible(true);
    		}
    	}
    }
    
    
    /**
     * 
     * @param g
     */
    public void setGameController(GameController g) {
    	_gameC = g;
    }
    
    

}

