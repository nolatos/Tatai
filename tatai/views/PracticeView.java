package tatai.views;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.concurrent.Task;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import tatai.*;
import tatai.models.*;
import tatai.utils.SpeechRecognition;

public class PracticeView {

	private PracticeController _controller;
	private Practice _model;
	
	
	@FXML
	private Pane _mainPane;
	
    @FXML
    private TextField _numberField;
    
    @FXML
    private Button _record;

    @FXML
    private Button _seeAns;

    @FXML
    private Button _go;

    @FXML
    private Button _back;
    
    @FXML
    private Button _menu;
    
    @FXML
    private Label _numberLabel;
    
    @FXML
    private Label _recording;
    
    @FXML
    private Label _choose;
    
    @FXML
    private Pane _choosePane;
    
    @FXML
    private Pane _recordPane;
    
    @FXML
    private Label _tens;

    @FXML
    private Label _maa;

    @FXML
    private Label _ones;
    
    @FXML
    private Button _playBack;
    
    @FXML
    private Button _checkAns;
    
    @FXML
    private Label _correct;
    
    @FXML
    private Label _whoops;
    
    @FXML
    private Button _ok;
    
    
    private Pane _showingPane = _choosePane; //The pane that is currently showing
    
    @FXML
    void playBack(ActionEvent event) {
    	Task<Void> task = new Task<Void>() {
    		@Override
    		public Void call() {
    			SpeechRecognition.playback();
    			return null;
    		}
    	};
    	Thread thread = new Thread(task);
    	thread.setDaemon(true);
    	thread.start();
    }
    
    @FXML
    void checkAns(ActionEvent event) {
    	_checkAns.setVisible(false);
    	if (_model.checkCorrect()) {
    		
    		_correct.setVisible(true);
    		_ok.setVisible(true);
    	}
    	else {
    		_whoops.setVisible(true);
    		_seeAns.setVisible(true);
    	}
    	_record.setVisible(false);
    	_playBack.setVisible(false);
    	_numberLabel.setVisible(false);
    	
    }
    
    @FXML
    void ok(ActionEvent event) {
    	_showingPane = _choosePane;
    	EventHandler<ActionEvent> eh = new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent e) {
    			_choosePane.setVisible(true);
    			revertToOriginal();
    		}
    	};
    	fadeOut(eh, _recordPane);
    	
    }
    
    
    @FXML
    void record(ActionEvent event) {

    	_seeAns.setDisable(true);
    	_playBack.setDisable(true);
    	_checkAns.setDisable(true);
    	
    	_recording.setVisible(true);
    	_record.setDisable(true);
    	
    	//Running background task of recording
    	Task<Void> task = new Task<Void>() {
    		@Override
    		public Void call() {
    			try {
					_model.setRecognised(SpeechRecognition.runVoiceRecognition());
					
				} 
    			catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
    			catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			recordingEnded();
    			
    			return null;
    		}
    	};
    	Thread thread = new Thread(task);
    	thread.setDaemon(true);
    	thread.start();
    }

    @FXML
    /**
     * Shows the correct answe
     * @param event
     */
    void seeAns(ActionEvent event) {
    	if (_model.getCurrentNumber() <= 10) {
    		_ones.setText(SpeechRecognition.translation(_model.getCurrentNumber()));
    	}
    	else if (_model.getCurrentNumber() % 10 == 0){
    		
    		_ones.setText(SpeechRecognition.translation(_model.getCurrentNumber()));
    	}
    	else {
    		int tens = _model.getCurrentNumber() / 10 * 10;
    		_tens.setText(SpeechRecognition.translation(tens));
    		_tens.setVisible(true);
    		_maa.setVisible(true);
    		_ones.setText(SpeechRecognition.translation(_model.getCurrentNumber() % 10));
    	}
    	_ones.setVisible(true);
    	_numberLabel.setVisible(false);
    	_seeAns.setVisible(false);
    	_record.setVisible(false);
    	_ok.setVisible(true);
    	_whoops.setVisible(false);
    	_correct.setVisible(false);
    }
    
    @FXML
    void back(ActionEvent event) {
    	
    	_choosePane.setVisible(true);
		_back.setVisible(false);
		_menu.setVisible(true);
		revertToOriginal();
		_recordPane.setVisible(false);
		_showingPane = _choosePane;
    }

    @FXML
    void backToMenu(ActionEvent event) {

    	EventHandler<ActionEvent> h = new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent e) {
    			_controller.showMenu();
    		}
    	};
    	this.fadeOut(h, _mainPane);
    }

    @FXML
    /**
     * Displays the number
     * @param event
     */
    void go(ActionEvent event) { 
    	
    	//Try block to catch the situation where the user puts in some bullshit
    	try {
    		if (_numberField.getText().compareTo("") != 0) {
    			
    			//Retrieving the number from the field
    			String str = _numberField.getText();
    			int i = Integer.parseInt(str);
    			
    			if (i < 1 || i > 99) {
    				throw new Exception();
    			}
    			else {
    				
    				//Setting the text
    				_model.setNumber(i);
    				_numberLabel.setText("" + i);
    				
    				//Playing the animation
    				EventHandler<ActionEvent> eh = new EventHandler<ActionEvent>() {
    					@Override
    					public void handle(ActionEvent event) {
    						_recordPane.setOpacity(1);
    						_recordPane.setVisible(true);
    						_menu.setVisible(false);
    						_back.setVisible(true);
    					}
    				};
    				
    				fadeOut(eh, _choosePane);
    				_showingPane = _recordPane;
    			}
    		}
    		else {
    			throw new Exception();
    		}
    	}
    	catch (Exception e) {
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("Incorrect input");
    		alert.setHeaderText(null);
    		alert.setContentText("The input must be an integer between 1 and 99 inclusive");
    		alert.showAndWait();
    	}
    	
    }
    
    @FXML
    void changeColour(MouseEvent event) {

    	_controller.changeColour(event);
    	
    }

    @FXML
    void changeColourBack(MouseEvent event) {
    	_controller.changeColourBack(event);
    }
    
    public void set(PracticeController controller, Practice model) {
    	_model = model;
    	_controller = controller;
    }
    
    /**
     * Causes a pane to fade out. What happens afterwards is determined 
     * by an eventHandler
     * @param eh determines what will happen after the animation
     * @param pane
     */
    private void fadeOut(EventHandler<ActionEvent> eh, Pane pane) {
    	//Causes the pane to fade out
    	EventHandler<ActionEvent> e = new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			eh.handle(event);
    			pane.setVisible(false);
    		}
    	};
    	FadeTransition ft = new FadeTransition(Duration.millis(200), pane);
    	ft.setFromValue(1);
    	ft.setToValue(0);
    	ft.play();
    	ft.setOnFinished(e);
    	
    }
    
    /**
     * Returns everything back to its original state. Does not affect whether or not
     * panes are visible.
     */
    private void revertToOriginal() {
    	_record.setVisible(true);
    	_recordPane.setOpacity(1);
    	_choosePane.setOpacity(1);
    	this._numberField.setText(null);
    	_ones.setVisible(false);
    	_maa.setVisible(false);
    	_tens.setVisible(false);
    	this._recording.setVisible(false);
    	_numberLabel.setVisible(true);
    	_playBack.setVisible(false);
    	_checkAns.setVisible(false);
    	_seeAns.setVisible(true);
    	_ok.setVisible(false);
    	_back.setVisible(false);
    	_correct.setVisible(false);
    	_whoops.setVisible(false);
    	_menu.setVisible(true);
    }
    
    private void recordingEnded() {
    	
    	_seeAns.setDisable(false);
    	_playBack.setDisable(false);
    	_checkAns.setDisable(false);
    	
    	_record.setDisable(false);
    	_recording.setVisible(false);
    	_playBack.setVisible(true);
    	_seeAns.setVisible(false);
    	_checkAns.setVisible(true);
    }
    
    
    public Pane getShowingPane() {
    	return _showingPane;
    }
    
    
    public void initialize() {
    	_showingPane = _choosePane; //The pane that is currently showing
    }
    

}
