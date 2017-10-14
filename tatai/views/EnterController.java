package tatai.views;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class EnterController {

	public final Stage _mainStage = new Stage();
	private Scene _menu;
	private Stage _enterStage;

	@FXML
	private TextField _name;

	@FXML
	private Button _enter;
	
	private boolean _started = false;

	@FXML
	/**
	 * Starts the game. Creates the stage where everything will be hosted on
	 * 
	 * @param event
	 * @throws IOException
	 */
	void enter(ActionEvent event) throws IOException {
		_enterStage.close();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("welcome.fxml"));

			Pane pane = (Pane)loader.load();

			WelcomeController controller = loader.<WelcomeController>getController();
			controller.setStage(_mainStage);
			controller.setEnterController(this);
			

			_menu = new Scene(pane);
			controller.setScene(_menu);
			_mainStage.setScene(_menu);
			_mainStage.setResizable(false);
			_mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override 
				public void handle(WindowEvent we) {


					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Confirm Exit");
					alert.setHeaderText("Are you sure you want to exit?");
					alert.setContentText("Any unsaved progress may be lost");
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK) {
						System.exit(0);
					}
					else {
						we.consume();
					}
				}
			});
			_mainStage.show();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	public void setEnterStage(Stage stage) {
		_enterStage = stage;
	}
	
	public void show() {
		_enterStage.show();
		_mainStage.close();
	}

}
