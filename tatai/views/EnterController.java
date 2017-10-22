package tatai.views;

import java.io.IOException;
import java.util.Optional;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tatai.Controller;
import tatai.utils.UserData;

public class EnterController implements Controller {

	public final Stage _mainStage = new Stage();
	private Scene _menu;
	private Stage _enterStage;

	@FXML
	private TextField _name;

	@FXML
	private Button _enter;
	
	@FXML
	private WelcomeController _welcomeC;

	private boolean _started = false;

	@FXML
	/**
	 * Starts the game. Creates the stage where everything will be hosted on
	 * 
	 * @param event
	 * @throws IOException
	 */
	void enter(ActionEvent event) throws IOException {

		boolean correct = false;		
		String user = _name.getText();		
		char[] chars = user.toCharArray();
		if (chars.length == 0) {
			correct = false;
		}
		
		
		
		for (char c : chars) {
			if (Character.isLetter(c) || Character.isDigit(c)) {
				correct = true;
			}
			if (!Character.isLetter(c) && !Character.isDigit(c) && !(c == ' ')) {
				correct = false;
				break;
			}
		}

		if (correct) {
			
			//If the username is acceptable, then we proceed
			//To login
			_enterStage.close();
			_name.setText("");
			UserData.login(user.split(" "));
			
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("welcome.fxml"));

				Pane pane = (Pane)loader.load();

				_welcomeC = loader.<WelcomeController>getController();
				_welcomeC.setStage(_mainStage);
				_welcomeC.setEnterController(this);

				//Setting up scene
				_menu = new Scene(pane);
				_welcomeC.setScene(_menu);
				_mainStage.setScene(_menu);
				_mainStage.setResizable(false);
				_mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					@Override 
					public void handle(WindowEvent we) {
						we.consume();

						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Confirm Exit");
						alert.setHeaderText("Are you sure you want to exit?");
						alert.setContentText("Any unsaved progress may be lost");
						Optional<ButtonType> result = alert.showAndWait();
						if (result.get() == ButtonType.OK) {
							try {
								UserData.storeUserData();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							_welcomeC.close();
						}
					}
				});
				_mainStage.show();
				_welcomeC.start();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Input must be either letters, numbers or spaces, and can't be all spaces");
			alert.show();
		}

	}


	public void setEnterStage(Stage stage) {
		_enterStage = stage;
	}

	public void show() {
		_enterStage.show();
		_mainStage.close();
	}
	
	public Pane getShowingPane() {
		return null; //Don't need it
	}

}
