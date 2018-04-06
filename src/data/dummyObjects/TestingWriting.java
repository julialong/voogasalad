package data.dummyObjects;

import javafx.application.Application;

import data.gamefiles.GameFileWriter;
import data.serialization.TextWriter;

public class TestingWriting {

	@Test
	public void TestingWriting() {
	}
	
	public static void main(String[] args) {
		GameFileWriter myWriter = new GameFileWriter("TestGame");

		

		myWriter.update(new HashMap<>);
	}
}
