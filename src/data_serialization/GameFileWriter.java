package data_serialization;

import java.io.File;

public class GameFileWriter {
	
	public String gameName;
	public String gameDirectory;
	public GameFile gameToEdit;
	
	public GameFileWriter(String game) 
	{
		gameName = game;
		gameDirectory = "./data/gameData/" + game;
	}
	
	public void newGame()
	{
		File gameFolder = new File(gameDirectory);
		gameFolder.mkdir();
		loadGame();
	}
	
	public void loadGame()
	{
		gameToEdit = new GameFile(gameName);
	}
	
	public void addToLevel(int level, String updateInfo)
	{
		gameToEdit.writeToLevel(1, updateInfo);
	}
	
	public void removeFromLevel(String gameName, int level, String updateInfo)
	{
	}
	
}
