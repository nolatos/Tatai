package tatai.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import tatai.*;

public class RecordView {

    @FXML
    private Label _recording;

    @FXML
    private Label _correct;

    @FXML
    private Label _incorrect;

    @FXML
    private Label _youSaid;

    @FXML
    private Label _whatYouSaid;

    @FXML
    private Button _retry;

    @FXML
    private Button _next;

    @FXML
    private Button _seeCorrect;

    @FXML
    private Label _whoops;

    @FXML
    private Label _answerWas;

    @FXML
    void next(ActionEvent event) {

    	advance();
    }

    @FXML
    void retry(ActionEvent event) {
    	advance();
    	
    }

    @FXML
    void seeCorrect(ActionEvent event) {
    	_seeCorrect.setVisible(false);
    	_next.setVisible(true);
//    	_retried = false;
    	_youSaid.setVisible(false);
    	_answerWas.setVisible(true);
//    	_whatYouSaid.setText( _playC.getNumber());
    }
    
    /**
     * Reverts the window back to its original state
     */
    private void advance() {
    	_recording.setVisible(true);
    	_correct.setVisible(false);
		_next.setVisible(false);
//    	_playC.closeRecorderStage();
//    	_playC.enableRecord();
    	_incorrect.setVisible(false);
		_youSaid.setVisible(false);
		_retry.setVisible(false);
		_whatYouSaid.setVisible(false);
		_answerWas.setVisible(false);
		_whoops.setVisible(false);
    }

}
