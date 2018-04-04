package game_player_api;

/**
 * Represents each individual item within the GameChooser visual display
 * This inheritance hierarchy will extend the Node class because each
 * GameItem will represent a clickable item that the user selects to
 * move to a game.
 */
public interface GameItem {

    /**
     * This method may be automatically defined for all
     * GameItem objects, but the difference is dependent on
     * how the GameItem was created
     */
    public void actionOnClick();
}