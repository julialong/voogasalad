/**
 * Main class called to run the game player application.
 */
package game_player;

import game_player_api.GameChooser;
import game_player_api.GameItem;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Main Driver for running the Game Player application
 *
 * @author Dorian Barber, Kelley Scroggs
 */
public class Driver extends Application {
	
	private VController myController;
	private VView myView;
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
		scene.getStylesheets().add("styleSheet.css");

		primaryStage.setScene(scene);

		primaryStage.show();
	}

	private void createChooser(){
		List<GameItem> list = new ArrayList<>();
		String[] gameNames = {"Mario", "Yoshi", "Marth", "Sammus this name is going to be really long for the sake of testing whether or not the list view can handle text at such great lengths"};
		for(String game : gameNames){
			GameItem newGame = new VoogaGame(game);
			list.add(newGame);
		}
		gameChooser = new VoogaChooser(list);
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
