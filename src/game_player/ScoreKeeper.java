package game_player;

import java.util.List;
import java.io.File;
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
 * Saves highscores program.
 * 
 * @author Kelley Scroggs, Dorian Barber
 *
 */
public class ScoreKeeper {

	private final String filePath = "src/game_player/resources/scores.properties";

	public ScoreKeeper() {
		
	}
	
	public ScoreKeeper(String player, String score) {
		updatePropertiesFile(player, score);
	}

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
	 * Clears the saved highscores.
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
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Couldn't delete scores");
			alert.setContentText(e.getMessage());
			alert.show();
		}
	}

}
