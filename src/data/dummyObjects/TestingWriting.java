
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

// public class TestingWriting {
//package data.dummyObjects;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import data.gamefiles.GameFileWriter;
//import engine.controls.Controls;
//import engine.controls.resources.Bindings;
//import engine.entity.*;
//import engine.level.Level;
//import engine.level.BasicLevel;
//
//public class TestingWriting {
//
//	public static void main(String[] args) {
//		GameFileWriter myWriter = new GameFileWriter("TestGame");
//
//		myWriter.update(makeDummyObjects());
//
//		System.out.println(new Controls(new Player(), Bindings.getKey("up"), Bindings.getKey("down"), Bindings.getKey("left"),
//				Bindings.getKey("right"), Bindings.getKey("jump"), Bindings.getKey("attac")));
//	}
//
//	private static Map<Level, List<GameEntity>> makeDummyObjects()	{
//		Map<Level, List<GameEntity>> objsOrganized = new HashMap<>();
//		List<GameEntity> objsToWrite = new ArrayList<>();
//
//		Player p = new Player();
//		objsToWrite.add(new Block());
//		objsToWrite.add(new Foes(p));
//		objsToWrite.add(new Flag());
//		objsToWrite.add(new Player());
//		objsToWrite.add(new Block());
//		objsToWrite.add(new Flag());
//		objsToWrite.add(new Foes(p));
//
//		Level one = new BasicLevel();
//		objsOrganized.put(one, new ArrayList<GameEntity>());
//		for (GameEntity obj:objsToWrite)	{
//			objsOrganized.get(one).add(obj);
//		}
//
//		return objsOrganized;
//	}
//
//}
