package data.dummyObjects;
import data.fileReading.GAEGameFileReader;

/**
 * Class used for testing serialization.
 * 
 * @author Belanie Nagiel
 *
 */
public class TestingGSON {
	
	public static void main(String[] args) 
	{
//		FileRetriever fr = new FileRetriever();
//		System.out.println(fr.retrieveAllGamePaths());
//		System.out.println(fr.retrieveCurrentGamePath("WithAL"));
//		GPGameFileReader gp = new GPGameFileReader();
//		System.out.println(gp.getGameNames());
//		System.out.println(gp.loadLevel("WithAL", "Default"));
		
//		LevelBuilder lb = new LevelBuilder(new File("./data/levelData/Default.json"));
//		Level l = lb.buildLevel();
//		System.out.println(l.getObjects());
		
//		GPGameFileReader gp = new GPGameFileReader();
//		gp.loadCompleteGame("TestA");
		
//		System.out.println(File.separator);
		
		GAEGameFileReader gae = new GAEGameFileReader();
		System.out.println(gae.loadAuthoredLevelNames());
		
	}
}
