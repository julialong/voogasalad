package game_player;

import engine.level.Level;
import game_player.buttons.ButtonBuilder;
import game_player.buttons.VMenuBar;
import game_player_api.GameViewMenu;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;

import java.util.List;
import data.fileReading.GPGameFileReader;
import data.fileReading.JSONtoGP;
import data.resources.DataFileException;

/**
 * The main application for the game player. Here is where the MVC design
 * pattern is used. The Player contains a menubar and a game view for buttons
 * and to display the actual game. This class simply acts as the container for
 * those parts.
 *
 * @Author Dorian Barber, Kelley Scroggs
 */
public class PlayerView extends BorderPane {
	private List<Level> gameMaterial;
	private VMenuBar myMenuBar;
	private VoogaGameView myGameView;
	private JSONtoGP reader = new GPGameFileReader();
	private String myName;
	private String myDescription;
	
	public PlayerView() {
		super();
		createGView();
		createMenuBar();
		setViewTop();
		setMiddle();
	}

	public PlayerView(List<Level> game, String name, String description) {
		super();
		gameMaterial = game;
		myName = name;
		myDescription = description;
		createGView();
		createMenuBar();
		setMiddle();
		setViewTop();

	}

	/**
	 * Resets the game
	 */
	public void resetGame() {
		this.getChildren().clear();
		resetGView();
		createMenuBar();
		setMiddle();
		setViewTop();
	}

	/**
	 * Reloads the game materials;
	 */
	private void resetGView() {
		try {
			myGameView = new VoogaGameView(reader.loadCompleteGame(myName));
		} catch (DataFileException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(e.getCause().toString());
			alert.setContentText(e.getMessage());
			alert.show();
		}
	}

	/**
	 * Creates the view where the game is displayed.
	 */
	private void createGView() {
		myGameView = new VoogaGameView(gameMaterial);
	}

	/**
	 * create the menubar
	 */
	private void createMenuBar() {
		// TODO Auto-generated method stub
		myMenuBar = new VMenuBar();
		addButtons();
	}

	/**
	 * add buttons to my menubar
	 */
	private void addButtons() {
		new ButtonBuilder(this, myMenuBar, (GameViewMenu) myGameView, myName, myDescription, gameMaterial);
	}

	/**
	 * Adds the menubar to the top of the game player UI.
	 */
	private void setViewTop() {
		//this.getChildren().add(myMenuBar.getNode());
		this.setTop(myMenuBar.getNode());
	}

	/**
	 * Adds the game image to the middle of the game player UI.
	 */
	private void setMiddle() {
		//this.getChildren().add(myGameView.getNode());
		this.setCenter(myGameView.getNode());
		myGameView.startGame();
	}

	/**
	 * Calls the game views keyPressed method. Called from the controller when the
	 * user presses a key.
	 * 
	 * @param keyCode
	 */
	public void startKey(KeyCode keyCode) {
		myGameView.keyPressed(keyCode);
	}

	/**
	 * Calls the game views keyUnPressed method. Called from the controller when the
	 * user unpresses a key.
	 * 
	 * @param keyCode
	 */
	public void endKey(KeyCode keyCode) {
		myGameView.keyUnPressed(keyCode);
	}
}
