package api.player;

/**
 * Defines the GamePlayerButton, a GUI object that is part of the menubar that
 * the user can manipulate with while playing the game
 */
public interface GamePlayerButton {

	/**
	 * Gets the value of the user button. Is a string if the user has clicked the
	 * button, null otherwise
	 *
	 * @return the value stored in the button if it has been clicked
	 */
	public String getCommandFromButton();

	/*
	 * Sets the button value back to null after its value has been taken from the
	 * controller.
	 */
	public void clearButton();

}
