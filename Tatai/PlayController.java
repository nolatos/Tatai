package tatai;



import java.io.IOException;

import javafx.concurrent.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PlayController {

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
    
    @FXML
    private RecordController _recordC;
    
    @FXML
    private GameController _gameC;
    
    private BashHandler _bh = new BashHandler();
    
    private int _number;
    private int _progress = 1;
    private Game _game;
    private Background _background = new Background();
    private String _recognised; //What GoSpeech Recognised
    private Scene _recordScene;
    private Stage _stage;
    
    @FXML
    void nextLevel(ActionEvent event) {

    	try {
    		_game = new Practice(true);
    			
    		
    	}
    	catch (Exception e) {
    		
    	}
    	restart();
    	
    	
    }
    
    @FXML
    void retry(ActionEvent event) {

    	restart();
    }

    @FXML
    void record(ActionEvent event) {
    	
    	
    	_record.setDisable(true);
    	_stage.show();
    	_background.set(event);
    	_background.restart();
    	
    	
    	
    	
    	
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
    	_progress = 1;
    	_game.reset();
    	setImage(_game.getNumber());
    	_score.setText("1/10");
    	updateMenuLabel();
    }
    
    
    public void setGame(Game g) {
    	_game = g;
    }
    
    /**
     * Sets the image and the _number field
     * CHANGE LATER
     * @param i the image we are setting to
     */
    public void setImage(int i) {
    	
    	_number = i;
    	
    	_tempLabel.setText("" + i);
    	
    	
    }
    
    /**
     * Advances to next number
     */
    public boolean updateScore() {
    	
    	if (_game.checkCorrect(_number, _recognised)) {
    		_game.updateScore();
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public void advance() {
    	_progress++;
    	
    	if (_progress > 10) {
    		terminate();
    	}
    	else {
    		_score.setText("" + _progress + "/10");
    		setImage(_game.getNumber());
    	}  	
    }
    
    /**
     * Ends the game
     */
    public void terminate() {
    	int score = _game.getScore();
    	_tempLabel.setText("" + score + "/10");
    	_record.setVisible(false);
    	_retry.setVisible(true);
    	_nextLevel.setVisible(true);
    	_message.setVisible(true);
    	_moveOn.setVisible(true);
    	
    	//Adding to the history list
    	String stat = "" + score + "/10";
    	if (_game.canLevelUp()) { //is easy
    		stat = stat + "\tEASY";
    	}
    	else {
    		stat = stat + "\tHARD";
    	}
    	
    	_gameC.addToList(stat);

    	
    	if (_game.getScore() >= 8 && _game.canLevelUp()) {
    		_nextLevel.setDisable(false);
    	}
    	else {
    		_nextLevel.setDisable(true);
    	}
    }
   
   /**
    * Reenables the "record" button
    */
   public void enableRecord() {
	   _record.setDisable(false);
   }
   
   /**
    * Allows the playcontroller to interact with the RecordController
    * @param recorder
    */
   public void setLoader(FXMLLoader loader) {
	   try {
		   Pane pane = (Pane)loader.load();
		   _recordScene = new Scene(pane);
		   _recordC = loader.getController();
		   _recordC.setPlay(this);
		   _stage = new Stage();
		   _stage.setScene(_recordScene);
		   _stage.setResizable(false);
		   _stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			   @Override
			   public void handle(WindowEvent we) {
				   we.consume();
			   }
		   });
		   
	   }
	   catch (IOException e) {
		   e.printStackTrace();
	   }
   }
   
   /**
    * Closes the recording stage
    */
   public void closeRecorderStage() {
	   _stage.close();
   }
   
   
   public String getRecognised() {
	   return _recognised;
   }
   
   /**
    * Allows interaction
    * @param g
    */
   public void setGameController(GameController g) {
	   _gameC = g;
   }
   
   
   public void updateMenuLabel() {
	   _gameC.setHard(!_game.canLevelUp());
   }
   
   public int getProgress() {
	   return _progress;
   }
   
   /**
    * Returns the number currently showing
    * @return
    */
   public String getNumber() {
	   return _bh.translation(_number);
   }
   
   
   
   
   
   
   class Background extends Service<Void> {
	   
	   private ActionEvent _e;
	   
	   
	   
	   public void set(ActionEvent e) {
		   _e = e;
	   }
	   
	   
	   public Task<Void> createTask() {
		   
		   Task<Void> t =  new Task<Void>() {
			   @Override
			   public Void call() {
				   try {
					   if (_e.getSource().equals(_record)) {
						   
						   //Showing the "recording" dialog		      
						   _recognised = _bh.runVoiceRecognition();
						   _recordC.recordingEnded(updateScore());

						   


					   }
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


