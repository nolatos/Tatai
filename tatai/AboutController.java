package tatai;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tatai.views.AboutView;
import tatai.views.WelcomeController;

public class AboutController implements Controller {

	private WelcomeController _welcomeC;
	private AboutView _view;

	private Scene _aboutScene;

	/**
	 * Constructor takes a welcomeController object to 
	 * be able to go back and forth
	 * @param controller
	 */
	public AboutController(WelcomeController controller) {
		_welcomeC = controller;

		//Loading the view
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("views/about.fxml"));
			Pane pane = (Pane) loader.load();
			_aboutScene = new Scene(pane);
			_view = loader.getController();
			_view.setController(this);
		}
		catch (Exception e) {
			
		}

	}
	
	/**
	 * Shows the thing
	 */
	public void show() {
		_welcomeC.getMainStage().setScene(_aboutScene);
		WelcomeController.setShowingController(this);
	}
	
	public Pane getShowingPane() {
		return _view.getShowingPane();
	}
	
	public void changeColour(MouseEvent event) {
		_welcomeC.changeColour(event);
	}
	
	public void changeColourBack(MouseEvent event) {
		_welcomeC.changeColourBack(event);
	}
	
	public void backToMenu() {
		
		_welcomeC.show();
	}
	
	/**
	 * Returns the mainStage
	 */
	public Stage getMainStage() {
		return _welcomeC.getMainStage();
	}
}
