package voogasalad_supersaladsquad;

public interface JSONtoGAE {
	/*
	 * Objects of this type will read through the JSON files for the settings and levels of a game
	 * and create the appropriate GAE objects. The settings file and each level file will be mapped
	 * to a list of the objects contained in the game authoring environement (obstacles, enemies, etc.) 
	 * and those objects will be updated to contain their relevant information like position or ID. 
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
