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
		System.out.println(fr.retrieveCurrentGamePath("WithAL"));
		GPGameFileReader gp = new GPGameFileReader();
		System.out.println(gp.getGameNames());
		System.out.println(gp.loadLevel("WithAL", "Default"));
	}
}
