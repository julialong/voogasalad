package data.dummyObjects;

import java.io.File;
import java.lang.reflect.Field;

import data.fileReading.GAEGameFileReader;
import data.fileReading.GPGameFileReader;
import data.levelBuilders.LevelBuilder;
import data.resources.DataFileException;
import data.serialization.Serializer;
import engine.behavior.MoveForward;
import engine.behavior.UseWeapon;
import engine.entity.Enemy;
import engine.entity.Player;
import engine.powerup.Invincible;


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
		
//		GPGameFileReader gae = new GPGameFileReader();
//		try {
//			System.out.println(gae.loadCompleteGame("ErrorWithEnemies"));
//		} catch (DataFileException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		try
//		{
//			Field[] fields = Class.forName("engine.level.BasicLevel").getDeclaredFields();
//			for (Field field : fields) {
//				System.out.println(field);
////			    NotNull notNull = field.getAnnotation(NotNull.class);
////			    field.setAccessible(true);
//			}	
//		} 
//		catch (SecurityException | ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		GAEGameFileReader gae = new GAEGameFileReader();
		try {
			System.out.println(gae.loadAuthoredLevel("testJBP"));
		} catch (DataFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
