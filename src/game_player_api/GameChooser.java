package game_player_api;

import javafx.scene.layout.BorderPane;


/**
 * The interface for letting the user choose a developed game.
 * This will be a visual display of GameItems that the user can
 * select from.
 *
 * @Author Dorian
 */
public interface GameChooser {

    /**
     * Occurs once the user has selected the game to play. This method will close the
     * current application and run the GameView application
     */
    public String sendToGame();

    /**
     * This will represent the extensive list of developed games that the
     * user can choose. Choosing a game will prompt the sendToGame method
     */
    public BorderPane displayChoices();

    /**
     * Adds the @param gameName to the list of available games to choose.
     */
    public void addChoice(String gameName);
}
