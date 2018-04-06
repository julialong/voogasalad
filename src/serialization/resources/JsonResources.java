/**
 * @author  Maya Messiner
 * Resource class to get string values of JSON file characters
 */
package serializer.resources;

import java.util.ResourceBundle;

public class JsonResources {
	public static final ResourceBundle RESOURCEKEYS = ResourceBundle.getBundle("serialization.resources/jsonCharacters");

	private JsonResources()	{
	}
	
	/**
	 * Returns the corresponding character value of key
	 * @param key
	 */
	public static String getString(String key) {
        return RESOURCEKEYS.getString(key);
    }
}
