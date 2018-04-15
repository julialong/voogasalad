package game_player;

import engine.level.Level;
import game_player_api.GameView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

import engine.controls.Controls;
import engine.entity.GameEntity;
import engine.entity.Player;

/**
 * Class to run the game loop and check for user commands
 * 
 * @author Kelley Scroggs
 *
 */
public class VoogaGameView implements GameView {

	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private HBox myContainer;
	private Pane myGP;
	private boolean myGameStatus = false;
	private List<Level> myGameLevels;
	//This should represent the index within the myGameLevels, the index should correspond to the level
	private int myCurrLevel = 0;
	private int myLeft = 0;
	private int screenOffset;

	//TODO: talk to backend about how to use these
	private Player myPlayer;
	private Controls myControls;
	
	//TODO: delete
	private int temp = 0;
	
	/**
	 * Creates a grid pane. initializes event listeners
	 * 
	 * @param gameLevels
	 */
	public VoogaGameView(List<Level> gameLevels) {
		myGameLevels = gameLevels;
		// TODO: screenOffset could be passed in from data
		screenOffset = 100;
		// TODO: myCurrLevel should be passed in from data. Need to know the name of the
		// first level. ALso need to know what the next level will be called, and how to
		// get the next one etc.

		myContainer = new HBox();
		myGP = new Pane();
		
		//TODO: player is probably already passed in right???
		myPlayer = new Player();
		myControls = new Controls(myPlayer);
		
		initListeners();
	}

	/**
	 * Listens for user input to the game to pass to the game engine.
	 * TODO: event listeners aren't getting attached properly
	 */
	private void initListeners() {
		System.out.println("INit game input listener");
		myContainer.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			System.out.println("USER PRESSED KEY IN THE GAME");
			if (myGameStatus) {
				myControls.activate(key.getCode());
			}
		});
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
			List<GameEntity> toDisplay = new ArrayList<>();
			System.out.println(myCurrLevel);
			System.out.println(myGameLevels);

			myGameLevels.get(myCurrLevel).update();
			//TODO: Discuss with Kelley -- instead of Player iterating through every single object the game engine actually updates it with the level class
//			for (Object o : myGameLevels.get(myCurrLevel)) {
//				GameEntity castedObject = (GameEntity) o;
//				castedObject.update();
//				double[] castedPosition = castedObject.getPosition();
//				if (castedPosition[0] >= myLeft && castedPosition[0] <= (myLeft + screenOffset)) {
//					toDisplay.add(castedObject);
//				}
//			}
			displayObjects(toDisplay);
		}
	}

	/**
	 * Displays the already updated objects that have been determined to be in
	 * bounds.
	 * 
	 * @param toDisplay
	 */
	private void displayObjects(List<GameEntity> toDisplay) {
		myGP.getChildren().clear();
		for (GameEntity entity : toDisplay) {
			// TODO: not yet a getImagePath method for entities
			// ImageView entityImage = new ImageView(Image(entity.getImagePath()));
			ImageView entityImage = new ImageView(new Image("../data/ExampleElementPictures/brick.png"));

			entityImage.relocate(entity.getPosition()[0] - myLeft, entity.getPosition()[1]);
			myGP.getChildren().add(entityImage);
		}
	}

	public void resumeGame() {
		myGameStatus = true;
	}

	public void pauseGame() {
		myGameStatus = false;
	}

	@Override
	public Node getNode() {
		return myContainer;
	}
	
	/**
	 * test method. remove later now that we have the game loop.
	 */
	@Override
	public void updateGame() {
		myGP.getChildren().clear();
		temp++;
		if (temp % 2 == 0) {
			Rectangle addRect = new Rectangle(20, 20, Color.BLUE);
			addRect.relocate(temp, temp);
			myGP.getChildren().add(addRect);
		} else {
			Rectangle addRect = new Rectangle(20, 20, Color.GREEN);
			addRect.relocate(temp, temp);
			myGP.getChildren().add(addRect);
		}
	}

}
