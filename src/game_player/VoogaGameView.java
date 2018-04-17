package game_player;

import engine.level.Level;
import game_player_api.GameView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.gamefiles.GameFileReader;
import data.gamefiles.JSONtoObject;
import engine.controls.*;
import engine.entity.GameEntity;
import engine.entity.Player;

/**
 * Class to run the game loop and check for user commands
 * 
 * @author Kelley Scroggs
 *
 */
public class VoogaGameView implements GameView {
	// constants
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private final double myHeight = Screen.getPrimary().getVisualBounds().getHeight();
	private final double myWidth = Screen.getPrimary().getVisualBounds().getWidth();
	// variables
	private boolean myGameStatus = false;
	private int myCurrLevel = 0;
	private List<Level> myGameLevels;
	private Map<GameEntity, ImageView> myDispMap = new HashMap<>();
	// parts
	private Pane myGP;
	private Controls myControls;

	/**
	 * Creates a grid pane. initializes event listeners
	 * 
	 * @param gameLevels
	 */
	public VoogaGameView(List<Level> gameLevels) {
		myGameLevels = gameLevels;
		myGP = new Pane();
		initDisplayMap();
	}

	/**
	 * Calibrates x coordinates to be at the center of the screen and multiplies by
	 * factor to make them bigger.
	 * 
	 * @param x
	 * @return
	 */
	private double adjustXCord(double x) {
		// TODO: adjust this factor based on sensitivity
		return x * (myWidth / 4000.0) + (myWidth / 2.0);
	}

	/**
	 * Calibrates y coordinates to be at thecenter of the screen and multiples by a
	 * factor to make the difference between them larger.
	 * 
	 * @param y
	 * @return
	 */
	private double adjustYCord(double y) {
		// TODO: adjust this factor based on sensitivity
		return (myHeight / 2.0) - y * (myHeight / 240.0);
	}

	/**
	 * Adds all of the levels objects to a map that maps them to a position
	 */
	private void initDisplayMap() {
		for (GameEntity ge : myGameLevels.get(myCurrLevel).getObjects()) {
			// TODO: below is filler for actual data, delete once gae sends us the real
			// stuff
			String imgPath = ge.getImagePath();
			if (ge.getClass().equals(new Player().getClass())) {
				myControls = new Controls((Player) ge);
				imgPath = "trump.gif";
				ge.setSpeedFactor(1000);
				ge.setMaxXVelocity(50);
				ge.setMaxYVelocity(500);
				ge.setFrictionConstant(200);
				ge.setJumpFactor(75);
			} else if (ge.getImagePath().equals(null) || ge.getImagePath().equals("")) {
				imgPath = "brick.png";
			}
			ImageView entityImage = new ImageView(new Image(getClass().getResourceAsStream(imgPath), ge.getSizeX() + 50,
					ge.getSizeY() + 50, true, true));

			// TODO: uncomment below once GAE sends us actual data
			// if (ge.getClass().equals(new Player().getClass())) {
			// myControls = new Controls((Player) ge);
			// }
			// ImageView entityImage = new ImageView(new
			// Image(getClass().getResourceAsStream(ge.getImagePath()), ge.getSizeX(),
			// ge.getSizeY(), true, true));

			entityImage.setX(adjustXCord(ge.getPosition()[0]));
			entityImage.setY(adjustYCord(ge.getPosition()[1]));
			myDispMap.put(ge, entityImage);
			myGP.getChildren().add(myDispMap.get(ge));
		}
	}

	/**
	 * initializes the game loop.
	 */
	public void startGame() {
		myGameStatus = true;
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
		if (myGameStatus) {
			myGameLevels.get(myCurrLevel).update();
			displayObjects();
		}
	}

	/**
	 * Displays the already updated objects that have been determined to be in
	 * bounds.
	 * 
	 * @param toDisplay
	 */
	private void displayObjects() {
		for (GameEntity ge : myGameLevels.get(myCurrLevel).getObjects()) {
			myDispMap.get(ge).setX(adjustXCord(ge.getPosition()[0]));
			myDispMap.get(ge).setY(adjustYCord(ge.getPosition()[1]));
		}
	}

	/**
	 * Resumes the game by authorizing the game loop and event filters.
	 */
	public void resumeGame() {
		myGameStatus = true;
	}

	/**
	 * Pauses the game by stopping the game loop and event filters from firing.
	 */
	public void pauseGame() {
		myGameStatus = false;
	}

	/**
	 * Returns the node so it can be added to the player view.
	 */
	@Override
	public Node getNode() {
		return myGP;
	}

	/**
	 * Sends the key code of the key pressed to the backend.
	 * 
	 * @param keyCode
	 */
	public void keyPressed(KeyCode keyCode) {
		if (myGameStatus) {
			myControls.activate(keyCode);
		}
	}

	/**
	 * Sends the key code of the key that was released to the backend.
	 * 
	 * @param keyCode
	 */
	public void keyUnPressed(KeyCode keyCode) {
		if (myGameStatus) {
			myControls.deactivate(keyCode);
		}
	}

	/**
	 * Passes changes from the Key Bindings UI to the backend.
	 * 
	 * @param propKey
	 * @param keyCode
	 */
	public void changeBinding(String propKey, KeyCode keyCode) {
		try {
			Object instance = Class.forName("engine.controls."+propKey).newInstance();
			Action a = (Action) instance;
			myControls.setBinding(keyCode, a);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
