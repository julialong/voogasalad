package api.gae;

import java.io.File;
import java.io.IOException;

/**
 * The GameCreator interface provides the methods needed for the features necessary in creating
 * a game
 *
 * @author julialong, judithsanchez
 */
public interface GameCreator {

    /**
     * Saves the current state of the edited game through the appropriate class in GameData
     */
    public abstract void saveGame();

    /**
     * Loads a current game to the editor to be further modified
     * @param game is the file containing the game to be loaded
     * @throws IOException when loading the game causes an IOException
     */
    public abstract void loadGame(File game) throws IOException;

    /**
     * Loads a current level to the editor to be further modified
     * @param level is the file containing the level to be loaded
     * @throws IOException when loading the level causes an IOException
     */
    public abstract void loadLevel(File level) throws IOException;
    
    public abstract createNewElement(String type);

}