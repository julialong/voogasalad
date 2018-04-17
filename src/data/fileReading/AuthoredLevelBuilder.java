package data.fileReading;

import java.io.File;
import java.io.FileReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import authoring_environment.game_elements.AuthoredLevel;
import authoring_environment.grid.ScrollingGrid;
import data.serialization.LevelSerializer;
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
		
		ScrollingGrid gridForAuthoredLevel = retrieveScrollingGrid(levelFile);
		
		return new AuthoredLevel(levelForAuthoredLevel, gridForAuthoredLevel);
	}

	private ScrollingGrid retrieveScrollingGrid(File levelFile) 
	{
		JsonParser jsonParser = new JsonParser();
		try 
		{
			JsonObject  jobject = jsonParser.parse(new FileReader(levelFile)).getAsJsonObject();
			JsonArray gridCells = jobject.getAsJsonArray("ScrollingGrid");
			LevelSerializer ls = new LevelSerializer();
			ScrollingGrid scrollingGrid = ls.deserialize(gridCells);
			return scrollingGrid;
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
