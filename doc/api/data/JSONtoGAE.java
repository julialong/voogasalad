package voogasalad_supersaladsquad;

public interface JSONtoGAE {
	/*
	 * Objects of this type will read through the JSON files for the settings and levels of a game
	 * and create the appropriate GAE objects. The settings file and each level file will be mapped
	 * to a list of the objects contained in the game authoring environement (obstacles, enemies, etc.) 
	 * and those objects will be updated to contain their relevant information like position or ID. 
	 */
	
	void loadExisting(String gameName);
	/*
	 * GAE calls this method if a user chooses to begin editing an existing game. This will locate the existing game folder 
	 * based on the game name and call the passToGAE method in order to create the appropriate map of levels to existing objects.
	 */
	
	Map<Level, List<FileObjects>> passToGAE();
	/*
	 * This method will return the map of settings/levels to the list of objects/properties of that level. 
	 * Will use reflection to create instances of the level/settings classes based on the file name and
	 * to create the diffierent objects.
	 * 
	 * Sample Flow of Events:
	 * 	newLevel()
	 * 		for enemy:enemies
	 * 			aGoomba = new Goomba();
	 * 			aGoomba.xPos = x;
	 * 			aGoomba.yPos = y;
	 * 			newLevel.enemies.add(aGoomba);
	 */
}
