/**
 * Main class called to run the game player application.
 */
package game_player;

import game_player_api.GameChooser;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main Driver for running the Game Player application
 *
 * @author Dorian Barber, Kelley Scroggs
 */
public class Driver extends Application {
	
	private VController myController;
	private PlayerView myView;
	private GameChooser gameChooser;
	
	
	private static final String TITLE = "GAME PLAYER";
	
	/**
	 * Creates the UI for the application
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		new VoogaChooser(primaryStage);
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
