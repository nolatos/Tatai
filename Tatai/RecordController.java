//package tatai;
//
//import java.io.IOException;
//
//import javafx.application.Platform;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.layout.Pane;
//import tatai.views.*;
//import tatai.models.*;
//
//public class RecordController {
//	
//	private Record _model;
//	private final PlayController _playC;
//	public final Scene RECORD_SCENE;
//	
//	@FXML
//	private RecordView _view;
//	
//	/**
//	 * Sets up a record
//	 * @param playC
//	 * @throws IOException 
//	 */
//	public RecordController(PlayController playC) throws IOException {
//		_playC = playC;
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("views/record.fxml"));
//		
//		Pane pane = (Pane)loader.load();
//		RECORD_SCENE = new Scene(pane);
//		_view = loader.getController();
//		_view.setController(this);
//		_model = new Record(this);
//			
//		
//	}
//	
//	/**
//	 * Sets whether or not the user has retried a number
//	 * @param b
//	 */
//	public void setRetried(boolean b) {
//		_model.setRetried(b);
//	}
//
//
//	
//	/**
//	 * Shows that the recording has ended.
//	 * @param correct whether the number said was correct or not
//	 */
//	public void recordingEnded(boolean correct) {
//		_view.recordingEnded(correct, _model.getRetried(), _playC.getRecognised());
//		_model.setRetried(true);
////		_playC.enableRecord(correct);
//	}
//	
//	
//	public void updateMenuLabel() {
////		_gameC.setHard(!_game.canLevelUp());
//	}
//
//
//
//	public void next() {
//		_playC.advance();
//		_model.setRetried(false);
//		_playC.closeRecorderStage();
//	}
//    
//	 
//
//
//	public String getCurrentNumberString() {
//		return _playC.getNumber();
//	}
//
//	
//	public void closeRecorderStage() {
//		_playC.closeRecorderStage();
//	}
//
//
//    
//
//}
//
