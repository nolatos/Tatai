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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tatai.models.*;
import tatai.views.*;

public class PlayController implements Controller {

	//Private fields
	private Play _model;
	private Background _background = new Background();
	private BashHandler _bh = new BashHandler();
    private Stage _recordStage; //Stage of record
    private RecordController _recordC;
    private StartController _startC;
    private Stage _playStage;
	
	
	
    
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
	    	_playStage = new Stage();
	    	_playStage.setScene(new Scene(pane));
	    	_view = loader.getController();
	    	_view.setController(this);
	    	_view.setModel(_model);
	    	_playStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	    		@Override
	    		public void handle(WindowEvent we) {
	    			
	    			if (_model.getProgress() == 11) {
	    				_startC.deleteGame();
	    			}
	    			else {
	    				Alert alert = new Alert(AlertType.CONFIRMATION);
	            		alert.setTitle("Confirm Exit");
	            		alert.setHeaderText("Are you sure you want to quit?");
	            		alert.setContentText("Progress will be lost");
	            		Optional<ButtonType> result = alert.showAndWait();
	            		if (result.get() == ButtonType.OK) {
	            			_startC.deleteGame();
	            		}
	            		else {
	            			we.consume();
	            		}
	    			}
	    		}
	    	});
	    	_playStage.setResizable(false);
		} 
		catch (IOException e) {

		}		
		
		
		
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
    	
    	_playStage.show();
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
    	if (progress > 10) {
    		terminate();
    	}
    	else {
    		_view.setLabel("" + progress + "/10");
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
     	
     	_startC.addToList("" + _model.getScore() + "/10\t" + String.valueOf(_model.getDifficulty()));
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
	   return _bh.translation(_model.getCurrentNumber());
   }
   
   
   
   class Background extends Service<Void> {
	   
	   
	   
	   
	   public Task<Void> createTask() {
		   
		   Task<Void> t =  new Task<Void>() {
			   @Override
			   public Void call() {
				   try {
					   
						   
					   //Showing the "recording" dialog		      
					   _model.setRecognised(_bh.runVoiceRecognition());
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


