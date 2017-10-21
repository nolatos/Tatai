package tatai;

import java.io.IOException;

import javafx.scene.layout.Pane;

public interface Controller {

	
	public void show() throws IOException;
	
	public Pane getShowingPane();
}
