package game_player;

import authoring_environment.editor_windows.EditorWindow;
import engine.level.Level;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
public class PlayerView extends VBox {
	private List<Level> gameMaterial;
	private VMenuBar myMenuBar;
	private VoogaGameView myGameView;
	private JSONtoGP reader = new GPGameFileReader();
	private String myName;
	private ScoreKeeper myHighScores = new ScoreKeeper();

	public PlayerView() {
		super();
		createGView();
		createMenuBar();
		setViewTop();
		setMiddle();
	}

	public PlayerView(List<Level> game, String name) {
		super();
		gameMaterial = game;
		myName = name;
		createGView();
		createMenuBar();
		setViewTop();
		setMiddle();
	}

	/**
	 * Resets the game
	 */
	private void resetGame() {
		this.getChildren().remove(myGameView.getNode());
		resetGView();
		setMiddle();
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
		// Home button
		VButton homeButton = new VButton("Home");
		homeButton.setOnMouseClicked(e -> {
			myGameView.pauseGame();
			goHome();
		});
		myMenuBar.addButton(homeButton);

		// Save button
		VButton saveButton = new VButton("Save Game");
		saveButton.setOnMouseClicked(e -> {
			myGameView.pauseGame();
			new SaveScreen(gameMaterial, myName);
		});
		myMenuBar.addButton(saveButton);

		// High Scores button
		VButton scoresButton = new VButton("High Scores");
		scoresButton.setOnMouseClicked(e -> {
			myGameView.pauseGame();
			myHighScores.setUpStage();
		});
		myMenuBar.addButton(scoresButton);

		// Resume game
		VButton resumeButton = new VButton("Resume Game");
		resumeButton.setOnMouseClicked(e -> myGameView.resumeGame());
		myMenuBar.addButton(resumeButton);

		// Pause game
		VButton pauseButton = new VButton("Pause Game");
		pauseButton.setOnMouseClicked(e -> myGameView.pauseGame());
		myMenuBar.addButton(pauseButton);

		// Change Bindings
		// TODO: new interface here
		VButton keysButton = new VButton("Change Bindings");
		keysButton.setOnMouseClicked(e -> {
			myGameView.pauseGame();
			new KeyBindingWindow(myGameView);
		});
		myMenuBar.addButton(keysButton);

		//Go to the Game Authoring Environment
		VButton gaeButton = new VButton("Edit Game");
		gaeButton.setOnMouseClicked(e ->{
			myGameView.pauseGame();
			Stage stage = new Stage();
			new EditorWindow(stage);
		});
		myMenuBar.addButton(gaeButton);

		// Reset game
		VButton resetButton = new VButton("Reset");
		resetButton.setOnMouseClicked(e -> resetGame());
		myMenuBar.addButton(resetButton);
	}

	/**
	 * launches a new homescreen
	 */
	private void goHome() {
		OverViewDriver relaunch = new OverViewDriver();
		try {
			relaunch.start(new Stage());
		} catch (Exception e) {
			System.out.println("Failed to relaunch");
		}
	}

	/**
	 * Adds the menubar to the top of the game player UI.
	 */
	private void setViewTop() {
		this.getChildren().add(myMenuBar.getNode());
	}

	/**
	 * Adds the game image to the middle of the game player UI.
	 */
	private void setMiddle() {
		this.getChildren().add(myGameView.getNode());
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
