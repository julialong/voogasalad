package api.player;

/**
 * Represents each individual item within the GameChooser visual display
 * This inheritance hierarchy will extend the Node class because each
 * GameItem will represent a clickable item that the user selects to
 * move to a game.
 */
public interface GameItem extends Node{

    /**
     * Constructing a GameItem with a specific dataPath which
     * holds all the information about the specific game.
     * @param dataPath the string that will connect each item to their
     *                  proper data source for relevant display information
     */
    public GameItem(String dataPath);


    /**
     * This method may be automatically defined for all
     * GameItem objects, but the difference is dependent on
     * how the GameItem was created
     */
    public void actionOnClick();
}