package engine.controls.resources;

import java.util.ResourceBundle;
import javafx.scene.input.KeyCode;

/**
 * @author Maya Messinger
 * Started 9 Apr 18
 * Class that parses keybindings from Java properties file
 */
public class Bindings	{
	public static final ResourceBundle RESOURCEKEYS = ResourceBundle.getBundle("engine.controls.resources/Controls");

	/**
	 * Returns a KeyCode from a properties file for use with key binding
	 * @param action	word oction to get key of
	 * @return			KeyCode parsed from string action is linked to in properties file
	 */
	public static KeyCode getKey(String action)	{
		return KeyCode.valueOf(RESOURCEKEYS.getString(action));
	}
}