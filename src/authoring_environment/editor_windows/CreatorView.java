package authoring_environment.editor_windows;


import authoring_environment.game_elements.AuthoredGame;
import javafx.scene.control.ScrollPane;

/**
 * 
 * @author Judi Sanchez, Julia Long
 *
 */
public interface CreatorView {

	/**
	 * Gets the Game assigned to the window
	 * @return
	 */
	AuthoredGame getGame();

	/**
	 * Changes the current game
	 * @param newGame
	 */
	@Deprecated
	void changeCurrentGame(AuthoredGame newGame);
	
	/**
	 * Opens a new window to allow for multi-window editing 
	 * of the same level or different levels of the game
	 */
	void openNewWindow();

	/**
	 * Gets the current pane
	 * @return the pane currently being shown
	 */
	ScrollPane getPane();
	
}
