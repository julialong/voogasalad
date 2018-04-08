package data.dummyObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.gamefiles.GameFileWriter;
import engine.entity.*;
import engine.level.Level;
import engine.level.BasicLevel;

public class TestingWriting {
	
	public static void main(String[] args) {
		GameFileWriter myWriter = new GameFileWriter("TestGame");

		myWriter.update(makeDummyObjects());
	}

	private static Map<Level, List<GameEntity>> makeDummyObjects()	{
		Map<Level, List<GameEntity>> objsOrganized = new HashMap<>();
		List<GameEntity> objsToWrite = new ArrayList<>();

		objsToWrite.add(new Block());
		objsToWrite.add(new Foes(new Player()));
		objsToWrite.add(new Flag());
		objsToWrite.add(new Player());
		objsToWrite.add(new Block());
		objsToWrite.add(new Flag());

		Level one = new BasicLevel();
		objsOrganized.put(one, new ArrayList<GameEntity>());
		for (GameEntity obj:objsToWrite)	{
			objsOrganized.get(one).add(obj);
		}

		return objsOrganized;
	}

}