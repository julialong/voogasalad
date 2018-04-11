package game_player_api;

import data.gamefiles.JSONtoObject;
import engine.level.Level;
import javafx.scene.control.ListCell;

import java.util.List;
import java.util.Map;

/**
 * Represents each individual item within the GameChooser visual display
 * This inheritance hierarchy will extend the Node class because each
 * GameItem will represent a clickable item that the user selects to
 * move to a game.
 *
 * @Author Dorian Barber
 */
public interface GameItem {

    /**
     * This method may be automatically defined for all
     * GameItem objects, but the difference is dependent on
     * how the GameItem was created
     */
    public void actionOnClick();

    /**
     * Sets up the Game view application environment
     * with the specific game that this item represents
     */
    public void setUpGame(List<Level> gameLevels);

    /**
     * Alters toString method to return a properly formatted
     * representation of the game that can be chosen
     */
    public String toString();
}