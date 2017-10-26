package tatai;

import java.io.IOException;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public interface Controller {

	
	public void show();
	
	public Pane getShowingPane();
	
	public void changeColour(MouseEvent event);
	
	public void changeColourBack(MouseEvent event);
	
	public Stage getMainStage();
}
