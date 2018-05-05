package game_player;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import engine.controls.Controls;
import engine.entity.GameEntity;
import engine.entity.Player;
import engine.level.Level;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

/**
 * Class to display game entities at their most up to date position. I think
 * this code shows good design because I was able to refactor the original
 * version to separate out all of the display and control features. This is
 * initially how we designed the gameview, but as the development progressed,
 * this class began to take on the dual purpose of displaying and controlling
 * the game. This exemplifies the first principle of SOLID design: single
 * purpose.
 */
public class GameDisplay {
	private Level myGameLevel;
	private Pane myGP;
	private Controls myControls;
	private Map<GameEntity, ImageView> myDispMap = new HashMap<>();

	public GameDisplay(Level gameLevel) {
		myGameLevel = gameLevel;
		myGP = new Pane();
		myGP.setBackground(
				new Background(new BackgroundFill(Color.web(myGameLevel.getColor()), CornerRadii.EMPTY, Insets.EMPTY)));
		initDisplayMap();

	}

	/**
	 * Adds all of the levels objects to a map that maps them to a position
	 */
	private void initDisplayMap() {
		for (GameEntity ge : myGameLevel.getObjects()) {
			String imgPath = ge.getImagePath();
			if (ge instanceof Player) {
				myControls = new Controls((Player) ge);
			}
			ImageView entityImage = new ImageView(new Image(new File(imgPath).toURI().toString(),
					adjustXCord(ge.getSizeX()), adjustYCord(ge.getSizeY()), false, false));
			entityImage.setX(adjustXCord(ge.getScenePosition()[0]));
			entityImage.setY(adjustYCord(ge.getScenePosition()[1]));
			myDispMap.put(ge, entityImage);
			myGP.getChildren().add(myDispMap.get(ge));
		}
	}

	/**
	 * Displays the already updated objects that have been determined to be in
	 * bounds. Removes the objects that have been destroyed after the level is
	 * updated.
	 */
	public void displayObjects() {
		Level level = myGameLevel;
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
	 * Calibrates x coordinates to be at the center of the screen and multiplies by
	 * factor to make them bigger.
	 * 
	 * @param x
	 * @return
	 */
	private double adjustXCord(double x) {
		return x * (Screen.getPrimary().getVisualBounds().getWidth() / myGameLevel.getCamSize()[0]);
	}

	/**
	 * Calibrates y coordinates to be at the center of the screen and multiples by a
	 * factor to make the difference between them larger.
	 * 
	 * @param y
	 * @return
	 */
	private double adjustYCord(double y) {
		return y * (Screen.getPrimary().getVisualBounds().getHeight() / myGameLevel.getCamSize()[1]);
	}

	public Controls findControlsObj() {
		return myControls;
	}

	public Pane getPane() {
		return myGP;
	}
}