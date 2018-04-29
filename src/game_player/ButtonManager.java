package game_player;

import java.util.List;

import authoring_environment.editor_windows.EditorWindow;
import engine.level.Level;
import javafx.stage.Stage;

/**
 * Adds buttons that control the game to the game's menubar.
 * 
 * @author Kelley Scroggs
 *
 */
public class ButtonManager {

	public ButtonManager(PlayerView playerView, VMenuBar myMenuBar, VoogaGameView myGameView, String myName,
			String myDescription, List<Level> gameMaterial) {
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
			new ScoreKeeperManager(myGameView);
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

		// Go to the Game Authoring Environment
		VButton gaeButton = new VButton("Edit Game");
		gaeButton.setOnMouseClicked(e -> {
			myGameView.pauseGame();
			Stage stage = new Stage();
			new EditorWindow(stage, myName, myDescription);

		});
		myMenuBar.addButton(gaeButton);

		// Reset game
		VButton resetButton = new VButton("Reset");
		resetButton.setOnMouseClicked(e -> playerView.resetGame());
		myMenuBar.addButton(resetButton);

		VButton replayButton = new VButton("Replay");
		replayButton.setOnAction(e -> {
			myGameView.pauseGame();
			new ReplayScreen(myGameView.getReplayList());
		});
		myMenuBar.addButton(replayButton);
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
}
