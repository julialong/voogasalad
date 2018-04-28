package engine.controls;

import java.io.IOException;

import javafx.scene.input.KeyCode;
/**
 * Interface for scheme that handles controls for moving the player
 * @author Robert Gitau
 *
 */
public interface Control {
	/**
	 * Sets the binding of KeyCode to Action class
	 * @param key - the KeyCode
	 * @param action - associated instance of action class
	 * @throws IOException 
	 */
	public abstract void setBinding(KeyCode key, Action action) throws IOException;
	/**
	 * Activate the actions associated with a key binding
	 * @param key - the KeyCode
	 */
	public abstract void activate(KeyCode key);
	/**
	 * Deactivates the pressed key on release.
	 * @param key - the KeyCode
	 */
	public abstract void deactivate(KeyCode key);
}
