package tatai.views;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import tatai.*;

/**
 * Faced unforseen consequences. Went with "view rather than "controller"
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
    
    
}
