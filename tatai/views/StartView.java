package tatai.views;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import tatai.*;
import tatai.models.Start;

public class StartView {

    @FXML
    private ListView<String> _progressList;

    @FXML
    private Button _start;

    @FXML
    private Label _levelLabel;

    @FXML
    private Button _backToMenu;
    
    private StartController _controller;
    private Start _model;

    
    @FXML
    void backToMenu(ActionEvent event) {
    	_controller.backToMenu();
		_controller.MAIN_STAGE.setScene(_controller.MENU_SCENE);

    }

    @FXML
    void startGame(ActionEvent event) {


    	_controller.startGame();
    	
    }
    
    /**
     * Sets the level label
     * @param difficulty
     */
    public void setLevel(Difficulty difficulty) {
    	_levelLabel.setText("" + difficulty);
    }
    
    /**
     * Sets the level label as "EASY" or "HARD"
     * @param hard
     */
    public void setLevel(boolean hard) {
    	if (hard) {
    		_levelLabel.setText("HARD");
    	}
    	else {
    		_levelLabel.setText("EASY");
    	}
    }

    
    public void setController(StartController controller) {
    	_controller = controller;
    }
    
    
    public void setModel(Start start) {
    	_model = start;
    }
    
    public void addToList(ObservableList<String> list) {
    	_progressList.setItems(list);
    }
}
