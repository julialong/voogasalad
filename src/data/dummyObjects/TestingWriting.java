package data.dummyObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.gamefiles.GameFileWriter;
import engine.controls.Controls;
import engine.controls.resources.Bindings;
import engine.entity.*;
import engine.level.Level;
import engine.level.BasicLevel;

public class TestingWriting {

	public static void main(String[] args) {
		GameFileWriter myWriter = new GameFileWriter("TestGame");

		myWriter.saveIndivLevel(makeDummyObjects());
		myWriter.saveData(makeDummyObjects(), makeDummyObjects().getObjects());
	}

	private static Level makeDummyObjects()	{
		Map<Level, List<GameEntity>> objsOrganized = new HashMap<>();
		List<GameEntity> objsToWrite = new ArrayList<>();

		Player p = new Player();
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
			one.addObject(obj);
		}

		return one;
	}

}
