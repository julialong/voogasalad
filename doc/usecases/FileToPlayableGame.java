package usecases;

import api.data.JSONtoObject;
import api.player.*;

public class FileToPlayableGame {
	
	/*
	 * Creates a new instance of the JSONtoObject interface that will facilitate taking 
	 * information from the database and parsing it into data that can be used by the 
	 * Game Engine
	 */
	JSONtoObject myGameLoader;

	GameChooser myChooser;
	
	Level level;

	/**
	 * Given a choice of game, calls the load existing function in the
	 * game loader which returns a list of game objects that the game
	 * engine can arrange into a playable game. This method would be called 
	 * by game player when the user decides on a game they would like to play.
	 * 
	 * @param gameName Name of the game that is being loaded
	 * @return a list of game objects to be arranged in the game engine
	 */
	List<Object> loadGameForPlay(String gameName)
	{
		return myGameLoader.loadGame(gameName);
	}

	public void useCase(){
	    myChooser.displayChoices();
	    myChooser.addChoice(new GameItem("New Game"));
	    //TODO: Add what is taking in the List<Object> returned by loadGameForPlay
	    List<Object> gameList = loadGameForPlay(myChooser.sendToGame());
		level.setObjects(gameList);
    }
}