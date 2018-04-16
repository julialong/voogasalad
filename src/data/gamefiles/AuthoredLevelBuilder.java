package data.gamefiles;

import java.io.File;

import authoring_environment.game_elements.AuthoredLevel;
import engine.level.Level;

public class AuthoredLevelBuilder {
	
	private File levelFile;
	
	public AuthoredLevelBuilder(File level) 
	{
		levelFile = level;
	}
	
	public AuthoredLevel buildAuthoredLevel()
	{
		LevelBuilder levelBuilder = new LevelBuilder(levelFile);
		Level levelForAuthoredLevel = levelBuilder.buildLevel();
		
		//get the scrolling grid
		
		//make a new AuthoredLevel object
		return null;
	}
}
