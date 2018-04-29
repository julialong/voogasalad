package game_player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Class to manage keeping track of highscores through successive runs of the
 * program.
 * 
 * @author Kelley Scroggs, Dorian Barber
 *
 */
public class ScoreKeeperManager {

	private final int MIN_WINDOW_WIDTH = 600;
	private VBox myDisplay;
	private VBox myScores;
	private List<ScoreItem> scoresToAdd;
	private VoogaGameView myGameView;
	
	public ScoreKeeperManager(VoogaGameView gv) {
		myGameView = gv;
		scoresToAdd = myGameView.getNewScores();
		setUpStage();
	}
	
	/**
	 * launches a new window to display the high scores.
	 */
	private void setUpStage() {
		Stage stage = new Stage();
		myDisplay = new VBox();
		myScores = new VBox();
		stage.setTitle("My High Scores");
		stage.setMinWidth(MIN_WINDOW_WIDTH);
		setUpDisplay();
		updateScores();
		Scene scene = new Scene(myDisplay, MIN_WINDOW_WIDTH, MIN_WINDOW_WIDTH);
		scene.getStylesheets().add("./game.player.styling/styleSheet.css");
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Adds high scores and clear button to the pop up
	 */
	private void setUpDisplay() {
		myDisplay.getChildren().add(myScores);
		// button to clear the scores
		VButton clearButton = new VButton("Clear List");
		clearButton.setOnMouseClicked(e ->{
			myScores.getChildren().clear();
			ScoreKeeper sk = new ScoreKeeper();
			myGameView.clearNewScores();
			sk.deletePropsFile();
		});
		myDisplay.getChildren().add(clearButton);
	}
	
	/**
	 * adds the sorted scores to the scene to be displayed.
	 */
	private void updateScores() {
		myScores.getChildren().clear();
		for (ScoreItem si : getScores()) {
			Label scoreLabel = new Label(si.getString());
			myScores.getChildren().add(scoreLabel);
		}
	}

	/**
	 * Returns the high scores stored in the properties file. Scores are mapped in
	 * ascending order of score to player name.
	 * 
	 * @return
	 */
	private List<ScoreItem> getScores() {
		ResourceBundle keys = ResourceBundle.getBundle("game_player.resources/scores");
		List<ScoreItem> retList = new ArrayList<>();
		Enumeration<String> s = keys.getKeys();
		// add the saved scores
		while (s.hasMoreElements()) {
			String key = s.nextElement();
			retList.add(new ScoreItem(key, Integer.parseInt(keys.getString(key))));
		}
		// add the current game's scores & save them to the prop file
		if(scoresToAdd != null) {
			for (ScoreItem si : scoresToAdd) {
				retList.add(si);
				new ScoreKeeper(si);
			}
		}
		return sortScores(retList);
	}

	/**
	 * Sort the scores before displaying them.
	 * 
	 * https://stackoverflow.com/questions/41982382/efficient-way-of-sorting-a-list-of-tuples-in-java?noredirect=1&lq=1
	 */
	private List<ScoreItem> sortScores(List<ScoreItem> list) {
		Comparator<ScoreItem> myComparator = (ScoreItem t1, ScoreItem t2) -> t2.getScore() - t1.getScore();
		Collections.sort(list, myComparator);
		return list;
	}
}
