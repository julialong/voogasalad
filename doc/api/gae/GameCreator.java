/**
 * The GameCreator interface provides the methods needed for the features necessary in creating
 * a game
 *
 * @author julialong, michaelacker, judithsanchez
 */
public interface GameCreator {

    /**
     * Saves the current state of the edited game through the appropriate class in GameData
     * @throws IOException when saving the game causes an IOException
     */
    public abstract void saveGame(String filePath) throws IOException;

    /**
     * Loads a current game to the editor to be further modified
     * @param game is the file containing the game to be loaded
     * @throws IOException when loading the game causes an IOException
     */
    public abstract void loadGame(File game) throws IOException;

    /**
     * Loads a current level to the editor to be further modified
     * @param level is the file containing the level to be loaded
     * @throws IOException when loading the game causes an IOException
     */
    public abstract void loadLevel(File level) throws IOException;

    /**
     * Adds a new element to the scene at the specified location
     * @param newElement is the element (either default or user-defined) to be added
     * @param xPosition is the x position where the element is placed
     * @param yPosition is the y position where the element is placed
     */
    public abstract void addElement(AuthoredElement newElement, double xPosition, double yPosition);

    
}