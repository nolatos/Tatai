package tatai.views;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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
    private Button _menu;
    
    @FXML
    private Pane _mainPane;
    
    @FXML
    private Button _instructions;
    
    
    
    private StartController _controller;
    private Start _model;
    
    @FXML
    void showInstructions(ActionEvent event) {
    	_controller.showInstructions();
    }

    
    @FXML
    void backToMenu(ActionEvent event) {
    	_controller.backToMenu();
 
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
    
  

    
    public void setController(StartController controller) {
    	_controller = controller;
    }
    
    
    public void setModel(Start start) {
    	_model = start;
    }
    
    public void addToList(ObservableList<String> list) {
    	_progressList.setItems(list);
    }
    
    @FXML
    void changeColour(MouseEvent event) {

    	_controller.changeColour(event);
    }

    @FXML
    void changeColourBack(MouseEvent event) {
    	_controller.changeColourBack(event);
    }
    
    public Pane getMainPane() {
    	return _mainPane;
    }
}
