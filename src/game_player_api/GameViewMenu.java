package game_player_api;

import java.util.List;
import java.util.Map;

import game_player.ScoreItem;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

/**
 * Interface that passes the public functions used by the menubar.
 * 
 * @author Kelley Scroggs
 *
 */
public interface GameViewMenu {

	/**
	 * Allows the user to resume a game in play.
	 */
	public void resumeGame();

	/**
	 * Allows the user to pause a game in play.
	 */
	public void pauseGame();

	/**
	 * Allows the user to change the key bindings for a game mid play.
	 * 
	 * @param propKey
	 * @param keyCode
	 */
	public void changeBinding(String propKey, KeyCode keyCode);

	/**
	 * Allows the user to access the new scores created during the current run of
	 * the game_player app.
	 */
	public List<ScoreItem> getNewScores();

	/**
	 * Allows teh user to clear the new scores created during teh current run of the
	 * game player app.
	 */
	public void clearNewScores();

	/**
	 * Passes the information required for a game replay to be displayed.
	 * @return
	 */
	public Map<ImageView, List<Point2D>> getReplayList();

}
