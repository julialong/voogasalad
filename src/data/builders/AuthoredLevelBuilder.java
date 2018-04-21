package data.builders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
	
	private File levelFile;
	
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
		
		ScrollingGrid gridForAuthoredLevel = retrieveScrollingGrid(levelFile);
		
		return new AuthoredLevel(levelForAuthoredLevel, gridForAuthoredLevel);
	}

	/**
	 * Creates the scrolling grid from the JSON array
	 * 
	 * @param levelFile
	 * @return
	 */
	private ScrollingGrid retrieveScrollingGrid(File levelFile) throws DataFileException
	{
		JsonParser jsonParser = new JsonParser();
		ScrollingGrid scrollingGrid = new ScrollingGrid();
		try 
		{
			JsonObject jobject;
			jobject = jsonParser.parse(new FileReader(levelFile)).getAsJsonObject();
			JsonArray gridCells = jobject.getAsJsonArray("ScrollingGrid");
			LevelSerializer ls = new LevelSerializer();
			scrollingGrid = ls.deserialize(gridCells);
		} 
		catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) 
		{
			throw new DataFileException("Could not find the file to load for AuthoredLevel", e);
			// TODO Auto-generated catch block
//			JOptionPane.showMessageDialog(new JFrame(),
//					 "Could not find the file to load for AuthoredLevel",
//					    "File Reader Exception",
//				    JOptionPane.WARNING_MESSAGE);
			// TODO remove print stack trace
//			e.printStackTrace();
		}
		return scrollingGrid;
	}
}
