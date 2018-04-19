package data.dummyObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

import authoring_environment.game_elements.AuthoredLevel;
import authoring_environment.grid.ScrollingGrid;
import data.gamefiles.GameFileWriter;
import engine.entity.*;
import engine.level.Level;
import engine.level.BasicLevel;

public class TestingWriting {

	public static void main(String[] args) {
		GameFileWriter myWriter = new GameFileWriter("WithAL");

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
