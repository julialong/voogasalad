/**
 * Main class called to run the game player application.
 */
package game_player;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Main Driver for running the Game Player application
 *
 * @author Dorian Barber, Kelley Scroggs
 */
public class Driver extends Application {
	
	private VController myController;
	private VView myView;
	
	
	private static final String TITLE = "GAME PLAYER";
	
	/**
	 * Creates the UI for the application
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		setScreenBounds(primaryStage);

		myView = new VView();
		myController = new VController(myView);
		Scene scene = new Scene(myView);
		//TODO: uncomment once we create the style sheet
		//scene.getStylesheets().add("styleSheet.css");
		primaryStage.setScene(scene);
		primaryStage.setTitle(TITLE);
		primaryStage.show();
	}


	/**
	 * Makes the application take up the entire window of the computer.
	 * @param primaryStage
	 */
	private void setScreenBounds(Stage primaryStage) {
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		primaryStage.setX(primaryScreenBounds.getMinX());
		primaryStage.setY(primaryScreenBounds.getMinY());
		primaryStage.setWidth(primaryScreenBounds.getWidth());
		primaryStage.setHeight(primaryScreenBounds.getHeight());
	}

	/**
	 * Runs the Game Player application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
