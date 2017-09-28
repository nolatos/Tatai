package tatai;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GameController {

    @FXML
    private ListView<?> _progressList;

    @FXML
    private Button _start;

    @FXML
    private Label _levelLabel;

    @FXML
    private Button _backToMenu;
    
    @FXML
    private WelcomeController _welcomeController;
    
    private boolean _hard;
    private Stage _mainStage;
    
    @FXML
    void backToMenu(ActionEvent event) {
    	try {
			_welcomeController.show();
		} 
    	catch (Exception e) {
			
		}
    	
    }
    
    
    /**
     * Sets the label of the difficulty
     * @param hard
     */
    public void setHard(boolean hard) {
    	_hard = hard;
    	if (_hard) {
    		_levelLabel.setText("HARD");
    	}
    	else {
    		_levelLabel.setText("EASY");
    	}

    }
    
    /**
     * Sets a welcomeController
     * @param w
     */
    public void setWelcomeController(WelcomeController w) {
    	_welcomeController = w;
    }
    
    @FXML
    void startGame(ActionEvent event) throws Exception {
    	
		Practice practice = new Practice(_hard);
		
		practice.getPlayController().setGameController(this);
    	
    	Stage stage = new Stage();
    	practice.setStage(stage);
    	stage.setScene(practice.getGame());
    	stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
    		@Override
    		public void handle(WindowEvent we) {
    			
    			if (practice.getPlayController().getProgress() == 11) {
    				;
    			}
    			else {
    				Alert alert = new Alert(AlertType.CONFIRMATION);
            		alert.setTitle("Confirm Exit");
            		alert.setHeaderText("Are you sure you want to quit?");
            		alert.setContentText("Progress will be lost");
            		Optional<ButtonType> result = alert.showAndWait();
            		if (result.get() == ButtonType.OK) {
            			
            		}
            		else {
            			we.consume();
            		}
    			}
    		}
    	});
    	stage.setResizable(false);
    	stage.showAndWait();
    	
    }
    
    /**
     * Sets the _mainStage field.
     * @param stage
     */
    public void setStage(Stage stage) {
    	_mainStage = stage;
    }
}
