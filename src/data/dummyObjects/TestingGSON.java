package data.dummyObjects;


import data.fileReading.FileRetriever;
import data.fileReading.GPGameFileReader;
import data.gamefiles.GameFileReader;

/**
 * Class used for testing serialization.
 * 
 * @author Belanie Nagiel
 *
 */
public class TestingGSON {
	
	public static void main(String[] args) 
	{
		FileRetriever fr = new FileRetriever();
		System.out.println(fr.retrieveAllGamePaths());
		System.out.println(fr.retrieveCurrentGamePath("Game1"));
		System.out.println(fr.retrieveLevel("Game3", "samplelevel"));
		
		GPGameFileReader gp = new GPGameFileReader();
		System.out.println(gp.getGameNames());
		
	}
}
