package data.gamefiles;

import java.util.List;
import java.util.Map;

import authoring_environment.game_elements.AuthoredLevel;
import engine.entity.GameEntity;
import engine.level.Level;

public interface JSONtoGAE {
	
	/**
	 * This method will return the list of AuthoredLevel objects created from the level
	 * files for a particular game. This will locate the folder for the specific
	 * game and load the different level files associated with that folder. This will 
	 * allow the game authoring environment to do continued editing on a game.
	 * 
	 * @param gameName
	 * @return 
	 */
	List<AuthoredLevel> loadCompleteAuthoredGame(String gameName);

	/**
	 * This will load one of the stray levels that an author can
	 * add to his or her game in the game authoring environment.
	 * 
	 * @param levelName
	 * @return
	 */
	AuthoredLevel loadAuthoredLevel(String levelName);

	/**
	 * This will load the author settings for a specific author in the 
	 * game authoring environment. It will return the map of image paths 
	 * to custom game entities. 
	 * 
	 * @param author
	 * @return
	 */
	Map<String,GameEntity> loadAuthorCustomObjects(String author);

}
