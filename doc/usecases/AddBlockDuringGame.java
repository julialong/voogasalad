package usecases;

import api.data.GAEtoJSON;
import api.data.GEtoJSON;
import api.data.JSONtoObject;
import api.gae.GameCreator;
import api.engine.Level;
import api.player.GameView;
import usecases.sample_object_classes.PlatformGameCreator;

public class AddBlockDuringGame {
	GameCreator myGameCreator;
	GamePlayer myGamePlayer;
	GameView myGameView;
	EngineManager myEngineManager;
	GAEtoJSON myGAEToJson;
	GEtoJSON myGEToJson;
	JSONtoObject myJSONToObj;
	Level myGameLevel;

	public void addBlock()	{
		// save current state of game to return to
		myDataToObj.saveData(myGameLevel, myGameLevel.objects);
		// make new instance of GAE to add objects
		myGameCreator = new GameCreator();

		// show GAE

		myGameCreator.addObject(new Block());

		myGameCreator.saveGame();

		// close GAE

		myJSONToObj.loadLevel(myGameLevel.toString());

		// reloads level from objects passed by myJSONToOjb.loadLevel()
		myGamePlayer.reload();
	}
}