package data_serialization;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * @author Belanie Nagiel
 * 
 * Class that facilitates the editing of level files through the 
 * GameFileWriter.
 *
 */
public class GameFile {
	
	private String gameDirectory;
	
	/**
	 * Class Constructor.
	 * Defines the game directory.
	 * 
	 * @param gameFilePath
	 */
	public GameFile(String gameFilePath)
	{
		gameDirectory = gameFilePath + "/";
	}
	
	/**
	 * Accesses the game level file and creates it if necessary.
	 * 
	 * @param levelNumber
	 * @return the level JSON file
	 */
	private File level(int levelNumber)
	{
		File newLevel = new File( gameDirectory + "Level_" + Integer.toString(levelNumber) + ".json");
		if(!newLevel.exists()) { 
			try {
				newLevel.createNewFile();
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return newLevel;
	}
	
	/**
	 * Adds given JSON information the the level file.
	 * 
	 * @param levelNumber
	 * @param jsonToWrite
	 */
	public void writeToLevel(int levelNumber, String jsonToWrite)
	{
		File currentLevel = level(levelNumber);
		try {
			FileWriter levelWriter = new FileWriter(currentLevel, true);
			levelWriter.write(jsonToWrite);
			levelWriter.flush();
			levelWriter.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeFromLevel(int levelNumber, String jsonToRemove)
	{
		
	}
}
