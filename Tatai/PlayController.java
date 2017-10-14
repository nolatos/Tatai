package tatai;



import java.io.IOException;
import java.util.Optional;

import javafx.concurrent.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tatai.models.*;
import tatai.utils.SpeechRecognition;
import tatai.views.*;

public class PlayController implements Controller {

	//Private fields
	private Play _model;
	private Background _background = new Background();
    private Stage _recordStage; //Stage of record
    private RecordController _recordC;
    private StartController _startC;
    private Scene _playScene;
	
	
	
    
	@FXML
	private PlayView _view;
	
	/**
	 * Constructor that takes in a difficulty parameter
	 * @param difficulty the difficulty of the game
	 */
	public PlayController(Difficulty difficulty, StartController startC) {
		_startC = startC;
		
		//Initialising the model and viewer
		_model = new Play(difficulty, this);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("views/play.fxml"));	
		try {
			
			
			
			//Have a stage with record at the ready
			_recordC = new RecordController(this);
			_recordStage = new Stage();
			_recordStage.setScene(_recordC.RECORD_SCENE);
			_recordStage.setResizable(false);
			_recordStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent we) {
					we.consume();
				}
			});
			
			//Setting up the play scene			
	    	Pane pane = (Pane) loader.load();
	    	_playScene = new Scene(pane);
	    	
	    	_view = loader.getController();
	    	_view.setController(this);
	    	_view.setModel(_model);
	    	
		} 
		catch (IOException e) {
			e.printStackTrace();
		}		
		
		
		
	}
	
	
	public void backToStart() {
		_startC.show();
	}
	
	
	/**
	 * Restarts a new game
	 */
	public void restart() {
		_view.restart();
		_model.reset();
	}
	
	
	/**
	 * TO BE CHANGED
	 */
	public void nextLevel() {
		_model.levelUp(); //TO BE CHANGED
		
	}
	
	
	/**
     * Sets the image and the _number field
     * CHANGE LATER
     * @param i the image we are setting to
     */
    public void setImage(int i) {
    	_view.setImage(i);
    }
    	
    	
    /**
     * Shows the game screen
     */
    public void show() throws IOException {
    	
    	_startC.MAIN_STAGE.setScene(_playScene);
    	_view.setImage(_model.getNumber());
    	
    }


    
    
    public void record() {
    	
    	_recordStage.show();
    	_background.restart();
    }
	
    /**
     * Advances to next number
     */
    public void advance() {
    	_model.advance();
    	
    	int progress = _model.getProgress();
    	if (progress > _model.TOTAL_QUESTIONS) {
    		terminate();
    	}
    	else {
    		_view.progress();
    		setImage(_model.getNumber());
    	}  	
    }
	
    
	
	
    
    public boolean canLevelUp() {
    	return _model.canLevelUp();
    }
    
    /**
     * Ends the game
     */
    private void terminate() {
    	 
     	_view.terminate(_model.getScore());
     	
     	_startC.addToList("Score: " + _model.getScore() + "/" + _model.TOTAL_QUESTIONS + 
     			" Diffculty: " + String.valueOf(_model.getDifficulty()));
     	_model.reset();
    }
    
    
	
   
    
    
   /**
    * Reenables the "record" button
    */
   public void enableRecord() {
	   _view.enableRecord();
   }
   
 
   
   /**
    * Closes the recording stage
    */
   public void closeRecorderStage() {
	   _recordStage.close();
   }
   
   
   
   public String getRecognised() {
	   return _model.getRecognised();
   }
   
   
   
   
   
   public int getProgress() {
	   return _model.getProgress();
   }
   
   /**
    * Returns the number currently showing as a string
    * @return 
    */
   public String getNumber() {
	   return SpeechRecognition.translation(_model.getCurrentNumber());
   }
   
   public void changeColour(MouseEvent event) {
	   _startC.changeColour(event);
   }
   
   public void changeColourBack(MouseEvent event) {
	   _startC.changeColourBack(event);
   }
   
   
   class Background extends Service<Void> {
	   
	   
	   
	   
	   public Task<Void> createTask() {
		   
		   Task<Void> t =  new Task<Void>() {
			   @Override
			   public Void call() {
				   try {
					   
						   
					   //Showing the "recording" dialog		      
					   _model.setRecognised(SpeechRecognition.runVoiceRecognition());
					   _recordC.recordingEnded(_model.updateScore());

						   


					   
				   }
				   catch (Exception e) {
					   e.printStackTrace();
				   }
				  
				   return null;
			   }
		   };
		   
		   reset();
		   return t;
		   
				   
	   }
	   
	   
   }
   
   
   
   
   

}


