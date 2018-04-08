package data.dummyObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.gamefiles.GameFileWriter;

public class TestingWriting {
	
	public static void main(String[] args) {
		GameFileWriter myWriter = new GameFileWriter("TestGame");

		myWriter.update(makeDummyObjects());
	}

	private static Map<Level, List<GameObject>> makeDummyObjects()	{
		Map<Level, List<GameObject>> objsOrganized = new HashMap<>();
		List<GameObject> objsToWrite = new ArrayList<>();

		objsToWrite.add(new GameObject());
		objsToWrite.add(new GameObject2());
		objsToWrite.add(new GameObject());
		objsToWrite.add(new GameObject2());
		objsToWrite.add(new GameObject3());
		objsToWrite.add(new GameObject3());

		Level one = new Level();
		objsOrganized.put(one, new ArrayList<GameObject>());
		for (GameObject obj:objsToWrite)	{
			objsOrganized.get(one).add(obj);
		}

		return objsOrganized;
	}

}
