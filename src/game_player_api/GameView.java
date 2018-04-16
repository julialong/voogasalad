package game_player_api;

import javafx.scene.Node;

/**
 * Defines the GameView, a GUI object that acts as the screen for the user to
 * play the game on.
 */
public interface GameView {
	/**
	 * Returns the GameView node so it can be attached to the game player GUI.
	 */
	public Node getNode();

}
