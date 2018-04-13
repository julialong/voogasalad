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
		createChooser();
		primaryStage.setTitle(TITLE);
        primaryStage.setMinWidth(550);
		Scene scene = new Scene(gameChooser.displayChoices());
		scene.getStylesheets().add("./data/styling/styleSheet.css");

		primaryStage.setScene(scene);

		primaryStage.show();
	}

	private void createChooser(){
		gameChooser = new VoogaChooser();
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
