package usecases;

import api.data.GAEtoJSON;
import api.data.GEtoJSON;
import api.data.JSONtoObject;
import api.gae.GameCreator;
import api.player.GameView;
import usecases.sample_object_classes.PlatformGameCreator;

public class AddBlockDuringGame {
	GameCreator myGameCreator;
	GameView myGameView;
	EngineManager myEngineManager;
	GAEtoJSON myGAEToJson;
	GEtoJSON myGEToJson;
	JSONtoObject myJSONToObj;
	GameLevel myGameLevel;

	public void addBlock()	{
		// save current state of game to return to
		myDataToObj.saveData(myGameLevel, myGameLevel.objects);
		// make new instance of GAE to add objects
		myGameCreator = new GameCreator();

		// myGameCreator.addObject(new Block());

		myGameCreator.saveGame();

		// myGameCreator.close();
	}


}

public class GameLevel	{
	List<Object> objects;

	public GameLevel()	{
	}
}