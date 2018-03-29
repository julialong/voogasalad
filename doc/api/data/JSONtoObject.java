package api.data;

public interface JSONtoObject {
	/*
	 * Objects of this type will read through the JSON files for the settings and levels of a game
	 * and create the appropriate GAE objects. The settings file and each level file will be mapped
	 * to a list of the objects contained in the game authoring environement (obstacles, enemies, etc.) 
	 * and those objects will be updated to contain their relevant information like position or ID. 
	 */
	
	List<Object> loadGame(String gameName);
	/*
	 * GAE calls this method if a user chooses to begin editing or playing an existing game. This will locate the existing game folder 
	 * based on the game name and create the appropriate list of levels and existing objects.
	 */
	
	List<Object> loadLevel(String gameName, String levelName);
	/*
	 * GAE calls this method if a user chooses to begin editing an existing level. This will locate the existing game folder 
	 * based on the game name and and the appropriate level based on the levelName. Then it will create a list of the appropriate
	 * objects for the level. 
	 */
	
	List<Object> loadSettings(String gameName);
	/*
	 * GAE calls this method if a user chooses to begin editing the general settings for a specific game. This will locate the 
	 * existing game folder based on the game name and will create a list of the appropriate objects associated with the current
	 * settings. 
	 */
	
}
