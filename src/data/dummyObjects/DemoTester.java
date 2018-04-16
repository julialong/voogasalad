package data.dummyObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.gamefiles.GameFileReader;
import data.gamefiles.GameFileWriter;
import engine.entity.Block;
import engine.entity.Flag;
import engine.entity.Foes;
import engine.entity.GameEntity;
import engine.entity.Player;
import engine.level.BasicLevel;
import engine.level.Level;

public class DemoTester {

	public static void main(String[] args) {
		
		//Writing
		GameFileWriter gameForDemo = new GameFileWriter("DemoGame");
		
		Map<Level, List<GameEntity>> objsOrganized = new HashMap<>();
		List<GameEntity> objsToWrite = new ArrayList<>();

		objsToWrite.add(new Block());
		objsToWrite.add(new Foes());
		objsToWrite.add(new Flag());
		objsToWrite.add(new Player());
		objsToWrite.add(new Block());
		objsToWrite.add(new Flag());
		objsToWrite.add(new Foes());

		Level one = new BasicLevel();
		objsOrganized.put(one, new ArrayList<GameEntity>());
		for (GameEntity obj:objsToWrite)	{
			objsOrganized.get(one).add(obj);
		}
		Level two = new BasicLevel();
		two.setName("Default2");
		objsOrganized.put(two, new ArrayList<GameEntity>());
		for (GameEntity obj:objsToWrite)	{
			objsOrganized.get(two).add(obj);
		}

		// gameForDemo.update(objsOrganized);
		
		//Reading
		GameFileReader fileReader = new GameFileReader();
		List<Level> game = fileReader.loadCompleteGame("DemoGame");
		System.out.print(game);
		

	}
}
