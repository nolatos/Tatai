package tatai;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import tatai.views.*;

public class InstructionController implements Controller {

	private WelcomeController _welcomeC;
	private InstructionView _view;

	private StartController _startC;
	
	private boolean _fromWelcome;

	//Don't need model because no data

	private Scene _instructionScene;



	public InstructionController(WelcomeController welcomeC) {
		_welcomeC = welcomeC;
		_fromWelcome = true;
		initialise();

	}
	
	public InstructionController(StartController startC) {
		_startC = startC;
		_fromWelcome = false;
		initialise();

	}

	public void show() {
		WelcomeController.setShowingController(this);
		_welcomeC.getMainStage().setScene(_instructionScene);
	}

	@Override
	public Pane getShowingPane() {
		return _view.getShowingPane();
	}

	/**
	 * Goes back to Menu
	 */
	public void backToMenu() {
		if (_fromWelcome) {
			try {
				_welcomeC.show();
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			_startC.show();
		}
	}

	public void changeColour(MouseEvent event) {
		_welcomeC.changeColour(event);
	}

	public void changeColourBack(MouseEvent event) {
		_welcomeC.changeColourBack(event);
	}

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
}

