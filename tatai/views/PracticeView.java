package tatai.views;

import javafx.animation.FadeTransition;
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
    
    private int _number;
    
    
    
    @FXML
    void record(ActionEvent event) {

    }

    @FXML
    void seeAns(ActionEvent event) {
    	if (_number <= 10) {
    		_ones.setText(SpeechRecognition.translation(_number));
    	}
    	else if (_number % 10 == 0){
    		
    		_ones.setText(SpeechRecognition.translation(_number));
    	}
    	else {
    		int tens = _number / 10 * 10;
    		_tens.setText(SpeechRecognition.translation(tens));
    		_tens.setVisible(true);
    		_maa.setVisible(true);
    		_ones.setText(SpeechRecognition.translation(_number % 10));
    	}
    	_ones.setVisible(true);
    	_numberLabel.setVisible(false);
    }
    
    @FXML
    void back(ActionEvent event) {
    	
    	_choosePane.setVisible(true);
		_back.setVisible(false);
		_menu.setVisible(true);
		revertToOriginal();
		_recordPane.setVisible(false);
//    	EventHandler<ActionEvent> eh = new EventHandler<ActionEvent>() {
//    		@Override
//    		public void handle(ActionEvent e) {
//    			
//    		}
//    	};
//    	
//    	this.fadeOut(eh, _recordPane);
//    	
    	
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
    				_number = i;
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
    	_recordPane.setOpacity(1);
    	_choosePane.setOpacity(1);
    	this._numberField.setText(null);
    	_ones.setVisible(false);
    	_maa.setVisible(false);
    	_tens.setVisible(false);
    	this._recording.setVisible(false);
    	_numberLabel.setVisible(true);
    	
    }
    

}
