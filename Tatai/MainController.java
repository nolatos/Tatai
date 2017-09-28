package tatai;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainController {

    @FXML
    private Button helloBtn;

    @FXML
    private Label myLabel;

    @FXML
    void helloBtnPressed(ActionEvent event) {
    	myLabel.setText("Hello!");
    }

}
