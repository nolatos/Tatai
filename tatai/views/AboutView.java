package tatai.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import tatai.AboutController;

public class AboutView {
	
	@FXML
	private Pane _mainPane;

    @FXML
    private Button _back;
    
    private AboutController _controller;

    @FXML
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
    
    public void setController(AboutController controller) {
    	_controller = controller;
    }
    
    public Pane getShowingPane() {
    	return _mainPane;
    }

}
