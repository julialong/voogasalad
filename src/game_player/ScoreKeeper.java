package game_player;

import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Class to manage keeping track of highscores through successive runs of the
 * program.
 * 
 * @author Kelley Scroggs, Dorian Barber
 *
 */
public class ScoreKeeper {

	private final int MIN_WINDOW_WIDTH = 600;
	private ResourceBundle myKeys;

	/**
	 * launches a new window to display the high scores.
	 */
	public void setUpStage() {
		myKeys = ResourceBundle.getBundle("game_player.resources/scores");
		Stage stage = new Stage();
		stage.setTitle("My High Scores");
		stage.setMinWidth(MIN_WINDOW_WIDTH);
		Scene scene = new Scene(this.displayScores(), MIN_WINDOW_WIDTH, MIN_WINDOW_WIDTH);
		scene.getStylesheets().add("./game.player.styling/styleSheet.css");
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * adds the sorted scores to the scene to be displayed.
	 * 
	 * @return
	 */
	private VBox displayScores() {
		// TODO Auto-generated method stub
		VBox scoreBox = new VBox();
		for (ScoreItem si : getScores()) {
			Label scoreLabel = new Label(si.getString());
			scoreBox.getChildren().add(scoreLabel);
		}
		return scoreBox;
	}

	/**
	 * Returns the high scores stored in the properties file. Scores are mapped in
	 * ascending order of score to player name.
	 * 
	 * @return
	 */
	private List<ScoreItem> getScores() {
		List<ScoreItem> retList = new ArrayList<>();
		Enumeration<String> s = myKeys.getKeys();
		while (s.hasMoreElements()) {
			String key = s.nextElement();
			retList.add(new ScoreItem(key, Integer.parseInt(myKeys.getString(key))));
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

	/*
	 * Adds a new high score to the scores property file. Called if the player has
	 * achieved a new highscore, or after a player has achieved a new score.
	 */
	public void updatePropertiesFile(String player, String score){
		try {
			FileInputStream in = new FileInputStream("src/game_player/resources/scores.properties");
			Properties props = new Properties();
			props.load(in);
			in.close();
	
			FileOutputStream out = new FileOutputStream("src/game_player/resources/scores.properties");
			props.setProperty(player, score);
			props.store(out, null);
			out.close();
		} catch(Exception e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Couldn't save highscore");
			alert.setContentText(e.getMessage());
			alert.show();
		}
		
	}
}
