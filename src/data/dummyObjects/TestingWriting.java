package data.dummyObjects;

import java.io.FileWriter;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import authoring_environment.game_elements.AuthoredLevel;
import authoring_environment.grid.ScrollingGrid;
import data.gamefiles.GameFileWriter;
import data.resources.DataFileException;
import engine.entity.*;
import engine.level.Level;
import engine.level.BasicLevel;

public class TestingWriting {

	public static void main(String[] args) {
		try	{
			GameFileWriter myWriter = new GameFileWriter("User3","TestB");

			FileWriter fw;

			myWriter.saveIndivLevel(makeDummyObjects());
			myWriter.saveData(makeDummyObjects());
		}
		catch (DataFileException e)	{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(e.getCause().toString());
			alert.setContentText(e.getMessage());
			alert.show();
		}
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
