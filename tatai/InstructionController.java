package tatai;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tatai.views.*;

public class InstructionController implements Controller {

	private Controller _controller;
	private InstructionView _view;

	//Don't need model because no data

	private Scene _instructionScene;



	public InstructionController(Controller welcomeC) {
		_controller = welcomeC;
		initialise();

	}

	public void show() {
		WelcomeController.setShowingController(this);
		_controller.getMainStage().setScene(_instructionScene);
	}
	
	/**
	 * Shows based on the difficulty
	 * @param difficulty
	 */
	public void show(Difficulty difficulty) {
		show();
		_view.chooseTab(difficulty);
	}

	@Override
	public Pane getShowingPane() {
		return _view.getShowingPane();
	}

	/**
	 * Goes back to Menu
	 */
	public void backToMenu() {
		_controller.show();
		
	}

	public void changeColour(MouseEvent event) {
		_controller.changeColour(event);
	}

	public void changeColourBack(MouseEvent event) {
		_controller.changeColourBack(event);
	}

	/**
	 * Loads the fxml
	 */
	private void initialise() {
		//Loading the view
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("views/instructions.fxml"));
			Pane pane = (Pane) loader.load();
			_instructionScene = new Scene(pane);
			_view = loader.getController();
			_view.setController(this);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Stage getMainStage() {
		return _controller.getMainStage();
	}
}

