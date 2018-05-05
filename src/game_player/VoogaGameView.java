package game_player;

import engine.controls.Action;
import engine.controls.Controls;
import engine.level.Level;
import game_player_api.GameView;
import game_player_api.GameViewMenu;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to run the game loop and check for user commands. I think this code
 * shows good design because I was able to refactor the original version to
 * separate out all of the display and control features. This is initially how we
 * designed the gameview, but as the development progressed, this class began to
 * take on the dual purpose of displaying and controlling the game. This
 * exemplifies the first principle of SOLID design: single purpose.
 */
public class VoogaGameView implements GameView, GameViewMenu {
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private boolean myGameStatus = true;
	private boolean myGameNotOver = true;
	private int myCurrLevel = 0;
	private List<Level> myGameLevels;
	private List<ScoreItem> newScores = new ArrayList<>();
	private GameDisplay myGD;
	private Controls myControls;

	/**
	 * Creates a grid pane. initializes event listeners
	 * 
	 * @param gameLevels
	 * @param myHighScores
	 */
	public VoogaGameView(List<Level> gameLevels) {
		myGameLevels = gameLevels;
		myGD = new GameDisplay(myGameLevels.get(0));
		myControls = myGD.findControlsObj();
	}

	/**
	 * initializes the game loop.
	 */
	public void startGame() {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	/**
	 * The game loop
	 * 
	 * @param elapsedTime
	 */
	private void step(double elapsedTime) {
		if (myGameStatus && myGameNotOver) {
			myGameLevels.get(myCurrLevel).update();
			myGD.displayObjects();
			if (myGameLevels.get(myCurrLevel).getLevelComplete()) {
				myCurrLevel++;
				if (myCurrLevel >= myGameLevels.size()) {
					endGame();
				} else {
					myGD = new GameDisplay(myGameLevels.get(myCurrLevel));
					myControls = myGD.findControlsObj();
				}
			}
		}
	}

	/**
	 * Handles the game logic when their are no more levels and the user has reached
	 * the end of the last level.
	 */
	private void endGame() {
		myGameStatus = false;
		myGameNotOver = false;
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setContentText("YOU'VE REACHED THE END!");
		alert.show();
	}

	/**
	 * Resumes the game by authorizing the game loop and event filters.
	 */
	@Override
	public void resumeGame() {
		myGameStatus = true;
	}

	/**
	 * Pauses the game by stopping the game loop and event filters from firing.
	 */
	@Override
	public void pauseGame() {
		myGameStatus = false;
	}

	/**
	 * Returns the node so it can be added to the player view.
	 */
	@Override
	public Node getNode() {
		return myGD.getPane();
	}

	/**
	 * Sends the key code of the key pressed to the backend.
	 * 
	 * @param keyCode
	 */
	public void keyPressed(KeyCode keyCode) {
		if (myGameStatus && myGameNotOver) {
			myControls.activate(keyCode);
		}
	}

	/**
	 * Sends the key code of the key that was released to the backend.
	 * 
	 * @param keyCode
	 */
	public void keyUnPressed(KeyCode keyCode) {
		if (myGameStatus && myGameNotOver) {
			myControls.deactivate(keyCode);
		}
	}

	/**
	 * Passes changes from the Key Bindings UI to the backend.
	 * 
	 * @param propKey
	 * @param keyCode
	 */
	@Override
	public void changeBinding(String propKey, KeyCode keyCode) {
		try {
			Object instance = Class.forName("engine.controls." + propKey).newInstance();
			Action a = (Action) instance;
			myControls.setBinding(keyCode, a);
		} catch (Exception e) {
			System.out.println("Key binding failed!");
		}
	}

	/**
	 * Returns the new scores that have been created during the current run of the
	 * program.
	 * 
	 * @return list of new scores
	 */
	@Override
	public List<ScoreItem> getNewScores() {
		List<ScoreItem> newScoresCopy = new ArrayList<>();
		for (ScoreItem s : newScores) {
			newScoresCopy.add(s.copy());
		}
		return newScoresCopy;
	}

	/**
	 * Clears the new/temp scores if the user presses the clear scores method in the
	 * menu bar.
	 */
	@Override
	public void clearNewScores() {
		newScores.clear();
	}
}