package game_player_api;

import javafx.scene.Node;

/**
 * Defines the MenuBar, a GUI object that user can interact with while playing the game, contains buttons 
 */
public interface MenuBar {

	/**
	 * Returns the menubar node so it can be attached to the game player GUI
	 */
	public Node getNode();

	/**
	 * Adds a button b to the menubar object
	 * 
	 * @param b 
	 * 			the button to add to the menubar
	 */
	public void addButton(GamePlayerButton b);
}
