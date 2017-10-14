//package tatai.views;
//
//import javafx.application.Platform;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import tatai.*;
//
//public class RecordView {
//
//	private RecordController _controller;
//	
//    @FXML
//    private Label _recording;
//
//    @FXML
//    private Label _correct;
//
//    @FXML
//    private Label _incorrect;
//
//    @FXML
//    private Label _youSaid;
//
//    @FXML
//    private Label _whatYouSaid;
//
//    @FXML
//    private Button _retry;
//
//    @FXML
//    private Button _next;
//
//    @FXML
//    private Button _seeCorrect;
//
//    @FXML
//    private Label _whoops;
//
//    @FXML
//    private Label _answerWas;
//
//    @FXML
//    void next(ActionEvent event) {
//
//    	revertToOriginal();
//    	_controller.next();
//    	_controller.setRetried(false);
//
//    }
//
//    @FXML
//    void retry(ActionEvent event) {
//    	revertToOriginal();
//    	
//    	_controller.closeRecorderStage();
//    }
//    
//    
//
//    @FXML
//    void seeCorrect(ActionEvent event) {
//    	_seeCorrect.setVisible(false);
//    	_next.setVisible(true);
//    	_controller.setRetried(false);
//    	_youSaid.setVisible(false);
//    	_answerWas.setVisible(true);
//    	_whatYouSaid.setText(_controller.getCurrentNumberString());
//    }
//    
//    
//    
//    
//    
//    /**
//     * Reverts the window back to its original state
//     */
//    private void revertToOriginal() {
//    	_recording.setVisible(true);
//    	_correct.setVisible(false);
//		_next.setVisible(false);
//
//    	_incorrect.setVisible(false);
//		_youSaid.setVisible(false);
//		_retry.setVisible(false);
//		_whatYouSaid.setVisible(false);
//		_answerWas.setVisible(false);
//		_whoops.setVisible(false);
//    }
//    
//    
//    /**
//     * Shows recording has ended
//     * @param correct whether or not the word said was correct
//     * @param retried whether or not it has been retried before
//     * @param recognised the word the recording recognised
//     */
//    public void recordingEnded(boolean correct, boolean retried, String recognised) {
//    	_recording.setVisible(false);
//    	//If it is correct, we display the "correct" screen
//    	if (correct) {
//    		_correct.setVisible(true);
//    		_next.setVisible(true);
//    	}
//    	else {
//    		
//    		
//    		_youSaid.setVisible(true);
//    		
//    		Platform.runLater(new Runnable() {
//    			@Override
//    			public void run() {
//    				_whatYouSaid.setText(recognised);
//    			}
//    		});
//    		
//    		_whatYouSaid.setVisible(true);
//    		
//    		if (retried == false) {
//    			_incorrect.setVisible(true);
//    			_retry.setVisible(true);
//    		}
//    		else {
//    			_whoops.setVisible(true);
//    			_seeCorrect.setVisible(true);
//    		}
//    	}
//    }
//    
//    
//    
//    
//    
//    
//    //Getters & Setters
//    public void setController(RecordController controller) {
//    	_controller = controller;
//    }
//
//}
