package game_player;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Saves highscores program.
 * 
 * @author Kelley Scroggs, Dorian Barber
 *
 */
public class ScoreKeeper {

	private final String filePath = "src/game_player/resources/scores.properties";
	private boolean myStatus;

	/*
	 * Creates a new scoreKeeper object
	 */
	public ScoreKeeper() {
		myStatus = true;
	}

	/**
	 * Creates a new score keeper object and updates the properites file with the
	 * given information.
	 * 
	 * @param player
	 * @param score
	 */
	public ScoreKeeper(String player, String score) {
		updatePropertiesFile(player, score);
	}

	/**
	 * Creates a new score keeper object and updates the properites file with the
	 * given information.
	 * 
	 * @param si
	 */
	public ScoreKeeper(ScoreItem si) {
		updatePropertiesFile(si.getString().split(": ")[0], si.getString().split(": ")[1]);
	}

	/*
	 * Adds a new high score to the scores property file. Called if the player has
	 * achieved a new highscore, or after a player has achieved a new score.
	 */
	private void updatePropertiesFile(String player, String score) {
		try {
			FileInputStream in = new FileInputStream(filePath);
			Properties props = new Properties();
			props.load(in);
			in.close();

			FileOutputStream out = new FileOutputStream(filePath);
			props.setProperty(player, score);
			props.store(out, null);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Couldn't save highscore");
			alert.setContentText(e.getMessage());
			alert.show();
		}
	}

	/**
	 * Clears the saved high scores.
	 */
	public void deletePropsFile() {
		try {
			FileInputStream in = new FileInputStream(filePath);
			Properties props = new Properties();
			props.load(in);
			in.close();

			ResourceBundle keys = ResourceBundle.getBundle("game_player.resources/scores");
			Enumeration<String> s = keys.getKeys();
			// remove the saved scores
			while (s.hasMoreElements()) {
				String key = s.nextElement();
				props.remove(key);
			}
			FileOutputStream out = new FileOutputStream(filePath);
			props.store(out, null);
			out.close();
			myStatus = true;
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Couldn't delete scores");
			alert.setContentText(e.getMessage());
			alert.show();
		}
	}

}
