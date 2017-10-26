package tatai;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tatai.models.Practice;
import tatai.views.*;

public class PracticeController implements Controller {
	
	private WelcomeController _welcomeC;
	private Scene _menuScene; //Scene of the main menu
	
	private Practice _model;
	private PracticeView _view;

	public void show() {
		_welcomeC.getMainStage().setScene(_menuScene);
		WelcomeController.setShowingController(this);
	}
	
	public PracticeController(WelcomeController welcomeC) throws IOException {
		//Loading the fxml
		FXMLLoader loader = new FXMLLoader(getClass().getResource("views/practice.fxml"));
		Pane pane = (Pane) loader.load();
		_menuScene = new Scene(pane);
		
		//Setting up the model and view
		_view = loader.getController();
		_model = new Practice(this);
		_view.set(this, _model);
		
		
		_welcomeC = welcomeC;
		 
	} 
	
	public void changeColour(MouseEvent e) {
		_welcomeC.changeColour(e);
	}
	
	public void changeColourBack(MouseEvent e) {
		_welcomeC.changeColourBack(e);
	}
	
	
	public void showMenu() {
		try {
			_welcomeC.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Pane getShowingPane() {
		return _view.getShowingPane();
	}
	
	public Stage getMainStage() {
		return _welcomeC.getMainStage();
	}
}


