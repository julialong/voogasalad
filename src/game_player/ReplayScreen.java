package game_player;

import java.util.List;
import java.util.Map;

import engine.entity.GameEntity;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Launch a new window and display the game that the user has just played from
 * start to finish.
 * 
 * @author Kelley Scroggs
 *
 */
public class ReplayScreen {
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private Stage myStage = new Stage();
	private Pane myScreen = new Pane();
	private VBox myView = new VBox();
	private Map<ImageView, List<Point2D>> myReplayMap;
	private boolean myReplayStatus = true;
	private int myFrameIndex = 0;
	private VMenuBar myMenuBar;
	private Timeline myAnimation;

	/**
	 * Creates, loads, and runs the replay screen.
	 * 
	 * @param map
	 */
	public ReplayScreen(Map<ImageView, List<Point2D>> map) {
		myReplayMap = map;
		setUpMenu();
		addToView();
		setUpStage();
		addToStage();
		startReplay();
	}
	
	/**
	 * Creates menubar that can manipulate the game's replay.
	 */
	private void setUpMenu() {
		myMenuBar = new VMenuBar();
		// Pause Replay
		VButton pauseButton = new VButton("Pause Replay");
		pauseButton.setOnMouseClicked(e -> myReplayStatus = false);
		myMenuBar.addButton(pauseButton);
		// Resume Replay
		VButton resumeButton = new VButton("Resume Replay");
		resumeButton.setOnMouseClicked(e -> {
			myReplayStatus = true;
			myAnimation.setRate(1.0);
		});
		myMenuBar.addButton(resumeButton);
		// Fast Forward Button
		VButton fastButton = new VButton("Fast Forward");
		fastButton.setOnMouseClicked(e -> myAnimation.setRate(1.5));
		myMenuBar.addButton(fastButton);
		// Fast Forward Button
		VButton slowButton = new VButton("Slow Forward");
		slowButton.setOnMouseClicked(e -> myAnimation.setRate(0.5));
		myMenuBar.addButton(slowButton);
	}

	/**
	 * adds menubar and replay screen to the view
	 */
	private void addToView() {
		myView.getChildren().add(myMenuBar.getNode());
		myView.getChildren().add(myScreen);
	}

	/**
	 * Add ImageViews for each game Entity onto the replay screen.
	 */
	private void addToStage() {
		for (ImageView key : myReplayMap.keySet()) {
			List<Point2D> pointsList = myReplayMap.get(key);
			key.setX(pointsList.get(0).getX());
			key.setY(pointsList.get(0).getY());
			myScreen.getChildren().add(key);
		}
	}

	/**
	 * Sets up the stage for the for the game replay.
	 */
	private void setUpStage() {
		myStage.setTitle("Replay of Game");
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		myStage.setX(primaryScreenBounds.getMinX());
		myStage.setY(primaryScreenBounds.getMinY());
		myStage.setWidth(primaryScreenBounds.getWidth());
		myStage.setHeight(primaryScreenBounds.getHeight());
		Scene scene = new Scene(myView);
		scene.getStylesheets().add("./game.player.styling/styleSheet.css");
		myStage.setScene(scene);
		myStage.show();
	}

	/**
	 * Updates the position of each game Entity
	 * 
	 * @param frameIndex
	 */
	private void displayObjects(int frameIndex) {
		for (ImageView key : myReplayMap.keySet()) {
			List<Point2D> pointsList = myReplayMap.get(key);
			if (frameIndex < pointsList.size()) {
				key.setX(pointsList.get(frameIndex).getX());
				key.setY(pointsList.get(frameIndex).getY());
			} else {
				myScreen.getChildren().remove(key);
			}
		}
		if (myScreen.getChildren().isEmpty()) {
			myStage.close();
		}
	}

	/**
	 * initializes the game loop.
	 */
	public void startReplay() {
		myReplayStatus = true;
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		myAnimation = new Timeline();
		myAnimation.setCycleCount(Timeline.INDEFINITE);
		myAnimation.getKeyFrames().add(frame);
		myAnimation.play();
	}

	/**
	 * The game loop
	 * 
	 * @param elapsedTime
	 */
	private void step(double elapsedTime) {
		if (myReplayStatus) {
			displayObjects(myFrameIndex);
			myFrameIndex++;
		}
	}

}