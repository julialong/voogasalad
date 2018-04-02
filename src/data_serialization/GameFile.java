package data_serialization;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GameFile {
	
	private String gameDirectory;
	
	public GameFile(String gameName)
	{
		gameDirectory = "./data/gameData/" + gameName + "/";
	}
	
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
}
