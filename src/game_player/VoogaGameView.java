package game_player;

import engine.controls.Action;
import engine.controls.Controls;
import engine.level.Level;
import game_player_api.GameView;
import heads_up_display.HeadsUpDisplay;
import heads_up_display.Hud;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	private static final int SECONDS_PER_MINUTE = 60;
	private static final double ADJUST_FACTOR = 400.0;
	// variables
	private boolean myGameStatus = false;
	private int myCurrLevel = 0;
	private List<Level> myGameLevels;
	private Map<GameEntity, ImageView> myDispMap = new HashMap<>();
	// parts
	private Pane myGP;
	private Controls myControls;
	private HeadsUpDisplay hud;
	private Point2D timer = new Point2D(0, 0);

	/**
	 * Creates a grid pane. initializes event listeners
	 * 
	 * @param gameLevels
	 */
	public VoogaGameView(List<Level> gameLevels) {
		myGameLevels = gameLevels;
		myGP = new Pane();
		setUpHud();
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
		return x * (myWidth / ADJUST_FACTOR);
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
		return y * (myHeight / ADJUST_FACTOR);
	}

	/**
	 * Adds all of the levels objects to a map that maps them to a position
	 */
	private void initDisplayMap() {
		for (GameEntity ge : myGameLevels.get(myCurrLevel).getObjects()) {
			// TODO: below is filler for actual data, delete once gae sends us the real
			// stuff
			//System.out.println("IV: "+ ge.getImageView());
			String imgPath;
			if (ge.getImageView() == null || ge.getImageView() == "") {
				imgPath = "./game_player/brick.png";
			} else {
				imgPath = ge.getImageView();
			}
			if (ge instanceof Player) {
				myControls = new Controls((Player) ge);
				//imgPath = "trump.gif";
			}
			System.out.println("imgPath: "+imgPath);
			ImageView entityImage = new ImageView(new Image(imgPath,
					adjustXCord(ge.getSizeX()), adjustYCord(ge.getSizeY()), false, false));
			// TODO: uncomment below once GAE sends us actual data
			// if (ge.getClass().equals(new Player().getClass())) {
			// myControls = new Controls((Player) ge);
			// }
			// ImageView entityImage = new ImageView(new
			// Image(getClass().getResourceAsStream(ge.getImagePath()), ge.getSizeX(),
			// ge.getSizeY(), true, true));

			entityImage.setX(adjustXCord(ge.getScenePosition()[0]));
			entityImage.setY(adjustYCord(ge.getScenePosition()[1]));
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
			updateHud(elapsedTime);
		}
	}

	/**
	 * Displays the already updated objects that have been determined to be in
	 * bounds. Removes the objects that have been destroyed after the level is
	 * updated.
	 */
	private void displayObjects() {
		Level level = myGameLevels.get(myCurrLevel);
		ArrayList<GameEntity> toRemove = new ArrayList<>();
		for (GameEntity ge : myDispMap.keySet()) {
			if (level.getObjects().contains(ge)) {
				myDispMap.get(ge).setX(adjustXCord(ge.getScenePosition()[0]));
				myDispMap.get(ge).setY(adjustYCord(ge.getScenePosition()[1]));
			} else {
				toRemove.add(ge);
				myGP.getChildren().remove(myDispMap.get(ge));
			}
		}
		for (GameEntity ge : toRemove) {
			myGP.getChildren().remove(myDispMap.get(ge));
			myDispMap.remove(ge);
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
			Object instance = Class.forName("engine.controls." + propKey).newInstance();
			Action a = (Action) instance;
			myControls.setBinding(keyCode, a);
		} catch (Exception e) {
			System.out.println("Key binding failed!");
		}
	}

	/**
	 * Sets up the heads up display for the game view multiple components can be
	 * added
	 */
	private void setUpHud() {
		hud = new Hud();
		timer = new Point2D(0, hud.addComponent(Double.toString(timer.getX())));
		myGP.getChildren().add(hud.getHUD());
	}

	/**
	 * Updates the heads up display during every step to make sure important
	 * information is kept accurate.
	 */
	private void updateHud(Double elapsedTime) {
		timer = timer.add(elapsedTime, 0);
		int minutes = (int) timer.getX() / SECONDS_PER_MINUTE;
		double seconds = timer.getX() % SECONDS_PER_MINUTE;
		String output = String.format("%d:%.1f", minutes, seconds);
		hud.updateComponent((int) timer.getY(), output);
	}
}
