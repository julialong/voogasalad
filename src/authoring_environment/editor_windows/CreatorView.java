package authoring_environment.editor_windows;

import authoring_environment.authored_elements.AuthoredElement;
import authoring_environment.game_elements.AuthoredGame;

/**
 * 
 * @author Judi Sanchez
 *
 */
public interface CreatorView {

	/**
	 * Gets the Game assigned to the window
	 * @return
	 */
	AuthoredGame getGame();
	
	/**
	 * Opens a new window to allow for multi-window editing 
	 * of the same level or different levels of the game
	 */
	void openNewWindow();
	
	
	
}
