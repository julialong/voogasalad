package engine.controls.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

import engine.controls.Action;
import javafx.scene.input.KeyCode;

/**
 * @author Maya Messinger and Marcus Oertle
 * Started 9 Apr 18
 * Class that parses keybindings from Java properties file
 */
public class Bindings	{
	public final ResourceBundle RESOURCEKEYS = ResourceBundle.getBundle("engine.controls.resources/Controls");

	/**
	 * Returns a KeyCode from a properties file for use with key binding
	 * @param action	word oction to get key of
	 * @return			KeyCode parsed from string action is linked to in properties file
	 */
	public KeyCode getKey(String action)	{
		return KeyCode.valueOf(RESOURCEKEYS.getString(action));
	}
	
	public void updatePropertiesFile(KeyCode key, Action action) throws IOException {
		FileInputStream in = new FileInputStream("src/engine/controls/resources/Controls.properties");
		Properties props = new Properties();
		props.load(in);
		in.close();
		
		FileOutputStream out = new FileOutputStream("src/engine/controls/resources/Controls.properties");
		props.setProperty(action.getClass().getSimpleName(), key.getName().toUpperCase());
		props.store(out, null);
		out.close();
	}
}