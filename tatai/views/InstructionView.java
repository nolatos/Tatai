package tatai.views;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class InstructionView {

    @FXML
    private Button _back;
    
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

}
