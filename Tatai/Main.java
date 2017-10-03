package tatai;

import java.util.Optional;

import javafx.application.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.*;
import tatai.views.WelcomeController;


public class Main extends Application {


	private Scene _menu;


	public static void main(String[] args) {
		launch(Main.class, args);
		

	}

	@Override
	public void start(Stage stage) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Welcome.fxml"));

		Pane pane = (Pane)loader.load();

		WelcomeController controller = loader.<WelcomeController>getController();
		controller.setStage(stage);
		controller.setMain(this);

		_menu = new Scene(pane);

		stage.setScene(_menu);
		stage.setResizable(false);
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent we) {


				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirm Exit");
				alert.setHeaderText(null);
				alert.setContentText("Are you sure you want to exit?");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					System.exit(0);
				}
				else {
					we.consume();
				}
			}
		});
		stage.show();
	}

	public void showMenu(Stage stage) {
		stage.setScene(_menu);
	}
}
