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
     * Sets up the Game view application environment
     * with the specific game that this item represents
     * @param string 
     * @param string 
     */
    public void setUpGame(List<Level> gameLevels, String string);

    /**
     * Alters toString method to return a properly formatted
     * representation of the game that can be chosen
     */
    public String toString();

    /**
     * Returns the name of the game used to retrieve all files
     * needed for the game engine to run the game.
     */
    public String getGameName();
}