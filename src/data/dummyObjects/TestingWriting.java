package data.dummyObjects;

import java.io.FileWriter;

import authoring_environment.game_elements.AuthoredLevel;
import authoring_environment.grid.ScrollingGrid;
import data.gamefiles.GameFileWriter;
import engine.entity.*;
import engine.level.Level;
import engine.level.BasicLevel;

public class TestingWriting {

	public static void main(String[] args) {
		GameFileWriter myWriter = new GameFileWriter("User3","TestB");

		FileWriter fw;

		myWriter.saveIndivLevel(makeDummyObjects());
		myWriter.saveData(makeDummyObjects());
	}

	private static AuthoredLevel makeDummyObjects()	{
		Level one = new BasicLevel();
		AuthoredLevel oneA = new AuthoredLevel(one, new ScrollingGrid());

		Player p = new Player();
		one.addObject(new Block());
		one.addObject(new Foes());
		one.addObject(new Flag());
		one.addObject(new Player());
		one.addObject(new Block());
		one.addObject(new Flag());
		one.addObject(new Foes());

		return oneA;
	}

}
