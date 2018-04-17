package data.fileReading;

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
			JsonObject jobject;
			jobject = jsonParser.parse(new FileReader(levelFile)).getAsJsonObject();
			JsonArray gridCells = jobject.getAsJsonArray("ScrollingGrid");
			LevelSerializer ls = new LevelSerializer();
			ScrollingGrid scrollingGrid = ls.deserialize(gridCells);
			return scrollingGrid;
		} 
		catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JFrame(),
					 "Could not find the file to load for AuthoredLevel",
					    "File Reader Exception",
				    JOptionPane.WARNING_MESSAGE);
			
			e.printStackTrace();
		}
		return null;
	}
}
