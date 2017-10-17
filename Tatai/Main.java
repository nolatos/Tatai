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
import tatai.utils.Constants;
import tatai.views.EnterController;
import tatai.views.WelcomeController;


public class Main extends Application {
 


	public static void main(String[] args) {
		System.out.println("\u00B2");
		launch(Main.class, args);
		
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("views/enter.fxml"));
		Pane pane = (Pane) loader.load();
		EnterController controller = loader.getController();
		controller.setEnterStage(stage);
		
		stage.setScene(new Scene(pane));
		stage.setResizable(false);
		stage.show();


	}

}
