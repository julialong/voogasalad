package game_player;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main Driver for running the Game Player application
 *
 * @author Dorian Barber, Kelley Scroggs
 */
public class Driver extends Application {
	/**
	 * Creates the UI for the application
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		new VoogaChooser(primaryStage);
	}


	/**
	 * Runs the Game Player application
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
