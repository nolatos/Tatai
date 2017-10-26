package tatai.views;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import tatai.*;

/**
 * @author oliver
 *
 */
public class InstructionView {

    @FXML
    private Button _back;
    
    @FXML
    private Pane _mainPane;
    
    @FXML
    private Tab _practice;

    @FXML
    private Tab _pronunciation;

    @FXML
    private Tab _three;

    @FXML
    private Tab _four;

    @FXML
    private Tab _insane;
    
    @FXML
    private TabPane _tabs;
    
    private InstructionController _controller;

    @FXML
    /**
     * Goes back to the menu
     * @param event
     */
    void backToMenu(ActionEvent event) {
    	_controller.backToMenu();
    }

    @FXML
    void changeColour(MouseEvent event) {
    	_controller.changeColour(event);
    }

    @FXML
    void changeColourBack(MouseEvent event) {
    	_controller.changeColourBack(event);
    }
    

    public void setController(InstructionController controller) {
    	_controller = controller;
    }
    
    public Pane getShowingPane() {
    	return _mainPane;
    }
    
    public void chooseTab(Difficulty difficulty) {
    	switch (difficulty) {
    	case ONE:
    		//Let it fall to step two
    	case TWO:
    		_tabs.getSelectionModel().select(_pronunciation);
    		break;
    	case THREE:
    		_tabs.getSelectionModel().select(_three);
    		break;
    	case FOUR:
    		_tabs.getSelectionModel().select(_four);
    		break;
    	case FIVE:
    		_tabs.getSelectionModel().select(_insane);
    		break;
    	}
    }
    
    
}
