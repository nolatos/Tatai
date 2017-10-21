package tatai.views;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import tatai.Controller;

/**
 * Faced unforseen consequences. Went with "view rather than "controller"
 * @author oliver
 *
 */
public class InstructionView implements Controller {

    @FXML
    private Button _back;
    
    @FXML
    private Pane _mainPane;
    
    private WelcomeController _welcomeC;

    @FXML
    /**
     * Goes back to the menu
     * @param event
     */
    void backToMenu(ActionEvent event) {
    	try {
			_welcomeC.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void changeColour(MouseEvent event) {
    	_welcomeC.changeColour(event);
    }

    @FXML
    void changeColourBack(MouseEvent event) {
    	_welcomeC.changeColourBack(event);
    }
    
    public void setWelcomeController(WelcomeController controller) {
    	_welcomeC = controller;
    }
    
    public Pane getShowingPane() {
    	return _mainPane;
    }

    public void show() {
    	
    }
}
