package data.builders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import authoring_environment.game_elements.AuthoredLevel;
import authoring_environment.grid.ScrollingGrid;
import data.resources.DataFileException;
import data.serialization.LevelSerializer;
import engine.level.Level;
/**
 * 
 * Creates authored levels to return to the Game Authoring Environment when a 
 * game is going to be edited again.
 * 
 * @author Belanie Nagiel
 *
 */
public class AuthoredLevelBuilder {
	
	private final static String SCROLLING_GRID = "ScrollingGrid";
	private File levelFile;
	private int levelID;
	
	/**
	 * Class Constructor
	 * Takes in the level JSON file to be converted to an authored level
	 * 
	 * @param level
	 */
	public AuthoredLevelBuilder(File level) 
	{
		levelFile = level;
	}
	
	/**
	 * Returns an authored level made up of a Level object and a ScrollingGrid
	 * @return
	 */
	public AuthoredLevel buildAuthoredLevel() throws DataFileException
	{
		LevelBuilder levelBuilder = new LevelBuilder(levelFile);
		Level levelForAuthoredLevel = levelBuilder.buildLevel();
		levelID = levelBuilder.getLevelID();
		
		ScrollingGrid gridForAuthoredLevel = new ScrollingGrid();		
		AuthoredLevel al = new AuthoredLevel(levelForAuthoredLevel, gridForAuthoredLevel);

		retrieveScrollingGrid(levelFile, gridForAuthoredLevel, al);

		return al;
	}

	/**
	 * Creates the scrolling grid from the JSON array
	 * 
	 * @param levelFile
	 * @return
	 */
	private void retrieveScrollingGrid(File levelFile, ScrollingGrid scrollingGrid, AuthoredLevel al) throws DataFileException
	{
		JsonParser jsonParser = new JsonParser();
		try 
		{
			JsonObject jobject;
			jobject = jsonParser.parse(new FileReader(levelFile)).getAsJsonObject();
			JsonArray gridCells = jobject.getAsJsonArray(SCROLLING_GRID);
			LevelSerializer ls = new LevelSerializer();
			scrollingGrid = ls.deserialize(gridCells, scrollingGrid, al);
		} 
		catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) 
		{
			throw new DataFileException("Could not find the file to load for AuthoredLevel", e);
		}
	}

	public int getLevelID() 
	{
		return levelID;
	}

}
